package com.rockthejvm.oop

import java.io.File
import java.io.BufferedInputStream
import java.io.DataInputStream
import java.io.FileInputStream

object Delegation {
    interface TextTransformer {
        val id: String
        fun transform(text: String): String
        fun getDescription(): String
    }

    open class Translator(open val from: String, open val to: String): TextTransformer {
        override val id: String = "Translator $from -> $to"
        override fun getDescription(): String  = "Translator $from -> $to"
        override fun transform(text: String): String =
            "[${getDescription()}] translating from $from to $to: $text" // perform the translation log
    }

    class QuickTranslator(override val from: String, override val to: String): Translator(from, to) {
        override fun getDescription(): String = "QUICK translator $from -> $to"
    }

    class GPT4: TextTransformer {
        override val id: String = "GPT-4"
        override fun getDescription(): String = "a simple AI"
        override fun transform(text: String): String =
            "[$id] something an AI would say"
    }

    // create a transformer
    val transformer: TextTransformer = Translator("English", "Romanian")
    // use it directly
    val transformedText = transformer.transform("This is a Kotlin lesson")

    // debate - composition vs inheritance
    // Decorator pattern
    class TextProcessor(private val t: TextTransformer): TextTransformer {
        override val id: String = t.id
        override fun getDescription(): String = t.getDescription()
        override fun transform(text: String): String = t.transform(text) // delegation to t
    }
    // example: Java Stream API - InputStream
    val bis = DataInputStream(BufferedInputStream(FileInputStream(File("src/main/kotlin/com/rockthejvm/oop/Delegation.kt"))))

    val processor = TextProcessor(Translator("English", "Romanian"))
    val transformedText_v2 = processor.transform("This is a Kotlin lesson")

    // Delegation
    class TextProcessorV2(private val t: TextTransformer): TextTransformer by t {// same as TextProcessor
        // can override any method
        // override fun transform(text: String): String = "grammar autocorrect for $text"
        // be careful with overridden properties/methods
        override fun getDescription(): String = "grammar autocorrector"
    }

    val processor_v2 = TextProcessorV2(Translator("English", "Romanian"))
    val transformedText_v3 = processor_v2.transform("This is a Kotlin lesson") // same thing

    // can you override any method? YES

    @JvmStatic
    fun main(args: Array<String>) {
        println(transformedText)
        println(transformedText_v2)
        println(transformedText_v3)

        println(QuickTranslator("English", "Romanian").transform("This is a Kotlin lesson"))
    }
}