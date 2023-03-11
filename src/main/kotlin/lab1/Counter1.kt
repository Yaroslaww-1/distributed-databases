package lab1

import java.sql.Connection

class Counter1(private val connection: Connection) : Runnable {
    override fun run() {
        for (i in 1..100) {
            val result = connection.prepareStatement("SELECT counter FROM user_counter WHERE user_id = 1").executeQuery()
            result.next()
            val counter = result.getInt("counter")
            connection.prepareStatement("update user_counter set counter = ${counter + 1} where user_id = 1").execute()
        }
    }
}