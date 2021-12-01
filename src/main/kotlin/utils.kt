fun loadStrings(fileName: String): List<String> =
    object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
        ?: throw Exception("Failed to load file $fileName")

fun loadInts(fileName: String) = loadStrings(fileName).map(String::toInt)
