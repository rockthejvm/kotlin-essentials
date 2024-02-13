package com.rockthejvm.oop

import kotlin.jvm.Throws

object Exceptions {
    fun maybeString(): String? = null

    fun demoExceptions() {
        // code that might fail
        // val division = 42 / 0 // ArithmeticException

        // throws NullPointerException
        val nullable: String? = maybeString()
        val theString = nullable!!
    }

    // throwing an exception
    class Person private constructor(val name: String, val age: Int) {
        companion object {
            @Throws(IllegalArgumentException::class) // <- can specify exceptions thrown, just a hint
            fun create(name: String, age: Int): Person {
                if (age < 0) throw IllegalArgumentException("Age must be non-negative!")
                return Person(name, age)
            }
        }
    }

    fun demoCatch() {
        // catching exceptions
        val maybePerson: Person = try {
            Person.create("Daniel", -10)
            // put the most SPECIFIC exceptions FIRST
        } catch (e: IllegalArgumentException) {
            // this catches the exception
            Person.create("Daniel", 100)
        } catch (e:  RuntimeException) {
            Person.create("Daniel", 99)
        } finally {
            // runs NO MATTER WHAT
            // release of resources
            println("Something needs to be released no matter what")
        }

        val danielsAge = maybePerson.age
        println(danielsAge)
    }

    /*
        Throwable
            - Exception - something wrong with the LOGIC, we can control
                - IOException, FileNotFoundException.... (checked exceptions)
                - RuntimeException (unchecked exceptions)
                    - NullPointerException, IllegalArgumentException
                - in Kotlin, all exceptions are "unchecked"
            - Error - something wrong with the JVM
                - StackOverflowError
                - OutOfMemoryError
                - ...
     */

    class MyException(val count: Int): RuntimeException("Something wrong") {
        // properties, methods
    }

    fun demoMyException(): String =
        throw MyException(4)

    @JvmStatic
    fun main(args: Array<String>) {
        demoCatch()
    }
}