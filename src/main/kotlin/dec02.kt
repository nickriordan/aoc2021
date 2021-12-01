fun dec02() = fileAsLines("dec02.txt").map { it.toInt() }.toList().let { data ->
    Pair(data[0], data[1])
}