package com.rockthejvm.practice

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import kotlin.test.*

class ColorTestingDemo { // testing individual functionalities/flows
    /*
        - unit testing - individual components
        - integration testing - tests a combination/interaction between components
            - end to end testing
     */

    private val black = Color(0,0,0)
    private val red = Color(255,0,0)
    private val green = Color(0,255,0)
    private val blue = Color(0,0,255)
    private val yellow = Color(255,255,0)

    // initialize this before every test
    private lateinit var colors: List<Color>

    @BeforeEach
    fun individualSetup() { // should run before every test
        println("individual setup")
        colors = listOf(red,green,blue,yellow)
    }

    @AfterEach
    fun individualCleanup() { // run after every test
        println("individual test cleanup")
        colors = listOf()
    }

    @Test
    fun testCombine() {
        println("Testing the + operator on colors")
        assertTrue { // the most general assertion
            red + green == yellow
        }

        // testing lib has assertions
        // equality
        assertEquals(red + green, yellow, "Operator + should combine color channels")
        // "sameness" = testing the SAME INSTANCE (reference equality)
        // assertSame(red + green, yellow)
        // negative assertions: not same, not contains, ...
        assertNotSame(red + green, yellow)
        // assertContains: collection contains an element
        // assertThrows: test whether your code throws an expected exception

        // can fail the test
        if (red + green != yellow) {
            fail("The operator + doesn't work! Or maybe something else...")
        }
    }

    @Test
    fun simpleTest() {
        println("simple test")
    }

    companion object {
        // "static" functions
        @JvmStatic
        @BeforeAll
        fun suiteSetup() { // will run before ANY function in the suite
            println("Suite setting up")
        }

        @JvmStatic
        @AfterAll
        fun suiteCleanup() { // will run after ANY function in the suite
            println("happily ever after")
        }
    }
}
