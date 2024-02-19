package com.rockthejvm.fp

object FunctionValues {

    interface Transformation { // it "is" a function
        operator fun invoke(n: Int): Int
    }

    fun transformList(list: List<Int>, transformation: Transformation): List<Int> {
        val result = mutableListOf<Int>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun transformList_v2(list: List<Int>, transformation: (Int) -> Int): List<Int> {
        //                                                ^^^^^^^^^^^^ Function type!
        val result = mutableListOf<Int>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun <A, B> transformList_v3(list: List<A>, transformation: (A) -> B): List<B> {
        //                                                     ^^^^^^^^ Function type!
        val result = mutableListOf<B>()
        for (n in list)
            result.add(transformation(n))
        return result
    }

    fun <A> reduce(list: List<A>, seed: A, op: (A, A) -> A): A {
        var result = seed
        for (n in list)
            result = op(result, n)
        return result
    }

    tailrec fun <A> reduceRec(list: List<A>, seed: A, op: (A, A) -> A): A =
        if (list.isEmpty()) seed
        else reduceRec(list.drop(1), op(seed, list[0]), op)

    @JvmStatic
    fun main(args: Array<String>) {
        val numbers = listOf(1,2,3,4)
        val tenxTransformation = object: Transformation {
            override fun invoke(n: Int): Int = n * 10
        }
        println(transformList(numbers, tenxTransformation))
        val add5Transformation = object : Transformation {
            override fun invoke(n: Int): Int = n + 5
        }
        println(transformList(numbers, add5Transformation))

        // functional programming = ability to pass functions as arguments, return functions as results
        // function values
        val tenxFun = fun (x: Int): Int { return x * 10 } // function VALUE
        val tenxFun_v2 = { x: Int -> x * 10 } // same thing - anonymous function aka LAMBDA
        println(transformList_v2(numbers, tenxFun_v2))

        // .map
        val tenxNumbers = numbers.map({ x: Int -> x * 10 }) // passing a function as an argument
        val tenxNumbers_v2 = numbers.map { x: Int -> x * 10 } // simplified syntax, when the last arg is a lambda
        val tenxNumbers_v3 = numbers.map { x -> x * 10 } // type inference helps
        val tenxNumbers_v4 = numbers.map { it * 10 } // default name is "it", only works for single-arg lambdas

        // multi-arg lambdas
        val adderFun: (Int, Int) -> Int = { a, b -> a + b }

        /*
            Exercises:
            1. Write a function to combine all the elements of a list, using a combination function that you pass as argument
                "reduce"
                reduce([1,2,3,4], +, 0) = 10
                reduce([1,2,3,4], a,b => a * b, 1) = 24
            2. Learn to use APIs that use lambdas - sort a list
               Sort a list of Strings by their length.
         */
        // 1
        println(reduce(numbers, 0) { a, b -> a + b })
        println(reduce(numbers, 1) { a, b -> a * b })
        println(reduce(listOf("I", "love", "Kotlin"), "") { a, b -> "$a $b" })
        // 1 - recursive
        println(reduceRec(numbers, 0) { a, b -> a + b })
        println(reduceRec(numbers, 1) { a, b -> a * b })
        println(reduceRec(listOf("I", "love", "Kotlin"), "") { a, b -> "$a $b" })

        // 2
        val someStrings = listOf("I'm learning about function values", "I love Kotlin", "I am writing a lot of code")
        val sortedStrings = someStrings.sortedBy { it.length } // ascending sort by this criterion
        println(sortedStrings)
    }
}