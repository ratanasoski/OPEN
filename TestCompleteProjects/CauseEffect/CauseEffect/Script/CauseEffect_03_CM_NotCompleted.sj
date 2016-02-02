//USEUNIT CauseEffect_01_ContinuosMovement
function CauseEffect_NotCompleted ()
{
 try{
    
  //Launch the tested application.
  launchApp();
  
  //User does not remove all leaves from the screen  
  Log.AppendFolder("This is negative scenario for ContinuousMovement of the mouse 'User does not remove all leaves from the screen'")
  
  //Check if the first layer (top layer - leaves) is getting displayed
  Regions.RegionLeaves.Check(Regions.CreateRegionInfo(Aliases.javaw.wndLWJGL2, 1, 29, 1445, 906, false));
  
  Eraser_N();
  
  //Check if the second layer (bottom layer - car/street) has been shown properly
  if(!Regions.Compare(Regions.CreateRegionInfo(Aliases.javaw.wndLWJGL2, 571, 320, 305, 143, false),"AnimationCar",lmNone))
  Log.Message("Leaves are not removed properly");
  
  Log.PopLogFolder();  
     
}
 
 finally
 {     
  closeApp();
 }
}
function Eraser_N()
{
  //Specifies the coordinates of the first click
  var destX = 171;
  var destY = 215;
 
  // Simulates continuous movement of the mouse
  LLPlayer.MouseMove(305, 215, 0);
  LLPlayer.MouseMove(285, 164, 0);
  LLPlayer.MouseMove(362, 172, 0);
  LLPlayer.MouseMove(400, 173, 16);
  LLPlayer.MouseMove(411, 174, 16);
  LLPlayer.MouseMove(900, 400, 0);
  LLPlayer.MouseMove(1200, 154, 0);
  LLPlayer.MouseMove(1665, 238, 0);
}