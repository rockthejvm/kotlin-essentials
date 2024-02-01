package com.rockthejvm.oop

// object in Kotlin = definition of a type + the ONLY instance of that type
// Singleton pattern = single e.g. service, connection, data source, state

object MySingleton { // type + the only instance of this type
    // add properties and methods
    val aProperty = 42
    fun aMethod(arg: Int): Int {
        println("Hello from singleton: $arg")
        return aProperty + arg
    }

    // define entry points to your Kotlin application
    // public static void main(String[] args) == equivalent Java syntax
    @JvmStatic
    fun main(args: Array<String>) {
        println("Singleton entry point")
    }
}

object ObjectsCompanions {
    // companion objects
    class Guitar(val nStrings: Int, val type: String) {
        // properties
        // methods
        fun play() {
            println("$type guitar with $nStrings strings playing!")
        }

        companion object GuitarObject {
            // properties/methods specific to the TYPE = "static"
            val HAS_STRINGS = true
            fun createSimpleGuitar(type: String): Guitar = Guitar(6, type)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val gibson = Guitar(6, "electric")
        gibson.play()

        val guitarsHaveStrings = Guitar.HAS_STRINGS
        val simpleGuitar = Guitar.createSimpleGuitar("acoustic")
    }
}

fun main() {
    val theSingleton = MySingleton
    val anotherSingleton = MySingleton

    val singletonProperty = MySingleton.aProperty
    println(theSingleton == anotherSingleton)
    val result = MySingleton.aMethod(89)
    println("Result of singleton method: $result")
}