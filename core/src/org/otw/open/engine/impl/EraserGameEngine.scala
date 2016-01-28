package org.otw.open.engine.impl

import com.badlogic.gdx.graphics.Pixmap.Blending
import com.badlogic.gdx.graphics.{Pixmap, Texture}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.{InputAdapter, Gdx, InputProcessor}
import org.otw.open.dto.{DrawablePixmap, Drawing}
import org.otw.open.engine.Engine
import org.otw.open.{GameScreen, OpenGame}

/**
  * Created by deftimov on 25.01.2016.
  * An Engine that handles the erasing canvas games.
  */
class EraserGameEngine extends InputAdapter with Engine {
  Gdx.input.setInputProcessor(this)
  private val pixmapMask: DrawablePixmap = new DrawablePixmap(new Pixmap(Gdx.files.internal("leaves.png")))
  private val backgroundTexture: Texture = new Texture(Gdx.files.internal("street.jpg"))
  private val maskTexture: Texture = pixmapMask.initializePixmapDrawingOntoTexture

  private var lastPointerPosition: Option[Vector2] = None
  private var mouseMoved = false

  Pixmap.setBlending(Blending.None)

  /** @param delta time elapsed between frames
    * @return List of drawings to be rendered
    **/
  override def getDrawings(delta: Float): List[Drawing] = {
    if (mouseMoved) {
      pixmapMask.drawOnTexture(maskTexture)
      if (pixmapMask.isPixmapMaskTransparent)
        OpenGame.changeScreen(new GameScreen(new CauseAndEffectEngine(25 until 580, 85 until 377, List(new Vector2(0, 500), new Vector2(800, 500)))))
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
    * Disposes of the textures.
    * The textures are not collected by the garbage collector.
    *
    **/
  override def dispose() = {
    pixmapMask.dispose
    backgroundTexture.dispose
    maskTexture.dispose
  }
}
