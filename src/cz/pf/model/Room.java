package cz.pf.model;

import java.util.ArrayList;
import java.util.List;

import cz.pf.Sound;

public class Room {
    Room north;
    Room east;
    Room west;
    Room south;
    Room up;
    Room down;
    Sound ajeje;
    public String description;
    List<Item> items;
    //public List<Entity> entities;
    boolean isExit;

    public Room(String description  ){
        this(null,null,null,null,null,null,description);
    }
    public Room(Room north,
         Room east,
         Room west,
         Room south,
         Room up,
         Room down,
         String description
    ) {
        if(north!=null) this.setNorth(north);
        if(east!=null) this.setEast(east);
        if(west!=null) this.setWest(west);
        if(south!=null) this.setSouth(south);
        if(up!=null) this.setUp(up);
        if(down!=null) this.setDown(down);

        this.description = description;
        items = new ArrayList<Item>();
        
    }

    public void printRoom(Player player) {
        System.out.println(description);
        if (items != null
                && (!items.isEmpty())) {
            System.out.println("Na podlaze se něco třpití:");
            // items.stream().forEach(i->System.out.println(i));
            for (Item item : items) {
                System.out.println(item.description);
            }
        }
        if (north != null) System.out.println("Můžeš jít dopředu.");
        if (south != null) System.out.println("Můžeš jít dozadu.");
        if (east != null) System.out.println("Můžeš jít doprava.");
        if (west != null) System.out.println("Můžeš jít doleva.");
        if (up != null) System.out.println("Můžeš jit nahoru");
        if (down != null) System.out.println("Můžeš jít dolu.");
        if (ajeje != null) ajeje.play();
    }
    Room goIfNotZero(Room room){
        return  room!=null?room:this;
    }
    public Room play(String response, Player player) {
        if(response==null || response.trim().isEmpty()) return this;
        if(response.toLowerCase().equals("w")) return goIfNotZero(this.getNorth());
        if(response.toLowerCase().equals("a")) return goIfNotZero(this.getWest());
        if(response.toLowerCase().equals("d")) return goIfNotZero(this.getEast());
        if(response.toLowerCase().equals("s")) return goIfNotZero(this.getSouth());
        if(response.toLowerCase().equals("u")) return goIfNotZero(this.getUp());
        if(response.toLowerCase().equals("j")) return goIfNotZero(this.getDown());
        if(response.toLowerCase().equals("e")) {
            player.getItems().addAll(this.items);
            this.items.clear();
        }

        if(response.toLowerCase().equals("p")) {
            this.items.addAll(player.getItems());
            player.getItems().clear();

        }
        return this;
    }

    public Room getNorth() {
        return north;
    }

    public void setNorth(Room north) {
        this.north = north;
        north.south = this;
    }

    public Room getEast() {
        return east;
    }

    public void setEast(Room east) {
        this.east = east;
        east.west = this;
    }

    public Room getWest() {
        return west;
    }

    public void setWest(Room west) {
        this.west = west;
        west.east = this;
    }

    public Room getSouth() {
        return south;

    }

    public void setSouth(Room south) {
        this.south = south;
        south.north = this;
    }
    public Room getUp() {
        return up;
    }

    public void setUp(Room up) {
        this.up = up;
        up.down = this;
    }

    public Room getDown() {
        return down;
    }

    public void setDown(Room down) {
        this.down = down;
        down.up = this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public boolean isExit() {
        return isExit;
    }

    public void setExit(boolean exit) {
        isExit = exit;
    }
}
