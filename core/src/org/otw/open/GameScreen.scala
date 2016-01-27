package org.otw.open

import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{Gdx, ScreenAdapter}
import org.otw.open.engine.Engine

/**
  * Created by eilievska on 1/13/2016.
  */
class GameScreen(val engine: Engine) extends ScreenAdapter {

  private val batch: SpriteBatch = new SpriteBatch

  override def render(delta: Float) = {
    Gdx.gl.glClearColor(0, 1, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin
    engine.getDrawings(delta).foreach(drawing => batch.draw(drawing.image, drawing.x, drawing.y))
    batch.end
  }

  override def dispose() = {
    batch.dispose()
    engine.dispose()
  }
}
