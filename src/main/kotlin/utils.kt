fun fileAsLines(fileName: String): List<String> =
    object {}::class.java.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
        ?: throw Exception("Failed to load file $fileName")
