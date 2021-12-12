data class Terrain(private val heights: List<List<Int>>) {
    private val width = heights.first().size
    private val height = heights.size

    private fun isPointValid(pt: Point) = (pt.x in 0 until width) && (pt.y in 0 until height)
    private fun pointsAround(pt: Point) = listOf(pt.left(), pt.right(), pt.up(), pt.down()).filter { isPointValid(it) }
    private fun allPointsWithin() = (0 until width).flatMap { x -> (0 until height).map { y -> Point(x, y) } }
    private fun isMinima(pt: Point) = pointsAround(pt).all { heightAt(it) > heightAt(pt) }

    fun heightAt(pt: Point) = heights[pt.y][pt.x]
    fun allMinima() = allPointsWithin().filter { isMinima(it) }

    fun hollowAround(pt: Point): Set<Point> = setOf(pt) + pointsAround(pt).mapNotNull {
        if (heightAt(it) in (heightAt(pt) + 1)..8) hollowAround(it) else null
    }.flatten().toSet()
}

private fun loadMap() = loadStrings("dec09.txt").map { it.toList().map { d -> d.digitToInt() } }

fun dec09part1() = Terrain(loadMap()).let { terrain ->
    terrain.allMinima().sumOf { terrain.heightAt(it) + 1 }
}

fun dec09part2() = Terrain(loadMap()).let { terrain ->
    terrain.allMinima().map { minimum -> terrain.hollowAround(minimum).size }
}.sortedDescending().take(3).fold(1) { acc, i -> acc * i }
