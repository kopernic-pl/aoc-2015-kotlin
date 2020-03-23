// primes taken from A000203

fun main() {
    val minimumOfPresents = 34000000
    val primeClosestToMinimumFor10 = 3400043

    var housesArray = IntArray(primeClosestToMinimumFor10)
    var presentsPerHouse = 10
    //visit all houses
    for (i in 1 until housesArray.size) {
        var j = i
        while (j < housesArray.size) {
            housesArray[j] += i * presentsPerHouse
            j += i
        }
    }
    printFirstHouseWithSufficientPresents(housesArray, minimumOfPresents)


    val primeClosestToMinFor11 = 3090959
    housesArray = IntArray(primeClosestToMinFor11)

    presentsPerHouse = 11
    val maxVisits = 50
    //visit houses, but stop after 50
    for (i in 1 until housesArray.size) {
        var count = 0
        var j = i
        while (j < housesArray.size) {
            housesArray[j] += i * presentsPerHouse
            if (++count == maxVisits) break
            j += i
        }
    }
    printFirstHouseWithSufficientPresents(housesArray, minimumOfPresents)
}

fun printFirstHouseWithSufficientPresents(houses: IntArray, nr: Int) {
    println("House that gets at least $nr presents in scenario has number ${houses.indexOfFirst { it >= nr }}")
}
