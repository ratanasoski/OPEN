package org.otw.open.controllers

import com.badlogic.gdx.Screen
import org.otw.open.{GameScreen, OpenGame}
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.{StaticAnimationEngine, CauseAndEffectEngine}
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 1/28/2016.
  */
class ScreenControllerTest extends UnitSpec {

  test("given EraserGameFinished event is dispatched, the returned game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(EraserGameFinished)
    assert(returnedScreenEngine.isInstanceOf[CauseAndEffectEngine])
  }

  test("given EraserGameFinished event is dispatched,the current game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(EraserGameFinished)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(currentGameScreen.engine == returnedScreenEngine)
  }

  test("given CauseAndEffectFinishedSuccessfully event is dispatched, the returned game screen engine is StaticAnimationEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(CauseAndEffectFinishedSuccessfully)
    assert(returnedScreenEngine.isInstanceOf[StaticAnimationEngine])
  }

  test("given CauseAndEffectFinishedSuccessfully event is dispatched, the current game screen engine is StaticAnimationEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(CauseAndEffectFinishedSuccessfully)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(returnedScreenEngine == currentGameScreen.engine)
  }

  test("given CauseAndEffectFinishedUnsuccessfully event is dispatched, the returned game screen engine is StaticAnimationEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(CauseAndEffectFinishedUnsuccessfully)
    assert(returnedScreenEngine.isInstanceOf[StaticAnimationEngine])
  }

  test("given CauseAndEffectFinishedUnsuccessfully event is dispatched, the current game screen engine is StaticAnimationEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(CauseAndEffectFinishedUnsuccessfully)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(returnedScreenEngine == currentGameScreen.engine)
  }


  test("given CauseAndEffectSecondLevel event is dispatched, the current game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(CauseAndEffectSecondLevel)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(returnedScreenEngine == currentGameScreen.engine)
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
