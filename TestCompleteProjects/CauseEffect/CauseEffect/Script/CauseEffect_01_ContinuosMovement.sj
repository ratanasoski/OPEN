function causeEffect()
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
  Log.PopLogFolder();  
 }
 
 finally
 {     
   closeApp();
 }
}

function launchApp()
{
  //TestedApps.desktop.Params.SimpleParams.FilePath="\\mkskfs01\Projects\OpenTheWindows\game for testing";
  //TestedApps.desktop.Params.SimpleParams.FileName="desktop.exe"
  //TestedApps.desktop.Params.SimpleParams.Activate();
  TestedApps.desktop.Run();
  causeEffectObject = Aliases.javaw.wndLWJGL2;
}

function closeApp()
{
 //Closes the game window.
  causeEffectObject.Close();
  Log.Message("The 'Cause and Effect' game finshed")
}


function eraser ()
{
  //Check if the first layer (top layer - leaves) is getting displayed
  Regions.RegionLeaves.Check(Regions.CreateRegionInfo(Aliases.javaw.wndLWJGL2, 1, 29, 1445, 906, false));

  //perform the erase action (continuos movement of the mouse over the scene) 
  LLCollection.ContinuousMovement.Execute();
  
  //Check if the second layer (bottom layer - car/street) has been shown properly
  Regions.AnimationCar.Check(Aliases.javaw.wndLWJGL2, false, false, 46024);
 
}