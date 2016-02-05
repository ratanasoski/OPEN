package org.otw.open.engine.util

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Disposable

/**
  * SoundEffect class that will handle the audio guidance in the application
  * Created by jivanovski on 2/4/2016.
  */
class SoundEffects(val audioSampleName: String) {

  /* sound instance from libGdx */
  private val sound: Sound = Gdx.audio.newSound(Gdx.files.internal(audioSampleName))

  sound.play(1.0f)

  /**
    * Disposes all sounds.
    * @return true if the method is implemented.
    */
  def dispose(): Boolean = {
    sound.dispose()
    true
  }
}
