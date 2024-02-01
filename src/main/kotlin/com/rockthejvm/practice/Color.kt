package com.rockthejvm.practice

import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// manipulating images
// 24bit integer = Int
// 00000000rrrrrrrrggggggggbbbbbbbb

/*
    Exercise:
    1. Define a Color class that takes 3 ints as arguments:
        - red
        - green
        - blue
    2. Make sure that the properties of the color (red, green, blue, all as integers) are always in between 0 and 255
    3. Add a method toInt() that returns a SINGLE integer with the repr above
        00000000rrrrrrrrggggggggbbbbbbbb
        use shl, shr, and, or, xor,...
    4. Add a draw(width, height, path) that draws an image of width x height, all with the same color
 */
class Color(r: Int, g: Int, b: Int) {
    val red: Int = clampColor(r) // 000000000000000000000000rrrrrrrr
    val green: Int = clampColor(g) // 000000000000000000000000gggggggg
    val blue: Int = clampColor(b) // 000000000000000000000000bbbbbbbb

    // 00000000rrrrrrrr0000000000000000
    // 0000000000000000gggggggg00000000
    // 000000000000000000000000bbbbbbbb
    // 00000000rrrrrrrrggggggggbbbbbbbb
    fun toInt() =
        (red shl 16) or (green shl 8) or blue

    fun draw(width: Int, height: Int, path: String) {
        val pixelInt = toInt()
        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
        val pixels = IntArray(width * height) { pixelInt }
        image.setRGB(0,0,width,height, pixels, 0, width)
        ImageIO.write(image, "JPG", File(path))
    }

    fun clampColor(v: Int) =
        if (v <= 0) 0
        else if (v >= 255) 255
        else v

    companion object {
        val BLACK = Color(0,0,0)
        val RED = Color(255,0,0)
        val GREEN = Color(0,255,0)
        val BLUE = Color(0,0,255)

        fun fromHex(arg: Int): Color {
            val red = (arg and 0xFF0000) shr 16
            val green = (arg and 0xFF00) shr 8
            val blue = arg and 0xFF
            return Color(red, green, blue)
        }
    }
}

/*
    Exercise: create a companion object for Color so that you can write
        Color.BLACK, Color.RED, ...
        Color.fromHex(int): Color instance out of that
 */

fun main() {
    Color.fromHex(0x888888).draw(20,20, "src/main/resources/gray.jpg")
}