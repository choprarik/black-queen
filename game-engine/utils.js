var rooms = require('./commons').rooms;
var constants = require('./constants');
var errors = require('./classes/errors');
const http = require('http');


const contains = function(value, iterable) {
    return iterable.indexOf(value) > -1 ? true : false;
}

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

    // arguments[0] ==> Has all the named parameters
    // arguments[1] ==> next
    next = arguments[1];
    error_list = new Array();

    //Assuming that arguments[0] will have all the named parameters passed to it.
    keys = Object.keys(arguments[0]);
    for (var i = 0; i < keys.length; i++) {
        let param_key = keys[i];
        let param_value = arguments[0][keys[i]]
        if (param_value == undefined || String(param_value).trim() == '') {
            error_list.push(String(param_key).toUpperCase() + ' is missing.')
        }
    }
    if (error_list.length > 0) {
        next(new errors.BadRequestError(error_list));
        return false;
    }

    for (var i = 0; i < keys.length; i++) {
        let param_key = keys[i];
        let param_value = arguments[0][keys[i]]
        if (param_key == 'room_id') {
            return check_room_status(param_value, next);
        } else if (param_key == 'trump') {
            return check_trump(param_value, next);
        } else if (param_key == 'current_bid') {
            return check_current_bid_value(param_value, next);
        }
    }
    return true;

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
        if (contains(user_id, rooms[room_id].users)) return;
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

const check_trump = function(trump, next) {
    if (typeof(trump) != constants.STRING_TYPE || trump.length != 1) {
        next(new errors.BadRequestError('Invalid trump passed'));
        return false;
    }
    if (!contains(trump, new Array('h', 's', 'c', 'd'))) {
        next(new errors.BadRequestError('Invalid trump passed'));
        return false;
    }
    return true;
}

const check_room_status = function(room_id, next) {
    if (rooms[room_id] == undefined) {
        next(new errors.ResourceNotFoundError('Specified room does not exist.'));
        return false;
    }
    return true;
}

const check_current_bid_value = function(current_bid, next) {
    if (typeof(current_bid) != constants.NUMBER_TYPE) {
        next(new errors.BadRequestError('Invalid bid value passed'));
        return false;
    }
    return true;
}


module.exports = { contains, send_message_to_websocket_server, is_room_full, request_validity_check, update_room }