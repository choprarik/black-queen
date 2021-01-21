const express = require('express');
const app = express();
const config = require('./config');

var rooms_router = require('./routes/room');
var bid_router = require('./routes/bidding');
var partner_router = require('./routes/partner');
var cards_router = require('./routes/cards');


app.use(express.json());
app.use(config.ROOMS_API, rooms_router);
app.use(config.BIDDING_API, bid_router);
app.use(config.PARTNER_API, partner_router);
app.use(config.CARDS_API, cards_router);

// Home APIs
app.get('/', (req, res) => {
    res.send({ 'status': 'OK' });
})

app.post('/', (req, res) => {
    res.send({ 'status': 'OK' });
})

////////////////////// Ends here

//An error handling middleware
app.use(function(err, req, res) {
    if (err.name == 'BadRequestError') {
        res.status(400);
        res.send(({
            'status': 400,
            'message': err.message
        }));
    } else if (err.name == 'UnauthorizedRequestError') {
        res.status(403);
        res.send(({
            'status': 403,
            'message': err.message
        }));
    } else if (err.name == 'ResourceNotFoundError') {
        res.status(404);
        res.send(({
            'status': 404,
            'message': err.message
        }));
    } else {
        res.status(500);
        res.send(({
            'status': 500,
            'message': err.message
        }));
    }
});


app.listen(config.EXPRESS_SERVER_PORT, () => {
    console.log(`Express server listening at http://localhost:${port}`)
})