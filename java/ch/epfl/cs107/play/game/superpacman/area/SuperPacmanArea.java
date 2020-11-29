package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.superpacman.SuperPacman;
import ch.epfl.cs107.play.io.FileSystem;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

abstract class SuperPacmanArea extends Area{

	private  SuperPacmanBehavior behavior;
	

    /**
     * Create the area by adding it all actors
     * called by begin method
     * Note it set the Behavior as needed !
     */
    protected  void createArea() {
    	behavior.registerActors(this);

    }
    
    
    

    /// EnigmeArea extends Area

    @Override
    public final float getCameraScaleFactor() {
        return SuperPacman.CAMERA_SCALE_FACTOR;
    }

    /// Demo2Area implements Playable

    @Override
    public boolean begin(Window window, FileSystem fileSystem) {
        if (super.begin(window, fileSystem)) {
            // Set the behavior map
        	
        	behavior = new SuperPacmanBehavior(window, getTitle());
            setBehavior(behavior);
            createArea();
            return true;
        }
        return false;
    }
}

