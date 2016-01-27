package org.otw.open.dto

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, GL20, Pixmap, Texture}
import com.badlogic.gdx.math.Vector2
import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable

/**
  * Created by deftimov on 25.01.2016.
  */
class DrawablePixmap(val pixmapMask: Pixmap) extends Disposable {
  private final val brushSize: Int = 160
  private final val drawColor: Color = new Color(0, 0, 0, 0)
  pixmapMask.setColor(drawColor)

  def getMaskAsTexture: Texture = {
    val maskTexture = new Texture(pixmapMask)
    maskTexture.bind(1)
    Gdx.gl.glActiveTexture(GL20.GL_TEXTURE0)
    maskTexture.draw(pixmapMask, 0, 0)
    maskTexture
  }

  def isMaskFilled: Boolean = {
    var maskAlphaSum = 0
    (0 until pixmapMask.getWidth).foreach(x => {
      (0 until pixmapMask.getHeight).foreach(y => {
        val color: Color = new Color
        Color.rgba8888ToColor(color, pixmapMask.getPixel(x, y))
        maskAlphaSum += color.a.toInt
      })

    })
    if (maskAlphaSum == 0) true
    else false
  }

  def drawDot(spot: Vector2): Unit = {
    pixmapMask.fillCircle(spot.x.toInt, spot.y.toInt, brushSize)

  }

  def drawLerped(from: Vector2, to: Vector2): Unit = {
    val dist: Float = to.dst(from)
    val alphaStep = brushSize / (8f * dist)
    val seq = 0f to 1f by alphaStep
    seq.foreach(currentFloat => {
      val lerped: Vector2 = from.lerp(to, currentFloat)
      drawDot(lerped)
    })
    drawDot(to)

  }

  override def dispose() = pixmapMask.dispose()
}
