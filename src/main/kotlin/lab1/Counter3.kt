package lab1

import java.sql.Connection

class Counter3(private val connection: Connection) : Runnable {
    override fun run() {
        for (i in 1..100) {
            connection.autoCommit = false
            val result = connection.prepareStatement("SELECT counter FROM user_counter WHERE user_id = 1 FOR UPDATE").executeQuery()
            result.next()
            var counter = result.getInt("counter")
            counter += 1
            connection.prepareStatement("update user_counter set counter = $counter where user_id = 1").execute()
            connection.commit()
        }
    }
}