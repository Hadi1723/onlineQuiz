const express = require('express');

let quizzes = [{
        "Name": "Quiz 1",
        "Questions": [{
            "question": "If you earn 15 points your license, the consequence is 30 days suspension. After this period of time, what will your points be reduced to?",
            "option1": "Three",
            "option2": "Two",
            "option3": "Five",
            "option4": "Seven"
        }, {
            "question": "You are prohibited from driving between _________ if you have a G1 license.",
            "option1": "Midnight and 5:00 a.m.",
            "option2": "5:00 a.m. and 9:00 a.m.",
            "option3": "9:00 a.m. and 5:00 p.m.",
            "option4": "5:00 p.m. and midnight."
        }, {
            "question": "What is the maximum speed limit in cities, towns, and villages where there's no posted speed?",
            "option1": "50km/h",
            "option2": "40km/h",
            "option3": "60km/h",
            "option4": "80km/h"
        }, {
            "question": "How quickly are you obligated to report an accident to the police?",
            "option1": "Within 72 hours",
            "option2": "Within 48 hours",
            "option3": "Within 24 hours",
            "option4": "Immediately"
        }, {
            "question": "What is indicated by a flashing blue light on a motor vehicle?",
            "option1": "Snow removal equipment",
            "option2": "An ambulance",
            "option3": "A motor vehicle carrying explosives",
            "option4": "A police emergency vehicle"
        }]
    },
    {
        "Name": "Quiz 2",
        "Questions": [{
            "question": "Before entering an intersection, what are you legally obligated to do?",
            "option1": "Stop, sound horn, then proceed",
            "option2": "Stop, and when it is safe to do so, proceed",
            "option3": "Slow down and if way is clear, proceed",
            "option4": "Slow down, sound horn and proceed"
        }, {
            "question": "How close can you park to a fire hydrant?",
            "option1": "3 m",
            "option2": "6 m",
            "option3": "4.5 m",
            "option4": "1.5 m"
        }, {
            "question": "Where on a roadway should you be prior to making a left turn if traffic moves in both directions?",
            "option1": "It does not matter provided you signal",
            "option2": "Close to the right-hand side of the roadway",
            "option3": "Close to the left side of the roadway",
            "option4": "Immediately to the right of the centerline of the roadway"
        }, {
            "question": "At what point do you have to change to low beam headlights when your lights are required to be on?",
            "option1": "Within 300m of an oncoming vehicle",
            "option2": "There is no set requirement",
            "option3": "Within 150 metres of an oncoming vehicle",
            "option4": "Within 1 KM of an oncoming vehicle"
        }, {
            "question": "What are you prohibited from carrying in a house or boat trailer?",
            "option1": "Persons",
            "option2": "Pets",
            "option3": "Flammable materials",
            "option4": "Firearms"
        }]
    },
    {
        "Name": "Quiz 3",
        "Questions": [{
            "question": "How quickly should you be able to stop in any road conditions?",
            "option1": "Stop within 150 m",
            "option2": "Stop within 60 m",
            "option3": "Stop within a safe distance",
            "option4": "Slow down, sound horn and proceed"
        }, {
            "question": "What happens if your BAC is greater than 0.08?",
            "option1": "Your license will be suspended immediately for 10 days",
            "option2": "Your license will be suspended immediately for 40 days",
            "option3": "Your license will be suspended immediately for 90 days",
            "option4": " Your license will be suspended immediately for 30 days"
        }, {
            "question": "What should you do if your vehicle breaks down on a 400-series highway?",
            "option1": "Blow your horn for help",
            "option2": "Get out and try to wave highway traffic around your vehicle",
            "option3": "Get out and walk to get help",
            "option4": "Pull over, activate your emergency flashers and wait for help"
        }, {
            "question": "What are you legally obligated to do if you are on a two-way street and hear an emergency vehicle's siren?",
            "option1": "Continue at same speed",
            "option2": "Pull as far as possible to the right and stop",
            "option3": "Speed up and get out of the way",
            "option4": "Signal the driver to pass"
        }, {
            "question": "What will happen if you are convicted of careless driving?",
            "option1": "All of these",
            "option2": "Your licence may be suspended for up to two years",
            "option3": " Six demerit points materials",
            "option4": "Can be fined up to $2,000 and sentenced to up to six months in jail"
        }]
    },

    {
        "Name": "Quiz 4",
        "Questions": [{
            "question": "If you are planning on going straight but are stopped at a red light, what should you do?",
            "option1": "Stop, wait until the light changes to green and the intersection is clear before moving through it",
            "option2": "Stop, proceed when the way is clear",
            "option3": "Slow down, proceed when the way is clear",
            "option4": "Stop, give pedestrians the right-of-way, and then proceed with caution"
        }, {
            "question": "What does threshold braking mean?",
            "option1": "You pump the brakes several times",
            "option2": "You use all the braking force available without locking the wheels",
            "option3": "You are braking dangerously",
            "option4": "You brake hard and stay on the brakes until the vehicle stops"
        }, {
            "question": "At what times must a driver accompany you in the front passenger seat if you are driving with a learner's license?",
            "option1": "On high speed highways",
            "option2": "At peak traffic times",
            "option3": "During the hours of darkness",
            "option4": "At all times"
        }, {
            "question": "What should you do before entering a freeway?",
            "option1": "Stop on the acceleration lane, wait for an opening, and then enter the freeway rapidly",
            "option2": "Signal and increase your speed to merge smoothly with traffic",
            "option3": "Slow down, and then enter freeway at a sharp angle",
            "option4": "Drive slowly and be prepared to stop for freeway traffic"
        }, {
            "question": "What is indicated by a red signal light with a green arrow at an intersection?",
            "option1": "Stop and wait for a green signal before making a turn in the direction of the arrow",
            "option2": "Proceed with caution in the direction of the arrow",
            "option3": "That pedestrians may cross",
            "option4": "Stop and then proceed"
        }]
    }


]


quizRouter = express.Router();

// Get all expressions
quizRouter.get('/', (req, res, next) => {
    res.send(quizzes);
});

// Get a single expression
quizRouter.get('/:Name', (req, res, next) => {

    for (var i = 0; i < quizzes.length; i++) {
        if (quizzes[i].Name == req.params.Name) {
            //res.end();
            //res.send(JSON.stringify(quizzes[i]));
            res.send(quizzes[i].Questions);
        }
    }

    res.status(404).send();

});

/*
// Update an expression
quizRouter.put('/:question', (req, res, next) => {

    var quiz;
    for (var i = 0; i < destinations.length; i++) {
        if (quizzes[i].id == req.params.id) {
            quizzes[i].city = req.body.city;
            quizzes[i].country = req.body.country;
            quizzes[i].description = req.body.description;
            quiz = destinations[i];

            res.send(quiz);
            //res.send(JSON.stringify(quiz));
        }
    }

    res.status(404).send();

}); 

// Create an expression
quizRouter.post('/', (req, res, next) => {

    var newQuiz = {
        "question": quizzes.length + 1,
        "option1": req.body.option1,
        "option2": req.body.option2,
        "option3": req.body.option3,
        "option4": req.body.option4,
        "option5": req.body.option5,
    }


    quizzes.push(newQuiz);
    //res.status(201).end();
    //res.status(201).end(JSON.stringify(newQuiz));
    res.status(201).end(newQuiz);

}); 

// Delete an expression
quizRouter.delete('/:question', (req, res, next) => {

    for (var i = 0; i < quizzes.length; i++) {
        if (quizzes[i].id == req.params.id) {
            quizzes.splice(i, 1);
            res.status(204).send();
        }
    }

    res.status(404).send();
}); */

module.exports.quizRouter = quizRouter;