package com.jrok.sceneman;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.FillResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.ui.activity.BaseGameActivity;

import com.jrok.sceneman.scenes.SceneManager;
import com.jrok.sceneman.scenes.SceneManager.SceneType;

/**
 * 
 * This is a simple activity to show a possible solution for scene management.
 * 
 * The Activity creates a sceneManager, which will create other scenes: Splash, Title, World.
 * The Splash screen is loaded and displayed first, while the other scenes get created.
 * 
 * Then it will transition to the Title Scene, (which is actually just a green screen),
 * wait a few moments and then go the the World Scene, where some boxes fell over each other.
 *   From inside the WorldScene class, it will change back to the Title screen after some seconds.
 *   
 * 
 * NOTE:
 *     This is my personal solution for this matter, probably not the better nor the most beautiful,
 *     but in the process of getting here I've learned a thing of too. Some of them I wish I would have had found
 *     somewhere before. Maybe you can find it useful.
 *     You are free to use/modify/enjoy this code, (Its also not %100 mine, I've taken some bits from here and there).
 *     
 *     Have fun!
 *     
 *     P.S.: In order to work you'll need the following images
 *     				in "assests/gfx":
 *     						- face_box.png 	(32x32)
 *     						- box_png      	(32x32)
 *   						- splash.png    (256x256 or smaller)
 *   
 *   				The first two come with AndEngineExamples! For the splash, I leave it to your creativity!
 * 
 * @author Joaquinrok
 */
public class SceneMan extends BaseGameActivity {
	
	private final int CAMERA_WIDTH = 720;
	private final int CAMERA_HEIGHT = 480;
	private Camera _camera;
	private SceneManager sceneManager;

	// ===========================================================
	// Engine Options
	// ===========================================================
	@Override
	public EngineOptions onCreateEngineOptions() {
		_camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		EngineOptions engineOptions = new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new FillResolutionPolicy(), _camera);
		return engineOptions;
	}

	
	// ===========================================================
	// Create Resources -> Creates the Scene Manager
	// ===========================================================
	@Override
	public void onCreateResources(OnCreateResourcesCallback pOnCreateResourcesCallback)	throws Exception {
		sceneManager = new SceneManager(this, mEngine, _camera);
	pOnCreateResourcesCallback.onCreateResourcesFinished();
	}
	
	
	// ===========================================================
	// Create Scene -> creates the Splash Scene
	// ===========================================================
	@Override
	public void onCreateScene(OnCreateSceneCallback pOnCreateSceneCallback)
			throws Exception {
				//                    creates the splash scene ------------\
		pOnCreateSceneCallback.onCreateSceneFinished(sceneManager.createSplashScene());
	}

	
	// ===========================================================
	// Populate Scene -> creates the next Scene(s)
	// ===========================================================
	@Override
	public void onPopulateScene(Scene pScene,OnPopulateSceneCallback pOnPopulateSceneCallback) throws Exception {
		
		// waits a bit (splash is being displayed) then loads next scene
		mEngine.registerUpdateHandler(new TimerHandler(3f, new ITimerCallback() 
		{
			public void onTimePassed(final TimerHandler pTimerHandler) 
			{
				mEngine.unregisterUpdateHandler(pTimerHandler);
				sceneManager.createGameScenes();
				sceneManager.setCurrentScene(SceneType.TITLE);
				mEngine.registerUpdateHandler(new TimerHandler(2f, new ITimerCallback() 
				{
					public void onTimePassed(final TimerHandler pTimerHandler) 
					{
						mEngine.unregisterUpdateHandler(pTimerHandler);
						sceneManager.setCurrentScene(SceneType.WORLDSCENE);
					}
				}));
			}
		}));

		pOnPopulateSceneCallback.onPopulateSceneFinished();

	}

}