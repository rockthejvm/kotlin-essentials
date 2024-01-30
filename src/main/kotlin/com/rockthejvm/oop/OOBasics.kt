package com.rockthejvm.oop

// object-oriented programming
class Person(val firstName: String, val lastName: String, age: Int) { // PRIMARY constructor
    // can define PROPERTIES (data = vals, vars) and METHODS (behavior = functions)
    val fullName = "$firstName $lastName" // <-- PROPERTY

    fun greet() = // METHOD
        "Hi everyone, my name is $firstName"

    // OVERLOADING = multiple methods with the same name and different signatures
    fun greet(firstName: String): String =
        "Hi $firstName, my name is ${this.firstName}, how do you do?"

    // secondary (overloaded) constructors
    // MUST always invoke another constructor
    constructor(firstName: String, lastName: String): this(firstName, lastName, 0)
    constructor(): this("Jane", "Doe")
}

fun main() {
    val daniel = Person("Daniel", "Ciocîrlan", 130) // constructed/instantiated a Person
    //  ^^^^^^ an INSTANCE of Person
    val danielsFullName = daniel.fullName

    println(danielsFullName)
    println(daniel.greet())
    println(daniel.greet("Anna"))
    val simplePerson = Person()
    println(simplePerson.fullName)
}
