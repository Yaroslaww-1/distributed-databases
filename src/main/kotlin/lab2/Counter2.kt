package lab2

import com.hazelcast.map.IMap

class Counter2(private val map: IMap<String, Int>) : Runnable {
    override fun run() {
        for (i in 1..100) {
            while (true) {
                val counter = map["1"] ?: 0
                if (map.replace("1", counter, counter + 1)) break
            }
        }
    }
}