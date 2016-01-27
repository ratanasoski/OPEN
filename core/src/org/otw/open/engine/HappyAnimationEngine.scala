package org.otw.open.engine

import org.otw.open.dto.Drawing
import org.otw.open.engine.util.Animator

/**
  * Created by jivanovski on 1/25/2016.
  */
class HappyAnimationEngine extends Engine {
  val animator = new Animator("happy-animation.atlas")
  private var timePassed = 0f

  override def getDrawings(delta: Float): List[Drawing] = {
    timePassed = timePassed + delta
    List(new Drawing(animator.getCurrentTexture(timePassed), 464, 194))
  }

  override def dispose(): Unit = {

  }
}

object HappyAnimationEngine {
  def apply: HappyAnimationEngine = new HappyAnimationEngine
}