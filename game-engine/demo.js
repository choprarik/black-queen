var express = require('express');
var app = express();
var errors = require('./classes/errors');
var constants = require('./constants');

class response_obj {
    constructor(message) {
        this.message = message
    }
}

app.get('/', function(req, res, next) {
    //Create an error and pass it to the next function
    // var err = new Error("Something went wrong");
    // var err = new errors.BadRequestError('test');
    // next(err);
    // console.log(constants.CREATE_METHOD);

    // res.send(JSON.stringify(new response_obj('test')));
});

app.get('/test', function(req, res, next) {
    console.log('testing')
    res.send('ok');
});


/*
 * other route handlers and middleware here
 * ....
 */
//An error handling middleware
app.use(function(err, req, res, next) {
    res.status(500);
    console.log(err);
    res.send(err.message);
});

app.listen(9009);