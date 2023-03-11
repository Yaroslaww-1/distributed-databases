package lab1

import java.sql.Connection

class Counter2(private val connection: Connection) : Runnable {
    override fun run() {
        for (i in 1..100) {
            connection.prepareStatement("update user_counter set counter = counter + 1 where user_id = 1").execute()
        }
    }
}