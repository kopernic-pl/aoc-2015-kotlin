import TemplateProvider.Companion.bitsGroup
import TemplateProvider.Companion.in1Group
import TemplateProvider.Companion.in2Group
import TemplateProvider.Companion.inGroup
import TemplateProvider.Companion.outGroup
import com.google.common.io.Resources
import org.intellij.lang.annotations.Language
import java.io.File

fun main() {
    val x = File(Resources.getResource("input.txt").toURI()).readLines()
        .map { line -> line to templates.find { it.template.toRegex().matches(line) } }
        .map { (line, transform) -> line to transform?.parse(line) }

    val elementsByOutput = x
        .filter { it.second != null }
        .map { (_, element) -> element!!.output to element }
        .toMap()

    val resultOfFirstTask = calculateNetwork(elementsByOutput)

    calculateNetworkWithValuesOverride(elementsByOutput, setOf("b" to resultOfFirstTask))
}

internal val templates: Set<TemplateProvider> = setOf(
    Negator.InputDef,
    LeftShifter.InputDef,
    RightShifter.InputDef,
    FixedAndGate.InputDef,
    AndGate.InputDef,
    OrGate.InputDef,
    Identity.InputDef,
    InputElement.InputDef
)

const val SIGNAL_LOOKED_FOR = "a"

private fun calculateNetwork(elements: Map<String, Element>): Int {
    val inputElements = findInputElements(elements)

    var busValues = inputElements.map { (bus, element) -> bus to (element as InputElement).apply() }.toMap()

    while (hasNoValueForSignal(busValues, SIGNAL_LOOKED_FOR)) {
        val unknownElements = elements - busValues.keys
        val elementsWithKnownInputs =
            unknownElements.filter { (_, element) -> busValues.keys.containsAll(element.inputs) }
        busValues = busValues + elementsWithKnownInputs
            .map { (bus, element) -> bus to calcElementOutput(element, busValues) }
    }

    println("Signal on a is ${busValues[SIGNAL_LOOKED_FOR]}")
    return busValues.getValue(SIGNAL_LOOKED_FOR)
}

private fun hasNoValueForSignal(busValues: Map<String, Int>, signalName: String) =
    !busValues.containsKey(signalName)

private fun calcElementOutput(e: Element, values: Map<String, Int>): Int = when (e) {
    is SingleInputElectronicElement -> e.apply(values.getValue(e.inputs.first()))
    is DualInputElectronicElement -> e.apply(values.getValue(e.inputs[0]), values.getValue(e.inputs[1]))
    else -> throw IllegalArgumentException()
}

private fun findInputElements(elementsByOutput: Map<String, Element>) =
    elementsByOutput.filter { (_, e) -> e.inputs.isEmpty() }

private fun calculateNetworkWithValuesOverride(
    elementsByOutput: Map<String, Element>,
    overrides: Set<Pair<String, Int>>
) {
    fun replaceValue(elements: Map<String, Element>, overrideVal: Pair<String, Int>): Map<String, Element> {
        val overrideValue = overrideVal.second
        val signalName = overrideVal.first
        return elements + (signalName to InputElement(overrideValue, signalName, emptyList()))
    }

    val overriddenInputs = overrides.fold(elementsByOutput, ::replaceValue)

    calculateNetwork(overriddenInputs)
}

interface TemplateProvider {
    val template: String
    fun parse(s: String): Element?

    companion object {
        const val inGroup = "in"
        const val in1Group = "in1"
        const val in2Group = "in2"
        const val outGroup = "out"
        const val bitsGroup = "bits"
    }
}

class Negator(override val inputs: List<String>, override val output: String) : SingleInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template = "NOT (?<$inGroup>[a-z]{1,2}) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let { Negator(listOf(it[inGroup]!!.value), it[outGroup]!!.value) }
        }
    }

    override fun apply(input: Int): Int = input.inv()
}

class LeftShifter(
    private val bits: Int,
    override val inputs: List<String>,
    override val output: String
) :
    SingleInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template =
            """(?<$inGroup>[a-z]{1,2}) LSHIFT (?<$bitsGroup>\d{1,2}) -> (?<$outGroup>[a-z]{1,2})"""

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    LeftShifter(
                        it[bitsGroup]!!.value.toInt(),
                        listOf(it[inGroup]!!.value),
                        it[outGroup]!!.value
                    )
                }
        }
    }

    override fun apply(input: Int): Int = input.shl(bits)
}

class RightShifter(
    private val bits: Int,
    override val inputs: List<String>,
    override val output: String
) :
    SingleInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template =
            """(?<$inGroup>[a-z]{1,2}) RSHIFT (?<$bitsGroup>\d{1,2}) -> (?<$outGroup>[a-z]{1,2})"""

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    RightShifter(
                        it[bitsGroup]!!.value.toInt(),
                        listOf(it[inGroup]!!.value),
                        it[outGroup]!!.value
                    )
                }
        }
    }

    override fun apply(input: Int): Int = input.shr(bits)
}

class Identity(override val inputs: List<String>, override val output: String) : SingleInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template = "(?<$inGroup>[a-z]{1,2}) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    Identity(listOf(it[inGroup]!!.value), it[outGroup]!!.value)
                }
        }
    }

    override fun apply(input: Int): Int = input
}

class InputElement(private val inputValue: Int, override val output: String, override val inputs: List<String>) :
    Element {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template = "(?<$inGroup>\\d+) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    InputElement(it[inGroup]!!.value.toInt(), it[outGroup]!!.value, emptyList())
                }
        }
    }

    fun apply(): Int = inputValue
}

class FixedAndGate(private val fixedVal: Int, override val inputs: List<String>, override val output: String) :
    SingleInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template =
            "(?<$in1Group>\\d+) AND (?<$in2Group>[a-z]{1,2}) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    FixedAndGate(
                        it[in1Group]!!.value.toInt(),
                        listOf(it[in2Group]!!.value),
                        it[outGroup]!!.value
                    )
                }
        }
    }

    override fun apply(input: Int): Int = fixedVal.and(input)
}

class AndGate(override val inputs: List<String>, override val output: String) : DualInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template =
            "(?<$in1Group>[a-z]{1,2}) AND (?<$in2Group>[a-z]{1,2}) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    AndGate(
                        listOf(
                            it[in1Group]!!.value, it[in2Group]!!.value
                        ),
                        it[outGroup]!!.value
                    )
                }
        }
    }

    override fun apply(input1: Int, input2: Int): Int = input1.and(input2)
}

class OrGate(override val inputs: List<String>, override val output: String) : DualInputElectronicElement {
    companion object InputDef : TemplateProvider {
        @Language("RegExp")
        override val template =
            "(?<$in1Group>[a-z]{1,2}) OR (?<$in2Group>[a-z]{1,2}) -> (?<$outGroup>[a-z]{1,2})"

        override fun parse(s: String): Element? {
            return template.toRegex()
                .matchEntire(s)
                ?.groups
                ?.let {
                    OrGate(listOf(it[in1Group]!!.value, it[in2Group]!!.value), it[outGroup]!!.value)
                }
        }
    }

    override fun apply(input1: Int, input2: Int): Int = input1.or(input2)
}
