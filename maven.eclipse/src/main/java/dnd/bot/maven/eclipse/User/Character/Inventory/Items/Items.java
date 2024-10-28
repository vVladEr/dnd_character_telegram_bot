package dnd.bot.maven.eclipse.User.Character.Inventory.Items;

import java.util.List;


import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item.Item;

public class Items extends State {
    public List<Item> items;

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

		var itemMessageObject = new MessageObject("Выберите предмет", "");

		for (var i = 0; i < items.size(); i++) {
			itemMessageObject.addInlineKeybordButton(getInlineKeybordButton(items.get(i).name, items.get(i).name));
		}

		response.addMessageObject(itemMessageObject);

		return response;
	}
}
