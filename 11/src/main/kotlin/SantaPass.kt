const val INPUT = "hepxcrrq"

typealias IncrementResult = Pair<Boolean, Char>

fun main() {
    val generator = SantaPass()
    val passwords = generator.generateNextPasswords(INPUT, 2)

    println(passwords)
}


internal class SantaPass {
    fun generateNextPasswords(currentPass: String, n: Int): List<String> {
        return generateSequence(currentPass) { generateNextPass(it) }
            .drop(1).take(n)
            .toList()
    }

    companion object {
        val rules = setOf(IOLMustBeAvoided(), MustContainTwoPairs(), MustContainThreeLettersIncrease())
    }

    internal fun generateNextPass(currentPass: String): String {
        return generateSequence(currentPass) { it.inc() }
            .drop(1)
            .filter { pass -> rules.all { rule -> rule.validate(pass) } }
            .first()
    }
}

internal operator fun String.inc(): String {
    if (this.isEmpty()) throw IllegalStateException()

    return this.foldRight(listOf(),
        { char, acc: List<IncrementResult> ->
            when {
                acc.isEmpty() -> acc + char.incrementWithCarry()
                lastHasCarry(acc) -> acc + char.incrementWithCarry()
                else -> acc + (false to char)
            }
        })
        .reversed()
        .let {
            if (firstHasCarry(it)) listOf(false to 'a') + it
            else it
        }
        .map { (_, value) -> value }
        .joinToString("")
}

private fun firstHasCarry(l: List<IncrementResult>): Boolean = l.first().first
private fun lastHasCarry(l: List<IncrementResult>): Boolean = l.last().first

internal fun Char.incrementWithCarry(): IncrementResult {
    check(this in 'a'..'z')

    return when {
        this == 'z' -> true to 'a'
        else -> false to this.inc()
    }
}
