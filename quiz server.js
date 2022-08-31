const express = require('express');
const app = express();


const PORT = process.env.PORT || 4001;

// Import and mount the expressionsRouter
const quizRouter = require('./quiz router.js');
app.use('/quiz', quizRouter.quizRouter);

// Import and mount the expressionsRouter
const userRouter = require('./user router.js');
app.use('/users', userRouter.userRouter);


app.use((req, res, next) => {
    res.send("Hello, World!!!")
})

app.listen(PORT, function(req, res) {

    console.log(`Server running at http://127.0.0.1:${PORT}/`);
})