package org.otw.open.engine.impl

/**
  * Created by smirakovska on 1/26/2016.
  */

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.{Vector3, Vector2}
import com.badlogic.gdx.{Gdx, InputAdapter}
import org.otw.open.dto.{HorizontalMovingObject, Drawing}
import org.otw.open.engine.Engine

/**
  * CauseAndEffectEngine - handles horizontal object movement
  */
class CauseAndEffectEngine(val xRange: Range, val yRange: Range, objectStandPoints: List[Vector2]) extends InputAdapter with Engine {

  /**
    * set current input processor
    */
  Gdx.input.setInputProcessor(this)

  /**
    * Boolean flag that is set to true when object is clicked
    */
  private var mouseWasClicked: Boolean = false

  /**
    * A texture is a bitmap image that gets drawn on the screen through mapping.
    * Car texture
    */
  private val objectTexture = new Texture(Gdx.files.internal("car.jpeg"))

  /**
    * Time interval on which the movingObject moves.
    */
  private val MOVE_TIME_IN_SECONDS: Float = 0.1F

  /**
    * Current time.
    */
  private var timer = MOVE_TIME_IN_SECONDS

  /**
    * Movement of the object
    */
  private val DELTA_MOVEMENT: Int = 30

  private val objectStartingPoint = objectStandPoints.head

  /**
    * Car object.
    */
  private var movingObject: HorizontalMovingObject = new HorizontalMovingObject(objectStartingPoint.x.toInt, objectStartingPoint.y.toInt, DELTA_MOVEMENT)


  var transformator: Option[((Vector3) => Vector2)] = None

  /**
    * @param x x coordinate of mouse click
    * @param y y coordinate of mouse click
    * @return true if movingObject object is clicked
    */
  def objectIsClicked(x: Int, y: Int): Boolean = {
    val transformedPosition: Vector2 = transformator.get(new Vector3(x, y, 0))
    val newClickY = Gdx.graphics.getHeight - transformedPosition.y.toInt
    if (xRange.contains(transformedPosition.x.toInt)
      && yRange.contains(transformedPosition.y.toInt)) {
      true
    }
    else false
  }

  /**
    * @param carX x coordinate of the movingObject
    * @param carY y coordinate of the movingObject
    * @return true if movingObject has reached the end point
    */
  def objectShouldStopAnimating(carX: Int, carY: Int): Boolean = {
    if (carX >= objectStandPoints.reverse.head.x) // end point
      true
    else false
  }

  /**
    * Method that handels mouse click on screen
    * @param screenX x coordinate of the mouse click
    * @param screenY y coordinate of the mouse click
    * @param pointer
    * @param button information about the button clicked
    * @return true if method is overridden
    */
  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    if (objectIsClicked(screenX, screenY)) {
      mouseWasClicked = true
      true
    }
    else false
  }

  override def getDrawings(delta: Float): List[Drawing] = {

    if (mouseWasClicked && !objectShouldStopAnimating(movingObject.x, movingObject.y)) {
      timer = timer - delta
      if (timer < 0) {
        timer = MOVE_TIME_IN_SECONDS
        movingObject = movingObject.moveObject
      }
    }
    List(new Drawing(new TextureRegion(objectTexture), movingObject.x, movingObject.y))
  }

  /**
    * @param transformator - High order function that transforms 3D to 2D coordinates
    * @return Boolean value indicating if method is overridden
    */
  override def setMouseClickPositionTransformator(transformator: (Vector3) => Vector2): Boolean = {
    this.transformator = Some(transformator)
    true
  }

  override def dispose(): Unit ={
    objectTexture.dispose()
  }
}

object CauseAndEffectEngine {

  /**
    * @param xRange
    * @param yRange
    * @param objectStandPoints Vector2 points where object should stop its movement
    * @return new CauseAndEffectEngine
    */
  def apply(xRange: Range, yRange: Range, objectStandPoints: List[Vector2]): CauseAndEffectEngine = new CauseAndEffectEngine(xRange, yRange, objectStandPoints)
}
