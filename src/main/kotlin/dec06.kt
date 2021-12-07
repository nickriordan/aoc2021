data class Fish(val days: Int, val count: Long = 1) {
    fun tick() = if (days != 0) listOf(Fish(days - 1, count)) else listOf(Fish(6, count), Fish(8, count))
}

private fun calculateNumber(countOfDays: Int, initial: List<Fish>) = (1..countOfDays).fold(initial) { res, _ ->
    res.flatMap { it.tick() }.groupBy { it.days }.map { (days, list) -> Fish(days, list.sumOf { it.count }) }
}.sumOf { it.count }

fun dec06part1() = calculateNumber(80, loadSingleLineInts("dec06.txt").map { Fish(it) })
fun dec06part2() = calculateNumber(256, loadSingleLineInts("dec06.txt").map { Fish(it) })
