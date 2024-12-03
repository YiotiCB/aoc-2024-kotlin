fun main() {
    fun prepareInput(input: List<String>): List<List<Int>> {
        return input.map { it.split(" ").map { num -> num.toInt() } }
    }


    fun isSafe(list: List<Int>): Boolean {
        val differences = list.windowed(2).map { it[1] - it[0] }
        return differences.all { it in 1..3 } || differences.all { it < 0 && it >= -3 }
    }

    fun part1(input: List<String>): Int {
        val preparedInput = prepareInput(input)
        var sum = 0
        preparedInput.map {
            if (isSafe(it)) {
                sum++
            }
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        val preparedInput = prepareInput(input)
        var sum = 0
        preparedInput.map {
            if ((isSafe(it)) || (0..it.size).any { index ->
                    isSafe(it.take(index) + it.drop(index + 1))
                }) {
                sum++
            }
        }
        return sum
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}