package org.otw.open.engine.util

import org.otw.open.testconfig.UnitSpec

/**
  * Created by jivanovski on 2/4/2016.
  */
class SoundEffectsTest extends UnitSpec {
  val soundEffectToTest = new SoundEffects("guidanceForCauseAndEffect.wav")

  test("tests that dispose method is implemented") {
    assert(soundEffectToTest.dispose())
  }
}
