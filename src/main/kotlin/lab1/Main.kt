import lab1.Counter1
import lab1.Counter2
import lab1.Counter3
import lab1.Counter4
import java.sql.Connection
import java.sql.DriverManager
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    getNewConnection().prepareStatement("""
        CREATE TABLE IF NOT EXISTS user_counter (
            user_id SERIAL PRIMARY KEY,
            counter INTEGER,
            version INTEGER
        );
        TRUNCATE TABLE user_counter; 
        INSERT INTO user_counter VALUES (1, 0, 0);
    """.trimIndent()).execute()

    val threads = mutableListOf<Thread>()
    for (t in 1..10) {
        threads.add(Thread(Counter4(getNewConnection())))
    }

    val time = measureTimeMillis {
        threads.forEach { t -> t.start() }
        threads.forEach { t -> t.join() }
    }

    val result = getNewConnection().prepareStatement("SELECT counter FROM user_counter WHERE user_id = 1").executeQuery()
    result.next()
    val counter = result.getInt("counter")
    println("Final counter is $counter, time spent is $time ms")
}

fun getNewConnection(): Connection {
    return DriverManager.getConnection("jdbc:postgresql://localhost:7433/lab1", "postgres", "postgres")
}