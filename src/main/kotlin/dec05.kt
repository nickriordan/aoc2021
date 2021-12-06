import kotlin.math.absoluteValue

data class Point(val x: Int, val y: Int)
fun IntProgression.magnitude() = (this.first - this.last).absoluteValue

class Line(pt1: Point, pt2: Point) {
    private val xRange = if (pt1.x < pt2.x) (pt1.x..pt2.x) else (pt1.x downTo pt2.x)
    private val yRange = if (pt1.y < pt2.y) (pt1.y..pt2.y) else (pt1.y downTo pt2.y)

    fun isVert() = xRange.first == xRange.last
    fun isHorz() = yRange.first == yRange.last

    fun points() = when {
        isVert() -> yRange.map { Point(xRange.first, it) }
        isHorz() -> xRange.map { Point(it, yRange.first) }
        xRange.magnitude() != yRange.magnitude() -> throw Exception()
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
