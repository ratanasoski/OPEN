package org.otw.open.engine.impl

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.files.FileHandle
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.ui.{Label, Skin, TextButton}
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.scenes.scene2d.{InputEvent, Stage}
import org.otw.open.dto.{Drawing, MenuButton}
import org.otw.open.engine.Engine
import org.otw.open.{GameScreen, OpenGame}

/**
  * Created by ratanasoski on 1/13/2016.
  */
class MainMenuEngine(menuScreenNumber: Int) extends Engine {

  val welcomeMenuScreen: List[MenuButton] = List(new MenuButton(1, "Chose game"), new MenuButton(2, "Chose account"))
  val choseGameMenuScreen: List[MenuButton] = List(new MenuButton(3, "Meet the computer"), new MenuButton(4, "Learn with computer"))
  val choseAccountMenuScreen: List[MenuButton] = List(new MenuButton(5, "New Account"), new MenuButton(6, "Existing Account"))

  val mainMenuContentMap: Map[Int, List[MenuButton]] = Map(0 -> welcomeMenuScreen, 1 -> choseGameMenuScreen, 2 -> choseAccountMenuScreen)

  val stage: Stage = new Stage
  val skinFile: FileHandle = Gdx.files.internal("uiskin.json")
  val skin: Skin = new Skin(skinFile)
  val font: BitmapFont = new BitmapFont

  /** @param delta time elapsed between frames
    * @return List of drawings to be rendered
    * */
  override def getDrawings(delta: Float): List[Drawing] = {
    List.empty
  }

  override def getStage(delta: Float): Option[Stage] = {

    Gdx.input.setInputProcessor(stage)

    if (menuScreenNumber == 0) {
      val welcomeLabel: Label = new Label("Welcome to OPEN game!", skin);
      welcomeLabel.setX(630)
      welcomeLabel.setY(500)
      stage.addActor(welcomeLabel)
    }

    val buttonXCoordinate: Float = 570
    val firstButtonYCoordinate: Float = 420
    val buttonWeight: Float = 300
    val buttonHeight: Float = 60

    var i: Int = 0

    if (menuScreenNumber >= 3 && menuScreenNumber <= 6) {
      OpenGame.changeScreen(new GameScreen(new EraserGameEngine))
    } else {
      mainMenuContentMap(menuScreenNumber).foreach(item => {
        val textButton: TextButton = new TextButton(item.buttonText, skin)
        val buttonY: Float = firstButtonYCoordinate - i * buttonHeight
        textButton.setBounds(buttonXCoordinate, buttonY, buttonWeight, buttonHeight)
        i += 1
        textButton.addListener(new ClickListener {
          override def clicked(event: InputEvent, x: Float, y: Float): Unit = {
            OpenGame.changeScreen(new GameScreen(new MainMenuEngine(item.buttonAction)))
          }
        })
        stage.addActor(textButton)
      })
    }

    Some(stage)
  }

  override def dispose: Unit = {
    font.dispose
    stage.dispose
  }

}
