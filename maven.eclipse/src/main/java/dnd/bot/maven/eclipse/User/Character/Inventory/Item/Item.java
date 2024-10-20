package dnd.bot.maven.eclipse.User.Character.Inventory.Item;

import dnd.bot.maven.eclipse.User.Character.BasicDescription.BasicDescription;

public abstract class Item extends BasicDescription {
    public String category;
    public int weight;
    public int amount;
    public boolean isMagic;
    public boolean isEquiped;
}
