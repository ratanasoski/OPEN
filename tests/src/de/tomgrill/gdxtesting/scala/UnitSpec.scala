package de.tomgrill.gdxtesting.scala

import com.badlogic.gdx.backends.headless.{HeadlessApplication, HeadlessApplicationConfiguration}
import org.junit.Before
import org.junit.runner.RunWith
import org.scalatest._
import org.scalatest.junit.JUnitRunner

/**
  * Base Test class that mixes together the most used features.
  */
@RunWith(classOf[JUnitRunner])
abstract class UnitSpec extends FunSuite with Matchers with PrivateMethodTester {

  @Before def initialize() {
    val conf = new HeadlessApplicationConfiguration();
    new HeadlessApplication(new CustomTestRunner, conf);
  }

  test("dummy test") {
    assert(true == true)
  }
}
