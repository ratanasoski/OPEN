package org.otw.open.engine.impl

import com.badlogic.gdx.math.{Vector2, Vector3}
import org.junit.Assert
import org.otw.open.dto.HorizontalMovingObject
import org.otw.open.testconfig.UnitSpec

/**
  * Created by smirakovska on 1/26/2016.
  */
class CauseAndEffectEngineTest extends UnitSpec {

  val xRange: Range = 25 until 580
  val yRange: Range = 85 until 377
  val standPoints: List[Vector2] = List(new Vector2(0, 0), new Vector2(500, 0))
  val movementDelta: Int = 20

  val causeAndEffectEngine: CauseAndEffectEngine = new CauseAndEffectEngine(xRange, yRange, standPoints)

  val engine = CauseAndEffectEngine.apply(xRange, yRange, standPoints)

  val transformator = new Function[Vector3, Vector2] {
    override def apply(vector: Vector3): Vector2 = {
      new Vector2(vector.x, vector.y)
    }
  }

  test("when objectShouldStopAnimating is invoked it should return false for object not at the end point") {
    val testObject = new HorizontalMovingObject(0, 0, movementDelta)
    Assert.assertFalse(causeAndEffectEngine.objectShouldStopAnimating(testObject.x, testObject.y))
  }

  test("when objectShouldStopAnimating is invoked it should return true for object at the end point") {
    val testObject = new HorizontalMovingObject(800, 500, movementDelta)
    Assert.assertTrue(causeAndEffectEngine.objectShouldStopAnimating(testObject.x, testObject.y))
  }

  test("when CauseAndEffectEngine object's apply method is invoked new CauseAndEffectEngine instance should be returned") {
    val returnedObject = CauseAndEffectEngine.apply(xRange, yRange, standPoints)
    assert(returnedObject.isInstanceOf[CauseAndEffectEngine])
  }

  test("when objectIsClicked is invoked should return false if object is not clicked") {
    engine.setMouseClickPositionTransformator(transformator)
    assert(engine.objectIsClicked(50, 60) == false)
  }


  test("when objectIsClicked is invoked should return true if object is clicked") {
    engine.setMouseClickPositionTransformator(transformator)
    assert(engine.objectIsClicked(90, 90) == true)
  }

  test("when mouse is clicked, should return true if object was clicked") {
    engine.setMouseClickPositionTransformator(transformator)
    assert(engine.touchDown(95, 120, 1, 1) == true)
  }

  test("when mouse is clicked, should return false if object was not clicked") {
    engine.setMouseClickPositionTransformator(transformator)
    assert(engine.touchDown(0, 900, 1, 1) == false)
  }
}
