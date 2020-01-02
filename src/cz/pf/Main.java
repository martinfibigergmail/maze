package cz.pf;

import cz.pf.model.Item;
import cz.pf.model.LockerRoom;
import cz.pf.model.Player;
import cz.pf.model.Room;

import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        Room main = new Room(null, null, null, null,null, null, "jsi na startuuuuuuuuuuuu");
        /*main.setNorth(new Room(null, null, null, null, "A step to the north is getting colder"));
        main.getNorth().setNorth(new Room(null, null, null, null, "A second step to the north is getting even more colder"));
        Item key = new Item("Golden Key");
        main.getNorth().getNorth().getItems().add(key);
        main.getNorth().getNorth().setNorth(new Room(null, null, null, null, "A third step to the north is nearly freezing"));
        main.getNorth().getNorth().getNorth().setEast(new Room(null, null, null, null, "There is a door. But they are locked!"));
        main.getNorth().getNorth().getNorth().getEast().setExit(true);*/

        main.setEast(new Room("Jsi o kousek vpravo"));

        main.getEast().setSouth(new Room("jsi o kousek vzadu"));
        main.getEast().getSouth().setEast(new Room("jsi o kousek vpravo"));
        main.getEast().getSouth().setWest(new Room("jsi o kousek vlevo"));
        main.getEast().getSouth().getEast().setEast(new Room("jsi o kousek vpravo"));
        main.getEast().getSouth().getEast().getEast().setNorth(new Room("jsi o kousek vepředu"));
        main.getEast().getSouth().getEast().getEast().getNorth().setEast(new Room("a jé je slepá ulička"));
        Item key = new Item("žlutej klíč");
        main.getEast().getSouth().getEast().getEast().getNorth().getEast().getItems().add(key);

        main.getEast().getSouth().getWest().setSouth(new Room("jsi o kousek vzadu"));

        LockerRoom yellowLockerRoom =  new LockerRoom("jsi o kousek vzadu napravo jsou žlutý dveře");
        main.getEast().getSouth().getWest().getSouth().setSouth(yellowLockerRoom);
        yellowLockerRoom.setKeyEast(key);
        yellowLockerRoom.setLockedEast(new Room("blížíš se ke schodům"));

        yellowLockerRoom.getLockedEast().setEast(new Room("už jsi skoro tam"));
        yellowLockerRoom.getLockedEast().getEast().setNorth(new Room("dolu vedou schody"));
        yellowLockerRoom.getLockedEast().getEast().getNorth().setDown(new Room("ses dole pod schodama"));

        new Main().run(main, key);
    }

    void run(Room room, Item key) {
        Player player = new Player();
        do {
            room.printRoom(player);
            Scanner scanner = new Scanner(System.in);
            if (!player.getItems().isEmpty()) {
                System.out.println("You are holding these items:");
                for (Item i : player.getItems()) {
                    System.out.println(i.getDescription());
                }
            }
            System.out.println("\nWhat are you going to do?");
            String response = scanner.nextLine();
            room = room.play(response, player);

        } while ((!room.isExit() )
                || (!player.getItems().contains(key)));
        System.out.println("This is the end of the game.");

    }
}
