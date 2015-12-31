package de.tomgrill.tests.tests

import com.endava.otw.engines.SnakeEngine
import de.tomgrill.tests.config.UnitSpec

/**
  * Created by eilievska on 12/30/2015.
  */
class SnakeEngineTest extends UnitSpec {

  val snakeEngine: SnakeEngine = SnakeEngine.apply


  test("when mouseMoved is invoked, it should return false") {
    assert(snakeEngine.mouseMoved(0, 0) == false)
  }

}
