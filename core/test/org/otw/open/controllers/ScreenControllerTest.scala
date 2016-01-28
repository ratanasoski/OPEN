package org.otw.open.controllers

import org.otw.open.OpenGame
import org.otw.open.engine.Engine
import org.otw.open.engine.impl.CauseAndEffectEngine
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 1/28/2016.
  */
class ScreenControllerTest extends UnitSpec {

  test("given EraserGameFinished, the new game screen engine is CauseAndEffectEngine") {
    val returnedScreenEngine: Engine = ScreenController.dispatchEvent(EraserGameFinished)
    assert(returnedScreenEngine.isInstanceOf[CauseAndEffectEngine])
  }

}
