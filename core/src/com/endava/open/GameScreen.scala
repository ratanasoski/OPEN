package com.endava.open

import com.badlogic.gdx.{Gdx, ScreenAdapter}
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.graphics.g2d.SpriteBatch

/**
  * Created by eilievska on 1/13/2016.
  */
class GameScreen extends ScreenAdapter{

  val batch: SpriteBatch = new SpriteBatch
  val img: Texture = new Texture("badlogic.jpg")

  override def render(delta: Float) = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin
    batch.draw(img, 0, 0)
    batch.end
  }
}
