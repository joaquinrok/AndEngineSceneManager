package com.jrok.sceneman.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.ui.activity.BaseGameActivity;

/**
 * 
 * The Scene Manager class, simply creates and stores all the scenes in the program
 * also making it possible to change within those scenes.
 * 
 * @author Joaquinrok
 *
 */
public class SceneManager {
	
	// ===========================================================
	// Fields
	// ===========================================================
	private SceneType currentScene;
	private BaseGameActivity activity;
	private Engine mEngine;
	
	// ===========================================================
	// Scenes
	// ===========================================================
	private SplashScene splashScene;
	private Scene titleScene;
	private WorldScene worldScene;
	
	// === Scene Names ===========================================
	public enum SceneType
	{
		SPLASH,
		TITLE,
		WORLDSCENE
	}
	
	// ===========================================================
	// Constructor
	// ===========================================================
	public SceneManager(BaseGameActivity activity, Engine mEngine, Camera camera) {
		this.activity = activity;
		this.mEngine = mEngine;		
	}
	
	
	// ===========================================================
	// Methods
	// ===========================================================
	
	//Method creates the Splash Scene
	public SplashScene createSplashScene() {
		splashScene = new SplashScene(this.activity, this);
		return splashScene;
	}
	
	//Method creates all of the Game Scenes
	public void createGameScenes() {
		
		//Create the Title Scene and set background color to green
		//  Title scene isnt an AbstractScene's child, it just a normal
		//  scene with green background.
		titleScene = new Scene();
		titleScene.setBackground(new Background(0, 1, 0));
		
		worldScene = new WorldScene(this.activity, this);
	}
	
	
	// ===========================================================
	// Getters & Setters
	// ===========================================================
	
	//Method allows you to get the currently active scene
	public SceneType getCurrentScene() {
		return currentScene;	
	}
	
	//Method allows you to set the currently active scene
	public void setCurrentScene(SceneType scene) {
		currentScene = scene;
		switch (scene)
		{
		case SPLASH:
			break;
		case TITLE:
			mEngine.setScene(titleScene);
			break;
		case WORLDSCENE:
			mEngine.setScene(worldScene);
		}		
	}
		
}
