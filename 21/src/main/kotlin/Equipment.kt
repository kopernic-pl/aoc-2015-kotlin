class Equipment (val weapon: Weapon, val armor: Armor?, val rings: Set<Ring>) {
    init {
        require(rings.size<=2) {
            "No more than two rings!"
        }
    }

    fun totalCost(): Int = weapon.getCost() + rings.sumOf { it.getCost() } + (armor?.getCost() ?: 0)

    fun atk(): Int = weapon.getAtk() + rings.sumOf { it.getAtk() }

    fun def(): Int = (armor?.getDef()?:0) + rings.sumOf { it.getDef() }
}

sealed class Ring(private val cost: Int, private val atk: Int, private val def: Int): Good, Weapon, Armor {
    override fun getCost(): Int {
        return cost
    }

    override fun getAtk(): Int {
        return atk
    }

    override fun getDef(): Int {
        return def
    }
}

interface Armor : Good {
    fun getDef(): Int
}

interface Weapon : Good {
    fun getAtk(): Int
}

interface Good {
    fun getCost(): Int
}

class Damage1: Ring(25,1,0)
class Damage2: Ring(50,2,0)
class Damage3: Ring(100,3,0)

class Def1: Ring(20,0,1)
class Def2: Ring(40,0,2)
class Def3: Ring(80,0,3)