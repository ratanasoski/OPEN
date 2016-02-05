package org.otw.open.engine.dto

import org.otw.open.dto.HorizontalMovingObject
import org.otw.open.testconfig.UnitSpec

/**
  * Created by smirakovska on 2/5/2016.
  *
  * Unit tests for HorizontalMovingObject
  */
class HorizontalMovingObjectTest extends UnitSpec {

  val horizontalMovingObject:HorizontalMovingObject = new HorizontalMovingObject(0,0,10)

  test("when moveObject is invoked, it should return new object with updated x coordinate "){
    val newObject = horizontalMovingObject.moveObject()
    assert(newObject.x > horizontalMovingObject.x)
  }
}
