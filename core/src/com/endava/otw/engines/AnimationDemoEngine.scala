package com.endava.otw.engines

import com.badlogic.gdx.graphics.g2d.{TextureRegion, Animation, TextureAtlas}
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.{Gdx, Input, InputProcessor}
import com.endava.otw.model.Drawing

/**
  * Created by eilievska on 12/30/2015.
  */
class AnimationDemoEngine extends InputProcessor with Engine {

  /**
    * The starting static image for the game screen. On click of this image, the animation is started.
    */
  private val cowStartImg = new TextureRegion(new Texture(Gdx.files.internal("cow-start.png")))

  /**
    * Animation texture atlas.
    */
  private val cowAtlas = new TextureAtlas(Gdx.files.internal("demo.atlas"))

  /**
    * Animation... Add more docs here.
    */
  private val animation = new Animation(1 / 5f, cowAtlas.getRegions)

  /**
    * Cursor object, for when the mouse hovers the cow image.
    */
  private val clickCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0)

  /**
    * Current time.
    */
  private var timePassed = 0F

  /**
    * Flag indicating if the cursor is in the cow image range.
    */
  private var cursorInClickRange = false

  /**
    * Flag indicating if the cursor is in the cow image range.
    */
  private var mouseWasClicked = false

  override def keyTyped(character: Char): Boolean = false

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {
    val region: TextureRegion = animation.getKeyFrame(timePassed)
    val xRange = 111 to 383
    val yRange = 333 to 594

    if (xRange.contains(screenX) && yRange.contains(screenY)) {
      Gdx.graphics.setCursor(clickCursor)
      cursorInClickRange = true
    }
    else {
      Gdx.graphics.setCursor(null)
      cursorInClickRange = false
    }
    true
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    if (button == Input.Buttons.LEFT && cursorInClickRange) {
      mouseWasClicked = true
      Gdx.graphics.setCursor(null)
    }
    true
  }

  override def getDrawings(delta: Float): List[Drawing] = {
    if (mouseWasClicked) {
      timePassed = timePassed + delta
      val textureRegion = animation.getKeyFrame(timePassed, true)
      List(new Drawing(textureRegion, 0, 0))
    }
    else {
      timePassed = 0
      List(new Drawing(cowStartImg, 0, 0))
    }
  }

  override def keyDown(keycode: Int): Boolean = false

  override def keyUp(keycode: Int): Boolean = false

  override def scrolled(amount: Int): Boolean = false

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
}
