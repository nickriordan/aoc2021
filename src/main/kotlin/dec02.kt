abstract class SubPosition(protected val horz: Int = 0, protected val vert: Int = 0) {
    abstract fun down(amt: Int): SubPosition
    abstract fun up(amt: Int): SubPosition
    abstract fun forward(amt: Int): SubPosition
    fun answer() = horz * vert
}

class Position1(horz: Int = 0, vert: Int = 0) : SubPosition(horz, vert) {
    override fun down(amt: Int) = Position1(horz, vert + amt)
    override fun up(amt: Int) = Position1(horz, vert - amt)
    override fun forward(amt: Int) = Position1(horz + amt, vert)
}

class Position2(horz: Int = 0, vert: Int = 0, private val aim: Int = 0) : SubPosition(horz, vert) {
    override fun down(amt: Int) = Position2(horz, vert, aim + amt)
    override fun up(amt: Int) = Position2(horz, vert, aim - amt)
    override fun forward(amt: Int) = Position2(horz + amt, vert + (aim * amt), aim)
}

fun dec02(initial: SubPosition) =
    loadStrings("dec02.txt").fold(initial) { pos, line ->
        val (cmd, amt) = line.split(" ").let { (f, s) -> Pair(f, s.toInt()) }
        when (cmd) {
            "down" -> pos.down(amt)
            "up" -> pos.up(amt)
            "forward" -> pos.forward(amt)
            else -> throw Exception("Unknown command")
        }
    }.answer()
