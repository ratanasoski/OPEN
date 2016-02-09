package org.otw.open

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.graphics.{Pixmap, Texture, GL20, Color}
import com.badlogic.gdx.math.{Vector2, Vector3}
import org.mockito.Mockito.{mock, verify, when, times}
import org.otw.open.dto.Drawing
import org.otw.open.engine.Engine
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 2/1/2016.
  */
class GameScreenTest extends UnitSpec {

  test("when render is invoked, the screen should be cleaned and color should be set") {
    val delta = 35f
    val mockEngine = mock(classOf[Engine])
    val gameScreen = new GameScreen(mockEngine)
    when(mockEngine.getDrawings(delta)).thenReturn(List.empty)
    gameScreen.render(delta)
    verify(mockEngine).getDrawings(delta)
    verify(Gdx.gl).glClear(GL20.GL_COLOR_BUFFER_BIT)
    verify(Gdx.gl).glClearColor(Color.WHITE.r, Color.WHITE.g, Color.WHITE.b, Color.WHITE.a)
  }

  test("when render is invoked, a list of drawings should be drawn on the screen") {
    val delta = 35f
    val drawing1 = mock(classOf[Drawing])
    val textureRegion: TextureRegion = new TextureRegion(new Texture(new Pixmap(20, 20, Pixmap.Format.RGBA4444)))
    when(drawing1.image).thenReturn(textureRegion)
    when(drawing1.x).thenReturn(0)
    when(drawing1.y).thenReturn(0)
    val mockEngine = new Engine {
      override def getDrawings(delta: Float): List[Drawing] = List(drawing1)

      override def dispose(): Unit = {}
    }
    val gameScreen = new GameScreen(mockEngine)
    gameScreen.render(delta)
    verify(drawing1, times(1)).image
    verify(drawing1, times(1)).x
    verify(drawing1, times(1)).y

  }

  test("when the transformator function is invoked without resize, it should return the same coordinates") {
    val mockEngine = mock(classOf[Engine])
    val gameScreen = new GameScreen(mockEngine)
    val transformator: (Vector2) => Vector2 = gameScreen.transformator
    val vector = new Vector2(12, 12)
    val returnedCoordinates: Vector2 = transformator(vector)
    assert(returnedCoordinates.x.toInt == vector.x.toInt && (11 until 13).contains(returnedCoordinates.y.toInt))
  }

  test("when the game screen is disposed, the engine despose method should be disposed as well") {
    val mockEngine = mock(classOf[Engine])
    val gameScreen = new GameScreen(mockEngine)
    gameScreen.dispose()
    verify(mockEngine).dispose()
  }

}
