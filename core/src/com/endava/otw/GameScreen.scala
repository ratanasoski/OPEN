package com.endava.otw

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{Color, GL20, Texture}
import com.badlogic.gdx.{Input, Gdx, ScreenAdapter}
import com.endava.otw.util._

import scala.util.Random

/**
  * A class that will implement the logic of the game screen. It extends the ScreenAdapter class, which itself
  * implements the Screen interface.
  * Created by eilievska on 12/22/2015.
  */
class GameScreen extends ScreenAdapter {

  /**
    * A Batch is used to draw 2D rectangles that reference a texture (region).<br/>
    *
    * To draw something with a Batch one has to first call the begin() method which will setup appropriate render
    * states. When you are done with drawing you have to call end() which will actually draw the things you specified.
    *
    * All drawing commands of the Batch operate in screen coordinates.
    */
  private val batch: SpriteBatch = new SpriteBatch

  /**
    * A texture is a bitmap image that gets drawn on the screen through mapping.
    * A Texture wraps a standard OpenGL ES texture..
    */
  private val snakeHead = new Texture(Gdx.files.internal("snakehead.png"))

  /**
    * Apple the snake is chasing.
    */
  private val apple = new Texture(Gdx.files.internal("apple.png"))

  /**
    * Time interval on which the snake moves.
    */
  private val MOVE_TIME_IN_SECONDS: Float = 0.6F

  /**
    * Snake movement unit for the X and Y axis.
    */
  private val MOVEMENT_DELTA = 32

  /**
    * Current (x, y) coordinates of the smake.
    */
  private var snakePosition: (Int, Int) = (0, 0)

  /**
    * Current (x, y) coordinates of the apple.
    */
  private var applePosition: (Int, Int) = generateRandomApplePosition

  /**
    * Current time.
    */
  private var timer = MOVE_TIME_IN_SECONDS

  /**
    * The direction in which the snake is moving.
    */
  private var direction: Direction = RIGHT()

  def moveTimeHasPassed: Boolean = timer < 0

  override def render(delta: Float): Unit = {
    queryKeyboardInput
    timer = timer - delta
    if (moveTimeHasPassed) {
      timer = MOVE_TIME_IN_SECONDS
      moveSnake
      checkAppleEaten
      checkSnakeOutOfBound
      checkAppleEaten
    }
    redrawBatch
  }

  def redrawBatch: Unit = {
    Gdx.gl20.glClearColor(Color.BLACK.r, Color.BLACK.g, Color.BLACK.b, Color.BLACK.a);
    Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT)
    batch.begin()
    batch.draw(snakeHead, snakePosition._1, snakePosition._2)
    batch.draw(apple, applePosition._1, applePosition._2)
    batch.end()
  }

  private def checkAppleEaten = if (snakePosition == applePosition) applePosition = generateRandomApplePosition

  private def moveSnake = {
    direction match {
      case _: RIGHT => snakePosition = (snakePosition._1 + MOVEMENT_DELTA, snakePosition._2)
      case _: LEFT => snakePosition = (snakePosition._1 - MOVEMENT_DELTA, snakePosition._2)
      case _: UP => snakePosition = (snakePosition._1, snakePosition._2 + MOVEMENT_DELTA)
      case _: DOWN => snakePosition = (snakePosition._1, snakePosition._2 - MOVEMENT_DELTA)
    }
  }

  private def checkSnakeOutOfBound = {
    val maxHeight = Gdx.graphics.getHeight - MOVEMENT_DELTA
    val maxWidth = Gdx.graphics.getWidth - MOVEMENT_DELTA
    direction match {
      case _: RIGHT => if (snakePosition._1 > Gdx.graphics.getWidth) snakePosition = (0, snakePosition._2)
      case _: LEFT => if (snakePosition._1 < 0) snakePosition = (maxWidth, snakePosition._2)
      case _: UP => if (snakePosition._2 > maxHeight) snakePosition = (snakePosition._1, 0)
      case _: DOWN => if (snakePosition._2 < 0) snakePosition = (snakePosition._1, maxHeight)
    }
  }

  private def queryKeyboardInput = {
    val leftPressed = Gdx.input.isKeyPressed(Input.Keys.LEFT)
    val rightPressed = Gdx.input.isKeyPressed(Input.Keys.RIGHT)
    val upPressed = Gdx.input.isKeyPressed(Input.Keys.UP)
    val downPressed = Gdx.input.isKeyPressed(Input.Keys.DOWN)
    if (leftPressed) direction = LEFT()
    if (rightPressed) direction = RIGHT()
    if (upPressed) direction = UP()
    if (downPressed) direction = DOWN()
  }

  private def generateRandomApplePosition(): (Int, Int) = {
    val x: Int = Random.nextInt((Gdx.graphics.getWidth - 1) / MOVEMENT_DELTA)
    val y: Int = Random.nextInt((Gdx.graphics.getHeight - 1) / MOVEMENT_DELTA)
    val coordinates = (x * MOVEMENT_DELTA, y * MOVEMENT_DELTA)
    if (coordinates == snakePosition)
      generateRandomApplePosition()
    coordinates
  }

  // TODO: Always implement dispose!
  //override def dispose(): Unit = super.dispose()
}