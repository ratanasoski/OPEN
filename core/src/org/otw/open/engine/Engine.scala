package org.otw.open.engine

import com.badlogic.gdx.math.{Vector2, Vector3}
import org.otw.open.dto.Drawing

/**
  * Created by eilievska on 12/29/2015.
  */
trait Engine {


  def getDrawings(delta: Float): List[Drawing]

  /**
    *
    * @param transformator - High order function that transforms 3D to 2D coordinates
    * @return Boolean value indicating if method is overriden
    */
  def setMouseClickPositionTransformator(transformator: ((Vector3) => Vector2)): Boolean = false

}
