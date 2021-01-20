const express = require('express')
const app = express()
const port = 8088
    // const https = require('https')
const http = require('http');
const errors = require('./classes/errors');
const logics = require('./logics');
const constants = require('./constants');


app.use(express.json());

const success_response = {
    'status': 'OK'
};


const contains = function(value, iterable) {
    return iterable.indexOf(value) > -1 ? true : false;
}


var rooms = {};
var user_cards = {};

const send_message_to_websocket_server = function(users, message) {

    const body = JSON.stringify({
        'users': users == undefined ? [] : users,
        'message': message
    });

    //TODO: Get the following from config file
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

const request_validity_check = function() {

    error_list = new Array();
    //Assuming that arguments[0] will have all the named parameters passed to it.
    keys = Object.keys(arguments[0]);
    for (var i = 0; i < keys.length; i++) {
        if (arguments[0][keys[i]] == undefined || String(arguments[0][keys[i]]).trim() == '') {
            error_list.push(String(keys[i]) + 'is missing.')
        }
    }
    return error_list;
}

const update_room = function(action, room_id, user_id, current_bid, next) {

    if (action == constants.CREATE_METHOD) {
        rooms[room_id] = {
            'startTime': Date.now(),
            'hostId': user_id,
            'current_bid': constants.DEFAULT_BID,
            'users': [user_id],
            'last_bid_by': user_id,
            'cards': new Array(),
            'users_point': {},
            'partners': new Array(),
            'double_partners': false,
            'non_partners': new Array(),
            'trump': undefined
        }
        rooms[room_id].users_point[user_id] = 0;
    } else if (action == constants.UPDATE_METHOD) {
        if (!is_room_full(room_id)) {
            {
                rooms[room_id].users.push(user_id);
                rooms[room_id].users_point[user_id] = 0;
            }
        } else {
            // Room is full
            next(new errors.UnauthorizedRequestError('Room already full'));
        }
    } else if (action == constants.BID_METHOD) {
        if (contains(user_id, rooms[room_id].users) && current_bid > rooms[room_id].current_bid && current_bid <= constants.MAX_BID_ALLOWED) {
            rooms[room_id].current_bid = current_bid;
            rooms[room_id].last_bid_by = user_id;
        } else {
            next(new errors.BadRequestError('Bad request for bidding. Check bid amount'));
        }
    }
}


// Home APIs
app.get('/', (req, res) => {
    res.send({ 'status': 'OK' });
})

app.post('/', (req, res) => {
    res.send({ 'status': 'OK' });
})

////////////////////// Ends here


// Room APIs
app.get('/rooms', (req, res) => {
    /**
     * API to get info about all rooms
     */
    res.status(200).send(rooms);
})


app.post('/room/join', (req, res, next) => {

    var room_id = req.body.room_id;
    var user_id = req.body.user_id;

    var error_list = request_validity_check({ 'room_id': room_id, 'user_id': user_id });
    if (error_list.length > 0) {
        next(new errors.BadRequestError(error_list));
    }

    if (rooms[room_id] == undefined) {
        update_room(constants.CREATE_METHOD, room_id, user_id, next);
    } else {
        update_room(constants.UPDATE_METHOD, room_id, user_id, next);
    }

    res.status(200).send(success_response);
})

////////////////// Ends here


// Bidding APIs
app.post('/bid', (req, res, next) => {
    var room_id = req.body.room_id;
    var user_id = req.body.user_id;
    var current_bid = req.body.current_bid;

    var error_list = request_validity_check({ 'room_id': room_id, 'user_id': user_id, 'current_bid': current_bid });
    if (error_list.length > 0) {
        next(new errors.BadRequestError(error_list));
    }
    if (typeof(current_bid) != constants.NUMBER_TYPE) {
        next(new errors.BadRequestError('Invalid bid value passed'));
    }

    update_room(constants.BID_METHOD, room_id, user_id, current_bid, next);
    send_message_to_websocket_server(rooms[room_id].users, 'updated bid: ' + current_bid); //TODO: send bid by user in ws server
    res.status(200).send(success_response);
})

app.post('/bid/end', (req, res, next) => {
    var room_id = req.body.room_id;

    var error_list = request_validity_check({ 'room_id': room_id });
    if (error_list.length > 0) {
        next(new errors.BadRequestError(error_list));
    }

    var message = {
        'current_bid': rooms[room_id].current_bid,
        'bidding_user': rooms[room_id].last_bid_by
    }

    res.status(200).send(message);
})

////////////////  Ends here



// Partners APIs

app.post('/partner', (req, res) => {
    var partner_cards = req.body.partner_cards; // Expected []
    var rooms_user_cards = {};
    var room_id = req.body.room_id;
    var trump = req.body.trump;

    var error_list = request_validity_check({ 'room_id': room_id, 'trump': trump });
    if (error_list.length > 0) {
        next(new errors.BadRequestError(error_list));
    }
    if (typeof(trump) != constants.STRING_TYPE || trump.length != 1) {
        next(new errors.BadRequestError('Invalid trump passed'))
    }

    rooms[room_id].trump = trump;

    // Filtering user's cards for respective room
    rooms[room_id].users.forEach((user) => {
        rooms_user_cards[user] = user_cards[user];
    });

    Object.entries(rooms_user_cards).forEach((element) => {
        //element[0] => user_id
        //element[1] => cards
        var is_partner = false;
        for (var i = 0; i < element[1].length; i++) {
            if (JSON.stringify(element[1][i]) == JSON.stringify(partner_cards[0]) || JSON.stringify(element[1][i]) == JSON.stringify(partner_cards[1])) {
                rooms[room_id].partners.push(element[0]);
                is_partner = true;
            }
        }
        if (!is_partner && element[0] != rooms[room_id].last_bid_by) {
            rooms[room_id].non_partners.push(element[0]);
        }
    });
    // Adding the original caller in partners list
    rooms[room_id].partners.push(String(rooms[room_id].last_bid_by));

    // Checking for double partners
    if (rooms[room_id].partners[0] == rooms[room_id].partners[1]) {
        rooms[room_id].double_partners = true;
        rooms[room_id].partners = new Array(rooms[room_id].partners[0])
    }

    res.status(200).send(success_response);

})

////////////////  Ends here


// Cards APIs
app.get('/cards', (req, res) => {
    var params = req.query;
    var room_id = params.room_id == undefined ? undefined : params.room_id;
    if (room_id == undefined || rooms[room_id] == undefined) {
        next(new errors.BadRequestError('No room id specified.'));
    }

    var cards = logics.shuffle_cards();

    for (var i = 0; i < cards.length; i++) {
        user_cards[rooms[room_id].users[i]] = cards[i];
        send_message_to_websocket_server([rooms[room_id].users[i]], String(cards[i]));
    }
    res.status(200).send(success_response);

})

// API to let everyone know the card played
app.post('/cards', (req, res) => {
    var user_id = req.body.user_id;
    var card = req.body.card;
    var room_id = req.body.room_id;

    // Remove the card from user's list
    user_cards[user_id] = user_cards[user_id].filter((user_card) => JSON.stringify(card) != JSON.stringify(user_card))

    // Add the card to the current room session
    let _ = {}
    _[user_id] = card;
    rooms[room_id].cards.push(_);

    filtered_user_list = rooms[room_id].users.filter((user) => user != user_id);

    var card_message = {
        "card_played_by": user_id,
        "card": card
    }

    send_message_to_websocket_server(filtered_user_list, card_message);

    // Check if round is over. If yes, then calculate the points and store them
    var round_over = rooms[room_id].cards.length % 6 == 0

    if (round_over) {
        round_cards = rooms[room_id].cards.slice(Math.max(rooms[room_id].cards.length - 6, 0))
        round_result = logics.determine_hand(rooms[room_id].trump, round_cards);

        let winner_id = round_result.winner;
        let points = round_result.points;

        // Add points to the winner's id
        rooms[room_id].user_points[winner_id] = Object.values(rooms[room_id].user_points[winner_id]) + points

        var round_over_message = {
            "card_played_by": user_id,
            "card": card,
            "round_over": round_over
        }

        send_message_to_websocket_server(filtered_user_list, round_over_message);

    }

    // Check if the game is over. If yes, determine the winning party and send the message
    var game_over = rooms[room_id].cards.length == 48;
    var winners = new Array();
    if (game_over) {
        total_points = 0;
        rooms[room_id].partners.forEach((partner_id) => {
            total_points += rooms[room_id].user_points[partner_id]
        })

        if (total_points > rooms[room_id].current_bid) {
            winners.push(...rooms[room_id].partners);
        } else {
            winners.push(...rooms[room_id].non_partners);
        }

        var game_over_message = {
            "game_over": true,
            "winners": winners
        }

        send_message_to_websocket_server(rooms[room_id].users, game_over_message);
    }

    res.sendStatus(200).send(success_response);

});

////////////////// Ends here


//An error handling middleware
app.use(function(err, req, res, next) {
    if (err.name == 'BadRequestError') {
        res.status(400);
        res.send(json({
            'status': 400,
            'message': err.message
        }));
    } else if (err.name == 'UnauthorizedRequestError') {
        res.status(403);
        res.send(json({
            'status': 403,
            'message': err.message
        }));
    } else if (err.name == 'ResourceNotFoundError') {
        res.status(404);
        res.send(json({
            'status': 404,
            'message': err.message
        }));
    } else {
        res.status(500);
        res.send(json({
            'status': 500,
            'message': err.message
        }));
    }
});


app.listen(port, () => {
    console.log(`Express server listening at http://localhost:${port}`)
})