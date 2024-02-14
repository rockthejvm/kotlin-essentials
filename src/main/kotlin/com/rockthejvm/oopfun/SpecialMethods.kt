package com.rockthejvm.oopfun

object SpecialMethods {
    // special methods in Java/JVM: equals, hashCode, toString
    class Person(val name: String, val age: Int) {
        override fun equals(other: Any?): Boolean = when(other) {
            is Person -> name == other.name && age == other.age
            else -> false
        }

        // a "unique" number for this instance
        // IMPORTANT: 2 "equal" instances should produce the same hashCode
        // used in hash-based data structures (sets, maps)
        override fun hashCode(): Int =
            name.hashCode() * 31 + age

        override fun toString(): String =
            "Person($name, $age)"

        // infix methods only work for methods with ONE ARGUMENT
        infix fun likes(movie: String) =
            "$name says: I LOVE $movie!"

        /*
            this < another => this.compareTo(another) < 0
            this > another => this.compareTo(another) > 0
            same for <=, >=

            IMPORTANT: if this.equals(another), then make sure that
                this.compareTo(another) == 0
         */
        operator fun compareTo(another: Person): Int =
            this.age - another.age
    }

    class ComplexNumber(var re: Double, var im: Double) {
        // operator overloading
        // plus, minus, times, div, rem
        operator fun plus(other: ComplexNumber) =
            ComplexNumber(re + other.re, im + other.im)

        operator fun plus(number: Double) =
            ComplexNumber(number + re, im)

        // compound operators - must return UNIT
        // plusAssign, minusAssign, ...
        operator fun plusAssign(number: Double): Unit {
            re += number
        }

        // inc, dec - for x++, x--
        operator fun inc(): ComplexNumber = ComplexNumber(re + 1, im)
        // unaryPlus, unaryMinus - for -x, +x
        operator fun unaryMinus(): ComplexNumber = ComplexNumber(-re, -im)

        // access elements
        // cn[1] == cn.get(index)
        operator fun get(index: Int): Double =
            when (index) {
                0 -> re
                1 -> im
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields")
            }

        operator fun get(part: String): Double =
            when (part) {
                "re" -> re
                "im" -> im
                else -> throw IllegalArgumentException("invalid field")
            }

        // cn[1] = 4.3
        operator fun set(index: Int, value: Double): Unit {
            when (index) {
                0 -> re = value
                1 -> im = value
                else -> throw IllegalArgumentException("Complex numbers only have 2 fields")
            }
        }



        // contains operator
        // 2.0 in cn
        // useful for collections
        operator fun contains(v: Double): Boolean =
            re == v || im == v

        // destructuring operators (Python-style declaration)
        // val (x,y) = cn
        operator fun component1() = re
        operator fun component2() = im
        operator fun component3() = Math.sqrt(re * re + im * im)

        // how you can "call" this instance like a function
        operator fun invoke(origin: ComplexNumber): ComplexNumber =
            ComplexNumber(re - origin.re, im - origin.im)

        override fun toString(): String =
            "($re, $im)"
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val alice = Person("Alice", 25)
        val alice2 = Person("Alice", 25)
        val bob = Person("Bob", 23)

        println(alice == alice2) // person1.equals(person2) == true
        println(alice === alice2) // "reference equality" = the exact same instance = false

        println(alice) // println(person1.toString())

        println(alice.likes("Forrest Gump"))
        println(alice likes "Forrest Gump") // same expression
        //            ^^^^^ infix position

        // operators
        val cn = ComplexNumber(1.2, 2.6)
        val acn = ComplexNumber(0.6, 2.9)
        println(cn.plus(acn)) // standard notation
        println(cn + acn) // same expressions
        println(cn + 6.7) // same as cn.plus(6.7)
        // reassignment
        cn += 8.9 // same as cn.plusAssign(8.9)
        println(-cn) // same as cn.unaryMinus()
        // comparison
        println(bob < alice) // same as bob.compareTo(alice) < 0
        // accessors
        println(cn[0]) // same as cn.get(0)
        println(cn["re"]) // same as cn.get("re")
        cn[1] = 4.3 // same as cn.set(1, 4.3)
        println(2.0 in cn) // same as cn.contains(2.0)

        /*
            val x = cn.component1()
            val y = cn.component2()
            val l = cn.component3()
         */
        val (x,y,l) = cn
        println(x)
        println(y)

        val relative = cn(acn) // same as cn.invoke(acn)
    }
}