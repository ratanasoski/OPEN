package com.endava.otw

import com.badlogic.gdx.graphics.{Pixmap, Texture, GL20}
import com.badlogic.gdx.graphics.g2d.{TextureAtlas, Animation, SpriteBatch, Batch}
import com.badlogic.gdx.{InputProcessor, Input, Gdx, ScreenAdapter}

/**
  * Created by eilievska on 12/27/2015.
  */
class AnimationDemo extends ScreenAdapter with InputProcessor {

  private val batch = new SpriteBatch()

  private val cowStartImg = new Texture(Gdx.files.internal("cow-start.png"))

  private val cowAtlas = new TextureAtlas(Gdx.files.internal("demo.atlas"))

  private val animation = new Animation(1 / 5f, cowAtlas.getRegions)

  private val clickCursor = Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0)

  private var timePassed = 0F

  private var cursorInClickRange = false

  private var mouseWasClicked = false

  Gdx.input.setInputProcessor(this)

  override def dispose(): Unit = {
    batch.dispose()
    cowAtlas.dispose()
  }

  override def mouseMoved(screenX: Int, screenY: Int) = {
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

  override def render(delta: Float): Unit = {
    Gdx.gl20.glClearColor(0, 0, 0, 0)
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)

    batch.begin()
    if (mouseWasClicked) {
      timePassed = timePassed + delta
      runAnimation
    }
    else {
      timePassed = 0
      batch.draw(cowStartImg, 0, 0)
    }
    batch.end()

  }


  def runAnimation: Unit = {
    batch.draw(animation.getKeyFrame(timePassed, true), 0, 0)
  }

  override def keyTyped(character: Char): Boolean = false

  override def keyDown(keycode: Int): Boolean = false

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    if (button == Input.Buttons.LEFT && cursorInClickRange) {
      mouseWasClicked = true
      Gdx.graphics.setCursor(null)
    }
    true
  }

  override def keyUp(keycode: Int): Boolean = false

  override def scrolled(amount: Int): Boolean = false

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
}
