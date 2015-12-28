package com.endava.otw

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, Texture}
import com.badlogic.gdx.{Game, Gdx}

class ScalaDemoGame extends Game {

  /**
    * Overriding the crete method of the game. Here we are just setting the screen, to be our own defined GameScreen.
    */
  override def create() {
    setScreen(new GameScreen)
  }
}