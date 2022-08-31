package com.example.g1onlinequiz

class checkingAnswer {

    companion object{
        fun verifyAnswer(question: String, userAnswer: String): Int {
            var correctAnswers: Map<String, String> = mapOf(
                "If you earn 15 points your license, the consequence is 30 days suspension. After this period of time, what will your points be reduced to?" to "Seven",
                "You are prohibited from driving between _________ if you have a G1 license." to "Midnight and 5:00 a.m.",
                "What is the maximum speed limit in cities, towns, and villages where there's no posted speed?" to "50km/h",
                "How quickly are you obligated to report an accident to the police?" to "Immediately",
                "What is indicated by a flashing blue light on a motor vehicle?" to "Snow removal equipment",

                "Before entering an intersection, what are you legally obligated to do?" to "Stop, and when it is safe to do so, proceed",
                "How close can you park to a fire hydrant?" to "3 m",
                "Where on a roadway should you be prior to making a left turn if traffic moves in both directions?" to "Immediately to the right of the centerline of the roadway",
                "At what point do you have to change to low beam headlights when your lights are required to be on?" to "Within 150 metres of an oncoming vehicle",
                "What are you prohibited from carrying in a house or boat trailer?" to "Persons",

                "How quickly should you be able to stop in any road conditions?" to "Stop within a safe distance",
                "What happens if your BAC is greater than 0.08?" to "Your license will be suspended immediately for 90 days",
                "What should you do if your vehicle breaks down on a 400-series highway?" to "Pull over, activate your emergency flashers and wait for help",
                "What are you legally obligated to do if you are on a two-way street and hear an emergency vehicle's siren?" to "Pull as far as possible to the right and stop",
                "What will happen if you are convicted of careless driving?" to "All of these",

                "If you are planning on going straight but are stopped at a red light, what should you do?" to "Stop, wait until the light changes to green and the intersection is clear before moving through it",
                "What does threshold braking mean?" to "You use all the braking force available without locking the wheels",
                "At what times must a driver accompany you in the front passenger seat if you are driving with a learner's license?" to "At all times",
                "What should you do before entering a freeway?" to "Signal and increase your speed to merge smoothly with traffic",
                "What is indicated by a red signal light with a green arrow at an intersection?" to "Proceed with caution in the direction of the arrow"

            )

            if (userAnswer.equals(correctAnswers.get(question))){
                return 1
            }

            return 0
        }
    }

}