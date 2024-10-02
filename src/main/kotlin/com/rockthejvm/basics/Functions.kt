package com.rockthejvm.basics

// Unit == "void" in other languages
fun simpleFunction(arg: String): Unit {
    println("Just passed an argument: $arg") // string "template" or "interpolation"
    // 1000 lines of code
}

fun printHello() {
    println("I'm a simple no-arg function")
}

// concatenates aString count times
// concatenateString("Kotlin", 3) = "KotlinKotlinKotlin"
fun concatenateString(aString: String, count: Int): String {
    var result = ""
    for (i in 1..count)
        result += aString
    return result
}

// special syntax for single-expr functions
fun combineStrings(strA: String, strB: String): String =
    "$strA---$strB"

// recursion (functional programming)
/*
    csr("Kotlin", 3) =
    "Kotlin" + csr("Kotlin", 2) = "Kotlin" + "KotlinKotlin" = "KotlinKotlinKotlin"

    csr("Kotlin", 2) =
    "Kotlin" + csr("Kotlin", 1) = "Kotlin" + "Kotlin" = "KotlinKotlin"

    csr("Kotlin", 1) =
    "Kotlin" + csr("Kotlin", 0) = "Kotlin" + "" = "Kotlin"

    csr("Kotlin", 0) =
    ""
 */
fun concatenateStringRec(aString: String, count: Int): String =
    if (count <= 0) ""
    else aString + concatenateStringRec(aString, count - 1)

// default args
fun demoDefaultArg(regularArg: String = "Kotlin", intArg: Int = 0) =
    "$regularArg------$intArg"

// nested function calls
fun complexFunction(someArg: String) {
    // very complex code
    fun innerFunction(innerArg: Int) {
        println("Outer arg: $someArg, inner arg: $innerArg")
    }
    // can use nested functions just inside here
    innerFunction(45)
}

/**
 * Exercises:
 * 1. a greeting function (name, age) => "Hi, my name is ___ and I am ___ years old".
 * 2. a factorial function n => 1 * 2 * 3 * ... * n
 * 3. a Fibonacci function n => nth Fibonacci number
 *      1 2 3 5 8 13 21, 34 ...
 * 4. a function that tests whether a number is prime n => boolean whether that n is prime
 *      isPrime(8) = false
 *      isPrime(7) = true
 *      isPrime(2003) = true
 *
 * BONUS if you also do recursive versions of ex 2-4.
 */

// 1
fun greetingForKids(name: String, age: Int): String =
    "Hi, my name is $name and I am $age years old."

// 2
fun factorial(n: Int): Long {
    if (n <= 0) return 0

    var product = 1L
    for (i in 1..n)
        product *= i
    return product
}

/*
    fact(5) = 5 * fact(4) = 5 * 24 = 120

    fact(4) = 4 * fact(3) = 4 * 6 = 24

    fact(3) = 3 * fact(2) = 3 * 2 = 6

    fact(2) = 2 * fact(1) = 2 * 1 = 2

    fact(1) = 1
 */
fun factorialRec(n: Int): Long =
    if (n <= 0) 0
    else if (n == 1) 1
    else n * factorialRec(n - 1)

// 3
fun fibonacci(n: Int): Long {
    var smaller = 1L
    var larger = 2L

    if (n <= 0) return -1
    if (n == 1) return 1
    if (n == 2) return 2

    // 1 2 3 5 8 13 21
    //
    for (i in 3..n) {
        val next = smaller + larger
        smaller = larger
        larger = next
    }

    return larger
}

fun fibonacciRec(n: Int): Long =
    if (n <= 0) -1
    else if (n == 1) 1
    else if (n == 2) 2
    else fibonacciRec(n - 1) + fibonacciRec(n - 2)

// 4
fun testPrime(n: Int): Boolean {
    // 1 < d < n, n % d == 0 => n is NOT a prime
    for (d in 2.. n/2)
        if (n % d == 0)
            return false
    return true
}

/*
    tpr(11) = tpr(11, 2) = tpr(11, 3) = tpr(11, 4) = tpr(11, 5) = tpr(11, 6) = true
    no stack frames to allocate
 */
tailrec fun testPrimeRec(n: Int, d: Int = 2): Boolean =
    if (d > n/2) true
    else if (n % d == 0) false
    else testPrimeRec(n, d + 1) // recursive call is computed LAST on this branch
//       ^^^^^^^^^^^^^^^^^^^^^ tail position == TAIL RECURSIVE

fun main() {
    simpleFunction("Kotlin")
    simpleFunction("Scala")
    println(concatenateString("Kotlin", 3))
    println(concatenateString("Kotlin", 10))
    println(concatenateStringRec("Kotlin", 3))
    println(concatenateStringRec("Kotlin", 10))
    // default args demo
    val normalCall = demoDefaultArg("Kotlin", 32)
    val defaultCall = demoDefaultArg("Kotlin") // number 0 is passed automatically
    val multipleDefaultCall = demoDefaultArg() // both args are default
    val secondNormalCall = demoDefaultArg(intArg = 99) // name an argument
    val kotlinx3 = concatenateString(aString = "Kotlin", count = 3)

    // exercises
    println(greetingForKids("Daniel", 7))
    println(factorial(5))
    println(factorialRec(5))
    println("Fibonacci series - iterative")
    for (i in 1..10)
        println(fibonacci(i))
    println("Fibonacci series - recursive")
    for (i in 1..10)
        println(fibonacciRec(i))
    println("prime tests - iterative")
    println("81 - ${testPrime(81)}")
    println("7 - ${testPrime(7)}")
    println("2003 - ${testPrime(2003)}")
    println("prime tests - recursive")
    println("81 - ${testPrimeRec(81)}")
    println("7 - ${testPrimeRec(7)}")
    println("2003 - ${testPrimeRec(2003)}")
}