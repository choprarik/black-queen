var express = require('express');
var router = express.Router();
var rooms = require('../commons').rooms;
var constants = require('../constants');
const success_response = require('../commons').success_response;
var utils = require('../utils');

router.post('/', (req, res, next) => {
    var room_id = req.body.room_id;
    var user_id = req.body.user_id;
    var current_bid = req.body.current_bid;

    if (!utils.request_validity_check({ 'room_id': room_id, 'user_id': user_id, 'current_bid': current_bid }, next)) return;


    utils.update_room(constants.BID_METHOD, room_id, user_id, current_bid, next);
    utils.send_message_to_websocket_server(rooms[room_id].users, 'updated bid: ' + current_bid); //TODO: send bid by user in ws server
    res.status(200);
    res.send(success_response);
});

router.post('/bid/end', (req, res, next) => {
    var room_id = req.body.room_id;

    if (!utils.request_validity_check({ 'room_id': room_id }, next)) return;

    var message = {
        'current_bid': rooms[room_id].current_bid,
        'bidding_user': rooms[room_id].last_bid_by
    }

    res.status(200);
    res.send(message);
});

module.exports = router;