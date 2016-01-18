package com.endava.open

import com.badlogic.gdx.Game

/**
  * Created by eilievska on 1/13/2016.
  */
class OpenGame extends Game {
  override def create(): Unit = setScreen(new GameScreen)
}
