package com.jrok.sceneman.scenes;

import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.extension.physics.box2d.PhysicsConnector;
import org.andengine.extension.physics.box2d.PhysicsFactory;
import org.andengine.extension.physics.box2d.PhysicsWorld;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.region.TextureRegion;
import org.andengine.ui.activity.BaseGameActivity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.jrok.sceneman.scenes.SceneManager.SceneType;

/**
 * 
 * World Scene, representing a normal Level, with some physics in it.
 *   as a sub-class of AbstractScene it already has the activity and SceneManager store in
 *   variables for their use.
 *   
 * Also, by calling the AbstractScene constructor in its constructor, it will run the
 *   other methods in order, loading textures, creating bodies, and finally going
 *   back to the TITLE scene after some seconds.
 * 
 * @author joaquinrok
 *
 */
public class WorldScene extends AbstractScene{

	// ===========================================================
	// Fields
	// ===========================================================
	
	protected PhysicsWorld 		_physicsWorld;

	private BitmapTextureAtlas	_mBitmapTextureAtlas;
	private TextureRegion		_mBoxFaceTextureRegion;
	private TextureRegion		_mBoxTextureRegion;
	
	
	// ===========================================================
	// Constructor
	// ===========================================================
	public WorldScene(BaseGameActivity activity, SceneManager manager) {
		super(activity, manager);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	protected void createInicialResources(){
		_physicsWorld = new PhysicsWorld(new Vector2(0, 15), true);
		this.registerUpdateHandler(_physicsWorld);
	}
	
	@Override
	protected void loadResources() {	
		
		// Create Textures
		// Its using the face_box.png and box.png in assests/gfx
		//    the images come in the AndEngineExamples
		
		this._mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(),256, 256, TextureOptions.DEFAULT );
		this._mBoxFaceTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset( this._mBitmapTextureAtlas,
				this._activity, "gfx/face_box.png", 0, 0 );
		this._mBoxTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this._mBitmapTextureAtlas,
				this._activity, "gfx/box.png",0,32);
		
		this._activity.getEngine().getTextureManager().loadTexture( this._mBitmapTextureAtlas );
	}

	@Override
	protected void applyResources() {
		
		this.setBackground(new Background(0.7f,0.7f,0.9f));
		
		//Create Sprites
		final Sprite boxFaceSprite = new Sprite( 400, 400, this._mBoxFaceTextureRegion, this.getVertexBufferObjectManager());
		final Sprite boxSprite = new Sprite(420,50,this._mBoxTextureRegion, this.getVertexBufferObjectManager());
		
		//Create the bodies and attach them to the sprites
		FixtureDef boxFixture = PhysicsFactory.createFixtureDef(1f, 0.4f, 0.3f);
		
		Body boxBody = PhysicsFactory.createBoxBody(_physicsWorld, boxSprite, BodyType.DynamicBody, boxFixture);
		_physicsWorld.registerPhysicsConnector(new PhysicsConnector(boxSprite, boxBody, true, true));
		Body boxBody2 = PhysicsFactory.createBoxBody(_physicsWorld, boxFaceSprite, BodyType.DynamicBody, boxFixture);
		_physicsWorld.registerPhysicsConnector(new PhysicsConnector(boxFaceSprite, boxBody2, true, true));
		
		//Create the ground
		final Rectangle ground = new Rectangle(0, this.getCamera().getHeight() - 2, this.getCamera().getWidth(), 2, this.getVertexBufferObjectManager());
		final FixtureDef wallFixtureDef = PhysicsFactory.createFixtureDef(1, 0.3f, 0.5f);
		PhysicsFactory.createBoxBody(this._physicsWorld, ground, BodyType.StaticBody, wallFixtureDef);
		this.attachChild(ground);
		 
		this.attachChild(boxSprite);
		this.attachChild(boxFaceSprite);
	}

	@Override
	protected void registerUpdateHandler() {
		
		// This scene runs for 6 seconds, and then makes the Scene Manager go the the TITLE scene
		//   this way you can change from one scene to another
		_engine.registerUpdateHandler(new TimerHandler(6f, new ITimerCallback() 
		{
			public void onTimePassed(final TimerHandler pTimerHandler) 
			{
				_engine.unregisterUpdateHandler(pTimerHandler);
				_manager.setCurrentScene(SceneType.TITLE);
				
			}
		}));
		
	}
	


}
