package cz.pf.model;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Item {
    String description;
    Color color;
    int type;
    BufferedImage itemImage;
    boolean important = false;
    public Item(String description,Color color, int type, BufferedImage itemImage){
        this.description = description;
        this.color = color;
        this.type = type;
        this.itemImage = itemImage;
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

    public BufferedImage getItemImage() {
		return itemImage;
	}
	public void setItemImage(BufferedImage itemImage) {
		this.itemImage = itemImage;
	}
	public void setImportant(boolean important) {
        this.important = important;
    }
}
