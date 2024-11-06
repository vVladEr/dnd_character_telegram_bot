package dnd.bot.maven.eclipse.User.Character.Inventory.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item.Item;

public class Items extends State {
    public List<Item> items = new ArrayList<Item>();

	public Items() {
		possibleTransitions = new HashMap<>();
		possibleTransitions.put("back", new Back());
	}

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

		var itemMessageObject = new MessageObject("Выберите предмет:");

		for (var i = 0; i < items.size(); i++) {
			itemMessageObject.addInlineKeybordButton(getInlineKeybordButton((items.get(i)).name, items.get(i).name));
		}
		var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		itemMessageObject.addInlineKeybordButton(backInlineKeybordButton);
		response.addMessageObject(itemMessageObject);

		return response;
	}
}
