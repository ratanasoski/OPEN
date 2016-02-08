package org.otw.open.controllers

/**
  * In this file we define all the Events that trigger a change of game screens.
  * Created by eilievska on 1/28/2016.
  */
sealed abstract class Event

/**
  * Eraser game finished successfully.
  */
case object EraserGameFinished extends Event

/**
  * Cause and effect game finished successfully.
  */
case object CauseAndEffectFinishedSuccessfully extends Event

/**
  * Cause and effect game finished unsuccessfully.
  */
case object CauseAndEffectFinishedUnsuccessfully extends Event

/**
  * Retry level play
  */
case object RetryLevel extends Event

/**
  * Continue play game to next level
  */
case object NextLevel extends Event

/**
  * Continue to same level on another theme.
  */
case object OtherTheme extends Event

/**
  * Continue to game main menu.
  */
case object ToMainMenu extends Event