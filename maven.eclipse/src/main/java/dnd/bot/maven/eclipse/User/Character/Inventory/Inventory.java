package dnd.bot.maven.eclipse.User.Character.Inventory;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Items;

public class Inventory extends State {
    public Items items;
    public int money;

    @Override
	public void render() {
		possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToItems", items);
        }};
	}
}
