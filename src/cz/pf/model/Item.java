package cz.pf.model;

import java.awt.Color;

public class Item {
    String description;
    Color color;
    boolean important = false;
    public Item(String description,Color color){
        this.description = description;
        this.color = color;
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
