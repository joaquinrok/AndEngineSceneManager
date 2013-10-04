package com.jrok.sceneman.scenes;

import org.andengine.engine.Engine;
import org.andengine.engine.camera.Camera;
import org.andengine.opengl.texture.TextureManager;
import org.andengine.opengl.vbo.VertexBufferObjectManager;
import org.andengine.ui.activity.BaseGameActivity;
import org.andengine.entity.scene.Scene;

/**
 * 
 * This class defines the basic structure for a scene so it can be managed by the SceneManager.
 * It stores the game activity in order to get access to things like buffers and the engine.
 * As well as the same SceneManager that would create it, in that way, the SceneManager has
 * knowledge over all the scenes, and the scenes also know the SceneManager, asking "him" to
 * make a scene change.
 * 
 * @author Joaquinrok
 *
 */
public abstract class AbstractScene extends Scene{
	
	// ===========================================================
	// Fields
	// ===========================================================
	protected BaseGameActivity	_activity;
	protected SceneManager		_manager;
	protected Camera			_camera;
	protected Engine			_engine;
	
	// ===========================================================
	// Constructor
	// ===========================================================
	/**
	 * The constructor of the AbstractScene stores the activity and SceneManager
	 * and then executes the other methods in order,
	 * those methods are just for the sake of organizing the scene execution.
	 * 
	 * @param activity
	 */
	public AbstractScene(BaseGameActivity activity, SceneManager manager){
		this._activity = activity;
		this._manager = manager;
		this._engine = activity.getEngine();
		
		this.createInicialResources();
		this.loadResources();
		this.applyResources();
		this.registerUpdateHandler();
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	/**
	 * This is where the initial resources should be created,
	 * the kind of things you would normally put in the Scene's constructor
	 * 
	 * 	(you cannot put them there because the super constructor is executed first,
	 *	 which itself runs all the other methods, so any code placed in a SubClass's constructor
	 *	 would be executed last, instead of fist of all, causing lots of problems).
	 */
	protected void createInicialResources(){
	}
	
	protected void loadResources() {
	}

	protected void applyResources() {
	}

	protected void registerUpdateHandler() {
	}

	// ===========================================================
	// Getters & Setters
	// ===========================================================
	protected VertexBufferObjectManager getVertexBufferObjectManager(){
		return this._activity.getVertexBufferObjectManager();}
	
	protected TextureManager getTextureManager(){
		return this._activity.getTextureManager();}
	
	protected Camera getCamera(){
		return this._activity.getEngine().getCamera();}

}
