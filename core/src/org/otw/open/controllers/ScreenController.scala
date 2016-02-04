package org.otw.open.controllers

import com.badlogic.gdx.math.Vector2
import org.otw.open.dto.StandPoint
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.{CauseAndEffectEngine, StaticAnimationEngine}
import org.otw.open.{GameScreen, OpenGame}

/**
  * Screen controller.
  * Created by eilievska on 1/28/2016.
  */
object ScreenController {

  /**
    * Switches the current game screen based on the Event type received.
    *
    * @param event - event received form engines.
    * @return the newly set screen engine.
    */
  def dispatchEvent(event: Event): Engine = {
    val engine = event match {
      case EraserGameFinished => new CauseAndEffectEngine(
        List(
          new StandPoint(101 until 337, 299 until 402, new Vector2(0, 320)),
          new StandPoint(101 until 337, 299 until 402, new Vector2(990, 320))
        )
      )
      case CauseAndEffectFinishedSuccessfully => new StaticAnimationEngine("happy-animation.atlas")
      case CauseAndEffectFinishedUnsuccessfully => new StaticAnimationEngine("unhappy-animation.atlas")
      case CauseAndEffectSecondLevel => new CauseAndEffectEngine(
        List(
          new StandPoint(101 until 337, 299 until 402, new Vector2(0, 320)),
          new StandPoint(330 until 665, 299 until 402, new Vector2(330, 320)),
          new StandPoint(660 until 995, 299 until 402, new Vector2(660, 320)),
          new StandPoint(1000 until 1100, 299 until 402, new Vector2(990, 320))
        )
      )
    }
    OpenGame.changeScreen(new GameScreen(engine))
    engine
  }

}
