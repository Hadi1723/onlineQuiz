let express = require('express')

var bodyParser = require('body-parser')


userRouter = express.Router()

userRouter.use(bodyParser.json());
userRouter.use(bodyParser.urlencoded({
    extended: true
}));

/*
let userResults = [{
    "name": "Hadi",
    "results": [{
        Score: 100,
        quizName: "Test 1"
    }]
}] */

let userResults = [{
    "name": "Hadi",
    "results": []
}]

userRouter.post('/', (req, res, next) => {
    let newUser = {
        "name": req.body.name,
        "results": req.body.results
    }

    userResults.push(newUser)
    res.send(newUser)
})

// Get all expressions
userRouter.get('/', (req, res, next) => {
    res.send(userResults);
});

// Get a single expression
userRouter.get('/:name', (req, res, next) => {

    for (var i = 0; i < userResults.length; i++) {
        if (userResults[i].name == req.params.name) {
            //res.end();
            //res.send(JSON.stringify(quizzes[i]));
            res.send(userResults[i].results);
        }
    }

    res.status(404).send();

});

// Create an expression
userRouter.put('/:name', (req, res, next) => {

    for (var i = 0; i < userResults.length; i++) {
        if (userResults[i].name == req.params.name) {

            /*userResults[i].results = [{
                Score: 10,
                quizName: "req.body.quizName"
            }] */

            let alrSentRes = false

            for (var j = 0; j < userResults[i].results.length; j++) {
                if (userResults[i].results[j].quizName == req.body.quizName) {
                    userResults[i].results[j] = { Score: req.body.Score, quizName: req.body.quizName, lastAttempt: req.body.lastAttempt }
                    res.status(201).send(userResults[i].results[j])
                    alrSentRes = true
                    break
                }
            }

            if (!alrSentRes) {
                userResults[i].results.push({
                    Score: req.body.Score,
                    quizName: req.body.quizName,
                    lastAttempt: req.body.lastAttempt
                })

                res.status(201).send(userResults[i].results[userResults[i].results.length - 1]);
            }

        }
    }

    res.status(404).send();

});

// Delete a Destination 
userRouter.delete('/:name', function(req, res) {
    for (var i = 0; i < userResults.length; i++) {
        if (userResults[i].name == req.params.name) {
            userResults.splice(i, 1);
            res.status(204).end();
            //res.status(204).end(JSON.stringify(destinations[i]));
        }
    }
});

module.exports.userRouter = userRouter;