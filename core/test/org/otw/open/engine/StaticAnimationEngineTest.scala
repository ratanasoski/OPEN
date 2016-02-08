package org.otw.open.engine

import com.badlogic.gdx.math.{Vector2, Vector3}
import org.otw.open.dto.Drawing
import org.otw.open.engine.impl.{CauseAndEffectEngine, EraserGameEngine, StaticAnimationEngine}
import org.otw.open.testconfig.UnitSpec
import org.otw.open.{GameScreen, OpenGame}
import org.scalatest.BeforeAndAfterEach

/**
  * Created by jivanovski on 1/28/2016.
  */
class StaticAnimationEngineTest extends UnitSpec with BeforeAndAfterEach {

  var staticAnimationEngine: StaticAnimationEngine = _

  override def beforeEach(): Unit = {
    staticAnimationEngine = new StaticAnimationEngine("test.atlas")
    staticAnimationEngine.setMouseClickPositionTransformator(
      new Function[Vector3, Vector2] {
        override def apply(vector: Vector3): Vector2 = {
          new Vector2(vector.x, vector.y)
        }
      })
  }

  override def afterEach(): Unit = {
    staticAnimationEngine.dispose()
  }

  test("when getDrawings is invoked with an empty list") {
    val drawings: List[Drawing] = staticAnimationEngine.getDrawings(0)
    assert(drawings.size == 6)
  }

  test("when getDrawings is invoked with one drawing") {
    val drawings: List[Drawing] = staticAnimationEngine.getDrawings(0.4f)
    assert(drawings.size == 6)
  }

  test("when objectIsClicked is invoked with x and y return false") {
    assert(!staticAnimationEngine.objectIsClicked(0, 0))
  }

  test("when objectIsClicked is invoked with x and y for ToMainMenu") {
    staticAnimationEngine.objectIsClicked(20, 260)
    assert(getCurrentGameScreen.engine.isInstanceOf[EraserGameEngine])
  }

  test("when objectIsClicked is invoked with x and y for RetryLevel") {
    staticAnimationEngine.objectIsClicked(150, 260)
    assert(getCurrentGameScreen.engine.isInstanceOf[EraserGameEngine])
  }

  test("when objectIsClicked is invoked with x and y for NextLevel") {
    staticAnimationEngine.objectIsClicked(380, 260)
    assert(getCurrentGameScreen.engine.isInstanceOf[CauseAndEffectEngine])
  }

  test("when objectIsClicked is invoked with x and y for OtherTheme") {
    staticAnimationEngine.objectIsClicked(600, 260)
    assert(getCurrentGameScreen.engine.isInstanceOf[CauseAndEffectEngine])
  }

  /**
    *
    * @return gets the current screen of our singleton game instance.
    */
  private def getCurrentGameScreen: GameScreen = {
    val screen: GameScreen = OpenGame.getGame.getScreen match {
      case gameScreen: GameScreen => gameScreen
      case _ => throw new scala.ClassCastException
    }
    screen
  }

}
