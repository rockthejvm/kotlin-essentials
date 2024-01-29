package com.rockthejvm.basics

fun main() {
    // statically-typed language
    val meaningOfLife: Int = 42
    // meaningOfLife = 100 // val cannot be reassigned

    var objectiveInLife: Int = 32
    objectiveInLife = 24 // vars CAN be reassigned

    // type inference = compiler figures out the type from the RHS of the assignment
    val meaningOfLive_v2 = 42
    val meaningOfLive_v3 = 40 + 2

    // common types: int, boolean, char, string, short, long, float, double
    val aBoolean: Boolean = true // false
    val aChar: Char = 'K'
    val aByte: Byte = 127 // 1 byte representation
    val aShort: Short = 1234 // 2 bytes
    val anInt: Int = 78 // 4 bytes
    val aLong: Long = 56785678235678L // 8 bytes
    val aFloat: Float = 2.4f // 4 bytes
    val aDouble: Double = 3.14 // 8 bytes

    // String
    val aString: String = "I love Kotlin"
}

// top-level values = constants
const val appWideMoL: Int = 42 // computed first