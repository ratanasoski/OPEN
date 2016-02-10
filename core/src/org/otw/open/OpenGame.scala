package org.otw.open

import com.badlogic.gdx.{Game, Screen}
import org.otw.open.engine.impl.{MainMenuEngine, EraserGameEngine}

/**
  * Created by eilievska on 1/13/2016.
  */
class OpenGame private() extends Game {

  override def create(): Unit = setScreen(new GameScreen(new MainMenuEngine(0)))
}

/**
  * A singleton object containing an instance of our game.
  */
object OpenGame {
  private lazy val game = new OpenGame

  /**
    *
    * @return our Game instance.
    */
  def getGame: OpenGame = game

  /**
    * Sets a new screen to our game.
    *
    * @param newScreen -  a new screen for our game.
    * @return the current (newly added) screen in the game.
    */
  def changeScreen(newScreen: Screen): Screen = {
    game.setScreen(newScreen)
    game.getScreen
  }

}
