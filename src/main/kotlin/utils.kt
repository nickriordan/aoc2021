fun fileAsLines(fileName: String) =
    object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.lines()?.toList()
        ?: throw Exception("Failed to load file $fileName")

fun <T : Number> pairs(items: List<T>): List<Pair<T, T>> {
    fun makePairs(first: T, rest: List<T>): List<Pair<T, T>> =
        rest.map { Pair(first, it) } + if (rest.isNotEmpty()) makePairs(rest.first(), rest.drop(1)) else emptyList()
    return makePairs(items.first(), items.drop(1))
}
