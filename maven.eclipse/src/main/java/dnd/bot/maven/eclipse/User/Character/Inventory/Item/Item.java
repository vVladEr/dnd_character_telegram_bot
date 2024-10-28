package dnd.bot.maven.eclipse.User.Character.Inventory.Item;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.db.dbo.ItemDBO;

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

    public Item(ItemDBO itemDbo)
    {
        characterId = itemDbo.characterId;
        category = itemDbo.category;
        weight = itemDbo.weight;
        amount = itemDbo.amount;
        isMagic = itemDbo.isMagic;
        isEquiped = itemDbo.isEquiped;
        description = itemDbo.description;
    }
}
