import java.io.File

println(
    File("input1")
        .reader().readText()
        .fold(0) { acc, c ->
            when {
                c.equals('(') -> acc + 1
                c.equals(')') -> acc - 1
                else -> throw RuntimeException("funny value in input")
            }
        }
)