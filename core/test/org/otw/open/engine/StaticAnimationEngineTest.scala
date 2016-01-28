package org.otw.open.engine

import org.otw.open.dto.Drawing
import org.otw.open.engine.impl.StaticAnimationEngine
import org.otw.open.engine.util.Animator
import org.otw.open.testconfig.UnitSpec

/**
  * Created by jivanovski on 1/28/2016.
  */
class StaticAnimationEngineTest extends UnitSpec {

  val atlasFileName: String = "test.atlas"
  val animatorToTest = new Animator(atlasFileName)

  val staticAnimationEngine: StaticAnimationEngine = new StaticAnimationEngine(atlasFileName)

  test("when getDrawings is invoked with an empty list") {
    val drawings: List[Drawing] = staticAnimationEngine.getDrawings(0)
    assert(drawings.size == 1)
  }

  test("when getDrawings is invoked with one drawing") {
    val drawings: List[Drawing] = staticAnimationEngine.getDrawings(0.4f)
    assert(drawings.size == 1)
  }

  test("test if dispose method is implemented") {
    assert(animatorToTest.dispose())
  }
}
