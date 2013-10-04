package com.jrok.sceneman.scenes;

import org.andengine.ui.activity.BaseGameActivity;

public class EmptyScene extends AbstractScene{

	// ===========================================================
	// Fields
	// ===========================================================

	
	// ===========================================================
	// Constructor
	// ===========================================================
	public EmptyScene(BaseGameActivity activity, SceneManager manager) {
		super(activity, manager);
	}
	
	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	protected void createInicialResources(){
	}
	
	@Override
	protected void loadResources() {		
	}

	@Override
	protected void applyResources() {
	}

	@Override
	protected void registerUpdateHandler() {
		
	}
	

}
