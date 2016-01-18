package org.otw.open

import com.badlogic.gdx.Game
import org.otw.open.engine.impl.FirstGameScreenEngine

/**
  * Created by eilievska on 1/13/2016.
  */
class OpenGame extends Game {
  override def create(): Unit = setScreen(new GameScreen(new FirstGameScreenEngine))
}
