package com.rockthejvm.practice

import java.awt.Graphics
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

// crop a picture
class Image(private val buffImage: BufferedImage) { // NEVER expose mutable state outside this class
    val width = buffImage.width
    val height = buffImage.height

    fun getColor(x: Int, y: Int): Color =
        Color.fromHex(buffImage.getRGB(x, y))

    fun setColor(x: Int, y: Int, color: Color) =
        buffImage.setRGB(x,y,color.toInt())

    fun draw(g: Graphics) {
        g.drawImage(buffImage, 0, 0, null)
    }

    fun save(path: String) =
        ImageIO.write(buffImage, "JPG", File(path))

    fun saveResource(path: String) =
        save("src/main/resources/$path")

    fun crop(startX: Int, startY: Int, w: Int, h: Int): Image? {
        // dimension checks
        if (startX < 0 || startX >= width || startY < 0 || startY >= height) return null
        if (w < 0 || startX + w >= width || h < 0 || startY + h >= height) return null

        // happy path
        val result = Image.black(w, h)
        for (x in startX ..< (startX + w))
            for (y in startY ..< (startY + h)) {
                val originalPixel = buffImage.getRGB(x,y)
                result.buffImage.setRGB(x - startX, y - startY, originalPixel)
            }

        return result
    }

    companion object {
        fun black(width: Int, height: Int): Image {
            val buffImage = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)
            val pixels = IntArray(width * height) { 0 }
            buffImage.setRGB(0,0,width,height,pixels,0,width)
            return Image(buffImage)
        }

        fun load(path: String) =
            Image(ImageIO.read(File(path)))

        fun loadResource(path: String) =
            load("src/main/resources/$path")
    }
}

object ImagePlayground {
    @JvmStatic
    fun main(args: Array<String>) {
        Image.loadResource("wikicrop.jpg").crop(480, 180, 350, 180)?.saveResource("cropped.jpg")
    }
}