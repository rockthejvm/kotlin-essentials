package com.rockthejvm.oop

object Generics {
    // goal - reuse code/logic on many (potentially unrelated) types

    // linked list
    // "head" = first element
    // "tail" = the rest of the list = a linked list
    // [1,2,3,4] -> head = 1, tail = [2,3,4]
    interface MyIntList {
        fun head(): Int
        fun tail(): MyIntList
        // many more methods
        //        fun add(elem: Int): MyLinkedList
        //        fun contains(elem: Int): Boolean
        // 36 more methods
    }

    class EmptyIntList: MyIntList {
        override fun head(): Int = throw NoSuchElementException()
        override fun tail(): MyIntList = throw NoSuchElementException()
    }

    class NonEmptyIntList(private val h: Int, private val t: MyIntList): MyIntList {
        override fun head(): Int = h
        override fun tail(): MyIntList = t
    }

    // you have linked lists of integers.
    // how do you add a linked list of Strings?

    // 1 - duplicate the code for other types
    interface MyStringList {
        fun head(): String
        fun tail(): MyStringList
        // all 36 more methods, duplicated
    }

    class EmptyStringList: MyStringList {
        override fun head(): String = throw NoSuchElementException()
        override fun tail(): MyStringList = throw NoSuchElementException()
    }

    class NonEmptyStringList(private val h: String, private val t: MyStringList): MyStringList {
        override fun head(): String = h
        override fun tail(): MyStringList = t
    }

    // Persons?
    // Doubles?
    // anything else?
    // not sustainable!

    // 2 - change our signatures to use Any
    interface MyGeneralList {
        fun head(): Any
        fun tail(): MyGeneralList
    }

    class EmptyGeneralList: MyGeneralList {
        override fun head(): Any = throw NoSuchElementException()
        override fun tail(): MyGeneralList = throw NoSuchElementException()
    }

    class NonEmptyGeneralList(private val h: Any, private val t: MyGeneralList): MyGeneralList {
        override fun head(): Any = h
        override fun tail(): MyGeneralList = t
    }

    // pro: logic applies to all types
    // cons: can store strings and Persons and ints and Animals in the same list!
    // (lost type safety)

    // GENERICS
    interface MyLinkedList<A> {
        //                 ^ generic type/type argument
        fun head(): A
        fun tail(): MyLinkedList<A>
        // 36 more methods
        companion object { // MyLinkedList.____
            fun <A> single(elem: A): MyLinkedList<A> =
                NonEmptyList(elem, EmptyList())
        }
    }

    class EmptyList<A>: MyLinkedList<A> {
        override fun head(): A = throw NoSuchElementException()
        override fun tail(): MyLinkedList<A> = throw NoSuchElementException()
    }

    class NonEmptyList<A> (private val h: A, private val t: MyLinkedList<A>): MyLinkedList<A> {
        override fun head(): A = h
        override fun tail(): MyLinkedList<A> = t
    }

    /*
        - no code duplication
        - can support any type, even unrelated types
        - maintained type safety
            - homogeneous lists
            - correct type returned
            - enforce correct types
     */

    // generic features in Kotlin
    // can specify multiple type arguments
    interface MyMap<K, V>

    // generic functions
    fun <A> singleElem(elem: A): MyLinkedList<A> =
        NonEmptyList(elem, EmptyList())

    // objects CANNOT have generic type arguments
    // companion objects: can use generic method


    @JvmStatic
    fun main(args: Array<String>) {
        // [1,2,3,4]
        val simpleNumbers = NonEmptyIntList(1, NonEmptyIntList(2, NonEmptyIntList(3, NonEmptyIntList(4, EmptyIntList()))))

        val simpleNumbers_v2: MyLinkedList<Int> = NonEmptyList(1, NonEmptyList(2, NonEmptyList(3, NonEmptyList(4, EmptyList()))))
        val meaningOfLife = simpleNumbers_v2.head() + 41

        val simpleStrings = NonEmptyList("I", NonEmptyList("love", NonEmptyList("Kotlin", EmptyList())))
        val firstLength = simpleStrings.head().length

        val singleNumber = singleElem(42)
        val singleString = singleElem("Kotlin")

        val singleNumber_v2 = MyLinkedList.single(42)
    }
}