package org.otw.open.dto

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20, Pixmap, Texture}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.Disposable

/**
  * Created by deftimov on 25.01.2016.
  * Wrapper for the drawing actions on the PixmapMask.
  *
  * @param pixmapMask
  */
class DrawablePixmap(val pixmapMask: Pixmap) extends Disposable {

  private final val brushSize: Int = 160

  /** maskTexture created from the pixmap */
  private val maskTexture = new Texture(pixmapMask)

  /** binds and sets the maskTexture to TEXTURE1 as active */
  maskTexture.bind(1)

  /** Resets the SpriteBatch to TEXTURE0, otherwise it will automatically bind to the current active texture */
  Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0)

  /** Draws the initial pixmap onto the texture
    *
    * @return maskTexture */
  def initializePixmapDrawingOntoTexture: Texture = {
    drawOnTexture(maskTexture)
    maskTexture
  }

  /** Draws the pixmap onto the texture
    *
    * @param texture
    **/
  def drawOnTexture(texture: Texture) = {
    texture.draw(pixmapMask, 0, 0)
  }

  /**
    * @return true if the pixmapMask is fully transparent
    *         false otherwise
    **/
  def isTransparent: Boolean = {
    var maskAlphaSum = 0
    val pixmapSize = pixmapMask.getWidth * pixmapMask.getHeight
    val percentageRequired = pixmapSize * 0.5 / 100
    (0 until pixmapMask.getWidth).foreach(x => {
      (0 until pixmapMask.getHeight).foreach(y => {
        val color: Color = new Color
        Color.rgba8888ToColor(color, pixmapMask.getPixel(x, y))
        maskAlphaSum += color.a.toInt
      })

    })
    maskAlphaSum <= percentageRequired

  }

  /** Draws a circle on the spot's x and y coordinates
    *
    * @param spot*/
  private def drawDot(spot: Vector2): Unit = {
    pixmapMask.fillCircle(spot.x.toInt, spot.y.toInt, brushSize)

  }

  /** Linear interpolated drawing(Lerp) for smooth drawing of the brush
    *
    * @param from
    * @param to
    **/
  def drawLerped(from: Vector2, to: Vector2): Unit = {
    val distance: Float = to.dst(from)
    val alphaStep = brushSize / (8f * distance)
    val seq = 0f to 1f by alphaStep
    seq.foreach(currentStep => {
      val lerped: Vector2 = from.lerp(to, currentStep)
      drawDot(lerped)
    })
    drawDot(to)

  }

  override def dispose() = pixmapMask.dispose()
}
