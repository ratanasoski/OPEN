package com.endava.otw.model

import com.badlogic.gdx.Gdx

import scala.util.Random

/**
  * Created by eilievska on 12/29/2015.
  */
class Apple(snake: Snake) {

  val MOVEMENT_DELTA = 32

  val (x, y) = generateRandomApplePosition()

  private def generateRandomApplePosition(): (Int, Int) = {
    val x: Int = Random.nextInt((Gdx.graphics.getWidth - 1) / MOVEMENT_DELTA)
    val y: Int = Random.nextInt((Gdx.graphics.getHeight - 1) / MOVEMENT_DELTA)
    val coordinates = (x * MOVEMENT_DELTA, y * MOVEMENT_DELTA)
    if (coordinates ==(snake.x, snake.y))
      generateRandomApplePosition()
    coordinates
  }

  def checkAppleIsEaten(snake: Snake): Apple = {
    if ((x, y) ==(snake.x, snake.y)) {
      new Apple(snake)
    } else
      this
  }


}
