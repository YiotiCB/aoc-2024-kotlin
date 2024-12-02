import kotlin.math.abs

fun main() {
    fun prepareInput(input: List<String>): Pair<List<String>, List<String>> {
        return input.map { it.split("   ") }.map { Pair(it[0], it[1]) }.unzip()
    }

    fun part1(input: List<String>): Int {
        val preparedInput = prepareInput(input).first.sorted() to prepareInput(input).second.sorted()
        var runningTotal = 0
        for (i in preparedInput.first.indices) {
            runningTotal += abs(preparedInput.first[i].toInt() - preparedInput.second[i].toInt())
        }
        return runningTotal
    }

    fun part2(input: List<String>): Int {
        val preparedInput = prepareInput(input)
        val inputMap = preparedInput.first.distinct().associateWith { 0 }.toMutableMap()
        for (i in preparedInput.second.indices) {
            if (inputMap.containsKey(preparedInput.second[i])) {
                inputMap[preparedInput.second[i]] = inputMap[preparedInput.second[i]]!! + 1
            }
        }
        return inputMap.keys.sumOf { key -> key.toInt() * inputMap[key]!!.toInt() }
    }

//    // Test if implementation meets criteria from the description, like:
//    check(part1(listOf("test_input")) == 1)
//
//    // Or read a large test input from the `src/Day01_test.txt` file:
//    val testInput = readInput("Day01_test")
//    check(part1(testInput) == 1)

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
