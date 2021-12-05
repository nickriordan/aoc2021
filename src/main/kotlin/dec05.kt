data class Point(val x: Int, val y: Int)

class Line(orig: Point, dest: Point) {
    private val xRange = if (orig.x < dest.x) (orig.x..dest.x) else (orig.x downTo dest.x)
    private val yRange = if (orig.y < dest.y) (orig.y..dest.y) else (orig.y downTo dest.y)

    fun isVert() = xRange.first == xRange.last
    fun isHorz() = yRange.first == yRange.last

    fun points() = when {
        isVert() -> yRange.map { Point(xRange.first, it) }
        isHorz() -> xRange.map { Point(it, yRange.first) }
        else -> (xRange zip yRange).map { Point(it.first, it.second) }
    }.toSet()
}

private fun loadLines() = "^(\\d+),(\\d+) -> (\\d+),(\\d+)$".toRegex().let { regex ->
    loadStrings("dec05.txt").map { regex.find(it)!!.groupValues }
        .map { (_, x1, y1, x2, y2) -> Line(Point(x1.toInt(), y1.toInt()), Point(x2.toInt(), y2.toInt())) }
}

fun dec05part1() =
    loadLines().filter { it.isHorz() || it.isVert() }.flatMap { it.points() }.groupBy { it }.count { it.value.size > 1 }

fun dec05part2() = loadLines().flatMap { it.points() }.groupBy { it }.count { it.value.size > 1 }
