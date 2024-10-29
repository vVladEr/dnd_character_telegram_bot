package dnd.bot.maven.eclipse.User.Character.Inventory.Item;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.Routing.State;

public class Item extends State {
    public ObjectId itemId;
    public ObjectId characterId;
    public String category;
    public double weight;
    public int amount;
    public boolean isMagic;
    public boolean isEquiped;
    public String description;

    @Override
	public void render() {
		
	}
}
