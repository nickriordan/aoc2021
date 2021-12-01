fun dec01() = fileAsLines("dec01.txt").map { it.toInt() }.toList().let { depths ->
    listOf(
        depths.windowed(2).count { it[1] > it[0] },
        depths.windowed(3, 1).map { it.sum() }.windowed(2).count { it[1] > it[0] }
    )
}
