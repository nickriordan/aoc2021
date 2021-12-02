interface Position {
    fun down(amt: Int): Position
    fun up(amt: Int): Position
    fun forward(amt: Int): Position
    fun answer(): Int
}

data class Position1(private val horz: Int = 0, private val vert: Int = 0) : Position {
    override fun down(amt: Int) = Position1(horz, vert + amt)
    override fun up(amt: Int) = Position1(horz, vert - amt)
    override fun forward(amt: Int) = Position1(horz + amt, vert)
    override fun answer() = horz * vert
}

data class Position2(private val horz: Int = 0, private val vert: Int = 0, private val aim: Int = 0) : Position {
    override fun down(amt: Int) = Position2(horz, vert, aim + amt)
    override fun up(amt: Int) = Position2(horz, vert, aim - amt)
    override fun forward(amt: Int) = Position2(horz + amt, vert + (aim * amt), aim)
    override fun answer() = horz * vert
}

fun dec02(initial: Position) =
    loadStrings("dec02.txt").fold(initial) { pos, line ->
        val (cmd, amt) = line.split(" ").let { (f, s) -> Pair(f, s.toInt()) }
        when (cmd) {
            "down" -> pos.down(amt)
            "up" -> pos.up(amt)
            "forward" -> pos.forward(amt)
            else -> throw Exception("Unknown command")
        }
    }.answer()

