package com.endava.otw.util

/**
  * Created by eilievska on 12/22/2015.
  */
sealed abstract class Direction

case class LEFT() extends Direction

case class RIGHT() extends Direction

case class UP() extends Direction

case class DOWN() extends Direction
