package com.rockthejvm.oop

object AccessModifiers {
    open class Person(open val name: String) {
        // protected = accessible inside this class AND all child types
        protected fun sayHi() = "Hi, I'm $name."

        // private properties/methods can only be accessed inside this body
        private fun watchNetflix(): String = "I'm binge watching my favorite series."

        // no modifier = "public"

        // modifier "internal" -> property/method accessible inside the same compilation module
        // useful for libraries
    }

    class Kid(override val name: String, age: Int): Person(name) {
        fun greetPolitely(): String =
            sayHi() + "I love to play!"
    }

    // complication
    class KidWithParents(override val name: String, val age: Int, val mom: Person, val dad: Person): Person(name) {
        // sayHi is protected, so I should be able to access it
        // ... only on THIS instance, not on other instances!

        fun everyoneIntroduceThemselves(): String =
//            "${this.sayHi()} Here are my parents! ${mom.sayHi()} ${dad.sayHi()}"
            "${this.sayHi()} Here are my parents: ${mom.name} ${dad.name}"
    }

    // private constructor
    class MyService private constructor(url: String) {
        // usually comes with a companion object
        companion object {
            fun local(): MyService =
                MyService("127.0.0.1")
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val alice = Person("Alice")
        val bob = Person("Bob")
        val kid = KidWithParents("Dennis", 5, alice, bob)

        // println(aPerson.sayHi()) // sayHi is inaccessible

        // val myService = MyService("127.0.0.1") // cannot build directly
        val myService = MyService.local()
    }
}