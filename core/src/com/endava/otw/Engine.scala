package com.endava.otw

import com.endava.otw.model.Drawing

/**
  * Created by eilievska on 12/29/2015.
  */
trait Engine {

  def getDrawings(delta: Float): List[Drawing]

}
