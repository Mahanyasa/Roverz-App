package com.projectyatri.newsappv32.utils

import com.projectyatri.newsappv32.utils.Constants.OPEN_GOOGLE
import com.projectyatri.newsappv32.utils.Constants.OPEN_SEARCH
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

object BotResponse {

    fun basicResponses(_message: String): String {

        val random = (0..2).random()
        val message = _message.lowercase(Locale.getDefault())

        return when {

            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }

            //Math calculations
            message.contains("calculate") -> {
                val equation: String = message.substringAfterLast("calculate")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }

            //Hello
            message.contains("hello") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }
            //hi
            message.contains("Hi") -> {
                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }

            //How are you?
            message.contains("how are you") -> {
                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            //Support
            message.contains("support") -> {
                when (random) {
                    0 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    1 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    2 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    else -> "error"
                }
            }

            message.contains("query") -> {
                when (random) {
                    0 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    1 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    2 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    else -> "error"
                }
            }

            message.contains("question") -> {
                when (random) {
                    0 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    1 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    2 -> "Please contant us via email: Projectyatri@gmail.com if you have any problem with the content"
                    else -> "error"
                }
            }

            //What time is it?
            message.contains("time")-> {
                val timeStamp = Timestamp(System.currentTimeMillis())
                val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm")
                val date = sdf.format(Date(timeStamp.time))

                date.toString()
            }

            //Open Google
            message.contains("open") && message.contains("google")-> {
                OPEN_GOOGLE
            }

            //Search on the internet
            message.contains("search")-> {
                OPEN_SEARCH
            }

            //When the programme doesn't understand...
            else -> {
                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}