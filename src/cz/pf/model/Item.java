package cz.pf.model;

import java.awt.Color;

public class Item {
    String description;
    Color color;
    int type;
    boolean important = false;
    public Item(String description,Color color, int type){
        this.description = description;
        this.color = color;
        this.type = type;
    }
    public int getType(){
    	return type;
    }
    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isImportant() {
        return important;
    }

    public void setImportant(boolean important) {
        this.important = important;
    }
}
