data class Fish(val daysToGo: Int, val count: Long = 1) {
    fun tick() = if (daysToGo != 0) listOf(Fish(daysToGo - 1, count)) else listOf(Fish(6, count), Fish(8, count))
}

private fun loadInitial() = loadStrings("dec06.txt").first().split(",").map(String::toInt).map { Fish(it) }

private fun calculateNumber(countOfDays: Int, initial: List<Fish>) = (1..countOfDays).fold(initial) { res, _ ->
    res.flatMap { it.tick() }.groupBy { it.daysToGo }.map { (timer, list) -> Fish(timer, list.sumOf { it.count }) }
}.sumOf { it.count }

fun dec06part1() = calculateNumber(80, loadInitial())
fun dec06part2() = calculateNumber(256, loadInitial())
