package dnd.bot.maven.eclipse.User.Character.Inventory.Item;

import dnd.bot.maven.eclipse.Routing.State;

public abstract class Item extends State {
    public String category;
    public int weight;
    public int amount;
    public boolean isMagic;
    public boolean isEquiped;
    public String description;

    @Override
	public void render() {
		
	}
}
