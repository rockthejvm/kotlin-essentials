package com.rockthejvm.oopfun

object NestedInnerClasses {

    class Outer {
        val aProp = 4

        // nested class == "static" class
        // depends on the Outer TYPE, no connection with any INSTANCE of Outer
        class Nested {
            val nestedProp = 42
            // val summedProp = aProp + 10
        }

        // inner class
        // depends on the current INSTANCE that created Inner
        inner class Inner {
            val innerProp = aProp + 10 // possible
            val outerInstance = this@Outer //
            //                  ^^^^^^^^^^ "qualified this"
        }
    }

    fun demoClasses() {
        // nested
        val nested = Outer.Nested()
        println(nested.nestedProp)

        // inner
        val outerInstance = Outer()
        val inner = outerInstance.Inner()
        println(inner.innerProp)
    }

    // nested classes are useful when the nested types are conceptually tied to the wrapping (outer) type
    // and they are relevant for the _definition_ of a particular service
    interface MyProtocol {
        sealed class Message
        data class Start(val nPlayers: Int): Message()
        data class GameEvent(val type: String, val playerId: String): Message()
        // ...
    }

    // inner classes are useful when the types are tied to the _implementation_ of the wrapping INSTANCE
    class MyPermissionsService {
        // only relevant in THIS INSTANCE
        open inner class Role(name: String)
        inner class Admin: Role("ADMIN")
        inner class Moderator: Role("MODERATOR")
        inner class Player: Role("PLAYER")
    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoClasses()
    }
}