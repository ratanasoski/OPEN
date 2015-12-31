package com.endava.otw.engines

import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.{Gdx, Input, InputProcessor}
import com.endava.otw.model.{Apple, Drawing, Snake}
import com.endava.otw.util._

/**
  * Created by eilievska on 12/29/2015.
  */
class SnakeEngine extends InputProcessor with Engine {

  /**
    * Apple the snake is chasing.
    */
  private val appleTexture = new Texture(Gdx.files.internal("apple.png"))

  /**
    * A texture is a bitmap image that gets drawn on the screen through mapping.
    * A Texture wraps a standard OpenGL ES texture..
    */
  private val snakeTexture = new Texture(Gdx.files.internal("snakehead.png"))

  /**
    * Direction in which the snake is moving.
    */
  private var direction: Direction = RIGHT()

  /**
    * Time interval on which the snake moves.
    */
  private val MOVE_TIME_IN_SECONDS: Float = 0.6F

  /**
    * Current time.
    */
  private var timer = MOVE_TIME_IN_SECONDS

  /**
    * Snake data.
    */
  private var snake: Snake = new Snake(0, 0)

  /**
    * Apple data.
    */
  private var apple: Apple = new Apple(snake)

  override def getDrawings(delta: Float): List[Drawing] = {
    timer = timer - delta
    if (timer < 0) {
      timer = MOVE_TIME_IN_SECONDS
      snake = snake.moveSnake(direction)
      apple = apple.checkAppleIsEaten(snake)
      snake = snake.checkSnakeOutOfBound(direction)
      apple = apple.checkAppleIsEaten(snake)
    }
    List(new Drawing(new TextureRegion(snakeTexture), snake.x, snake.y), new Drawing(new TextureRegion(appleTexture), apple.x, apple.y))
  }

  override def keyDown(keycode: Int): Boolean = {
    val leftPressed = keycode == Input.Keys.LEFT
    val rightPressed = keycode == Input.Keys.RIGHT
    val upPressed = keycode == Input.Keys.UP
    val downPressed = keycode == Input.Keys.DOWN
    if (leftPressed) direction = LEFT()
    if (rightPressed) direction = RIGHT()
    if (upPressed) direction = UP()
    if (downPressed) direction = DOWN()
    true
  }

  override def keyTyped(character: Char): Boolean = false

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = false

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def keyUp(keycode: Int): Boolean = false

  override def scrolled(amount: Int): Boolean = false

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = false

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = false
}

object SnakeEngine {

  def apply: SnakeEngine = new SnakeEngine

}