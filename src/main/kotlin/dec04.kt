data class Board(private val rows: List<List<Int?>>, val winningNumber: Int = -1) {
    private fun rowWin(rows: List<List<Int?>>) = rows.any { r -> r.all { it == null } }
    private fun colWin(rows: List<List<Int?>>) = rows[0].indices.map { ix -> rows.map { it[ix] } }.let { cols ->
        cols.any { c -> c.all { it == null } }
    }

    fun call(number: Int) = if (isWon()) this else
        rows.map { it.map { e -> if (e == number) null else e } }.let { updatedRows ->
            if (rowWin(updatedRows) || colWin(updatedRows)) Board(updatedRows, number) else Board(updatedRows)
        }

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
