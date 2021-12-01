fun main() {
    val depths = fileAsLines("dec01.txt").map { it.toInt() }.toList()

    // Part 1
    println(depths.windowed(2).count { it[1] > it[0] })

    // Part 2
    println(depths.windowed(3, 1).map { it.sum() }.windowed(2).count { it[1] > it[0] })
}
