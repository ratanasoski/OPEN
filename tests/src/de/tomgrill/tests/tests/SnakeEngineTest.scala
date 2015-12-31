package de.tomgrill.tests.tests

import com.badlogic.gdx.{Gdx, Input}
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.endava.otw.engines.SnakeEngine
import com.endava.otw.model.Drawing
import de.tomgrill.tests.config.UnitSpec

/**
  * Created by eilievska on 12/30/2015.
  */
class SnakeEngineTest extends UnitSpec {

  val snakeEngine: SnakeEngine = SnakeEngine.apply

  test("when getDrawings is invoked with 0 delta time, the snake's coordinates should be the initial ones (0,0).") {
    val drawings: List[Drawing] = snakeEngine.getDrawings(0)
    assert(drawings.size == 2)
    assert(drawings.head.x == 0)
    assert(drawings.head.y == 0)
  }

  test("when getDrawings is invoked with delta > 0.6 the snake should move") {
    val drawings: List[Drawing] = snakeEngine.getDrawings(1.5F)
    assert(drawings.size == 2)
    assert(drawings.head.x != 0)
    assert(drawings.head.y == 0)
  }

  test("when a keybord button is pressed, it should return true.") {
    assert(snakeEngine.keyDown(Input.Keys.DOWN) == true)
  }

  test("when mouseMoved is invoked, it should return false.") {
    assert(snakeEngine.mouseMoved(0, 0) == false)
  }

}
