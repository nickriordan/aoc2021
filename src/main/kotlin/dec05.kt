data class Point(val x: Int, val y: Int)

data class Line(val orig: Point, val dest: Point) {
    fun isVert() = orig.x == dest.x
    fun isHorz() = orig.y == dest.y
    fun pointsOnLine() = when {
        isVert() -> (if (orig.y < dest.y) (orig.y..dest.y) else (dest.y..orig.y)).map { Point(orig.x, it) }
        isHorz() -> (if (orig.x < dest.x) (orig.x..dest.x) else (dest.x..orig.x)).map { Point(it, orig.y) }
        else -> {
            val xRange = if (orig.x < dest.x) (orig.x..dest.x) else (orig.x downTo dest.x)
            val yRange = if (orig.y < dest.y) (orig.y..dest.y) else (orig.y downTo dest.y)
            xRange.zip(yRange).map { Point(it.first, it.second) }
        }
    }.toSet()
}

private fun loadLines(): List<Line> {
    val regex = "^(\\d+),(\\d+) -> (\\d+),(\\d+)$".toRegex()
    return loadStrings("dec05.txt").map { regex.find(it)!!.groupValues }
        .map { (_, oX, oY, dX, dY) -> Line(Point(oX.toInt(), oY.toInt()), Point(dX.toInt(), dY.toInt())) }
}

fun dec05part1() = loadLines().filter { it.isHorz() || it.isVert() }.flatMap { it.pointsOnLine() }.groupBy { it }
    .count { it.value.size > 1 }

fun dec05part2() = loadLines().flatMap { it.pointsOnLine() }.groupBy { it }.count { it.value.size > 1 }
