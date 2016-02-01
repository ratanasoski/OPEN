package org.otw.open.engine.impl

import com.badlogic.gdx.math.{Vector2, Vector3}
import org.junit.Before
import org.otw.open.controllers.{CauseAndEffectFinishedUnsuccessfully, ScreenController}
import org.otw.open.dto.HorizontalMovingObject
import org.otw.open.testconfig._
import org.otw.open.{GameScreen, OpenGame}
import org.scalatest.BeforeAndAfterEach

/**
  * Created by smirakovska on 1/26/2016.
  */
class CauseAndEffectEngineTest extends UnitSpec with BeforeAndAfterEach{

  val xRange: Range = 25 until 580
  val yRange: Range = 85 until 377
  val standPoints: List[Vector2] = List(new Vector2(0, 0), new Vector2(500, 0))
  val movementDelta: Int = 20

  var causeAndEffectEngine: CauseAndEffectEngine = _

  val transformator = new Function[Vector3, Vector2] {
    override def apply(vector: Vector3): Vector2 = {
      new Vector2(vector.x, vector.y)
    }
  }


  override def beforeEach(): Unit ={
    causeAndEffectEngine = new CauseAndEffectEngine(xRange, yRange, standPoints)
    causeAndEffectEngine.setMouseClickPositionTransformator(transformator)
  }

  override def afterEach(): Unit ={
    causeAndEffectEngine.dispose()
  }
  test("when objectShouldStopAnimating is invoked it should return false for object not at the end point") {
    val testObject = new HorizontalMovingObject(0, 0, movementDelta)
    assert(causeAndEffectEngine.objectShouldStopAnimating(testObject.x, testObject.y) == false)
  }

  test("when objectShouldStopAnimating is invoked it should return true for object at the end point") {
    val testObject = new HorizontalMovingObject(800, 500, movementDelta)
    assert(causeAndEffectEngine.objectShouldStopAnimating(testObject.x, testObject.y))
  }

  test("when CauseAndEffectEngine object's apply method is invoked new CauseAndEffectEngine instance should be returned") {
    val returnedObject = CauseAndEffectEngine.apply(xRange, yRange, standPoints)
    assert(returnedObject.isInstanceOf[CauseAndEffectEngine])
  }

  test("when objectIsClicked is invoked should return false if object is not clicked") {
    causeAndEffectEngine.setMouseClickPositionTransformator(transformator)
    assert(causeAndEffectEngine.objectIsClicked(50, 60) == false)
  }


  test("when objectIsClicked is invoked should return true if object is clicked") {
    causeAndEffectEngine.setMouseClickPositionTransformator(transformator)
    assert(causeAndEffectEngine.objectIsClicked(90, 90) == true)
  }

  test("when mouse is clicked, should return true if object was clicked") {
    causeAndEffectEngine.setMouseClickPositionTransformator(transformator)
    assert(causeAndEffectEngine.touchDown(95, 120, 1, 1) == true)
  }

  test("when mouse is clicked, should return false if object was not clicked") {
    causeAndEffectEngine.setMouseClickPositionTransformator(transformator)
    assert(causeAndEffectEngine.touchDown(0, 900, 1, 1) == false)
  }

  test("when object is clicked and is animating, getDrawings should return a list with new moving object that has updated x coordinate") {
    causeAndEffectEngine.touchDown(90, 90, 1, 1) // mouseWasClicked will be set to true
    val drawings = causeAndEffectEngine.getDrawings(movementDelta)
    assert(drawings.reverse.head.x != standPoints.head.x.toInt)
  }

  test("when getDrawings is invoked, it should return a list of two drawings") {
    causeAndEffectEngine.touchDown(90, 90, 1, 1) // mouseWasClicked will be set to true
    val drawings = causeAndEffectEngine.getDrawings(movementDelta)
    assert(drawings.size == 2)
  }

  test("when 3 failed attempts are made to click the object, screen with unhappy animation is shown") {
    causeAndEffectEngine.touchDown(600, 600, 1, 1)
    causeAndEffectEngine.touchDown(600, 600, 1, 1)
    causeAndEffectEngine.touchDown(600, 600, 1, 1)
    assert(ScreenController.dispatchEvent(CauseAndEffectFinishedUnsuccessfully).isInstanceOf[StaticAnimationEngine])
  }

  test("when object is clicked and animation is finished, screen with happy animation is shown") {
    causeAndEffectEngine.touchDown(900, 900, 1, 1)
    causeAndEffectEngine.touchDown(900, 900, 1, 1)
    /**
      * object is clicked
      */
    causeAndEffectEngine.touchDown(90, 90, 1, 1)

    val gameScreen = OpenGame.getGame.getScreen match {
      case s: GameScreen => s
      case _ => throw new scala.ClassCastException
    }
    val staticAnimationEngine = gameScreen.engine match {
      case sae: StaticAnimationEngine => sae
      case _ => throw new scala.ClassCastException
    }

    assert(staticAnimationEngine.atlasFileName.endsWith("happy-animation.atlas"))
  }
}
