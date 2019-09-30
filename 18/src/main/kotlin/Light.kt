class Light(var state: Boolean = false) {
    companion object {
        fun byBoolean(isOn: Boolean) = Light(isOn)
    }


    private var nextState: Boolean = false
    internal lateinit var neighbours: List<Light>

    fun calculateNextState() {
        val onNeighbours = neighbours.count { it.state }
        nextState = when (state) {
            true -> onNeighbours == 2 || onNeighbours == 3
            false -> onNeighbours == 3
        }
    }

    fun switchState() {
        state = nextState
    }
}
