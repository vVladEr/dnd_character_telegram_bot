package dnd.bot.maven.eclipse.User.Character.Inventory.Items;

import java.util.HashMap;
import java.util.List;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Item.Item;

public class Items extends State {
    public List<Item> items;

    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>();
	}
}
