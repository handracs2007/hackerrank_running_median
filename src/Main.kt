import java.util.*

// https://www.hackerrank.com/challenges/find-the-running-median/problem

fun runningMedian(a: Array<Int>): Array<Double> {
    var median = 0.0
    val result = mutableListOf<Double>()
    val lowerQueue = PriorityQueue<Int>(kotlin.Comparator { o1, o2 ->
        return@Comparator o2 - o1
    })
    val higherQueue = PriorityQueue<Int>()

    a.forEach {
        if (it.toDouble() >= median)
            higherQueue.add(it)
        else
            lowerQueue.add(it)

        if (higherQueue.size - lowerQueue.size > 1)
            lowerQueue.add(higherQueue.poll())
        else if (lowerQueue.size - higherQueue.size > 1)
            higherQueue.add(lowerQueue.poll())

        median = when {
            higherQueue.size == lowerQueue.size -> (higherQueue.peek() + lowerQueue.peek()).toDouble() / 2.0
            higherQueue.size > lowerQueue.size -> higherQueue.peek().toDouble()
            else -> lowerQueue.peek().toDouble()
        }

        result.add(median)
    }

    return result.toTypedArray()
}

fun main() {
    println(runningMedian(intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toTypedArray()).toList())
}