package lab2

import com.hazelcast.cp.IAtomicLong

class Counter4(private val counter: IAtomicLong) : Runnable {
    override fun run() {
        for (i in 1..100) {
            counter.incrementAndGet()
        }
    }
}