package org.otw.open.controllers

import com.badlogic.gdx.math.Vector2
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
    * @param event - event received form engines.
    * @return the newly set screen engine.
    */
  def dispatchEvent(event: Event): Engine = {
    val engine = event match {
      case EraserGameFinished => new CauseAndEffectEngine(101 until 337, 299 until 402, List(new Vector2(0, 320), new Vector2(850, 320)))
      case CauseAndEffectFinishedSuccessfully => new StaticAnimationEngine("happy-animation.atlas")
      case CauseAndEffectFinishedUnsuccessfully => new StaticAnimationEngine("happy-animation.atlas")
    }
    OpenGame.changeScreen(new GameScreen(engine))
    engine
  }

}
