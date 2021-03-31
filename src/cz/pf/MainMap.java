package cz.pf;

import cz.maze.main.BarrierCanvas;
import cz.pf.model.Entity;
import cz.pf.model.Item;
import cz.pf.model.Leopard;

import java.awt.Color;
import java.awt.image.BufferedImage;

import cz.pf.model.LockerRoom;
import cz.pf.model.Player;
import cz.pf.model.Room;
import cz.pf.model.Strazce;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainMap {
		public static BufferedImage loadImage(String imageName){
			
			InputStream imageInputStreamKey = BarrierCanvas.class.getResourceAsStream(imageName);
			try {
				BufferedImage imgKey = ImageIO.read(imageInputStreamKey);
				return imgKey;
			}catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1);
				return null;
				}
		}
	
		public static void main(String args[]) {
		
		Sound welcome = new Sound();
		welcome.file = "zvukvitej.wav";
		welcome.play();
		
		
		/*
		 * main.setNorth(new Room(null, null, null, null,
		 * "A step to the north is getting colder")); main.getNorth().setNorth(new
		 * Room(null, null, null, null,
		 * "A second step to the north is getting even more colder")); Item key = new
		 * Item("Golden Key"); main.getNorth().getNorth().getItems().add(key);
		 * main.getNorth().getNorth().setNorth(new Room(null, null, null, null,
		 * "A third step to the north is nearly freezing"));
		 * main.getNorth().getNorth().getNorth().setEast(new Room(null, null, null,
		 * null, "There is a door. But they are locked!"));
		 * main.getNorth().getNorth().getNorth().getEast().setExit(true);
		 */
		InputStream imageInputStreamKey = BarrierCanvas.class.getResourceAsStream("ItemKeyYellow.png");
		try {
			BufferedImage imgKey = ImageIO.read(imageInputStreamKey);
			Item key = new Item("Klíč ke konci",Color.YELLOW, 1, imgKey);
			ArrayList entityDirectory  = new ArrayList();
			MainMap support=new MainMap();
	    
			Room map=support.createMap(key,entityDirectory);
			support.run(map, key);
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
	}
	
    public Room createFirstFloor (Sound ajajajaj,Item key,ArrayList entityDirectory) {
    	
    	Room startingPosition = new Room(null, null, null, null, null, null, "Stratil jses sám v husté džungli, sanžíš se najít cestu ven");
		startingPosition.setEast(new Room("Banánoví les"));
		startingPosition.getEast().setSouth(new Room("Banánoví les"));
		startingPosition.getEast().getSouth().setSouth(new Room("Hlíněná stezka"));
		startingPosition.getEast().getSouth().getSouth().setEast(new Room("Hlíněná stezka"));
		startingPosition.getEast().getSouth().getSouth().setWest(new Room("Hlíněná stezka"));
		startingPosition.getEast().getSouth().getSouth().getEast().setEast(new Room("Hlíněná stezka"));
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().setNorth(new Room("Hlíněná stezka"));		
		Room soundRoom = new Room("konec Hlíněné stezky");
		
		
		
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().getNorth().setEast(soundRoom);		
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().getNorth().getEast().getItems().add(key);
		
		Leopard leopardFirstFloor = new Leopard();
		entityDirectory.add(leopardFirstFloor);
		Room leopardRoom = new Room("Hlíněná stezka");
		leopardFirstFloor.entityPosition = leopardRoom;
		// leopard.entities.add(leopardFirstFloor);
		startingPosition.getEast().getSouth().getSouth().getWest().setSouth(leopardRoom );
		//startingPosition.getEast().getSouth().getSouth().getWest().getSouth().entities.add(leopard);		
		startingPosition.getEast().getSouth().getSouth().getWest().getSouth().setSouth(new Room("Hlíněná stezka"));
		LockerRoom yellowLockerRoom = new LockerRoom("Hlíněná stezka");
		startingPosition.getEast().getSouth().getSouth().getWest().getSouth().getSouth().setSouth(yellowLockerRoom);
		yellowLockerRoom.setKeyEast(key);
		yellowLockerRoom.setLockedEast(new Room("Temná jeskyně"));   
		yellowLockerRoom.getLockedEast().setEast(new Room("Temná jeskyně"));
		yellowLockerRoom.getLockedEast().getEast().setNorth(new Room("Honil tě Leopard tak ses schoval do jeskyně"));
	return startingPosition;}
    
    
    public Room createSecondFloor (Sound disapointmentSound,Item key,LockerRoom yellowLockerRoom,Item keyh,ArrayList entityDirectory) {
	    yellowLockerRoom.getLockedEast().getEast().getNorth().setDown(new Room("Gratuluji prošel jsi Džunglí, to co se zdálo jako jeskyně je ve skutečnosti tajné sklepení, rozhodl jses ho prozkoumat."));
		Room first = new Room("rozcestí");
		first.setSouth(yellowLockerRoom.getLockedEast().getEast().getNorth().getDown());
		first.setEast(new Room("podivná místnost"));
		first.getEast().setEast(new Room("podivná místnost"));
		first.getEast().getEast().setNorth(new Room("podivná místnost"));
		Room soundBlind = new Room("konec podivné místnosti");
		first.getEast().getEast().getNorth().setEast(soundBlind);
		//soundBlind.ajeje = disapointmentSound;
		Item keyb = new Item("modrej klíč",Color.BLUE, 1, loadImage("ItemKeyBlue.png"));
		first.getEast().getEast().getNorth().getEast().getItems().add(keyb);
		LockerRoom BLR = new LockerRoom("špinavá chodba");
		first.setWest(BLR);
		BLR.setKeyWest(keyb);
		BLR.setLockedWest(new Room("špinavá chodba"));
		LockerRoom BLRT = new LockerRoom("křivý sál");
		first.setNorth(new Room("křivý přechod"));
		
		Strazce strazceSecondFloor = new Strazce();
		entityDirectory.add(strazceSecondFloor);
		Room strazceRoom = new Room("Hlíněná stezka");
		strazceSecondFloor.entityPosition = strazceRoom;
		first.getNorth().setNorth(strazceRoom);
		
		first.getNorth().setNorth(BLRT);
		BLRT.setKeyNorth(keyb);
		LockerRoom orangeLockerRoom = new LockerRoom("křivý sál");
		Item keyo = new Item("oranžovej klíč",Color.ORANGE, 1, loadImage("ItemKeyOrange.png"));
		BLRT.setLockedNorth(orangeLockerRoom);
		orangeLockerRoom.setKeyEast(keyo); 
		orangeLockerRoom.setLockedEast(new Room("křivý sál")); 
		orangeLockerRoom.getLockedEast().setEast(new Room("křivý sál")); 
		BLR.getLockedWest().setNorth(new Room("špinavá chodba"));
		Room fRoom = new Room("konec špinavé chodby");
		BLR.getLockedWest().getNorth().setNorth(fRoom);
		BLR.getLockedWest().getNorth().getNorth().getItems().add(keyo);
		Room dRoom = new Room("křivý sál");
		orangeLockerRoom.getLockedEast().getEast().setEast(dRoom);
		
		
		orangeLockerRoom.getLockedEast().getEast().getEast().getItems().add(keyh);
		orangeLockerRoom.getLockedEast().getEast().setSouth(new Room("Ze dveřmi na konci sálu je chodba dál pokračuješ v cestě"));
		return orangeLockerRoom;
		
    }
    
    
    public Room createThirdFloor(Item keyh, LockerRoom orangeLockerRoom) {
    	orangeLockerRoom.getLockedEast().getEast().getSouth().setDown(new Room("Gratuluji prošel jsi vchodovou halou, nacháziš ve skladu chemikalií."));
    	orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().setSouth(new Room("chodba od A do Z"));
    	orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().getSouth().setWest(new Room("chodba od A do Z"));
    	LockerRoom HLR = new LockerRoom("chodba od A do Z");
    	orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().getSouth().getWest().setWest(HLR);
    	HLR.setKeySouth(keyh);
		HLR.setLockedSouth(new Room("vchod do skladu"));
		HLR.setWest(new Room("seznamovací koutek"));		
		HLR.getLockedSouth().setSouth(new Room("překvapivě čistý sklad"));
		HLR.getLockedSouth().getSouth().setEast(new Room("překvapivě čistý sklad"));
		HLR.getLockedSouth().getSouth().getEast().setEast(new Room("překvapivě čistý sklad"));	
		Item keyg = new Item("zelenej klíč",Color.GREEN, 1, loadImage("ItemKeyGreen.png"));
		HLR.getLockedSouth().getSouth().getEast().getEast().getItems().add(keyg);
		HLR.getLockedSouth().getSouth().setWest(new Room("překvapivě čistý sklad"));
		HLR.getLockedSouth().getSouth().getWest().setWest(new Room("překvapivě čistý sklad"));
		HLR.getLockedSouth().getSouth().getWest().getWest().setNorth(new Room("překvapivě čistý sklad"));
		HLR.getLockedSouth().getSouth().getWest().getWest().getNorth().setNorth(new Room("fake regál"));
		LockerRoom GLR = new LockerRoom("tajná chodba za regálem");
		HLR.getLockedSouth().getSouth().getWest().getWest().getNorth().getNorth().setNorth(GLR);
		GLR.setKeyEast(keyg);
		GLR.setLockedEast(new Room("vchod do krápníkové jeskyně"));
		GLR.getLockedEast();
		return GLR ;
		
		
	}
	
        public Room createFourthFloor(LockerRoom GLR) {
        	Room firstFourthFloorRoom= new Room("Jseš v krapnikové jeskyni spadly ti na hlavu kapky vody máš jí celou mokrou.");
        	GLR.getLockedEast().setDown(firstFourthFloorRoom);
        	firstFourthFloorRoom.setNorth(new Room ("plesnivý dóm"));
        	firstFourthFloorRoom.getNorth().setNorth(new Room ("plesnivý dóm"));
        	firstFourthFloorRoom.getNorth().getNorth().setNorth(new Room ("plesnivý dóm"));
        	firstFourthFloorRoom.getNorth().getNorth().getNorth().setWest(new Room ("anomální štěrbina"));
        	firstFourthFloorRoom.getNorth().getNorth().getNorth().getWest().setWest(new Room ("anomální štěrbina"));
        	firstFourthFloorRoom.getNorth().getNorth().getNorth().getWest().getWest().setSouth(new Room ("nevyspytatelný tunel"));
        	Room midTunnelRoom = new Room("nevyspytatelný tunel");
        	firstFourthFloorRoom.getNorth().getNorth().getNorth().getWest().getWest().getSouth().setSouth(midTunnelRoom);
        	midTunnelRoom.setSouth(new Room("nevyspytatelný tunel"));
        	midTunnelRoom.getSouth().setSouth(new Room("nevyspytatelný tunel"));
        	midTunnelRoom.getSouth().getSouth().setSouth(new Room("nevyspytatelný tunel"));
        	midTunnelRoom.getSouth().getSouth().getSouth().setSouth(new Room("nevyspytatelný tunel"));
        	Room tunnelCorner = new Room("nevyspytatelný tunel");
        	midTunnelRoom.getSouth().getSouth().getSouth().getSouth().setSouth(tunnelCorner);
        	Item keyRed = new Item ("Červenej klič",Color.RED, 1, loadImage("ItemKeyRed.png")); 
        	tunnelCorner.getItems().add(keyRed);
        	tunnelCorner.setEast(new Room("škvoří chodba"));
        	tunnelCorner.getEast().setEast(new Room("škvoří chodba"));
        	
        	tunnelCorner.getEast().getEast().setEast(new Room("škvoří chodba"));
        	
            LockerRoom goldenDoor = new LockerRoom("pavoučí trhlina");
            tunnelCorner.getEast().getEast().setSouth(goldenDoor);
            
        	goldenDoor.setLockedSouth(new Room("pavoučí trhlina"));
        	goldenDoor.setKeySouth(keyRed);
        	goldenDoor.getLockedSouth().setSouth(new Room("pavoučí trhlina"));
        	Item keyGreen = new Item ("zelenej klič",Color.GREEN, 1, loadImage("ItemKeyGreen.png")); 
        	goldenDoor.getLockedSouth().getSouth().setEast(new Room("pavoučí trhlina"));
        	goldenDoor.getLockedSouth().getSouth().getEast().setEast(new Room("pavoučí trhlina"));        	
        	goldenDoor.getLockedSouth().getSouth().getEast().getEast().getItems().add(keyGreen);
        	tunnelCorner.getEast().getEast().getEast().setNorth(new Room("Nekromantova soutěska"));
        	Room necromancersNarrowPassage = new Room("Nekromantova úzka přihrávka");
        	tunnelCorner.getEast().getEast().getEast().getNorth().setNorth(necromancersNarrowPassage);
        	necromancersNarrowPassage.setEast(new Room("Nekromantova soutěska"));
        	necromancersNarrowPassage.getEast().setEast(new Room("Nekromantova soutěska"));
        	necromancersNarrowPassage.getEast().getEast().setEast(new Room("Nekromantova soutěska"));
        	necromancersNarrowPassage.getEast().getEast().getEast().setEast(new Room("Nekromantova soutěska"));
        	necromancersNarrowPassage.getEast().getEast().getEast().getEast().setEast(new Room("Nekromantova soutěska"));
        	LockerRoom purpleDoubleDoor = new LockerRoom("Nekromantova soutěska");

        	necromancersNarrowPassage.getEast().getEast().getEast().getEast().getEast().setEast(purpleDoubleDoor);

        	LockerRoom purpleDoubleDoorOnEast = new LockerRoom("Nekromantova Laboratoř");
        	purpleDoubleDoor.setLockedEast(purpleDoubleDoorOnEast);
        	purpleDoubleDoorOnEast.setLockedWest(purpleDoubleDoor);
 
        	LockerRoom greenDoor = new LockerRoom("Temná cesta");
        	greenDoor.setKeyEast(keyGreen);
        	firstFourthFloorRoom.setEast(new Room("Temná cesta"));
        	firstFourthFloorRoom.getEast().setEast(new Room("Temná cesta"));
        	firstFourthFloorRoom.getEast().getEast().setEast(greenDoor);
        	greenDoor.setLockedEast(new Room("Temná cesta"));
        	greenDoor.getLockedEast().setNorth(new Room("zatopené schodiště"));
        	greenDoor.getLockedEast().getNorth().setNorth(new Room("zatopené schodiště"));
        	greenDoor.getLockedEast().getNorth().getNorth().setNorth(new Room("zatopené schodiště"));
        	Room floodedStairs = new Room("zatopené schodiště");
        	greenDoor.getLockedEast().getNorth().getNorth().getNorth().setNorth(floodedStairs);
        	floodedStairs.setEast(new Room("zatopené schodiště"));
        	floodedStairs.getEast().setEast(new Room("zatopené schodiště"));
        	LockerRoom floodedLockerStairs = new LockerRoom("zatopené schodiště");
        	floodedStairs.getEast().getEast().setEast(floodedLockerStairs);
        	Item keyPurple = new Item("fialoví klíč",Color.MAGENTA,1, loadImage("ItemKeyMagenta.png"));
        	floodedStairs.getEast().getEast().getItems().add(keyPurple);
        	purpleDoubleDoor.setKeyEast(keyPurple);
        	purpleDoubleDoorOnEast.setKeyWest(keyPurple);
        	Room secretTunnel = new Room("tajný průchod");
        	floodedStairs.getEast().getEast().getEast().setSouth(secretTunnel);
        	secretTunnel.setSouth(new Room("tajný průchod"));
        	secretTunnel.getSouth().setSouth(new Room("tajný průchod"));
        	secretTunnel.getSouth().getSouth().setSouth(new Room("tajný průchod"));



        	secretTunnel.getSouth().getSouth().getSouth().setSouth(purpleDoubleDoor.getLockedEast());
        	Item keyBlack = new Item ("Černej klíč",Color.BLACK, 1, loadImage("ItemKeyBlack.png")); 
        	Room portal = new LockerRoom("Portál");
        	floodedLockerStairs.setKeyNorth(keyBlack);
        	floodedLockerStairs.setLockedNorth(portal);
        	portal.setNorth(new Room("jiný svět"));
        	portal.getNorth().setWest(new Room("jiný svět"));
        	portal.getNorth().getWest().setWest(new Room("jiný svět"));
        	portal.getNorth().getWest().getWest().setDown(new Room("jiný svět"));
        	purpleDoubleDoor.getLockedEast().setSouth(new Room("Nekromantova Laboratoř"));
        	purpleDoubleDoor.getLockedEast().getSouth().setSouth(new Room("Nekromantova Laboratoř"));
        	purpleDoubleDoor.getLockedEast().getSouth().getSouth().setSouth(new Room("Nekromantova Laboratoř"));
        	purpleDoubleDoor.getLockedEast().getSouth().getSouth().getSouth().setWest(new Room("Nekromantova Laboratoř"));
        	purpleDoubleDoor.getLockedEast().getSouth().getSouth().getSouth().getWest().setWest(new Room("Nekromantova Laboratoř"));
        	purpleDoubleDoor.getLockedEast().getSouth().getSouth().getSouth().getWest().getWest().getItems().add(keyBlack);    //udělat texturu jako kyblík(keyBlack)
        return tunnelCorner; 
        }
	
	
	public Room createMap(Item key,ArrayList entityDirectory) {
		
		LockerRoom yellowLockerRoom;
		Sound disapointmentSound = new Sound();
		disapointmentSound.file = "D:\\Filip\\Videa\\ajajajajaj.wav";
		Room startingPosition = createFirstFloor(disapointmentSound,key,entityDirectory);
		yellowLockerRoom = (LockerRoom)startingPosition.getEast().getSouth().getSouth().getWest().getSouth().getSouth().getSouth();
		Item keyh = new Item("CYAN klíč",Color.CYAN, 1, loadImage("ItemKeyCyan.png"));
		LockerRoom orangeLockerRoom = (LockerRoom)createSecondFloor(disapointmentSound,key,yellowLockerRoom,keyh, entityDirectory);
		LockerRoom GLR = (LockerRoom)createThirdFloor(keyh, orangeLockerRoom);
		Room shortcut = createFourthFloor(GLR);
		//return shortcut;
		return startingPosition;
	}
	
	

	void run(Room room, Item key) {
		Player player = new Player();
		do {
			room.printRoom(player);
			Scanner scanner = new Scanner(System.in);
			if (!player.getItems().isEmpty()) {
				System.out.println("V kapse máš tyhle věci:");
				for (Item i : player.getItems()) {
					System.out.println(i.getDescription());
				}
			}
			System.out.println("\nCo uděláš?");
			String response = scanner.nextLine();
			room = room.play(response, player);

		} while ((!room.isExit()) || (!player.getItems().contains(key)));
		System.out.println("Došel jsi cíle gratuluji.                 HURRRRÁÁÁÁÁÁÁÁÁÁÁÁÁÁ");

	}
}
