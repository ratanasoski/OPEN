package org.otw.open.testconfig
import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics

/**
  * Created by eilievska on 12/31/2015.
  */
class MockGdxGraphics extends MockGraphics {

  override def getWidth: Int = 1440

  override def getHeight: Int = 900

}
