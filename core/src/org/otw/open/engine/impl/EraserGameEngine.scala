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
  */
class EraserGameEngine extends InputAdapter with Engine {
  Gdx.input.setInputProcessor(this)

  private var lastPointerPosition: Option[Vector2] = None

  private val pixmapMask: DrawablePixmap = new DrawablePixmap(new Pixmap(Gdx.files.internal("leaves.png")))
  private val background: Texture = new Texture(Gdx.files.internal("street.jpg"))
  private var foreground: Option[Texture] = None
  private var mouseMoved = false

  Pixmap.setBlending(Blending.None)

  override def getDrawings(delta: Float): List[Drawing] = {
    if (mouseMoved || foreground.isEmpty) {
      foreground = Some(pixmapMask.getMaskAsTexture)
      if (pixmapMask.isMaskFilled)
        OpenGame.changeScreen(new GameScreen(new CauseAndEffectEngine( 25 until 580 ,85 until 377, List(new Vector2(0, 500), new Vector2(800, 500)))))
    }
    List(new Drawing(background, 0, 0), new Drawing(foreground.get, 0, 0))
  }

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

  override def dispose() = {
    pixmapMask.dispose
    background.dispose
    if (foreground.nonEmpty) foreground.get.dispose
  }
}
