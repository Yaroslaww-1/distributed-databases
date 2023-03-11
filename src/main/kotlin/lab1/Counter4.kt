package lab1

import java.sql.Connection

class Counter4(private val connection: Connection) : Runnable {
    override fun run() {
        for (i in 1..100) {
            while (true) {
                val result = connection.prepareStatement("SELECT counter, version FROM user_counter WHERE user_id = 1").executeQuery()
                result.next()
                val counter = result.getInt("counter")
                val version = result.getInt("version")

                val updated = connection.prepareStatement("""
                    update user_counter
                    set counter = ${counter + 1}, version = ${version + 1}
                    where user_id = 1 and version = $version
                """.trimIndent()).executeUpdate()

                if (updated > 0) break
            }
        }
    }
}