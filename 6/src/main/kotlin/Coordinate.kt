data class Coordinate(val first: Int, val second: Int) {
    companion object {
        fun generateRange(from: Coordinate, to: Coordinate): Sequence<Coordinate> {
            require(from.first <= to.first)
            require(from.second <= to.second)
            return sequence {
                for (i in from.first..to.first) {
                    for (j in from.second..to.second) {
                        yield(Coordinate(i, j))
                    }
                }
            }
        }
    }
}
