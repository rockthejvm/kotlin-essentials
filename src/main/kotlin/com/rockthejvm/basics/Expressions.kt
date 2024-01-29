package com.rockthejvm.basics

fun main() {
    // expressions = structures that are turned into a value
    val meaningOfLife: Int = 40 + 2

    // math expression: +,-,*,/,%
    val mathExpression = 2 + 3 * 4

    // bitwise operators: shr, shl, ushr, and, or, xor, inv
    val bitwiseExpression = 2 shl 2 // 1000

    // comparison expressions: == (equality), != (not equality), <, <=, >, >=, ===, !==
    val equalityTest = 1 == 2 // false

    // boolean expressions: !(not), && (and), || (or)
    val nonEqualityTest = !equalityTest

    // instructions and expressions
    // expressions are EVALUATED to a value - functional programming
    // instructions are EXECUTED - imperative programming

    // if structure
    val aCondition = 1 > 2 // false
    // instruction
    if (aCondition) {
        println("the condition is true")
    } else {
        println("the condition is false")
    }

    val anIfExpression = if (aCondition) 42 else 999 // if EXPRESSION
    println(anIfExpression)

    // when - "switch on steroids"
    when (meaningOfLife) {
        42 -> println("The meaning of life from HGG")
        43 -> println("Not the meaning of life, but quite close")
        else -> println("Something else")
    }

    // when EXPRESSION
    val meaningOfLifeMessage = when (meaningOfLife) {
        42 -> "The meaning of life from HGG"
        43 -> "Not the meaning of life, but quite close"
        else -> "Something else"
    }

    // a branch in when with multiple values
    val meaningOfLifeMessage_v2 = when (meaningOfLife) {
        42, 43 -> "The meaning of life or close enough"
        else -> "Something else"
    }

    // branches can be arbitrary expressions
    val meaningOfLifeMessage_v3 = when (meaningOfLife) {
        40 + 2 -> "The meaning of life, computed"
        else -> "Something else"
    }

    // conditions in branches
    /*
    if (mol < 42) {...}
    else if (mol > 100) { ... }
    else { ... }
    * */
    val meaningOfLifeMessage_v4 = when {
        meaningOfLife < 42 -> "Meaning of life is too small"
        meaningOfLife > 100 -> "Meaning of life is too big"
        else -> "Close enough"
    }

    // test for types in a when clause
    val something: Any = 42
    val someExpression = when (something) {
        is Int -> "It's an integer, maybe the meaning of life!"
        is String -> "It's a string, so maybe not the meaning of life"
        else -> "Not sure what this can be"
    }

    val complexWhen = when (something) {
        40, 41 -> "Close to correct, from multiple cases"
        40 + 2 -> "Correct answer, from expression"
        is String -> "Maybe, but it's a string type test"
        else -> "Something else"
    }

    // looping - instructions
    // for loops
    println("inclusive range")
    for (i in 1 .. 10) {
        println(i)
    }

    println("exclusive range")
    for (i in 1 ..< 10) {
        println(i)
    }

    println("exclusive range v2")
    for (i in 1 until 10) {
        println(i)
    }

    println("inclusive range, step 2")
    for (i in 1 .. 10 step 2) {
        println(i)
    }

    println("descending range")
    for (i in 10 downTo 1) {
        println(i)
    }

    // arrays
    println("iterating over collection")
    val anArray = arrayOf(5,2,1,3,4,7,6)
    for (elem in anArray) {
        println(elem)
    }

    // while loops
    println("while loops")
    var i = 1
    while (i <= 10) {
        println(i)
        i += 1
    }

    // do-while
    println("do-while loops")
    var j = 10
    do {
        println(j)
        j -= 1
    } while (j >= 1)
}