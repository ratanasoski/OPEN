package org.otw.open.controllers

import com.badlogic.gdx.math.Vector2
import org.otw.open.dto.StandPoint
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.{EraserGameEngine, CauseAndEffectEngine, StaticAnimationEngine}
import org.otw.open.{GameScreen, OpenGame}

/**
  * Screen controller.
  * Created by eilievska on 1/28/2016.
  */
object ScreenController {

  val r = scala.util.Random

  var themeKey: Int = 0

  val themes: Map[Int, String] = Map(0 -> "car_theme", 1 -> "rabbit_theme")

  var currentLevel: Int = 1

  val maxLevel: Int = 3

  /**
    * Switches the current game screen based on the Event type received.
    *
    * @param event - event received form engines.
    * @return the newly set screen engine.
    */
  def dispatchEvent(event: Event): Engine = {
    val engine = event match {
      case EraserGameFinished => new StaticAnimationEngine("happy-animation.atlas")
      case CauseAndEffectFinishedSuccessfully => new StaticAnimationEngine("happy-animation.atlas")
      case CauseAndEffectFinishedUnsuccessfully => new StaticAnimationEngine("unhappy-animation.atlas")
      case RetryLevel => initializeEngine(currentLevel)
      case NextLevel => if(currentLevel < maxLevel) currentLevel += 1; initializeEngine(currentLevel)
      case OtherTheme => themeKey = generateRandomThemeKey; initializeEngine(currentLevel)
      case ToMainMenu => currentLevel = 1; new EraserGameEngine
    }
    OpenGame.changeScreen(new GameScreen(engine))
    engine
  }

  def initializeEngine(providedLevel: Int): Engine = {
    val engine = providedLevel match {
      case 1 => new EraserGameEngine
      case 2 => new CauseAndEffectEngine(
        List(
          new StandPoint(0 until 344, 299 until 402, new Vector2(0, 320)),
          new StandPoint(101 until 337, 299 until 402, new Vector2(990, 320))
        )
      )
      case 3 => new CauseAndEffectEngine(
        List(
          new StandPoint(0 until 337, 299 until 402, new Vector2(0, 320)),
          new StandPoint(330 until 665, 299 until 402, new Vector2(330, 320)),
          new StandPoint(660 until 995, 299 until 402, new Vector2(660, 320)),
          new StandPoint(1000 until 1100, 299 until 402, new Vector2(990, 320))
        )
      )
      case _ => currentLevel = 1; new EraserGameEngine
    }
    engine
  }

  def generateRandomThemeKey: Int = {
    val nextThemeKey = r.nextInt(themes.size)
    if(themeKey != nextThemeKey) nextThemeKey
    else generateRandomThemeKey
  }

}
