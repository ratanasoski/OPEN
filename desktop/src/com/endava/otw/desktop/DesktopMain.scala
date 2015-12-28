package com.endava.otw.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.endava.otw.ScalaDemoGame

/**
  * A Desktop Launcer in Scala.
  * Created by eilievska on 12/22/2015.
  */
object DesktopMain {

  def main(args: Array[String]) {
    val config = new LwjglApplicationConfiguration()
    config.title = "Demo game"
    config.useGL30 = true
    //640 x 480 for snake
    config.width = 640
    config.height = 480
    config.resizable = false

    new LwjglApplication(new ScalaDemoGame, config)
  }

}

