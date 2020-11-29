package ch.epfl.cs107.play.game.superpacman.area;

import ch.epfl.cs107.play.game.areagame.Area;
import ch.epfl.cs107.play.game.areagame.AreaBehavior;
import ch.epfl.cs107.play.game.areagame.actor.Interactable;
import ch.epfl.cs107.play.game.areagame.actor.Orientation;
import ch.epfl.cs107.play.game.areagame.handler.AreaInteractionVisitor;
import ch.epfl.cs107.play.game.superpacman.actor.Wall;
import ch.epfl.cs107.play.game.tutosSolution.actor.GhostPlayer;
import ch.epfl.cs107.play.math.DiscreteCoordinates;
import ch.epfl.cs107.play.window.Window;

public class SuperPacmanBehavior extends AreaBehavior{
	
	
	
	private Wall wall;
	
	
	public enum SuperPacmanCellType{
		//https://stackoverflow.com/questions/25761438/understanding-bufferedimage-getrgb-output-values
		NONE(0), // never used as real content
		WALL(-16777216), //black 
		FREE_WITH_DIAMOND(-1), //white 
		FREE_WITH_BLINKY(-65536), //red 
		FREE_WITH_PINKY(-157237), //pink 
		FREE_WITH_INKY(-16724737), //cyan 
		FREE_WITH_CHERRY(-36752), //light red 
		FREE_WITH_BONUS(-16478723), //light blue 
		FREE_EMPTY(-6118750); // sort of gray

		final int type;
		

		SuperPacmanCellType(int type){
			this.type = type;
			
		}

		public static SuperPacmanCellType toType(int type){
			for(SuperPacmanCellType ict : SuperPacmanCellType.values()){
				if(ict.type == type)
					return ict;
			}
			// When you add a new color, you can print the int value here before assign it to a type
			System.out.println(type);
			return NONE;
		
	}
	}
	/**
	 * Default Tuto2Behavior Constructor
	 * @param window (Window), not null
	 * @param name (String): Name of the Behavior, not null
	 */
	public SuperPacmanBehavior(Window window, String name){
		super(window, name);
		int height = getHeight();
		int width = getWidth();
		for(int y = 0; y < height; y++) {
			for (int x = 0; x < width ; x++) {
				SuperPacmanCellType color = SuperPacmanCellType.toType(getRGB(height-1-y, x));
				setCell(x,y, new SuperPacmanCell(x,y,color));
			}
		}
	}
	
	protected boolean[][] among( int x,int y ){
		
		boolean [][] a = new boolean [3][3];
		
		for(int i = 0 ; i < 3 ; ++i) {
			for(int j = 0 ; j < 3 ; ++j) {
				if (((SuperPacmanCell)getCell(x+j-1,y-i+1)).type == SuperPacmanCellType.WALL) {
					a [i][j] = true;
				}
				else a [i][j] = false;
			}
		}
		a [1][1] = true;
		return a;
		
		
		
		
	}
	
	protected void registerActors(Area area) {
		
		int height = getHeight();
		int width = getWidth();
		for(int x = 0; x < height; x++) {
			for (int y = 0; y < width ; y++) {
				if (((SuperPacmanCell)getCell(x,y)).type == SuperPacmanCellType.WALL) {
					wall = new Wall(area, new DiscreteCoordinates(x,y) , among(x,y));
					area.registerActor(new Wall(area, new DiscreteCoordinates(x,y) , among(x,y)));
					
				}
			}
		
		}
		
		}
	
	
	/**
	 * Cell adapted to the Tuto2 game
	 */
	public class SuperPacmanCell extends AreaBehavior.Cell {
		/// Type of the cell following the enum
		private final SuperPacmanCellType type;
		
		/**
		 * Default Tuto2Cell Constructor
		 * @param x (int): x coordinate of the cell
		 * @param y (int): y coordinate of the cell
		 * @param type (EnigmeCellType), not null
		 */
		public  SuperPacmanCell(int x, int y, SuperPacmanCellType type){
			super(x, y);
			this.type = type;
		}
	
		@Override
		protected boolean canLeave(Interactable entity) {
			return true;
		}

		@Override
		protected boolean canEnter(Interactable entity) {
			
			
    		return !hasNonTraversableContent();
			
			
	    }

	    
		@Override
		public boolean isCellInteractable() {
			return true;
		}

		@Override
		public boolean isViewInteractable() {
			return false;
		}

		@Override
		public void acceptInteraction(AreaInteractionVisitor v) {
		}

	}
	
}

		

