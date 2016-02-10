package org.otw.open.engine.impl

/**
  * Created by smirakovska on 1/26/2016.
  */

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.{Gdx, Input, InputAdapter}
import org.otw.open.controllers.{CauseAndEffectFinishedSuccessfully, CauseAndEffectFinishedUnsuccessfully, ScreenController}
import org.otw.open.dto.{Drawing, HorizontalMovingObject, StandPoint}
import org.otw.open.engine.Engine
import org.otw.open.engine.util.{Animator, SoundEffects}

/**
  * CauseAndEffectEngine - handles horizontal object movement
  *
  * @param objectStandPoints - list of StandPoint objects
  */
class CauseAndEffectEngine(objectStandPoints: List[StandPoint]) extends InputAdapter with Engine {

  /**
    * set current input processor
    */
  Gdx.input.setInputProcessor(this)

  /**
    * Name of running theme.
    */
  private val themeName: String = ScreenController.themes(ScreenController.themeKey)

  /**
    * Max number of failed attempts allowed
    */
  private val maxFailedAttempts = 3

  /**
    * The background texture where the object moves on.
    */
  private val backgroundTexture = new Texture(Gdx.files.internal("theme/" + themeName + "/light-background.png"))

  /**
    * Time interval on which the movingObject moves.
    */
  private val MOVE_TIME_IN_SECONDS: Float = 0.1F

  /**
    * Movement of the object
    */
  private val DELTA_MOVEMENT: Int = 30

  /**
    * Animator object
    */
  private val animator: Animator = new Animator("theme/" + themeName + "/animation-object.atlas")

  /**
    * Sound instance for cause and effect game
    */
  private val sound: SoundEffects = new SoundEffects("guidanceForCauseAndEffect.wav")

  /**
    * end point of the animation
    */
  private val endVector = objectStandPoints.reverse.head.standPointCoordinates
  /**
    * Transforms the click coordinates based on the screen size. Uses the camera transformation.
    */
  var transformator: Option[((Vector2) => Vector2)] = None
  /**
    * Boolean flag that is set to true when object is clicked
    */
  private var objectClicked: Boolean = false
  /**
    * Counter for the number of failed attempts
    */
  private var numOfFailedAttempts = 0
  /**
    * Current time.
    */
  private var timer = MOVE_TIME_IN_SECONDS
  /**
    * index of the next stand point
    */
  private var nextPointIndex: Int = 1
  /**
    * next standpoint for the moving object
    */
  private var nextPoint: StandPoint = objectStandPoints(nextPointIndex)

  /**
    * Moving object.
    */
  private var movingObject: HorizontalMovingObject = new HorizontalMovingObject(objectStandPoints(0).standPointCoordinates.x.toInt, objectStandPoints(0).standPointCoordinates.y.toInt, DELTA_MOVEMENT)

  /**
    * Timer for the vibrating object
    */
  private var animationTime = 0f

  /**
    * Method that handles mouse click on screen
    *
    * @param screenX x coordinate of the mouse click
    * @param screenY y coordinate of the mouse click
    * @param pointer
    * @param button  information about the button clicked
    * @return true if method is overridden
    */
  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    if (button == Input.Buttons.LEFT) {
      if (objectIsClicked(screenX, screenY, objectStandPoints(nextPointIndex - 1))
        && !objectShouldStopMoving(movingObject.x, movingObject.y, objectStandPoints(nextPointIndex))
      ) true
      else {
        if (!objectClicked)
          numOfFailedAttempts += 1
        if (numOfFailedAttempts == maxFailedAttempts)
          ScreenController.dispatchEvent(CauseAndEffectFinishedUnsuccessfully)
        false
      }
    }
    else false
  }

  /**
    * @param x x coordinate of mouse click
    * @param y y coordinate of mouse click
    * @return true if movingObject object is clicked
    */
  def objectIsClicked(x: Int, y: Int, point: StandPoint): Boolean = {
    val transformedPosition: Vector2 = transformator.get(new Vector2(x, y))
    if (point.clickXRange.contains(transformedPosition.x.toInt)
      && point.clickYRange.contains(transformedPosition.y.toInt)) {
      objectClicked = true
      true
    }
    else false
  }

  /**
    * @param delta
    * @return list of drawings
    */
  override def getDrawings(delta: Float): List[Drawing] = {
    animationTime += delta
    if (objectClicked) {
      if (endReached(movingObject.x, movingObject.y)) {
        ScreenController.dispatchEvent(CauseAndEffectFinishedSuccessfully)
      }
      else {
        if (!objectShouldStopMoving(movingObject.x, movingObject.y, objectStandPoints(nextPointIndex))) {
          timer = timer - delta
          if (timer < 0) {
            timer = MOVE_TIME_IN_SECONDS
            movingObject = movingObject.moveObject
          }
        }
      }
    }
    List(new Drawing(backgroundTexture, 0, 0), new Drawing(animator.getCurrentTexture(animationTime), movingObject.x, movingObject.y))
  }

  /**
    * @param objectX x coordinate of the movingObject
    * @param objectY y coordinate of the movingObject
    * @return true if movingObject has reached the end point
    */
  def objectShouldStopMoving(objectX: Int, objectY: Int, point: StandPoint): Boolean = {
    if (objectX >= point.standPointCoordinates.x) {
      nextPointIndex += 1
      nextPoint = objectStandPoints(nextPointIndex)
      objectClicked = false
      true
    }
    else false
  }

  /**
    * @param objectX x coordinate of the moving object
    * @param objectY y coordinate of the moving object
    * @return true if object has reached final endpoint
    */
  def endReached(objectX: Int, objectY: Int): Boolean = {
    objectX >= endVector.x
  }

  /**
    * @param transformator - High order function that transforms 3D to 2D coordinates
    * @return Boolean value indicating if method is overridden
    */
  override def setMouseClickPositionTransformator(transformator: (Vector2) => Vector2): Boolean = {
    this.transformator = Some(transformator)
    true
  }

  override def dispose(): Unit = {
    backgroundTexture.dispose()
    animator.dispose()
    sound.dispose()
  }
}

object CauseAndEffectEngine {
  def apply(objectStandPoints: List[StandPoint]): CauseAndEffectEngine = new CauseAndEffectEngine(objectStandPoints)
}
