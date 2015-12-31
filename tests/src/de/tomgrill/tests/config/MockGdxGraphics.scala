package de.tomgrill.tests.config

import com.badlogic.gdx.backends.headless.mock.graphics.MockGraphics

/**
  * Created by eilievska on 12/31/2015.
  */
class MockGdxGraphics extends MockGraphics {

  override def getWidth: Int = 640

  override def getHeight: Int = 480

}
