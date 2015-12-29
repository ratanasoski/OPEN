package com.endava.otw.model

import com.badlogic.gdx.Gdx
import com.endava.otw.util._

/**
  * Created by eilievska on 12/29/2015.
  */
class Snake(val x: Int, val y: Int) {

  val MOVEMENT_DELTA = 32

  def moveSnake(direction: Direction): Snake = {
    direction match {
      case _: RIGHT => new Snake(x + MOVEMENT_DELTA, y)
      case _: LEFT => new Snake(x - MOVEMENT_DELTA, y)
      case _: UP => new Snake(x, y + MOVEMENT_DELTA)
      case _: DOWN => new Snake(x, y - MOVEMENT_DELTA)
    }
  }

  def checkSnakeOutOfBound(direction: Direction): Snake = {
    val maxHeight = Gdx.graphics.getHeight - MOVEMENT_DELTA
    val maxWidth = Gdx.graphics.getWidth - MOVEMENT_DELTA
    direction match {
      case _: RIGHT => if (x > Gdx.graphics.getWidth) new Snake(0, y) else this
      case _: LEFT => if (x < 0) new Snake(maxWidth, y) else this
      case _: UP => if (y > maxHeight) new Snake(x, 0) else this
      case _: DOWN => if (y < 0) new Snake(x, maxHeight) else this
    }
  }

}