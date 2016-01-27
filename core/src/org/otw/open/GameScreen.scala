package org.otw.open

import com.badlogic.gdx.graphics.{OrthographicCamera, Color, GL20}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.{Gdx, ScreenAdapter}
import org.otw.open.engine.Engine

/**
  * Created by eilievska on 1/13/2016.
  */
class GameScreen(val engine: Engine) extends ScreenAdapter {
  private val batch: SpriteBatch = new SpriteBatch

  private val camera: OrthographicCamera = new OrthographicCamera
  camera.setToOrtho(true, 1440, 900)

  override def render(delta: Float) = {
    camera.update
    Gdx.gl20.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin
    engine.getDrawings(delta).foreach(drawing => batch.draw(drawing.image, drawing.x, drawing.y))
    batch.end
  }

  override def dispose(): Unit = {
    batch.dispose()
    super.dispose()
  }

  val transformator = new Function[Vector3, Vector2] {
    override def apply(vector: Vector3): Vector2 = {
      camera.unproject(vector)
      new Vector2(vector.x, vector.y)
    }
  }

  engine.setMouseClickPositionTransformator(transformator)
}
