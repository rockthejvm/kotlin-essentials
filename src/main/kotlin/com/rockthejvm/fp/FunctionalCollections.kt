package com.rockthejvm.fp

object FunctionalCollections {

    fun concatenate(n: Int, s: String): String =
        if (n <= 0) ""
        else s + concatenate(n-1, s)

    fun demoLists() {
        val numbers = listOf(1,2,3,4,5)
        // mapping
        val tenxNumbers = numbers.map { x -> x * 10 }
        // may return a different list type
        val kotlinRepeated = numbers.map { x -> concatenate(x, "Kotlin") }

        // filtering
        val evenNumbers = numbers.filter { x -> x % 2 == 0 }

        // doing something
        evenNumbers.forEach { x ->
            println(x)
        }

        // expand a list
        val expandedList = numbers.flatMap { x -> (1..x).toList() }
        println(expandedList)

        // reducing a list to a single value
        val numbersSum = numbers.fold(0) { a, b -> a + b }
        val numbersSum_v2 = numbers.reduce { a,b -> a + b }

        // processing with predicates
        val firstEven = numbers.find { x -> x % 2 == 0 } // returns nullable - the first item in the list passing the predicate
        val evenPrefix = numbers.takeWhile { x -> x % 2 == 0 } // returns a list containing the first items of the list, as long as the predicate holds true
        val evenCount = numbers.count { x -> x % 2 == 0 }

        // many more
        val stringRep = numbers.joinToString("|", "{", "}") { x -> x.toString() }
        println(stringRep)
    }

    fun demoSets() {
        val numbers = setOf(1,2,3,4,5)

        // check whether an entire set satisfies a predicate
        val lt10 = numbers.all { x -> x < 10 }
        val lt100 = numbers.none { x -> x >= 100 }
    }

    fun demoMaps() {
        val phonebook = mapOf(
            "Daniel" to 123,
            "Alice" to 999
        )

        // map, filter, flatMap... -- just for PAIRS not individual items

        // filtering keys
        val sectionA = phonebook.filterKeys { it.startsWith("A") } // map of all pairs whose keys start with A

        // mapping values
        val addSuffix = phonebook.mapValues { pair -> pair.value * 10 } // map of all pairs with the same keys, but values transformed

        // construct a map with a default value (avoid crashes for invalid keys)
        val phonebookWithDefault = phonebook.withDefault { invalidKey -> -9000 }
    }

    /**
     * Exercises
     * 1. you have a list of strings -> return a list of those strings' length
     *  ["kotlin", "is", "cool"] -> [6,2,4]
     * 2. you have two lists of numbers of the same size, return a sum of corresponding elements
     *  [1,2,3,4], [5,6,7,8] -> [6, 8, 10, 12]
     *   use the function .zip
     * 3. two lists of things, return all the combinations as strings, feel free to choose the format
     *  [1,2,3], ["black", "white", "red", "blue"] -> ["1-black", "1-white", "1-red", "1-blue", ...]
     * 4. list of strings, return the concatenation of all the strings
     *  ["kotlin", "is", "cool"] -> "kotliniscool"
     *  - reduce
     *  - fold
     * 5. concatenate "kotlin" a number of times, by using just the standard library API, NOT concatenate(n, s)
     */

    fun exercises() {
        // 1
        val strings = listOf("kotlin", "is", "cool")
        val stringsLengths = strings.map { it.length }

        // 2
        val numbers1 = listOf(1,2,3,4)
        val numbers2 = listOf(5,6,7,8)
        val sums = numbers1.zip(numbers2).map { it.first + it.second }
        val sums_v2 = numbers1.zip(numbers2) { a,b -> a + b }

        // 3 - flatMap
        val numbers = listOf(1,2,3)
        val colors = listOf("black", "white", "red", "blue")
        val combinations = numbers.flatMap { number ->
            // 1 -> colors.map(...) -> [1-black, 1-white, 1-red, 1-blue]
            // 2 -> colors.map(...) -> [2-black, 2-white, 2-red, 2-blue]
            //...
            colors.map { color -> "$number-$color" }
        }

        // 4 - joinToString, reduce, fold
        val statement = strings.joinToString("")
        val statement_r = strings.reduce { a,b -> a + b }
        val statement_f = strings.fold("") { a,b -> a + b }

        // 5
        val kotlinx5 = (1..5).joinToString("") { _ -> "kotlin" }
        val kotlinx5_v2 = (1..5).toList()
            .map { x -> "kotlin" }
            .reduce { a,b -> a + b }
        println(kotlinx5_v2)

    }

    @JvmStatic
    fun main(args: Array<String>) {
        exercises()
    }
}