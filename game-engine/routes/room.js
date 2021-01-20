var express = require('express');
var router = express.Router();
var rooms = require('../commons').rooms;
var constants = require('../constants');
const success_response = require('../commons').success_response;
var utils = require('../utils');

/**
 * API to get info about all rooms.
 */
router.get('/', (req, res) => {
    res.status(200);
    res.send(rooms);
});


/**
 * API to add a room.
 */
router.post('/', (req, res, next) => {
    var user_id = req.body.user_id;
    if (!utils.request_validity_check({ 'user_id': user_id }, next)) return;

    var room_id = 1;
    utils.update_room(constants.CREATE_METHOD, room_id, user_id, -1, next);

    res.status(200);
    res.send(success_response);
})


/**
 * API to join a given room.
 */
router.post('/join', (req, res, next) => {

    var room_id = req.body.room_id;
    var user_id = req.body.user_id;

    if (!utils.request_validity_check({ 'room_id': room_id, 'user_id': user_id }, next)) return;


    utils.update_room(constants.UPDATE_METHOD, room_id, user_id, -1, next);

    res.status(200);
    res.send(success_response);
});


module.exports = router;