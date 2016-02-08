package org.otw.open.controllers

import com.badlogic.gdx.Screen
import org.otw.open.{GameScreen, OpenGame}
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.{EraserGameEngine, StaticAnimationEngine, CauseAndEffectEngine}
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 1/28/2016.
  */
class ScreenControllerTest extends UnitSpec {

  test("given EraserGameFinished event is dispatched, the returned game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(EraserGameFinished)
    assert(returnedScreenEngine.isInstanceOf[StaticAnimationEngine])
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

  test("given RetryLevel event is dispatched, the current game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(RetryLevel)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(returnedScreenEngine == currentGameScreen.engine)
  }

  test("given NextLevel event is dispatched, the current game screen engine is EraserGameEngine") {
    val currentLevel = ScreenController.currentLevel
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(NextLevel)
    assert(returnedScreenEngine.isInstanceOf[CauseAndEffectEngine])
    assert(ScreenController.currentLevel == (currentLevel + 1))
  }

  test("given OtherTheme event is dispatched, the current game screen engine is EraserGameEngine") {
    val currentThemeKey: Int = ScreenController.themeKey
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(OtherTheme)
    val currentGameScreen: GameScreen = getCurrentGameScreen
    assert(returnedScreenEngine == currentGameScreen.engine)
    assert(ScreenController.themeKey != currentThemeKey)
  }

  test("given ToMainMenu event is dispatched, the current game screen engine is EraserGameEngine") {
    ScreenController.dispatchEvent(NextLevel)
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(ToMainMenu)
    assert(returnedScreenEngine.isInstanceOf[EraserGameEngine])
    assert(ScreenController.currentLevel == 1)
  }

  test("given generateRandomThemeKey function is called, the current themeKey is 0") {
    val returnedScreenEngine: Engine = ScreenController.initializeEngine(4)
    assert(returnedScreenEngine.isInstanceOf[EraserGameEngine])
    assert(ScreenController.currentLevel == 1)
  }

  test("given initializeEngine function is called, the providedLevel is 10") {
    val returnedThemeKey: Int = ScreenController.generateRandomThemeKey
    assert(returnedThemeKey != ScreenController.themeKey)
  }


  test("given CauseAndEffectSecondLevel event is dispatched, the current game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(NextLevel)
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
