package com.rockthejvm.practice

import java.io.IOException

/*
    1. Create an interface Transformation with a single `process` method
        taking an Image and returning another Image
    2. Create 3 subclasses of Transformation
        - Crop - x,y,w,h as constructor args
        - Blend - fgImage, blendMode as constructor args
        - Noop - does nothing
        - stub the process method e.g. printing something
    3. Add a `parse` method in the Transformation companion
        taking a String and returning a Transformation instance
        - parse("crop 0 10 100 200") -> Crop(0, 10, 100, 200)
        - parse("blend paris.jpg transparency") -> Blend(Image(..), Transparency)
        - parse("master yoda") -> Noop

        hints
        - string.split(" ") -> array of strings (words)
        - string.toInt() -> convert a String to a number
        - BlendMode.parse()
    4. Application main method.
    5. Parse transformations in the main app.
    6. Implement the transformations.
 */

interface Transformation {
    operator fun invoke(image: Image): Image

    companion object {
        fun parse(string: String): Transformation {
            val words = string.split(" ")
            val command = words[0]
            return when (command) {
                "crop" ->
                    try {
                        Crop(
                            words[1].toInt(),
                            words[2].toInt(),
                            words[3].toInt(),
                            words[4].toInt(),
                        )
                    } catch (e: Exception) {
                        println("Invalid crop format. Usage: 'crop [x] [y] [w] [h]'")
                        Noop
                    }
                "blend" ->
                    try {
                        Blend(
                            Image.loadResource(words[1]),
                            BlendMode.parse(words[2])
                        )
                    } catch (e: IOException) {
                        println("Invalid image.")
                        Noop
                    } catch (e: Exception) {
                        println("Invalid blend format. Usage: 'blend [path] [mode]'")
                        Noop
                    }
                "invert" -> Invert
                "grayscale" -> Grayscale
                else -> Noop
            }
        }
    }
}

class Crop(val x: Int, val y: Int, val w: Int, val h: Int): Transformation {
    override fun invoke(image: Image): Image =
        try {
            image.crop(x,y,w,h)!! // will crash if the coords are out of bounds
        } catch (e: Exception) {
            println("Error: coordinates are out of bounds. Max coordinates: ${image.width} x ${image.height}")
            image
        }
}

/*
    1. make sure that the images have the exact same dimensions
    2. create a black image from those dimensions
    3. for every pixel in fgImage with every _corresponding_ pixel in bgImage,
        use the blendMode to combine the colors
        write that pixel in those coordinates in the result image
    4. return the image
 */
class Blend(val fgImage: Image, val mode: BlendMode): Transformation {
    override fun invoke(bgImage: Image): Image {
        // 1
        if (fgImage.width != bgImage.width || fgImage.height != bgImage.height) {
            println("Error: images don't have the same sizes: ${fgImage.width} x ${fgImage.height} vs  ${bgImage.width} x ${bgImage.height} ")
            return bgImage
        }

        val width =  fgImage.width
        val height = fgImage.height
        // 2
        val result = Image.black(width, height)
        // 3
        for (x in 0 ..< width)
            for (y in 0 ..< height)
                result.setColor(
                    x,
                    y,
                    mode.combine(
                        fgImage.getColor(x,y),
                        bgImage.getColor(x,y)
                    )
                )
        // 4
        return result
    }
}

abstract class PixelTransformation(val pixelFun: (Color) -> Color): Transformation {
    override fun invoke(image: Image): Image {
        val width = image.width
        val height = image.height
        val result = Image.black(width, height)
        for (x in 0 ..< width)
            for (y in 0 ..< height) {
                val originalColor = image.getColor(x,y)
                val newColor = pixelFun(originalColor)
                result.setColor(x,y, newColor)
            }

        return result
    }
}

object Invert: PixelTransformation({ color ->
    Color(
        255 - color.red,
        255 - color.green,
        255 - color.blue,
    )
})

object Grayscale: PixelTransformation({ color ->
    val avg = (color.red + color.green + color.blue) / 3
    Color(avg, avg, avg) // last expression is the value of the lambda
})


object Noop: Transformation {
    override fun invoke(image: Image): Image = image
}