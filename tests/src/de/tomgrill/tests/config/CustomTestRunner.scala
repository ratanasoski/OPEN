package de.tomgrill.tests.config

import com.badlogic.gdx.ApplicationListener

/**
  * Simple Empty class that extends ApplicationListener
  * Only for the purpose of UnitSpec
  */
class CustomTestRunner extends ApplicationListener{

  override def resize(width: Int, height: Int): Unit = {}

  override def dispose(): Unit = {}

  override def pause(): Unit = {}

  override def render(): Unit = {}

  override def resume(): Unit = {}

  override def create(): Unit = {}
}
