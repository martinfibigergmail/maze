package cz.pf.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	public int selectedItem=0;
	public int health=3;
    List<Item> items;
    public Player(){
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
        
        
    }
}
