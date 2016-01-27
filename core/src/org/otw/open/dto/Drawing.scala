package org.otw.open.dto

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
  * Created by eilievska on 12/29/2015.
  */
case class Drawing(image: TextureRegion, x: Int, y: Int) {
  def this(texture: Texture, x: Int, y: Int) = this(new TextureRegion(texture), x, y)
}
