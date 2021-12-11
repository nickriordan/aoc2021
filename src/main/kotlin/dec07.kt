import kotlin.math.absoluteValue

fun dec07part1() = loadSingleLineInts("dec07.txt").let { data ->
    (data.minOf { it }..data.maxOf { it }).map { target -> data.sumOf { (it - target).absoluteValue } }.minOf { it }
}

fun dec07part2() = loadSingleLineInts("dec07.txt").let { data ->
    val range = data.minOf { it }..data.maxOf { it }
    val fuelCost = (1..range.magnitude()).fold(listOf(0)) { acc, i -> acc + (acc.last() + i) }
    range.map { target -> data.sumOf { fuelCost[(it - target).absoluteValue] } }.minOf { it }
}
