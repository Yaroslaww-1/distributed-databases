package lab2

import com.hazelcast.map.IMap

class Counter1(private val map: IMap<String, Int>) : Runnable {
    override fun run() {
        for (i in 1..100) {
            val counter = map["1"] ?: 0
            map["1"] = counter + 1
        }
    }
}