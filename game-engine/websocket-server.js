const config = require('./config');

var WebSocketServer = require('ws').Server,
    wss = new WebSocketServer({ port: config.WEBSOCKET_PORT }),
    users_conn_mappings = {};


wss.broadcast = function broadcast(data) {
    wss.clients.forEach(function each(client) {
        client.send(data);
    });
};


wss.on('connection', function(ws) {
    let length = Object.keys(users_conn_mappings).length;
    console.log('Client connected. Count = ' + length);
    ws.on('message', function(message) {
        var parsed = JSON.parse(message)
        users_conn_mappings[parsed.userName] = ws
        ws.send('Welcome user: ' + length);
    })
})

console.log('WebSocketServer started');


const express = require('express');
const app = express();

app.use(express.json());

app.post('/', (req, res) => {
    console.log(req.body);
    // console.dir(users_conn_mappings);
    let users = req.body.users == undefined ? [] : req.body.users;
    let msg = req.body.message == undefined ? '' : req.body.message;
    users.forEach((user) => {
        if (users_conn_mappings != {}) {
            if (users_conn_mappings[user] != undefined) {
                users_conn_mappings[user].send(msg);
            }
        }
    })

    res.status(200);
    res.send({ 'status': 'Received' });
})

app.listen(config.WEBSOCKET_SERVER_PORT, () => {
    console.log(`Websocket app listening at http://localhost:${port}`)
})