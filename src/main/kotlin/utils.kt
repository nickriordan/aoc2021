import kotlin.math.absoluteValue

fun loadStrings(fileName: String): List<String> =
    object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
        ?: throw Exception("Failed to load file $fileName")

fun loadInts(fileName: String) = loadStrings(fileName).map(String::toInt)

fun loadSingleLineInts(fileName: String) = loadStrings(fileName).first().split(",").map(String::toInt)

fun IntProgression.magnitude() = (this.first - this.last).absoluteValue

data class Point(val x: Int, val y: Int)
