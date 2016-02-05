//USEUNIT CauseEffect_01_ContinuosMovement
function causeEffect_OneClick()
{
try{
  //Launch the tested application.
  launchApp();
  
  //As a user I want remove leaves from the top layer of the scene by continuous movement of the mouse So that I can see the bottom layer of the scene
  Log.AppendFolder("This test is for ContinuousMovement of the mouse");
  eraser ();
  Log.PopLogFolder();  
  
  //As a user I want to be able to click the object on the screen So that it reaches the target in one step
  Log.AppendFolder("This test is for Reaching the goal with one click");  
  Level1UserAction();  
  aqUtils.Delay(1500);  
  //Check if the second layer (bottom layer - car/street) has been shown properly
  Regions.happyAnimation.Check(Regions.CreateRegionInfo(Aliases.javaw.wndLWJGL2, 571, 320, 305, 143, false),false,false,46024);  
  Log.PopLogFolder(); 
 }
 
 finally
 {     
   closeApp();
 }
}

function Level1UserAction()
{  
  //Specifies the coordinates of the first click (it depends on the screen resolution)
  var coorX = 450;
  var coorY = 500;
 
  // Specifies a delay in milliseconds
  sDelay = 1000 ;
 
  // Simulates pressing and releasing the left mouse button
  LLPlayer.MouseDown(MK_LBUTTON, coorX, coorY, sDelay);
  LLPlayer.MouseUp(MK_LBUTTON, coorX, coorY, sDelay); 

}