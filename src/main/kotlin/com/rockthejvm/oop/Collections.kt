package com.rockthejvm.oop

import com.rockthejvm.basics.printHello

object Collections {

    // lists
    fun demoLists() {
        // immutable
        val aList: List<Int> = listOf(1,2,3)
        // main API: index an element + size
        val thirdElement: Int = aList.get(2) // 0-index
        val thirdElement_v2: Int = aList[2] // C-style indexing syntax
        val length = aList.size

        // other API
        val find3 = aList.indexOf(3) // index of 3, otherwise -1 if 3 does not exist in the list
        val subList = aList.subList(1,2) // from(inclusive), to(exclusive) => [2]
        val has3 = aList.contains(3) // boolean
        val with4 = aList.plus(4) // NEW LIST -> [1,2,3,4]
        // plus others (FP)

        // mutable lists
        val mutableList = mutableListOf(1,2,3)
        mutableList.add(0, 42) // puts 42 at index 0, pushes everything else 1 index further -> [42,1,2,3]
        mutableList.removeAt(0) // cuts the item out, pulls everything 1 index closer
        mutableList.set(1, 56) // changes the element at this index
        mutableList[1] = 56 // same thing, C-style syntax
        println(mutableList)

        // work best with immutable lists

        val uniqueItems = aList.toSet()
    }

    // arrays - map to JVM arrays - map to OS-level arrays (very fast)
    // always mutable
    fun demoArrays() {
        val anArray = arrayOf(1,2,3,4) // [1,2,3,4]
        // only have "get" and "set"
        val secondItem = anArray[1] // 0-indexed
        // set
        anArray[1] = 100
        // length
        val length = anArray.size

        for (element in anArray) // iterate through elements of a collections - for lists, arrays, sets, maps
            println(element)
    }

    // sets - don't contain duplicates
    // the order of elements is NOT enforced
    fun demoSets() {
        // immutable
        val aSet = setOf(1,2,3,4,1,2,3) // will only contain 1,2,3 just once
        // API - contains
        val contains1 = aSet.contains(1) // true if 1 is in the set - done in constant time
        val contains1_v2 = 1 in aSet // Kotlin-specific syntax sugar
        // secondary APIs
        val add7 = aSet.plus(7) // returns ANOTHER set with 7 included
        val add7_v2 = aSet + 7 // same thing (syntax sugar)
        val without3 = aSet.minus(3) // returns ANOTHER set with 3 removed
        val without3_v2 = aSet - 3 // same
        val combined = aSet.plus(setOf(7,8,9)) // combines (unions) two sets
        val diff = aSet.minus(setOf(3,4,5)) // difference between sets
        val intersect = aSet.intersect(setOf(3,4,5)) // a new set with just common elements -> [3,4]

        // mutable
        val mutableSet = mutableSetOf(1,2,3,4,1,2,3,5,6)
        mutableSet.add(9) // changes the existing set
        mutableSet.remove(4)
        // same secondary API

        println(mutableSet)

        // convert back to another collection
        val numbers = aSet.toList()
    }

    // maps - key-value associations (key is unique)
    fun demoMaps() {
        // immutable maps
        val phonebook = mapOf(
            Pair("Daniel", 123),
            "Alice" to 999 // syntax sugar for Pair("Alice", 999)
        )
        // fundamental API
        val hasAlice = phonebook.contains("Alice") // true if "Alice" is a key in the map
        val aliceNumber = phonebook.get("Alice") // gets the value associated to the key "Alice"
        val aliceNumber_v2 = phonebook["Alice"] // will crash if the key does not exist

        // secondary API
        val newMap = phonebook.plus("Bob" to 567) // new map with the new association
        val withoutDaniel = phonebook.minus("Daniel") // new map with "Daniel" removed
        val pairs = phonebook.toList() // returns a list of pairs
        // viceversa
        val pairs_v2 = listOf(Pair("Daniel", 123), "Alice" to 999)
        val phonebook_v2 = pairs_v2.toMap() // only for lists of pairs

        // mutable maps
        val mutablePhonebook = mutableMapOf(
            Pair("Daniel", 123),
            "Alice" to 999
        )
        mutablePhonebook.remove("Daniel") // changes the same instance
        mutablePhonebook.put("Bob", 567)

    }

    @JvmStatic
    fun main(args: Array<String>) {
        demoLists()
        demoArrays()
        demoSets()
    }
}