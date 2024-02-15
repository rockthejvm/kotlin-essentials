package com.rockthejvm.oopfun

object Extensions {

    // we can add NEW methods & properties to EXISTING types
    // 3.multiply("Kotlin")
    fun Int.multiply(aString: String): String {
    //  ^^^^^^^^^^^^ extension method
        var result = ""
        for (i in 1..this)
            result += aString
        return result
    }

    // extension properties
    val Int.nDigits: Int // extension property (cannot have a backing field)
        get() {
            var result = 0
            var theNumber = this
            while (theNumber != 0) {
                theNumber /= 10
                result ++
            }
            return result
        }

    // restriction: can be shadowed (have the same signature) as a real method from the class
    // in this case the real method is called
    class Person(val name: String) {
        fun greet() = "Hi everyone, I'm $name."
    }

    fun Person.greet() =
    //  ^^^^^^ the "receiver" type
        "$name says: I HATE EVERYONE! AAAAARH!"

    /*
        compiler makes new synthetic function (hidden)
        fun greet($this: Person): String = ...
     */

    // restriction: in the impl of extension method, you can only access public properties/methods of `this`

    @JvmStatic
    fun main(args: Array<String>) {
        val kotlinx3 = 3.multiply("Kotlin")
        println(kotlinx3)
        println(123456.nDigits)

        val obiwan = Person("Obi-Wan Kenobi")
        println(obiwan.greet())
    }
}