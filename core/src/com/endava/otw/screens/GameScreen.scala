package com.endava.otw.screens

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{Color, GL20}
import com.badlogic.gdx.{Gdx, ScreenAdapter}
import com.endava.otw.engines.{SnakeEngine, AnimationDemoEngine}

/**
  * A class that will implement the logic of the game screen. It extends the ScreenAdapter class, which itself
  * implements the Screen interface.
  * Created by eilievska on 12/22/2015.
  */
class GameScreen extends ScreenAdapter {

  /**
    * A Batch is used to draw 2D rectangles that reference a texture (region).<br/>
    *
    * To draw something with a Batch one has to first call the begin() method which will setup appropriate render
    * states. When you are done with drawing you have to call end() which will actually draw the things you specified.
    *
    * All drawing commands of the Batch operate in screen coordinates.
    */
  private val batch: SpriteBatch = new SpriteBatch

  private val engine: AnimationDemoEngine = new AnimationDemoEngine

  Gdx.input.setInputProcessor(engine)

  override def render(delta: Float): Unit = {
    Gdx.gl20.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    drawBatch(delta)
    batch.end()
  }

  def drawBatch(delta: Float) = {
    val drawings = engine.getDrawings(delta)
    drawings.foreach(drawing => batch.draw(drawing.image, drawing.x, drawing.y))
  }

  // Always implement dispose!
  override def dispose(): Unit = {
    batch.dispose()
    super.dispose()
  }
}