package dnd.bot.maven.eclipse.User.Character.Inventory;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Items;
import dnd.bot.maven.eclipse.User.Character.Inventory.Money.Money;

public class Inventory extends State {

    public Inventory(
            Items items, 
            Money money
        ) {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("moveToItems", items);
        possibleTransitions.put("moveToMoney", money);
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

        var itemsMessageObject = new MessageObject("Куда?");
        itemsMessageObject.addInlineKeybordButton(getInlineKeybordButton("Предметы", "moveToItems"));
        itemsMessageObject.addInlineKeybordButton(getInlineKeybordButton("Монеты", "moveToMoney"));
		itemsMessageObject.addInlineKeybordButton(getInlineKeybordButton("Назад", "back"));
        response.addMessageObject(itemsMessageObject);
        
        return response;
	}
}
