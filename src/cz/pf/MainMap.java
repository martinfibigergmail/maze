package cz.pf;

import cz.pf.model.Item;
import java.awt.Color;
import cz.pf.model.LockerRoom;
import cz.pf.model.Player;
import cz.pf.model.Room;

import java.io.File;
import java.util.Scanner;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MainMap {
	public static void main(String args[]) {
		
		Sound welcome = new Sound();
		welcome.file = "D:\\Filip\\Videa\\zvukvitej.wav";
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

		Item key = new Item("Klíč ke konci",Color.YELLOW, 1);
	    MainMap support=new MainMap();

		Room map=support.createMap(key);
		support.run(map, key);
		
	}
	
    public Room createFirstFloor (Sound ajajajaj,Item key) {
    	
    	Room startingPosition = new Room(null, null, null, null, null, null, "jsi na startuuuuuuuuuuuu");
		startingPosition.setEast(new Room("Jsi o kousek vpravo"));
		startingPosition.getEast().setSouth(new Room("jsi o kousek vzadu"));
		startingPosition.getEast().getSouth().setSouth(new Room("jsi o kousek vzadu"));
		startingPosition.getEast().getSouth().getSouth().setEast(new Room("jsi o kousek vpravo"));
		startingPosition.getEast().getSouth().getSouth().setWest(new Room("jsi o kousek vlevo"));
		startingPosition.getEast().getSouth().getSouth().getEast().setEast(new Room("jsi o kousek vpravo"));
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().setNorth(new Room("jsi o kousek vepředu"));		
		Room soundRoom = new Room("a jé je slepá ulička");
		soundRoom.ajeje = ajajajaj;
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().getNorth().setEast(soundRoom);		
		startingPosition.getEast().getSouth().getSouth().getEast().getEast().getNorth().getEast().getItems().add(key);
		startingPosition.getEast().getSouth().getSouth().getWest().setSouth(new Room("jsi o kousek vzadu"));
		startingPosition.getEast().getSouth().getSouth().getWest().getSouth().setSouth(new Room("jsi o kousek vzadu"));
		LockerRoom yellowLockerRoom = new LockerRoom("jsi o kousek vzadu napravo jsou žlutý dveře");
		startingPosition.getEast().getSouth().getSouth().getWest().getSouth().getSouth().setSouth(yellowLockerRoom);
		yellowLockerRoom.setKeyEast(key);
		yellowLockerRoom.setLockedEast(new Room("blížíš se ke schodům"));   
		yellowLockerRoom.getLockedEast().setEast(new Room("už jsi skoro tam"));
		yellowLockerRoom.getLockedEast().getEast().setNorth(new Room("dolu vedou schody"));
		return startingPosition;
	}
    public Room createSecondFloor (Sound disapointmentSound,Item key,LockerRoom yellowLockerRoom,Item keyh) {
	    yellowLockerRoom.getLockedEast().getEast().getNorth().setDown(new Room("Gratuluji prošel jsi tutoriál, jsi dole pod schodama."));
		Room first = new Room("seš na rozcestí");
		first.setSouth(yellowLockerRoom.getLockedEast().getEast().getNorth().getDown());
		first.setEast(new Room("jsi o kousek vpravo"));
		first.getEast().setEast(new Room("jsi o kousek vpravo"));
		first.getEast().getEast().setNorth(new Room("jsi o kousek vepředu"));
		Room soundBlind = new Room("a jé je slepá ulička");
		first.getEast().getEast().getNorth().setEast(soundBlind);
		soundBlind.ajeje = disapointmentSound;
		Item keyb = new Item("modrej klíč",Color.BLUE, 1);
		first.getEast().getEast().getNorth().getEast().getItems().add(keyb);
		LockerRoom BLR = new LockerRoom("jsi o kousek vlevo vlevo jsou modrý dveře");
		first.setWest(BLR);
		BLR.setKeyWest(keyb);
		BLR.setLockedWest(new Room("jsi o kousek vlevo"));
		LockerRoom BLRT = new LockerRoom("jsi o kousek vepředu před tebou jsou modrý dveře");
		first.setNorth(new Room("Jsi o kousek ve predu"));
		first.getNorth().setNorth(BLRT);
		BLRT.setKeyNorth(keyb);
		LockerRoom orangeLockerRoom = new LockerRoom("jsi o kousek vlevo vlevo jsou oranžový dveře");
		Item keyo = new Item("ornžovej klíč",Color.ORANGE, 1);
		BLRT.setLockedNorth(orangeLockerRoom);
		orangeLockerRoom.setKeyEast(keyo); 
		orangeLockerRoom.setLockedEast(new Room("jsi o kousek vpravo")); 
		orangeLockerRoom.getLockedEast().setEast(new Room("jsi o kousek vpravo")); 
		BLR.getLockedWest().setNorth(new Room("jsi o kousek vepředu"));
		Room fRoom = new Room("a jé je slepá ulička");
		BLR.getLockedWest().getNorth().setNorth(fRoom);
		fRoom.ajeje = disapointmentSound;
		BLR.getLockedWest().getNorth().getNorth().getItems().add(keyo);
		Room dRoom = new Room("a jé je slepá ulička");
		orangeLockerRoom.getLockedEast().getEast().setEast(dRoom);
		dRoom.ajeje = disapointmentSound;
		
		orangeLockerRoom.getLockedEast().getEast().getEast().getItems().add(keyh);
		orangeLockerRoom.getLockedEast().getEast().setSouth(new Room("jsi o kousek vzadu a dolu vedou schody"));
		return orangeLockerRoom;
    }
	private void createThirdFloor(Item keyh, LockerRoom orangeLockerRoom) {
		orangeLockerRoom.getLockedEast().getEast().getSouth().setDown(new Room("Gratuluji prošel jsi první level, jsi dole pod schodama."));
		orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().setSouth(new Room("jsi kousek vzadu"));
		orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().getSouth().setWest(new Room("jsi o kousek vlevo"));
		LockerRoom HLR = new LockerRoom("jsi o kousek vlevo vzadu jsou hnědý dveře");
		orangeLockerRoom.getLockedEast().getEast().getSouth().getDown().getSouth().getWest().setWest(HLR);
		HLR.setKeySouth(keyh);
		HLR.setLockedSouth(new Room("jsi o kousek vzadu"));
		HLR.setWest(new Room("a jé je slepá ulička"));		
		HLR.getLockedSouth().setSouth(new Room("jsi o kousek vzadu"));
		HLR.getLockedSouth().getSouth().setEast(new Room("jsi o kousek vpravo"));
		HLR.getLockedSouth().getSouth().getEast().setEast(new Room("a jé je slepá ulička"));	
		Item keyg = new Item("zelenej klíč",Color.GREEN, 1);
		HLR.getLockedSouth().getSouth().getEast().getEast().getItems().add(keyg);
		HLR.getLockedSouth().getSouth().setWest(new Room("jsi o kousek vlevo"));
		HLR.getLockedSouth().getSouth().getWest().setWest(new Room("jsi o kousek vlevo"));
		HLR.getLockedSouth().getSouth().getWest().getWest().setNorth(new Room("jsi o kousek vepředu"));
		HLR.getLockedSouth().getSouth().getWest().getWest().getNorth().setNorth(new Room("jsi o kousek vepředu"));
		LockerRoom GLR = new LockerRoom("jsi o kousek vepředu a vpravo jsou zelený dveře");
		HLR.getLockedSouth().getSouth().getWest().getWest().getNorth().getNorth().setNorth(GLR);
		GLR.setKeyEast(keyg);
		GLR.setLockedEast(new Room("Došel jsi cíle gratuluji.                 HURRRRÁÁÁÁÁÁÁÁÁÁÁÁÁÁ"));
		GLR.getLockedEast().setExit(true);
	}
	public Room createMap(Item key) {
		
		LockerRoom yellowLockerRoom;
		Sound disapointmentSound = new Sound();
		disapointmentSound.file = "D:\\Filip\\Videa\\ajajajajaj.wav";
		Room startingPosition = createFirstFloor(disapointmentSound,key);
		yellowLockerRoom = (LockerRoom)startingPosition.getEast().getSouth().getSouth().getWest().getSouth().getSouth().getSouth();
		Item keyh = new Item("CYAN klíč",Color.CYAN, 1);
		LockerRoom orangeLockerRoom = (LockerRoom)createSecondFloor(disapointmentSound,key,yellowLockerRoom,keyh);
		createThirdFloor(keyh, orangeLockerRoom);
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
