package battleship.players;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import battleship.handling.Settings;
import battleship.ships.Ship;
import battleship.utilities.Coordinates;

/**
 * This class lets you make your own AI for a computer-controlled player.
 * Read the notes below to find out what you should rewrite.
 */
public class CustomComputerOpponent extends ComputerOpponent {

    /**
     * This is this class' constructor.
     * @param name The player's name.
     */
    public CustomComputerOpponent(String name) {
        super(name); // This calls the base class' constructor, which calls its base class' constructor. You should keep this line.
    }

    /**
	 toString() overrides an object's behavior when it is cast to or used as a string.
     */
    @Override
    public String toString() {
        return name + " (Custom AI)";
    }

    // ------------------------------------------------------------------------------------------------
    // In runPlayerTypeSpecificShipPlacement() you can change how the AI places its ships.
    // ------------------------------------------------------------------------------------------------

    /**
     * This method handles the placement of all ships available to this player.
     * It overrides ComputerOpponent's default behavior, which is to place all ships randomly.
     * If you want to keep that behaviour, you can call super.placeShipsRandomly().
     * 
     * If you want to write your own ship placement procedure, these are the rules:
     * 1. You must place all the ships contained in the player's fleet's ship list. You can get this list by calling fleet.getShips().
     * 2. You must place every ship exactly once. You must not place any ship twice, or leave any ships unplaced.
     * 3. Ships may not overlap.
     * 4. Ships must be entirely within the playing field. To get the playing field's extents, get Settings.PLAYING_FIELD_HORIZONTAL_SIZE and Settings.PLAYING_FIELD_VERTICAL_SIZE. 
     *
     * Breaking any of these rules will throw an exception and abort the game.
     * 
     * To actually place a ship, call placeShip(Ship ship, Coordinates coordinates, int length, Orientation orientation).
     * To check whether a spot is free, call fleet.locationIsFree(Coordinates coordinates, int length, Orientation orientation).
     */
    @Override
    public void runPlayerTypeSpecificShipPlacement() throws Exception {
        super.runPlayerTypeSpecificShipPlacement();	// This calls the base class' implementation of this method, in which all ships are placed randomly.
    }

    // ------------------------------------------------------------------------------------------------
    // In the methods below you can let your AI decide its next move.
    // ------------------------------------------------------------------------------------------------

    /**
     * This method is called whenever your turn begins, but before being prompted to make your shot.
     * You could use it to print some information about your AI's state.
     */
    @Override
    public void yourTurnHasBegun() {
        // TODO Auto-generated method stub
    }
    
    private int i=0;
    private int count=1;
    private List<Coordinates> possibleTargets = new ArrayList<Coordinates>();
    private List<Coordinates>  lastTargetHit= new ArrayList<Coordinates>();
    private List<Coordinates> lastTarget = new ArrayList<Coordinates>();
    int [] lastHitNum= new int [Settings.PLAYING_FIELD_HORIZONTAL_SIZE * Settings.PLAYING_FIELD_VERTICAL_SIZE * 10];
    private List<Coordinates> rejectedCoordinates = new ArrayList<Coordinates>();

    @Override
    public Coordinates promptToFireShot() throws Exception {

        return shoot();
    }
    
    public void huntingMode (Coordinates coordinates) {
    	int x=coordinates.x;
    	int y=coordinates.y;
    	
    	List<Coordinates> closeTargets = new ArrayList<Coordinates>();
    	
    	
    	Coordinates leftT= new Coordinates (x-1, y);
    	Coordinates rightT= new Coordinates (x+1, y);
    	Coordinates upT= new Coordinates (x, y-1);
    	Coordinates downT= new Coordinates (x, y+1);
    	closeTargets.add(0, leftT);
    	closeTargets.add(1, rightT);
    	closeTargets.add(2, upT);
    	closeTargets.add(3, downT);
    	
    	lastTargetHit.add(coordinates);
    	
    	if (lastTargetHit.size()<=1) {
    	for (int j=0; j<closeTargets.size(); j++) { 
    		if (0<=closeTargets.get(j).x && 0<=closeTargets.get(j).y) {
    			if (closeTargets.get(j).x <10 && closeTargets.get(j).y <10) {
    				
    					possibleTargets.add(closeTargets.get(j));
    				
    		  }
    	    }
    	  }
    	}
    	else {
    	
    	
    	if ((lastTargetHit.get(i).x==lastTargetHit.get(i-1).x) && (lastTargetHit.get(i).y<lastTargetHit.get(i-1).y)) {
    		
    		
            //possibleTargets = new ArrayList<Coordinates>();
			
            upT= new Coordinates (lastTargetHit.get(i-1).x, lastTargetHit.get(i-1).y+1);
            downT= new Coordinates (lastTargetHit.get(i).x, lastTargetHit.get(i).y-1);
            if (0<=upT.x && 0<=upT.y) {
    			if (upT.x <10 && upT.y <10) 
            possibleTargets.add(upT);
            }
            if (0<=downT.x && 0<=downT.y) {
    			if (downT.x <10 && downT.y <10) 
            possibleTargets.add(downT);
            }
    	}
            
        if ((lastTargetHit.get(i).x==lastTargetHit.get(i-1).x) && (lastTargetHit.get(i).y>lastTargetHit.get(i-1).y)) {
        	
               // possibleTargets = new ArrayList<Coordinates>();
    			
                downT= new Coordinates (lastTargetHit.get(i-1).x, lastTargetHit.get(i-1).y-1);
                upT= new Coordinates (lastTargetHit.get(i).x, lastTargetHit.get(i).y+1);
                if (0<=upT.x && 0<=upT.y) {
        			if (upT.x <10 && upT.y <10) 
                possibleTargets.add(upT);
                }
                if (0<=downT.x && 0<=downT.y) {
        			if (downT.x <10 && downT.y <10) 
                possibleTargets.add(downT);
                }
            
					}
		if ((lastTargetHit.get(i).y==lastTargetHit.get(i-1).y) && (lastTargetHit.get(i).x>lastTargetHit.get(i-1).x)) {
			//possibleTargets = new ArrayList<Coordinates>();
			Coordinates right = new Coordinates (lastTargetHit.get(i).y, lastTargetHit.get(i).x+1);
            Coordinates left = new Coordinates (lastTargetHit.get(i-1).y, lastTargetHit.get(i-1).x-1);
            if (0<=right.x && 0<=right.y) {
    			if (right.x <10 && right.y <10)
            possibleTargets.add(right);
            }
            if (0<=left.x && 0<=left.y) {
    			if (left.x <10 && left.y <10)
            possibleTargets.add(left);
            }
            
			
		  }
		
		if ((lastTargetHit.get(i).y==lastTargetHit.get(i-1).y) && (lastTargetHit.get(i).x<lastTargetHit.get(i-1).x)) {
			// possibleTargets = new ArrayList<Coordinates>();
			Coordinates left = new Coordinates (lastTargetHit.get(i).y, lastTargetHit.get(i).x-1);
            Coordinates right = new Coordinates (lastTargetHit.get(i-1).y, lastTargetHit.get(i-1).x+1);
            if (0<=right.x && 0<=right.y) {
    			if (right.x <10 && right.y <10)
            possibleTargets.add(right);
            }
            if (0<=left.x && 0<=left.y) {
    			if (left.x <10 && left.y <10)
            possibleTargets.add(left);
            }
            
		  }
		
    	}
    	
    	
    	
    	i++;
    	
    	/*int size = possibleTargets.size();
    	
    	if (lastTargetHit.size()>2) {
    		if ((lastTargetHit.get(i).x==lastTargetHit.get(i-1).x) && ((lastTargetHit.get(i).y==lastTargetHit.get(i-1).y-1) || (lastTargetHit.get(i).y==lastTargetHit.get(i-1).y+1))) {
    			for (int k = 0; k <size; k++) {
    				if (((possibleTargets.get(k).x==lastTargetHit.get(i-1).x-1) && (possibleTargets.get(k).y==lastTargetHit.get(i-1).y)) || ((possibleTargets.get(k).y==lastTargetHit.get(i-1).y) && (possibleTargets.get(k).x==lastTargetHit.get(i-1).x+1))) {
    					rejectedCoordinates.add(possibleTargets.get(k));
    				    
				}
    				if (((possibleTargets.get(k).x==lastTargetHit.get(i).x-1) && (possibleTargets.get(k).y==lastTargetHit.get(i).y)) || ((possibleTargets.get(k).y==lastTargetHit.get(i).y) && (possibleTargets.get(k).x==lastTargetHit.get(i).x+1))) {
    					rejectedCoordinates.add(possibleTargets.get(k));
    				    
    				}
    			}   				
    		}
    		
    		if ((lastTargetHit.get(i).y==lastTargetHit.get(i-1).y) && ((lastTargetHit.get(i).x==lastTargetHit.get(i-1).x-1) || (lastTargetHit.get(i).x==lastTargetHit.get(i-1).x+1))) {
    			for (int l = 0; l < size; l++) {
    				if (((possibleTargets.get(l).y==lastTargetHit.get(i-1).y-1)  && (possibleTargets.get(l).x==lastTargetHit.get(i-1).x))  ||  ((possibleTargets.get(l).x==lastTargetHit.get(i-1).x) && (possibleTargets.get(l).y==lastTargetHit.get(i-1).y+1))) {
    					rejectedCoordinates.add(possibleTargets.get(l));
    				
				}
    				if (((possibleTargets.get(l).x==lastTargetHit.get(i).x) && (possibleTargets.get(l).y==lastTargetHit.get(i).y-1)) || ((possibleTargets.get(l).y==lastTargetHit.get(i).y+1) && (possibleTargets.get(l).x==lastTargetHit.get(i).x))) {
    					rejectedCoordinates.add(possibleTargets.get(l));
    					
    				}
    				
    			}
    				
    		}
    		System.out.println("Removed coordinates");
    		
    	} */
    	
    }
    
    protected Coordinates shoot() throws Exception {
    	Random random = new Random();


        
        int x=Settings.PLAYING_FIELD_HORIZONTAL_SIZE;
        int y=Settings.PLAYING_FIELD_VERTICAL_SIZE;
        
        lastHitNum[0]=0;
    	
    	Coordinates coordinates = new Coordinates(
        		random.nextInt(x),
                random.nextInt(y));
    	
    	
        
        for (int attempts = Settings.PLAYING_FIELD_HORIZONTAL_SIZE * Settings.PLAYING_FIELD_VERTICAL_SIZE * 10; attempts > 0; attempts--) { 
        	
        	lastHitNum[count]=getHits();
        	if (lastHitNum[count]>lastHitNum[count-1]) {
        		
                huntingMode(lastTarget.get(0));
                
                
               }
        	
        	
        	
        	if (possibleTargets.isEmpty()) {
        		lastTargetHit.clear();
        		i=0;
        		
        		if (attempts>975) {
        			do {
            			coordinates = new Coordinates(
                        		random.nextInt(x/2)+2,
                                random.nextInt(y/2)+2);
            		} while (((x%2)!=0 && (y%2)!=0) && ((x%2)==0 && (y%2)==0));
        			
        		}
        		else {
        		do {
        			coordinates = new Coordinates(
                    		random.nextInt(x),
                            random.nextInt(y));
        		} while (((x%2)!=0 && (y%2)==0) && ((x%2)==0 && (y%2)==0));
            	
               }
        		
        	}
            
            else {
            	coordinates = possibleTargets.get(0);
            	possibleTargets.remove(0);
                
            }
            boolean coordinatesHaveNotYetBeenFiredUpon = resultObservations[coordinates.x][coordinates.y] == null;
            boolean fireAtTheseCoordinates = coordinatesHaveNotYetBeenFiredUpon;
            if (coordinatesHaveNotYetBeenFiredUpon == false) {
                rejectedCoordinates.add(coordinates);
                attempts--;
            }
            
            if (fireAtTheseCoordinates) {
            	
            	lastTarget.add(0, coordinates);
            
            	 
            	for (Coordinates targets : possibleTargets) {
          	      System.out.println(targets + "targets");
          	}
            	for (Coordinates thisones : lastTargetHit) {
            	      System.out.println(thisones + "hits");
            	}
            	count++;
            
            	return coordinates;
            }
                
        }
        
        throw new Exception("Attempts depleted, no firing solution found.");
    }
    
    
    
    //public void 
    


    // ------------------------------------------------------------------------------------------------
    // The methods below here are called when either player has fired a shot.
    // Use these to see how your strategy is working out, and to adjust it.
    // The calls to base functions (super.something) feed information to the base player class' results observation.
    // You don't have to use it, but be aware:
    // Without calling these base functions at the appropriate times, the default results observation will not work and you have to make your own.
    // ------------------------------------------------------------------------------------------------

    @Override
    public void youHaveBeenMissed(Coordinates coordinates) {
        // TODO Auto-generated method stub
    }

    @Override
    public void youHaveBeenHit(Coordinates coordinates, Ship ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void yourShipHasBeenSunk(Coordinates lastHit, Ship ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void youHaveMissed(Coordinates coordinates) {
        super.youHaveMissed(coordinates);
        // TODO Auto-generated method stub
    }

    @Override
    public void youHaveHitYourTarget(Coordinates coordinates) {
        super.youHaveHitYourTarget(coordinates);
        // TODO Auto-generated method stub
    }

    @Override
    public void youHaveSunkAnEnemyShip(Coordinates lastHit, Ship ship) {
        super.youHaveSunkAnEnemyShip(lastHit, ship);
        // TODO Auto-generated method stub
    }

    @Override
    public void youKeepFiringAtASunkShip(Coordinates lastHit, Ship ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void youKeepFiringAtTheSameHole(Coordinates lastHit) {
        // TODO Auto-generated method stub
    }

    @Override
    public void youKeepFiringAtNothing(Coordinates lastHit) {
        // TODO Auto-generated method stub
    }

    @Override
    public void theEnemyKeepsFiringAtASunkShip(Coordinates lastHit, Ship ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void theEnemyKeepsFiringAtTheSameHole(Coordinates lastHit, Ship ship) {
        // TODO Auto-generated method stub
    }

    @Override
    public void theEnemyKeepsFiringAtNothing(Coordinates lastHit) {
        // TODO Auto-generated method stub
    }
}
