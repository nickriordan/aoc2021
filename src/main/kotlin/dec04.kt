data class Board(private val rows: List<List<Int?>>, val winningNumber: Int = -1) {
    fun attempt(n: Int) =
        if (winningNumber < 0) {
            val updatedRows = rows.map { it.map { e -> if (e == n) null else e } }
            val cols = updatedRows[0].indices.map { ix -> updatedRows.map { it[ix] } }
            if (updatedRows.any { r -> r.all { it == null } } || cols.any { c -> c.all { it == null } }) {
                Board(updatedRows, n)
            } else
                Board(updatedRows)
        } else
            this

    fun isWin() = winningNumber >= 0
    fun sumOfRemaining() = rows.sumOf { it.filterNotNull().sum() }
}

data class Round(val boards: List<Board>, val lastTry: Int = 0) {
    fun attempt(n: Int) = Round(boards.map { board -> board.attempt(n) }, n)
    fun anyBoardWithWin() = boards.any { it.isWin() }
    fun allBoardsWon() = boards.all { it.isWin() }
    fun boardsWonInThisRound() = boards.filter { it.winningNumber == lastTry }
}

private fun calculateRounds(): List<Round> {
    val lines = loadStrings("dec04.txt")
    val tries = lines[0].split(",").map(String::toInt)
    val boards = lines.drop(2).windowed(5, 6).map {
        it.map { l -> l.trim().split("\\s+".toRegex()).map(String::toInt) }
    }.map { Board(it) }

    return tries.fold(listOf(Round(boards))) { rounds, n -> rounds + rounds.last().attempt(n) }
}

fun dec04part1() = calculateRounds().first { it.anyBoardWithWin() }
    .let { it.boardsWonInThisRound().first().sumOfRemaining() * it.lastTry }

fun dec04part2() = calculateRounds().first { it.allBoardsWon() }
    .let { it.boardsWonInThisRound().first().sumOfRemaining() * it.lastTry }
