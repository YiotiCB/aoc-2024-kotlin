fun main() {
    val regex = Regex("""mul\(\d{1,3},\d{1,3}\)""")
    fun prepareInput(input: List<String>): List<String> {
        return input.flatMap { regex.findAll(it).map { match -> match.value } }
    }

    val regext2 = Regex("""mul\((\d{0,3}),?(\d{0,3})?\)|(?:do|don't)\(\)""")
    fun prepareInput2(input: List<String>): List<String> {
        return input.flatMap { regext2.findAll(it).map { match -> match.value } }
    }

    fun part1(input: List<String>): Int {
        val inputs = prepareInput(input)
        val numbersRegext = Regex("""\d{1,3},\d{1,3}""")
        return inputs.asSequence().flatMap { numbersRegext.findAll(it) }.map { match -> match.value }
            .map { it.split(",") }.map { it.map { it.toInt() } }.map { it.reduce { acc, i -> acc * i } }.sum()
    }

    fun sanitiseInputs(inputs: List<String>): List<String> {
        var drop = false
        val mutableInputs = inputs.toMutableList()
        val dropped: MutableList<String> = mutableListOf()
        for (i in inputs.indices) {
            if (inputs[i].contains("do()")) {
                drop = false
                dropped.add(mutableInputs[i])
            }
            if (inputs[i].contains("don't()")) {
                drop = true
            }
            if (drop) {
                dropped.add(mutableInputs[i])
            }
        }
        mutableInputs.removeAll(dropped)
        mutableInputs.println()
        return mutableInputs
    }

    fun part2(input: List<String>): Int {
        val inputs = prepareInput2(input)
        val sanitisedInputs = sanitiseInputs(inputs)
        return part1(sanitisedInputs)
    }

    // Read the input from the `src/inputs/Day01.txt` file.
    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}