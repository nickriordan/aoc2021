fun dec01() = fileAsLines("dec01.txt").map(String::toInt).toList().let { depths ->
    listOf(
        depths.windowed(2).count { (first, second) -> second > first },
        depths.windowed(3) { it.sum() }.windowed(2).count { (first, second) -> second > first }
    )
}
