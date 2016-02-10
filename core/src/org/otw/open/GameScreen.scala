package org.otw.open

import com.badlogic.gdx.graphics.{OrthographicCamera, Color, GL20}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.{Gdx, ScreenAdapter}
import org.otw.open.engine.Engine

/**
  * Created by eilievska on 1/13/2016.
  */
class GameScreen(val engine: Engine) extends ScreenAdapter {
  private val batch: SpriteBatch = new SpriteBatch

  private val camera: OrthographicCamera = new OrthographicCamera
  camera.setToOrtho(false, 1440, 900)

  override def render(delta: Float) = {
    camera.update
    Gdx.gl.glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.setProjectionMatrix(camera.combined)
    batch.begin
    engine.getDrawings(delta).foreach(drawing => batch.draw(drawing.image, drawing.x, drawing.y))
    batch.end
    engine.getStage(delta) match {
      case Some(stage) => {
        stage.act(delta)
        Gdx.gl.glClearColor(0f, 0f, 0f, 1f)
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT )
        stage.draw
      }
      case None => {}
    }
  }

  override def dispose(): Unit = {
    batch.dispose()
    engine.dispose()
  }

  val transformator: (Vector2 => Vector2) = new Function[Vector2, Vector2] {
    override def apply(vector: Vector2): Vector2 = {
      val vector3: Vector3 = new Vector3(vector.x, Gdx.graphics.getHeight - vector.y, 0)
      camera.unproject(vector3)
      new Vector2(vector3.x, vector3.y)
    }
  }

  engine.setMouseClickPositionTransformator(transformator)
}
