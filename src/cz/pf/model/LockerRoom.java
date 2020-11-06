package cz.pf.model;

import cz.pf.Sound;

public class LockerRoom extends Room {
    public LockerRoom(String description){
        super(description);
        door = new Sound();
//        door.file = "D:\\Filip\\Videa\\sound3.wav";
    }
    Item keyNorth;
    Item keySouth;
    Item keyWest;
    Item keyEast;
    Item keyUp;
    Item keyDown;
    Sound door;
    Room lockedNorth;
    Room lockedEast;
    Room lockedWest;
    Room lockedSouth;
    Room lockedUp;
    Room lockedDown;
    Room lockedNorthDouble;
    Room lockedEastDouble;
    Room lockedWestDouble;
    Room lockedSouthDouble;

    private void printKey(Item key, Room room){
        if(key!=null && room == null) {
            System.out.println("Jsou tu zamčený dveře, potřebuješ tenhle klíč:");
            System.out.println(key.description);
        }
    }
    public void printRoom(Player player) {
        super.printRoom(player);
        if(player.getItems().contains(keyNorth) && north == null){
            System.out.println("Odemkl jsi přední dveře, můžeš jít dopředu.");
            this.setNorth(this.lockedNorth);
            if(door!=null){ 
            	door.play();
            }
        }
        if(player.getItems().contains(keySouth) && south == null){
            System.out.println("Odemkl jsi zadní dveře, můžeš jít dozadu.");
            this.setSouth( this.lockedSouth);
            if(door!=null){ 
            	door.play();
            }
        }
        if(player.getItems().contains(keyWest) && west == null){
            System.out.println("Odemkl jsi levé dveře, můžeš jít doleva.");
            this.setWest(this.lockedWest);
            if(door!=null){ 
            	door.play();
            }
        }
        if(player.getItems().contains(keyEast) && east == null){
            System.out.println("Odemkl jsi pravé dveře, můžeš jít doprava.");
            this.setEast( this.lockedEast);
            if(door!=null){ 
            	door.play();
            }
        }
        if(player.getItems().contains(keyUp) && up == null){
            System.out.println("Odemkl jsi schodiště nahoru, můžeš jít nahoru.");
            this.setUp( this.lockedUp);
            if(door!=null){ 
            	door.play();
            }
        }
        if(player.getItems().contains(keyDown) && down == null){
            System.out.println("Odemkl jsi schodiště dolu, můžeš jít dolu.");
            this.setDown( this.lockedDown);
            if(door!=null){ 
            	door.play();
            }
        }

        printKey(keyNorth, north);
        printKey(keySouth, south);
        printKey(keyWest, west);
        printKey(keyEast, east);
        printKey(keyUp, up);
        printKey(keyDown, down);


    }

        public Room play(String response, Player player) {
        return super.play(response,player);
    }

    public Item getKeyNorth() {
        return keyNorth;
    }

    public void setKeyNorth(Item keyNorth) {
        this.keyNorth = keyNorth;
    }

    public Item getKeySouth() {
        return keySouth;
    }

    public void setKeySouth(Item keySouth) {
        this.keySouth = keySouth;
    }

    public Item getKeyWest() {
        return keyWest;
    }

    public void setKeyWest(Item keyWest) {
        this.keyWest = keyWest;
    }

    public Item getKeyEast() {
        return keyEast;
    }

    public void setKeyEast(Item keyEast) {
        this.keyEast = keyEast;
    }

    public Item getKeyUp() {
        return keyUp;
    }

    public void setKeyUp(Item keyUp) {
        this.keyUp = keyUp;
    }

    public Item getKeyDown() {
        return keyDown;
    }

    public void setKeyDown(Item keyDown) {
        this.keyDown = keyDown;
    }

    public Room getLockedNorth() {
        return lockedNorth;
    }

    public void setLockedNorth(Room lockedNorth) {
        this.lockedNorth = lockedNorth;
    }

    public Room getLockedEast() {
        return lockedEast;
    }

    public void setLockedEast(Room lockedEast) {
        this.lockedEast = lockedEast;
    }

    public Room getLockedWest() {
        return lockedWest;
    }

    public void setLockedWest(Room lockedWest) {
        this.lockedWest = lockedWest;
    }

    public Room getLockedSouth() {
        return lockedSouth;
    }

    public void setLockedSouth(Room lockedSouth) {
        this.lockedSouth = lockedSouth;
    }

    public Room getLockedUp() {
        return lockedUp;
    }

    public void setLockedUp(Room lockedUp) {
        this.lockedUp = lockedUp;
    }

    public Room getLockedDown() {
        return lockedDown;
    }

    public void setLockedDown(Room lockedDown) {
        this.lockedDown = lockedDown;
        
       
    }
    
 /*	public Room getLockedNorthDouble(Room lockedNorthDouble) {
 		return lockedNorthDouble;
 		
 		
 	}
 	
 	public void getLockedNorthDouble(Room lockedNorthDouble) {
 		this.lockedNorthDouble;
 		
 		
	}
 	
 	public Room getLockedNorthDouble(Room lockedNorthDouble) {
 		return lockedNorthDouble;
 		
 		
	}
 	
 	public void getLockedNorthDouble(Room lockedNorthDouble) {
 		this.lockedNorthDouble;
 		
 		
	}
 	
 	public Room getLockedNorthDouble(Room lockedNorthDouble) {
 		return lockedNorthDouble;
 		
 		
	}
 	
 	public void getLockedNorthDouble(Room lockedNorthDouble) {
 		this.lockedNorthDouble;
 		
 		
	}
	
	public Room getLockedNorthDouble(Room lockedNorthDouble) {
	        return lockedNorthDouble;
	        
	        
	}
 	
 	public void getLockedNorthDouble(Room lockedNorthDouble) {
	        return lockedNorthDouble;
	        
	        
	}
 	*/
}
