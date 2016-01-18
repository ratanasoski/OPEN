package org.otw.open.engine.impl

import org.otw.open.dto.Drawing
import org.otw.open.testconfig.UnitSpec

/**
  * Created by eilievska on 1/18/2016.
  */
class FirstGameScreenEngineTest extends UnitSpec {

  val firstGameEngine = new FirstGameScreenEngine

  test("when getDrawings is invoked it should return a list with one drawing") {
    val drawings: List[Drawing] = firstGameEngine.getDrawings(0.4f)
    assert(drawings.size == 1)
    assert(drawings.head.x == 0)
    assert(drawings.head.y == 0)
  }

}
