data class Board(private val rows: List<List<Int?>>, val winningNumber: Int = -1) {
    fun call(number: Int) =
        if (winningNumber < 0) {
            val updatedRows = rows.map { it.map { e -> if (e == number) null else e } }
            val cols = updatedRows[0].indices.map { ix -> updatedRows.map { it[ix] } }
            if (updatedRows.any { r -> r.all { it == null } } || cols.any { c -> c.all { it == null } }) {
                Board(updatedRows, number)
            } else
                Board(updatedRows)
        } else
            this

    fun isWon() = winningNumber >= 0
    fun sumOfRemaining() = rows.sumOf { it.filterNotNull().sum() }
}

data class Round(private val boards: List<Board>, private val lastTry: Int = 0) {
    fun call(number: Int) = Round(boards.map { board -> board.call(number) }, number)
    fun anyBoardsWon() = boards.any { it.isWon() }
    fun allBoardsWon() = boards.all { it.isWon() }
    fun winningScore() = boards.first { it.winningNumber == lastTry }.sumOfRemaining() * lastTry
}

private fun calculateRounds(): List<Round> {
    val lines = loadStrings("dec04.txt")
    val numbersToCall = lines[0].split(",").map(String::toInt)
    val boards = lines.drop(2).windowed(5, 6).map {
        it.map { l -> l.trim().split("\\s+".toRegex()).map(String::toInt) }
    }.map { Board(it) }

    return numbersToCall.fold(listOf(Round(boards))) { rounds, number -> rounds + rounds.last().call(number) }
}

fun dec04part1() = calculateRounds().first { it.anyBoardsWon() }.winningScore()
fun dec04part2() = calculateRounds().first { it.allBoardsWon() }.winningScore()
