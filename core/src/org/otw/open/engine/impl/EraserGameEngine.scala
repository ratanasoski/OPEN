package org.otw.open.engine.impl

import com.badlogic.gdx.graphics.Pixmap.Blending
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{Gdx, InputAdapter}
import org.otw.open.controllers.{EraserGameFinished, ScreenController}
import org.otw.open.dto.{DrawablePixmap, Drawing}
import org.otw.open.engine.Engine
import org.otw.open.engine.util.SoundEffects

/**
  * Created by deftimov on 25.01.2016.
  * An Engine that handles the erasing canvas games.
  */
class EraserGameEngine extends InputAdapter with Engine {

  Gdx.input.setInputProcessor(this)

  /**
    * Name of running theme.
    */
  private val themeName: String = ScreenController.themes(ScreenController.themeKey)

  private val pixmapMask: DrawablePixmap = new DrawablePixmap(new Pixmap(Gdx.files.internal("theme/" + themeName + "/mask.png")))
  private val backgroundTexture: Texture = new Texture(Gdx.files.internal("theme/" + themeName + "/dark-background.png"))
  private val maskTexture: Texture = pixmapMask.initializePixmapDrawingOntoTexture
  /**
    * Sound instance for cause and effect game
    */
  private val sound: SoundEffects = new SoundEffects("guidanceForEraser.wav")
  private var lastPointerPosition: Option[Vector2] = None
  private var mouseMoved = false

  Pixmap.setBlending(Blending.None)

  /** @param delta time elapsed between frames
    * @return List of drawings to be rendered
    * */
  override def getDrawings(delta: Float): List[Drawing] = {
    if (mouseMoved) {
      pixmapMask.drawOnTexture(maskTexture)
      if (pixmapMask.isTransparent) ScreenController.dispatchEvent(EraserGameFinished)
    }
    List(new Drawing(backgroundTexture, 0, 0), new Drawing(maskTexture, 0, 0))
  }

  /**
    * @param screenX x current coordinate of the pointer
    * @param screenY y current coordinate of the pointer
    * @return true if the mouse was moved from one point to another,
    *         false otherwise if the mouse is idle
    */
  override def mouseMoved(screenX: Int, screenY: Int): Boolean = {
    val currentPosition: Vector2 = new Vector2(screenX, screenY)
    val lastPosition = lastPointerPosition.getOrElse(currentPosition)
    lastPointerPosition = Some(lastPosition)
    if (currentPosition != lastPosition) {
      pixmapMask.drawLerped(lastPosition, currentPosition)
      mouseMoved = true
      true
    }
    else {
      mouseMoved = false
      false
    }
  }

  /**
    * Disposes of the textures and sounds.
    * The resources are not collected by the garbage collector.
    *
    **/
  override def dispose() = {
    pixmapMask.dispose
    backgroundTexture.dispose
    maskTexture.dispose
    sound.dispose
  }

}
