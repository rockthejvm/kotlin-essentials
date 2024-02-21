package com.rockthejvm.fp

object ScopeFunctions {

    fun obtainExternalList() = listOf(1,2,3,4,5)

    fun demoLet() {
        val numbers = obtainExternalList()
        val tenxNumbers = numbers.map { n -> n * 10 }
        println("The 10x values are ${tenxNumbers}")
        val sumNumbers = tenxNumbers.reduce { a,b -> a + b }
        println("The sum of the 10x numbers is ${sumNumbers}")

        // let allows you to use the old computation for the next step
        obtainExternalList().let { numbers -> // let is a "scope" function
            val tenxNumbers = numbers.map { n -> n * 10 }
            println("The 10x values are ${tenxNumbers}")
            val sumNumbers = tenxNumbers.reduce { a,b -> a + b }
            println("The sum of the 10x numbers is ${sumNumbers}")
        }

        // mental clarity and flow especially in chained computations
        /*
            keep just the even numbers  = filter
            multiply the numbers * 10 = map
            "avg" = list.sum / (list.size + 1)
         */
        val result = obtainExternalList()
            .filter { n -> n % 2 == 0 }
            .map { n -> n * 10 }
            .let { list -> list.sum() / (list.size + 1) }

        val step1 = obtainExternalList()
        val step2 = step1.filter { n -> n % 2 == 0 }
        val step3 = step2.map { n -> n * 10 }
        val step4 = step3.sum() / (step2.size + 1)
    }

    // run = same as let, but has access to the internal scope of the object on which you're running
    data class Person(var name: String, var age: Int)
    fun demoRun() {
        val masterYoda = Person("Master Yoda", 800)
        masterYoda.age = 850
        masterYoda.name = "Yoda the Master Jedi"
        val result = "${masterYoda.name} (${masterYoda.age})"

        val result_v2 = masterYoda.run {
            // you have access to the internals of `masterYoda` as the `this` instance
            age = 850
            name = "Yoda the Master Jedi"
            "$name (${age})" // this is the result of the lambda
        }

        println(result)
        println(result_v2)
    }

    // with = similar to run, but it's not an extension method
    data class GamingChannel(val playerA: String, val playerB: String, var open: Boolean) {
        fun msg(content: String) = println("[$playerA][to $playerB] $content")
    }

    fun demoWith() {
        val channel = GamingChannel("alice", "bob", true)
        channel.msg("build up your forces!")
        channel.msg("attack here!")
        channel.open = false

        // `with` useful when we use "resources"
        with(channel) {
            // inside this scope, `this` is the object that you're calling `with` on
            msg("build up your forces!")
            msg("attack here!")
            open = false
        }
    }

    // apply = same as run, but it RETURNS the same object that you're processing
    fun demoApply() {
        val alice = Person("Alice", 23)
        // changing this instance
        alice.name = "Alice in Wonderland"
        alice.age = 24

        val modifiedAlice = alice.apply {
            // alice == `this` in this scope
            name = "Alice in Wonderland"
            age = 24
        }

        println(modifiedAlice == alice) // same instance
    }

    // also - same as let, but the lambda doesn't return anything, the expr returns the same instance
    // useful for side effects
    fun demoAlso() {
        obtainExternalList()
            .filter { it % 2 == 0 }
            .map { it % 10 }
            .also { list -> println(list.sum() / (list.size + 1)) } // takes a lambda that returns unit -> returns the same list
            .forEach { println(it) } // that list we can use later
    }

    // context object = the instance subject to the scope function

    @JvmStatic
    fun main(args: Array<String>) {
        demoRun()
    }
}