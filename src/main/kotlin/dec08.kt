fun load() = loadStrings("dec08.txt").map { it.split("|") }.map { (patterns, output) ->
    Pair(patterns.trim().split(" "), output.trim().split(" "))
}

private fun decode(inp: List<String>): Map<Set<Char>, Int> = inp.toSet().let { patterns ->
    val one = patterns.first { it.length == 2 }
    val four = patterns.first { it.length == 4 }
    val seven = patterns.first { it.length == 3 }
    val eight = patterns.first { it.length == 7 }

    // Find the 6
    val six = patterns.sortedBy { it.length }.first { (it.toSet() + one.toSet()).size == 7 }

    // Find the 9 - only in common with 4 has segments in common with 8, 9
    val nine = (patterns - eight - four).first { it.toSet().containsAll(four.toSet()) }

    // Find the 0 - only three have length 6
    val zero = (patterns - six - nine).first { it.length == 6 }

    // Find the 5 - contains everything in 6
    val five = (patterns - six).first { six.toSet().containsAll(it.toSet()) }

    // Find the 2 - one does not match against 2, 5 and 6
    val two = (patterns - five - six).first { !it.toSet().containsAll(one.toSet()) }

    // Only three remaining
    val three = (patterns - zero - one - two - four - five - six - seven - eight - nine).first()

    listOf(zero, one, two, three, four, five, six, seven, eight, nine).mapIndexed { i, s -> s.toSet() to i }.toMap()
}

fun dec08part1() = load().sumOf { (patterns, display) ->
    val encoding = patterns.associate { pattern ->
        pattern.toSet() to when (pattern.length) {
            2 -> 1
            4 -> 4
            3 -> 7
            7 -> 8
            else -> null
        }
    }
    display.mapNotNull { signal -> encoding[signal.toSet()] }.count()
}

fun dec08part2() = load().sumOf { (patterns, display) ->
    val code = decode(patterns)
    display.fold(0) { a, s -> (a * 10) + code.getOrElse(s.toSet()) { throw Exception("Unknown pattern") } }.toInt()
}
