import kotlin.math.absoluteValue

fun loadStrings(fileName: String): List<String> =
    object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
        ?: throw Exception("Failed to load file $fileName")

fun loadInts(fileName: String) = loadStrings(fileName).map(String::toInt)

fun loadSingleLineInts(fileName: String) = loadStrings(fileName).first().split(",").map(String::toInt)

fun IntProgression.magnitude() = (this.first - this.last).absoluteValue

data class Point(val x: Int, val y: Int) {
    fun up() = Point(x, y - 1)
    fun down() = Point(x, y + 1)
    fun left() = Point(x - 1, y)
    fun right() = Point(x + 1, y)
}
