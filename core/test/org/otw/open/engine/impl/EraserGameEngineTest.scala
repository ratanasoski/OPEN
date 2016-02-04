package org.otw.open.engine.impl

import org.junit.Assert
import org.otw.open.dto.Drawing
import org.otw.open.testconfig.UnitSpec
import org.scalatest.BeforeAndAfterEach

/**
  * Created by deftimov on 26.01.2016.
  */
class EraserGameEngineTest extends UnitSpec with BeforeAndAfterEach {
  var eraserGameEngine: EraserGameEngine = _

  override def beforeEach(): Unit = {
    eraserGameEngine = new EraserGameEngine

  }

  override def afterEach(): Unit = {
    eraserGameEngine.dispose()
  }

  test("when getDrawings is invoked without mousemoved  and no foreground set it should return a list with two drawings") {
    val drawings: List[Drawing] = eraserGameEngine.getDrawings(1f)
    assert(drawings.size == 2)
  }

  test("when getDrawings is invoked with mouse moved for the first time it should return a list with drawing") {
    eraserGameEngine.mouseMoved(12, 12)
    eraserGameEngine.getDrawings(1f)
    val drawings: List[Drawing] = eraserGameEngine.getDrawings(1f)
    assert(drawings.size == 2)
  }

  test("when getDrawings is invoked with mouse moved consequently it should return a list with drawing") {
    eraserGameEngine.mouseMoved(12, 12)
    eraserGameEngine.mouseMoved(22, 22)
    eraserGameEngine.getDrawings(1f)
    val drawings: List[Drawing] = eraserGameEngine.getDrawings(1f)
    assert(drawings.size == 2)
  }

  test("when mouseMoved is invoked once with some x,y coordinates it should return false") {
    val isMouseMoved = eraserGameEngine.mouseMoved(12, 12)
    assert(!isMouseMoved)
  }

  test("when mouseMoved is invoked twice with some x,y coordinates it should return true") {
    eraserGameEngine.mouseMoved(12, 12)
    val isMouseMoved = eraserGameEngine.mouseMoved(24, 24)
    assert(isMouseMoved)
  }
}
