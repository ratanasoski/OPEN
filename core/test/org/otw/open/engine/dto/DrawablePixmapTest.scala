package org.otw.open.engine.dto

import com.badlogic.gdx.graphics.Pixmap.Format
import com.badlogic.gdx.graphics.{Color, Pixmap, Texture}
import org.otw.open.dto.DrawablePixmap
import org.otw.open.testconfig.UnitSpec

/**
  * Created by deftimov on 28.01.2016.
  */
class DrawablePixmapTest extends UnitSpec {

  test("when initializePixmapDrawingOntoTexture is invoked the pixmap should be converted in a texture") {
    val pixmapMask: Pixmap = new Pixmap(400, 400, Format.RGBA8888)
    val drawablePixmap: DrawablePixmap = new DrawablePixmap(pixmapMask)
    assert(drawablePixmap.initializePixmapDrawingOntoTexture.isInstanceOf[Texture])
  }
  test("when isPixmapMaskTransparent is invoked it should return true if the mask is transparent") {
    val pixmapTransparentMask: Pixmap = new Pixmap(400, 400, Format.RGBA8888)
    val drawablePixmap: DrawablePixmap = new DrawablePixmap(pixmapTransparentMask)
    assert(drawablePixmap.isPixmapMaskTransparent)
  }

  test("when isPixmapMaskTransparent is invoked it should return false if the mask is opaque") {
    val pixmapOpaqueMask: Pixmap = new Pixmap(400, 400, Format.RGB888)
    val drawablePixmap: DrawablePixmap = new DrawablePixmap(pixmapOpaqueMask)
    assert(!drawablePixmap.isPixmapMaskTransparent)
  }
}
