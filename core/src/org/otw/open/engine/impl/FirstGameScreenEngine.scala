package org.otw.open.engine.impl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion
import org.otw.open.dto.Drawing
import org.otw.open.engine.Engine

/**
  * Created by eilievska on 1/18/2016.
  */
class FirstGameScreenEngine extends Engine {

  /**
    * Demo image.
    */
  val img: Texture = new Texture(Gdx.files.internal("logo.png"))

  override def getDrawings(delta: Float): List[Drawing] = {
    List(new Drawing(new TextureRegion(img), 0, 0))
  }

  override def dispose() = {
    img.dispose()
  }
}
