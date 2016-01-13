package com.endava.open.testconfig

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.headless.{HeadlessApplication, HeadlessApplicationConfiguration}
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
  new HeadlessApplication(new CustomTestRunner, conf)

  Gdx.gl = mock(classOf[GL20])

  Gdx.graphics = new MockGdxGraphics

}
