package org.otw.open

import com.badlogic.gdx.Game
import com.badlogic.gdx.math.Vector2
import org.otw.open.engine.impl.CauseAndEffectEngine

/**
  * Created by eilievska on 1/13/2016.
  */
class OpenGame extends Game {
  val standPoints: List[Vector2] =List( (new Vector2(0,500)), (new Vector2(800,500)))

  override def create(): Unit = setScreen(new GameScreen(new CauseAndEffectEngine(25 until 580, 85 until 377, standPoints )))
}
