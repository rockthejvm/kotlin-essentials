package com.rockthejvm.oop

// object-oriented programming
class Person(val firstName: String, val lastName: String, age: Int) { // PRIMARY constructor
    init {
        // run arbitrary code when this class is being instantiated
        println("initializing a Person with the name $firstName $lastName")
    }

    // can run multiple init blocks, they run one after another in the order they're defined in the class
    init {
        println("some other arbitrary code")
    }

    // can define PROPERTIES (data = vals, vars) and METHODS (behavior = functions)
    val fullName = "$firstName $lastName" // <-- PROPERTY

    var favMovie: String = "Forrest Gump"
        get() = field
        set(value: String) {
            // run any code here
            println("Setting the value of favMovie to $value")
            field = value // set(value)
        }

    /*
        Properties with get() and/or set(value) may or may not have backing fields (= memory zones for them).
        Create a backing field simply by using `field` in the implementation of get() or set().
        The compiler detects if you have a backing field or not.
        - if you have a backing field, you MUST initialize the property
        - if you don't have a backing field, you CANNOT initialize the property
     */

    // initialization
    lateinit var favLanguage: String
    fun initializeFavLang() {
        if (!this::favLanguage.isInitialized)
            favLanguage = "Kotlin"
    }

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

// immutable = data cannot be changed, must create another instance
// mutable = data CAN be changed without allocating another instance

fun main() {
    val daniel = Person("Daniel", "Ciocîrlan", 130) // constructed/instantiated a Person
    //  ^^^^^^ an INSTANCE of Person
    val danielsFullName = daniel.fullName

    println(danielsFullName)
    println(daniel.greet())
    println(daniel.greet("Anna"))
    val simplePerson = Person()
    println(simplePerson.fullName)

    // get and set
    println("Getting and setting")
    println(daniel.favMovie) // calling the get() method on the favMovie property
    daniel.favMovie = "Mission Impossible" // calling the set("Mission Impossible") method on the favMovie property
    println(daniel.favMovie)
}
