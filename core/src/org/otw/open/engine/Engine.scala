package org.otw.open.engine

import org.otw.open.dto.Drawing

/**
  * Created by eilievska on 12/29/2015.
  */
trait Engine {

  def getDrawings(delta: Float): List[Drawing]

}
