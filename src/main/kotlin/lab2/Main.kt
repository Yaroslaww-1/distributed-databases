package lab2

import com.hazelcast.client.HazelcastClient
import com.hazelcast.client.config.ClientConfig
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val config = ClientConfig()
    config.networkConfig.addAddress("0.0.0.0:5701")
    config.clusterName = "ucu"
    val hz = HazelcastClient.newHazelcastClient(config)

    val map = hz.getMap<String, Int>("counter")
    map["1"] = 0

    val counter = hz.getCPSubsystem().getAtomicLong( "counter" )
    counter.set(0)

    val threads = mutableListOf<Thread>()
    for (t in 1..10) {
//        threads.add(Thread(Counter3(map)))
        threads.add(Thread(Counter4(counter)))
    }

    val time = measureTimeMillis {
        threads.forEach { t -> t.start() }
        threads.forEach { t -> t.join() }
    }

//    val counter = map["1"]
    println("Final counter is ${counter.get()}, time spent is $time ms")
}
