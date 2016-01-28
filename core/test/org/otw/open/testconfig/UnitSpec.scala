package org.otw.open.testconfig

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.headless._
import com.badlogic.gdx.graphics.GL20
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.scalatest.junit.JUnitRunner
import org.scalatest.{FunSuite, Matchers, PrivateMethodTester}

/**
  * Base Test class that mixes together the most used features.
  */
@RunWith(classOf[JUnitRunner])
abstract class UnitSpec extends FunSuite with Matchers with PrivateMethodTester {

  val conf = new HeadlessApplicationConfiguration()
  new HeadlessApplication(new TestApplicationRunner, conf)

  Gdx.gl = mock(classOf[GL20])
  Gdx.gl20 = new MockGL20

  Gdx.graphics = new MockGdxGraphics

}
