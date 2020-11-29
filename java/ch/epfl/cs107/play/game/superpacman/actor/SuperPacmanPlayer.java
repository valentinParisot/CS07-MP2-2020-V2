package ch.epfl.cs107.play.game.superpacman.actor;

import java.awt.Color;
import java.util.Collections;
import java.util.List;

import ch.epfl.cs107.play.game.actor.TextGraphics;
import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.actor.Sprite;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.rpg.actor.Player;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.math.Vector;
import ch.epfl.cs107.play.window.Button;
import ch.epfl.cs107.play.window.Canvas;
import ch.epfl.cs107.play.window.Keyboard;

public class SuperPacmanPlayer extends Player   {
	
	
	
	
	
	private  DiscreteCoordinates PLAYER_SPAWN_POSITION;
	public Orientation desiredOrientation;
	private float hp;
	private TextGraphics message;
	private Sprite sprite;
	/// Animation duration in frame number
    private final static int SPEED= 8; //constante vitesse
	/**
	 * Demo actor
	 * 
	 */
   
    
    
    
    
	public SuperPacmanPlayer(Area owner, DiscreteCoordinates coordinates) {
		super(owner, Orientation.LEFT, coordinates);
		this.hp = 10;
		message = new TextGraphics(Integer.toString((int)hp), 0.4f, Color.BLUE);
		message.setParent(this);
		message.setAnchor(new Vector(-0.3f, 0.1f));
		sprite = new Sprite("superpacman/bonus", 1.f, 1.f,this);

		resetMotion();
	}
	 
	 @Override
	    public void update(float deltaTime) {
		
			 Keyboard keyboard = getOwnerArea().getKeyboard();
			  
	        moveOrientate(Orientation.LEFT, keyboard.get(Keyboard.LEFT));
	        moveOrientate(Orientation.UP, keyboard.get(Keyboard.UP));
	        moveOrientate(Orientation.RIGHT, keyboard.get(Keyboard.RIGHT));
	        moveOrientate(Orientation.DOWN, keyboard.get(Keyboard.DOWN));
	            
	        
			if(isDisplacementOccurs() && getOwnerArea().canEnterAreaCells((Interactable)this, (Collections.singletonList(getCurrentMainCellCoordinates().jump(desiredOrientation.toVector()))))) {
	        	orientate(desiredOrientation);
	        	move(SPEED);
	        }
	       
	         super.update(deltaTime);
	       
	    }

	    /**
	     * Orientate or Move this player in the given orientation if the given button is down
	     * @param orientation (Orientation): given orientation, not null
	     * @param b (Button): button corresponding to the given orientation, not null
	     */
	    private void moveOrientate(Orientation orientation, Button b){
	    
	        if(b.isDown()) {
	            if(getOrientation() == orientation) {desiredOrientation = orientation;} 
	           
	        }
	    }
	    /**
	     * Leave an area by unregister this player
	     */
	    public void leaveArea(){
	        getOwnerArea().unregisterActor(this);
	    }

	    /**
	     *
	     * @param area (Area): initial area, not null
	     * @param position (DiscreteCoordinates): initial position, not null
	     */
	    public void enterArea(Area area, DiscreteCoordinates position){
	        area.registerActor(this);
	        area.setViewCandidate(this);
	        setOwnerArea(area);
	        setCurrentPosition(position.toVector());
	        resetMotion();
	    }
    
	@Override
	public void draw(Canvas canvas) {
		sprite.draw(canvas);	
		message.draw(canvas);
	}

	public boolean isWeak() {
		return (hp <= 0.f);
	}

	public void strengthen() {
		hp = 10;
	}

	///Ghost implements Interactable

	@Override
	public boolean takeCellSpace() {    //traversable??????
		return false;
	}

	@Override
	public boolean isCellInteractable() {
		return false;
	}

	@Override
	public boolean isViewInteractable() {
		return true;
	}
	@Override
	public List<DiscreteCoordinates> getCurrentCells() {
		return Collections.singletonList(getCurrentMainCellCoordinates());
	}

	@Override
	public void acceptInteraction(AreaInteractionVisitor v) {
	}




	@Override
	public List<DiscreteCoordinates> getFieldOfViewCells() {
		
		return null;
	}




	@Override
	public boolean wantsCellInteraction() {
	
		return true;
	}




	@Override
	public boolean wantsViewInteraction() {
		
		return false;
	}




	@Override
	public void interactWith(Interactable other) {
		
		
	}
}


