import java.io.File

var floor = 0
File("input1")
        .reader().readText()
        .forEach {
            when {
                it.equals('(') -> floor++
                it.equals(')') -> floor--
                else -> throw RuntimeException("funny value in input")
            }
        }

println(floor)