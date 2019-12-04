class Light(var state: Boolean = false) {
    companion object {
        fun byBoolean(isOn: Boolean) = Light(isOn)
        private val neighborsCountNeededToStayOn = setOf(2, 3)
        private const val neighborsCountNeededToTurnOn = 3
    }

    private var nextState: Boolean = false
    internal lateinit var neighbours: List<Light>

    fun calculateNextState() {
        val onNeighbours = neighbours.count { it.state }
        nextState = when (state) {
            true -> neighborsCountNeededToStayOn.contains(onNeighbours)
            false -> onNeighbours == neighborsCountNeededToTurnOn
        }
    }

    fun switchState() {
        state = nextState
    }
}
