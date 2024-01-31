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
}

fun drawColor(width: Int, height: Int, path: String) {
    val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
    val pixels = IntArray(width * height) { 0xFF0000 }
    image.setRGB(0,0,width,height, pixels, 0, width)
    ImageIO.write(image, "JPG", File(path))
}

fun main() {
    val red = Color(255, 0, 0)
    val green = Color(-1, 561566, 0)
//    red.draw(20, 20, "src/main/resources/red.jpg")
//    green.draw(20, 20, "src/main/resources/green.jpg")
    val magenta = Color(255, 0, 255)
    magenta.draw(20, 20, "src/main/resources/magenta.jpg")
}