package cz.pf.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
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
