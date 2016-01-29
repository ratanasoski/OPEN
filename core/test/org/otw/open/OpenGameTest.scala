package org.otw.open

import com.badlogic.gdx.{Game, Screen}
import org.mockito.Mockito.mock
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.EraserGameEngine
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 1/25/2016.
  */
class OpenGameTest extends UnitSpec {

  test("when a call to the OpenGame.getGame is made, it should return an instance of libgdx Game class") {
    val game: OpenGame = OpenGame.getGame
    assert(game.isInstanceOf[Game])
  }

  test("when multiple calls to OpenGame.getGame are made, it should always return the same isntance") {
    val game1: OpenGame = OpenGame.getGame
    val game2: OpenGame = OpenGame.getGame
    assert(game1.eq(game2))
  }

  test("when OpenGame.changeScreen is invoked, the game screen should be changed") {
    val game: OpenGame = OpenGame.getGame
    val mockScreen = mock(classOf[Screen])
    val setScreen: Screen = OpenGame.changeScreen(mockScreen)
    assert(game.getScreen == mockScreen)
    assert(setScreen == mockScreen)
  }

  test("when create() method of the OpenGame class instance is invoked, " +
    "a new screen should be created with the EraserGameEngine as engine") {
    val game: OpenGame = OpenGame.getGame
    game.create()
    val returnedScreenEngine: Engine = game.getScreen match {
      case s: GameScreen => s.engine
      case _ => throw new scala.ClassCastException
    }
    assert(returnedScreenEngine.isInstanceOf[EraserGameEngine])
  }

}
