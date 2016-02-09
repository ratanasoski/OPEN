package org.otw.open.dto

import com.badlogic.gdx.math.Vector2

/**
  *
  * @param clickXRange the x range of object
  * @param clickYRange they range of object
  * @param standPointCoordinates moving object's standpoint
  */
case class StandPoint(val clickXRange: Range, val clickYRange: Range, standPointCoordinates: Vector2) {

}
