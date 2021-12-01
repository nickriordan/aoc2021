fun dec01part1() = loadInts("dec01.txt").windowed(2).count { (f, s) -> s > f }
fun dec01part2() = loadInts("dec01.txt").windowed(3) { it.sum() }.windowed(2).count { (f, s) -> s > f }
