const express = require('express')
const app = express()
const port = 8088
    // const https = require('https')
const http = require('http');
const logics = require('./logics');

app.use(express.json());

const success_response = {
    'status': 'OK'
};

const error_response = {
    'status': 'KO'
}

const contains = function(value, iterable) {
    return iterable.indexOf(value) > -1 ? true : false;
}


var rooms = {};

const send_message_to_websocket_server = function(users, message) {

    const body = JSON.stringify({
        'users': users == undefined ? [] : users,
        'message': message
    });

    const options = {
        hostname: 'localhost',
        port: 8089,
        path: '/',
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Content-Length': body.length
        }
    };

    const re = http.request(options, res => {
        console.log(`statusCode: ${res.statsCode}`);
    })


    re.on('error', error => {
        console.error(error)
    });

    re.write(body);
    re.end();
}

const is_room_full = function(room_id) {
    return rooms[room_id].users.length == 6;
}

const update_room = function(action, room_id, user_id, current_bid) {

    if (action == 'create') {
        rooms[room_id] = {
            'startTime': Date.now(),
            'hostId': user_id,
            'people': 1,
            'current_bid': 170, //default bidding,
            'users': [user_id],
            'last_bid_by': user_id,
            'cards': new Array(),
            'users_point': { user_id: 0 }
        }
        return true;
    } else if (action == 'update') {
        if (!is_room_full(room_id)) {
            if (user_id in rooms[room_id].users) {
                return false;
            } else {
                rooms[room_id].users.push(user_id);
                rooms[room_id].users_point[user_id] = 0;
                return true;
            }
        } else {
            return false;
        }
    } else if (action == 'bid') {
        if (contains(user_id, rooms[room_id].users) && current_bid > rooms[room_id].current_bid && current_bid <= 270) {
            rooms[room_id].current_bid = current_bid;
            rooms[room_id].last_bid_by = user_id;
            return true;
        }
        return false;
    }
}


// Home APIs
app.get('/', (req, res) => {
    res.send({ 'status': 'OK' });
})

app.post('/', (req, res) => {
    console.dir(req.body);
    console.log(req.body.tesT);
    res.send({ 'this': 'is' }).status(200);
})

////////////////////// Ends here


// Room APIs
app.get('/rooms', (req, res) => {
    res.send(rooms).status(200);
})


app.post('/room/join', (req, res) => {

    room_id = req.body.room_id;
    user_id = req.body.user_id;
    var result;

    if (rooms[room_id] == undefined) {
        result = update_room('create', room_id, user_id)
    } else {
        result = update_room('update', room_id, user_id)
    }

    if (result) {
        // Marks success in operations
        res.send(success_response).status(200);
        send_message_to_websocket_server(users = [user_id], message = 'welcome to room')
    } else {
        send_message_to_websocket_server(users = [user_id], message = 'unable to join room')
        res.send(error_response).status(500);
    }
})

////////////////// Ends here


// Bidding APIs
app.post('/bid', (req, res) => {
    var room_id = req.body.room_id;
    var user_id = req.body.user_id;
    var current_bid = req.body.current_bid;
    if (update_room('bid', room_id, user_id, current_bid)) {
        res.send(success_response).status(200);
        send_message_to_websocket_server(rooms[room_id].users, 'updated bid: ' + current_bid);
    } else {
        res.send(error_response).status(500);
    }
})

////////////////  Ends here


user_cards = {};
user_points = {};

// Cards APIs
app.get('/cards', (req, res) => {
    var params = req.query;
    var room_id = params.room_id == undefined ? undefined : params.room_id;
    if (room_id == undefined || rooms[room_id] == undefined) {
        // res.send(error_response).status(500);
        res.sendStatus(500).send(error_response);
    }

    var cards = logics.shuffle_cards();

    for (var i = 0; i < cards.length; i++) {
        user_cards[rooms[room_id].users[i]] = cards[i];
        send_message_to_websocket_server(rooms[room_id].users[i], cards[i]);
    }
    res.sendStatus(200).send(success_response);

})

// API to let everyone know the card played
app.post('/cards', (req, res) => {
    var user_id = req.body.user_id;
    var card = req.body.card;
    var room_id = req.body.room_id;

    // Remove the card from user's list
    Object.values(user_cards[user_id]).splice(Object.values(user_cards[user_id]).indexOf(card), 1);

    // Add the card to the current room session
    rooms[room_id].cards.push({ user_id: card });

    filtered_user_list = rooms[room_id].users.filter((user) => {
        user != user_id
    });

    // Check if round is over. If yes, then calculate the points and store them
    var round_over = room_id[rooms].cards.length % 6 == 0

    if (round_over) {
        round_cards = rooms[room_id].cards.slice(Math.max(cards.length - 6, 0))
        round_result = logics.determine_hand(rooms[room_id].trump, round_cards);

        let winner_id = round_result.winner;
        let points = round_result.points;

        rooms[room_id].user_points[winner_id] = Object.values(rooms[room_id].user_points[winner_id]) + points
    }

    // Check if the game is over. If yes, determine the winning party and send the message
    var game_over = room_id[rooms].cards.length == 48;

    var round_over_message = {
        "card_played_by": user_id,
        "card": card,
        "round_over": round_over
    }

    var game_over_message = {
        "game_over": true,
        "winners": [] // TODO: Write logic to get winning players id
    }

    if (!game_over) {
        send_message_to_websocket_server(filtered_user_list, round_over_message);
    } else {
        send_message_to_websocket_server(rooms[room_id].users, game_over_message);
    }


    res.sendStatus(200).send(success_response);

})

app.listen(port, () => {
    console.log(`Example app listening at http://localhost:${port}`)
})