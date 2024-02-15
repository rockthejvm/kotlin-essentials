package com.rockthejvm.oopfun

object DataClasses {

    // hashCode, toString, equals - lots of boilerplate!
    class CityNaive(val name: String, val country: String, val population: Int) {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as CityNaive

            if (name != other.name) return false
            if (country != other.country) return false
            if (population != other.population) return false

            return true
        }

        override fun hashCode(): Int {
            var result = name.hashCode()
            result = 31 * result + country.hashCode()
            result = 31 * result + population
            return result
        }

        override fun toString(): String =
            "City($name, $country, $population)"
    }

    // data class - THE choice for storing "information" as classes
    // meant to be passed around and stored
    // equals, hashCode, toString - made by the compiler!
    // copy
    // destructuring - componentN functions made by the compiler
    data class City(val name: String, val country: String, val population: Int) {
        // new properties, methods, ...
    }

    // data objects - not very useful, all generated niceties don't make sense
    data object NoOperation

    // useful for sealed interfaces
    // example: a gaming chat room
    sealed interface Message
    data class Join(val player: String): Message
    data class Ping(val from: String, val to: String): Message
    data class Exit(val player: String): Message
    data object TerminateGame: Message

    // restriction: all the constructor args MUST be properties
    // restriction: must have at least one constructor arg
    // restriction: cannot inherit from a data class

    @JvmStatic
    fun main(args: Array<String>) {
        val bucharest = City("Bucharest", "Romania", 2000000)
        val bucharest_v2 = City("Bucharest", "Romania", 2000000)
        val grownBucharest = bucharest.copy(population = 2500000)

        // equals
        println(bucharest) // bucharest.toString()
        println(grownBucharest)
        println(bucharest == bucharest_v2)
        // destructuring
        /*
            val name = bucharest.component1()
            val country = bucharest.component2()
            val pop = bucharest.component3()
         */
        val (name, country, pop) = bucharest
        println("$name, $country, $pop")
    }
}