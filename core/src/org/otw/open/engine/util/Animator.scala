package org.otw.open.engine.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.{Animation, TextureAtlas}

/**
  * Animator class that will handle all texture transitions for game animations
  *
  * Created by jivanovski on 1/25/2016.
  */
class Animator(val atlasFileName: String) {

  /*global atlas*/
  private val atlas = new TextureAtlas(Gdx.files.internal(atlasFileName))

  /*animation setup*/
  private val animation = new Animation(1 / 7f, atlas.getRegions)

  /**
    *
    * @param timePassed time passed since animation started
    * @return current texture region for the animation
    */
  def getCurrentTexture(timePassed: Float) = animation.getKeyFrame(timePassed, true)

  /**
    * Disposes all disposable resources.
    * @return true if the method is implemented.
    */
  def dispose(): Boolean = {
    atlas.dispose()
    true
  }
}

