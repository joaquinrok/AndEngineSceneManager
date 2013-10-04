package com.jrok.sceneman.scenes;

import org.andengine.engine.camera.Camera;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.opengl.util.GLState;
import org.andengine.ui.activity.BaseGameActivity;


/**
 * Just a Splash Screne, it loads a splash.png from "assets/gfx" and displays it.
 * The changing logic to the next scene is done outside, by the activity
 * 
 * @author joaquinrok
 *
 */
public class SplashScene extends AbstractScene{

	// ===========================================================
	// Fields
	// ===========================================================
	
	protected PhysicsWorld 		_physicsWorld;

	private BitmapTextureAtlas 	splashTextureAtlas;
	private TextureRegion 		splashTextureRegion;
	
	// ===========================================================
	// Constructor
	// ===========================================================
	public SplashScene(BaseGameActivity activity, SceneManager manager) {
		super(activity, manager);}
	
	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	protected void createInicialResources(){
	}
	
	@Override
	protected void loadResources() {		
		splashTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 256, 256, TextureOptions.DEFAULT);
		splashTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(splashTextureAtlas, this._activity, "gfx/splash.png", 0, 0);
		splashTextureAtlas.load();
	}

	@Override
	protected void applyResources() {
		
		this.setBackground(new Background(1, 1, 1));
		Sprite splash = new Sprite(0, 0, splashTextureRegion, this.getVertexBufferObjectManager())
		{
			@Override
			protected void preDraw(GLState pGLState, Camera pCamera) 
			{
				super.preDraw(pGLState, pCamera);
				pGLState.enableDither();
			}
		};
		splash.setScale(1.5f);
		splash.setPosition((this.getCamera().getWidth() - splash.getWidth()) * 0.5f, (this.getCamera().getHeight() - splash.getHeight()) * 0.5f);
		this.attachChild(splash);
	}

	@Override
	protected void registerUpdateHandler() {
	}
	


}
