var express = require('express');
var router = express.Router();
var rooms = require('../commons').rooms;
var user_cards = require('../commons').user_cards;
const utils = require('../utils');
const success_response = require('../commons').success_response;
const errors = require('../classes/errors');


/**
 * API to register partners of the bid winning user.
 */
router.post('/', (req, res, next) => {
    var partner_cards = req.body.partner_cards; // Expected []
    var rooms_user_cards = {};
    var room_id = req.body.room_id;
    var trump = req.body.trump;

    if (!utils.request_validity_check({ 'room_id': room_id, 'trump': trump }, next)) return;

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

    res.status(200);
    res.send(success_response);

})

module.exports = router;