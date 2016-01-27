package org.otw.open.dto

/**
  * The HorizontalMovingObject model
  * x the x coordinate of the object
  * y the y coordinate of the object
  * movementDelta the step by which the object will be moving on the screen
  */
case class HorizontalMovingObject(x: Int, y: Int, movementDelta:Int) {

  /**
    * @return new HorizontalMovingObject with new x coordinate
    */
  def moveObject(): HorizontalMovingObject = {
    new HorizontalMovingObject(x + movementDelta, y, movementDelta)
  }
}

