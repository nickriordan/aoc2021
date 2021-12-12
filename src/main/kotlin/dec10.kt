private data class State(val stack: List<Char> = emptyList(), val res: Int = 0) {
    fun push(ch: Char) = State(stack + ch, res)
    fun pop(ch: Char, score: Int) = State(stack.dropLast(1), if (res == 0 && stack.last() != ch) score else res)
}

private fun matchBrackets(line: String) =
    line.fold(State()) { state, ch ->
        when (ch) {
            '(' -> state.push(')')
            '[' -> state.push(']')
            '{' -> state.push('}')
            '<' -> state.push('>')
            ')' -> state.pop(ch, 3)
            ']' -> state.pop(ch, 57)
            '}' -> state.pop(ch, 1197)
            '>' -> state.pop(ch, 25137)
            else -> throw Exception("huh?")
        }
    }

fun dec10part1() = loadStrings("dec10.txt").sumOf { line -> matchBrackets(line).res }

fun dec10part2() = loadStrings("dec10.txt").map { matchBrackets(it) }.filter { it.res == 0 }.map {
    it.stack.reversed().fold(0L) { acc, c -> (5 * acc) + (")]}>".indexOf(c) + 1) }
}.sorted().let { res -> res[res.size / 2] }