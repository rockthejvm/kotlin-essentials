package com.rockthejvm.oopfun

object ValueClasses {
    // wrapper types ("boxing")
    // value class
    // downside = memory overhead
    // @JvmInline value classes do NOT do any boxing
    @JvmInline value class ProductName(val name: String)
    @JvmInline value class ProductDescription(val description: String)
//  ^^^^^^^^^^ necessary if the compile target is the JVM

    data class Product(val name: ProductName, val description: ProductDescription) // 37 fields

    val kotlinCourse = Product(
        ProductName("Kotlin Essentials"),
        ProductDescription("Learn Kotlin for any kind of platform, including Android, JVM, and web!")
        // 37 other properties
    )

    @JvmStatic
    fun main(args: Array<String>) {
        println(kotlinCourse)
    }
}