//USEUNIT CauseEffect_01_ContinuosMovement
function CauseEffect_ThreeClickOutOfBounds()
{
 try{
    
  //Launch the tested application.
  launchApp();
  
  //User 3 times is clicking out of the object bounds.  
  Log.AppendFolder("GIVEN a scene with object WHEN number of retries is equal to 3 THEN unhappy animation is shown AND game ends")
  
  eraser ();
  multipleClicksOutOfBounds();
  aqUtils.Delay(2000);
  
  //Check if the unhappy animation has been shown properly
  Regions.SadAnimation.Check(Regions.CreateRegionInfo(Aliases.javaw.wndLWJGL2, 593, 505, 279, 97, false), false, false, 1804);
  
  Log.PopLogFolder();  
     
 
 }
 
 finally
 {     
  closeApp();
 }
}

function multipleClicksOutOfBounds()
{
  //Specifies the coordinates of the first click
  var coorX = 658;
  var coorY = 658;
 
  // Specifies a delay in milliseconds
  sDelay = 1000 ;
 
  // Simulates the first click
  LLPlayer.MouseDown(MK_LBUTTON, coorX, coorY, sDelay);
  LLPlayer.MouseUp(MK_LBUTTON, coorX, coorY, sDelay); 
  
  // Simulates the 2nd click
  LLPlayer.MouseDown(MK_LBUTTON, coorX+200, coorY, sDelay);
  LLPlayer.MouseUp(MK_LBUTTON, coorX+200, coorY, sDelay);
  
  // Simulates the 3rd click
  LLPlayer.MouseDown(MK_LBUTTON, coorX+400, coorY, sDelay);
  LLPlayer.MouseUp(MK_LBUTTON, coorX+400, coorY, sDelay);
}