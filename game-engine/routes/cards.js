var express = require('express');
var router = express.Router();
var rooms = require('../commons').rooms;
var user_cards = require('../commons').user_cards;
const success_response = require('../commons').success_response;
const utils = require('../utils');
const logics = require('../logics');
const errors = require('../classes/errors');

/**
 * API to distribute cards to all players for the specified room id.
 */
router.get('/', (req, res, next) => {
    var params = req.query;
    var room_id = params.room_id == undefined ? undefined : params.room_id;
    if (room_id == undefined || rooms[room_id] == undefined) {
        next(new errors.BadRequestError('No room id specified.'));
    }

    if (!utils.request_validity_check({ 'room_id': room_id }, next)) return;

    var cards = logics.shuffle_cards();

    for (var i = 0; i < cards.length; i++) {
        user_cards[rooms[room_id].users[i]] = cards[i];
        utils.send_message_to_websocket_server([rooms[room_id].users[i]], String(cards[i]));
    }
    res.status(200);
    res.send(success_response);

})

// API to let everyone know the card played
router.post('/', (req, res, next) => {
    var user_id = req.body.user_id;
    var card = req.body.card;
    var room_id = req.body.room_id;

    if (!utils.request_validity_check({ 'room_id': room_id }, next)) return;

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

    utils.send_message_to_websocket_server(filtered_user_list, card_message);

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

        utils.send_message_to_websocket_server(rooms[room_id].users, game_over_message);
    }

    res.status(200);
    res.send(success_response);

});

module.exports = router;