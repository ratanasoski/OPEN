package org.otw.open.dto

import com.badlogic.gdx.math.Vector2

/**
  *
  * @param xRange the x range of object
  * @param yRange they range of object
  * @param coordinates moving object's standpoint
  */
case class StandPoint(val xRange: Range, val yRange: Range, coordinates: Vector2) {

}
