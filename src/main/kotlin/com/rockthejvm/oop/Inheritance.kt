package com.rockthejvm.oop

object Inheritance {
    open class Animal { // class can be inherited
        open fun eat() { // can be overridden in child classes
            println("I'm eating, naf, naf")
        }
    }

    class Dog: Animal() {
        // Dog "extends"/inherits from Animal
        // Dog is a subtype of Animal
        // Dog IS AN Animal

        // method eat is available

        // override = change the behavior of the eat() method
        override fun eat() {
            // super.eat() // calls the eat() method in the parent class
            println("I'm a dog, I chew things!")
        }
    }

    val lassie = Dog()
    val anAnimal: Animal = Dog() // subtype polymorphism
    // same thing happens with properties

    // restrictions
    // need to provide a constructor for the parent class
    open class Person(open val name: String, open val age: Int)
    class Adult(override val name: String, override val age: Int, idCard: String): Person(name, age)

    // restrict inheritance with the `final` keyword
    open class Travel(val destination: String) {
        final fun confirm(): String = "Congrats! You're going to $destination" // restricts overriding
        // final = cannot be overriden
    }

    open class Leisure {
        open fun confirmExperience(): String = "Chill"
    }

    open class Travel_V2(val destination: String): Leisure() {
        // overriding stops here
        final override fun confirmExperience(): String =
            "Congrats! You're going to $destination"
    }

    class SpecialTickets: Travel_V2("USA") {
//        override fun confirmExperience(): String =
//            "Seeing Breaking Benjamin"
        // overriding stopped at Travel_V2
    }

    // sealing a type hierarchy = restricts inheritance to THIS FILE ONLY
    sealed class ProtocolMessage(contents: String) // automatically open
    class BeginningExchange(flag: String, contents: String): ProtocolMessage(contents)
    class Exchange(sender: String, receiver: String, contents: String): ProtocolMessage(contents)
    object EndExchange: ProtocolMessage("")
    // no other subtypes of ProtocolMessage may exist outside this file

    /*
        Any     ->    Any?
         ^             ^
         |             |
       Animal   ->   Animal?
         ^             ^
         |             |
        Dog     ->    Dog?
         ^             ^
         |             |
         ..           ..
       Nothing  ->   Nothing?
     */

    // val nothing: Nothing = throw RuntimeException("Nothing")

    @JvmStatic
    fun main(args: Array<String>) {
        lassie.eat()
        anAnimal.eat()
    }
}