fun dec03part1(): Int = loadStrings("dec03.txt").fold(mapOf<Int, Int>()) { res, line ->
    line.foldIndexed(res) { i, out, c -> out + (i to (out.getOrDefault(i, 0) + if (c == '1') 1 else -1)) }
}.toSortedMap().values.fold(0) { res, v -> (res * 2) + if (v > 0) 1 else 0 }.let { v -> (v.inv() and 0xFFF) * v }

fun dec03part2(): Int {
    fun findCO2Scrubber(lines: List<String>, pos: Int = 0): Int {
        val expected = if (lines.fold(0) { res, line -> res + if (line[pos] == '1') 1 else -1 } < 0) '1' else '0'
        val res = lines.filter { line -> expected == line[pos] }
        return (if (res.size == 1) res.single().toInt(2) else findCO2Scrubber(res, pos + 1))
    }

    fun findOxygenGenerator(lines: List<String>, pos: Int = 0): Int {
        val expected = if (lines.fold(0) { res, line -> res + if (line[pos] == '1') 1 else -1 } >= 0) '1' else '0'
        val res = lines.filter { line -> expected == line[pos] }
        return (if (res.size == 1) res.single().toInt(2) else findOxygenGenerator(res, pos + 1))
    }

    return loadStrings("dec03.txt").let { lines -> findOxygenGenerator(lines) * findCO2Scrubber(lines) }
}
