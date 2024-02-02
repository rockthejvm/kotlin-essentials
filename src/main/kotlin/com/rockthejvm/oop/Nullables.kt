package com.rockthejvm.oop

object Nullables {
    class Developer(val name: String, val favLanguage: String) {
        fun writeCode(code: String = "") {
            println("$name writing code in $favLanguage: $code")
        }
    }

    // null = no value
    // Developer daniel = null; // possible in other languages
    // val daniel: Developer = null; // not possible in Kotlin
    // NPE
    val maybeDeveloper: Developer? = null; // possible
    //                  ^^^^^^^^^^ nullable type

    fun createDeveloper(name: String) =
        if (name.isNotEmpty()) Developer(name, "Kotlin")
        else null

    val maybeDeveloper_v2: Developer? = createDeveloper("Master Yoda")
    // once you have a nullable, you CANNOT access properties or methods
    // val masterYoda = maybeDeveloper_v2.name

    fun makeDevWriteCode(dev: Developer?, code: String) =
        if (dev != null) dev.writeCode(code) // "flow typing"
        // ^ on this branch, the compiler KNOWS that the value is not null -> can use proerties/methods
        else println("Error: developer is null")

    // if you KNOW that a nullable is NOT null, then you can force the value to be of concrete type
    val masterYoda = maybeDeveloper_v2!! // type is now concrete
    // if you're wrong, then it will crash with NPE
    // do NOT use !! unless you REALLY know what you're doing

    // safe call operator ?. + property or methods
    val maybeName: String? = maybeDeveloper?.name

    val realDeveloper: Developer = maybeDeveloper ?: Developer("John Doe", "Cobol")
    //                                                  ^^ Elvis operator

    fun makeDevWriteCode_v2(dev: Developer?, code: String) =
        dev?.writeCode(code) ?: println("Error: dev does not exist")

    // side note: safe casting
    val something: Any = 42
    // if you know you have an Int, you can cast it down
    val anInt = something as Int // crashes if you're wrong
    val maybeInt = something as? Int // does not crash but returns nullable

    @JvmStatic
    fun main(args: Array<String>) {
        makeDevWriteCode(maybeDeveloper_v2, "val x = 42")
        makeDevWriteCode(null, "val x = 42")
        maybeDeveloper_v2?.writeCode("some code in Kotlin")


    }
}