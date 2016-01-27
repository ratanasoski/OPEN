package org.otw.open.engine

import com.sun.media.jfxmediaimpl.MediaDisposer.Disposable
import org.otw.open.dto.Drawing

/**
  * Created by eilievska on 12/29/2015.
  */
trait Engine extends Disposable {

  def getDrawings(delta: Float): List[Drawing]

}
