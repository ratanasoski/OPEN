package org.otw.open.engine.impl

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.{Vector2, Vector3}
import com.badlogic.gdx.{Gdx, InputAdapter}
import org.otw.open.controllers._
import org.otw.open.dto.Drawing
import org.otw.open.engine.Engine
import org.otw.open.engine.util.Animator

/**
  * An engine class used for displaying animation that should be static and placed in the center of the screen.
  * Created by jivanovski on 1/25/2016.
  */
class StaticAnimationEngine(val atlasFileName: String) extends InputAdapter with Engine {

  /**
    * set current input processor
    */
  Gdx.input.setInputProcessor(this)

  val currentLevel = ScreenController.currentLevel

  val maxLevel = ScreenController.maxLevel

  /**
    * Name of running theme.
    */
  private val themeName: String = ScreenController.themes(ScreenController.themeKey)

  val animator = new Animator(atlasFileName)
  val background = new Texture(Gdx.files.internal("theme/" + themeName + "/dark-background.png"))
  private var timePassed = 0f

  private val nextLevelButtonTexture = new Texture(Gdx.files.internal("next-level.png"))
  private val disabledNextLevelButtonTexture = new Texture(Gdx.files.internal("disabled-next-level.png"))
  private val retryLevelButtonTexture = new Texture(Gdx.files.internal("retry-level.png"))
  private val toMainMenuButtonTexture = new Texture(Gdx.files.internal("to-main-menu.png"))
  private val toOtherThemeButtonTexture = new Texture(Gdx.files.internal("to-other-theme.png"))

  private val xCentar = 464
  private val yCentar = 194

  private val gameNavigationButtonsY = 45

  private val retryLevelButtonX = 535
  private val toMainMenuButtonX = 348
  private val nextLevelButtonX = 722
  private val toOtherThemeButtonX = 909

  val startingPointY = Gdx.graphics.getHeight - gameNavigationButtonsY

  val xRangeToMainMenu: Range = (toMainMenuButtonX.toInt until (toMainMenuButtonX + toMainMenuButtonTexture.getWidth).toInt)
  val xRangeRetryLevel: Range = (retryLevelButtonX.toInt until (retryLevelButtonX + retryLevelButtonTexture.getWidth).toInt)
  val xRangeNextLevel: Range = (nextLevelButtonX.toInt until (nextLevelButtonX + nextLevelButtonTexture.getWidth).toInt)
  val xRangeOtherTheme: Range = (toOtherThemeButtonX.toInt until (toOtherThemeButtonX + toMainMenuButtonTexture.getWidth).toInt)
  val yRangeGameNavigationButtons: Range = ((startingPointY - retryLevelButtonTexture.getHeight).toInt until (startingPointY).toInt)

  /**
    * Transforms the click coordinates based on the screen size. Uses the camera transformation.
    */
  var transformator: Option[((Vector2) => Vector2)] = None

  /**
    * Method that handels mouse click on screen
    *
    * @param screenX x coordinate of the mouse click
    * @param screenY y coordinate of the mouse click
    * @param pointer
    * @param button  information about the button clicked
    * @return true if method is overridden
    */
  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = {
    objectIsClicked(screenX, screenY)
    false
  }

  /**
    * @param x x coordinate of mouse click
    * @param y y coordinate of mouse click
    * @return true if continueObject object is clicked
    */
  def objectIsClicked(x: Int, y: Int): Boolean = {
    val transformedPosition: Vector2 = transformator.get(new Vector2(x, y))
    if (yRangeGameNavigationButtons.contains(transformedPosition.y.toInt)) {
      if (xRangeToMainMenu.contains(transformedPosition.x.toInt)) ScreenController.dispatchEvent(ToMainMenu)
      if (xRangeRetryLevel.contains(transformedPosition.x.toInt)) ScreenController.dispatchEvent(RetryLevel)
      if (xRangeNextLevel.contains(transformedPosition.x.toInt) && (currentLevel < maxLevel)) ScreenController.dispatchEvent(NextLevel)
      if (xRangeOtherTheme.contains(transformedPosition.x.toInt)) ScreenController.dispatchEvent(OtherTheme)
    }
    false
  }

  /**
    * @param transformator - High order function that transforms 3D to 2D coordinates
    * @return Boolean value indicating if method is overridden
    */
  override def setMouseClickPositionTransformator(transformator: (Vector2) => Vector2): Boolean = {
    this.transformator = Some(transformator)
    true
  }

  override def getDrawings(delta: Float): List[Drawing] = {
    timePassed = timePassed + delta
    val happyFaceTexture = animator.getCurrentTexture(timePassed)
    var nextLevelDrawing: Drawing = new Drawing(nextLevelButtonTexture, nextLevelButtonX, gameNavigationButtonsY)
    if (currentLevel == maxLevel) nextLevelDrawing = new Drawing(disabledNextLevelButtonTexture, nextLevelButtonX, gameNavigationButtonsY)
    List(new Drawing(background, 0, 0), new Drawing(happyFaceTexture, xCentar, yCentar),
      new Drawing(toMainMenuButtonTexture, toMainMenuButtonX, gameNavigationButtonsY),
      new Drawing(retryLevelButtonTexture, retryLevelButtonX, gameNavigationButtonsY),
      nextLevelDrawing,
      new Drawing(toOtherThemeButtonTexture, toOtherThemeButtonX, gameNavigationButtonsY))
  }

  override def dispose(): Unit = {
    animator.dispose()
    background.dispose()
    toMainMenuButtonTexture.dispose()
    retryLevelButtonTexture.dispose()
    nextLevelButtonTexture.dispose()
    disabledNextLevelButtonTexture.dispose()
    toOtherThemeButtonTexture.dispose()
  }
}