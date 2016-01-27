package org.otw.open.engine

import org.otw.open.dto.Drawing
import org.otw.open.engine.util.Animator

/**
  * An engine class used for displaying animation that should be static and placed in the center of the screen.
  * Created by jivanovski on 1/25/2016.
  */
class StaticAnimationEngine(val atlasFileName: String) extends Engine {
  val animator = new Animator(atlasFileName)
  private var timePassed = 0f

  override def getDrawings(delta: Float): List[Drawing] = {
    timePassed = timePassed + delta
    List(new Drawing(animator.getCurrentTexture(timePassed), 464, 194))
  }

  override def dispose(): Unit = {
    animator.dispose()
  }
}