package com.rockthejvm.oopfun

object AnonymousClasses {

    abstract class Plant {
        abstract fun grow(): String
    }

    class Rose(val color: String): Plant() {
        override fun grow(): String =
            "nice flowers of color $color"
    }

    class Pepper(val spicyFactor: Int): Plant() {
        override fun grow(): String =
            "peppers that are spicy $spicyFactor/10"
    }

    // a weird plan that grows in a weird way
    // only relevant HERE
    // only need one instance

//    object WeirdPlant: Plant() {
//        override fun grow(): String =
//            "weird flowers that no one has seen before"
//    }
//
//    val weirdPlant = WeirdPlant

    val weirdPlant = object:Plant() { // anonymous class
        override fun grow(): String = "weird flowers that nobody has seen before"
    }

    // works with normal classes, interfaces

    val myRose = Rose("red")
    val pepper = Pepper(7)

    val myDb = listOf(myRose, pepper, weirdPlant)

    open class Instructor(val type: String) {
        open fun encourage(name: String) =
            "Come on $name, you can do it!"
    }

    val jocko = object:Instructor("SEAL") {
        override fun encourage(name: String): String =
            "DO IT OR YOU'RE DEAD!"
    }

    @JvmStatic
    fun main(args: Array<String>) {

    }
}