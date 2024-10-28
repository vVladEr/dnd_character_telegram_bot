package dnd.bot.maven.eclipse.User.Character.Inventory;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Inventory.Items.Items;
import dnd.bot.maven.eclipse.User.Character.Inventory.Money.Money;

public class Inventory extends State {
    public Items items;
    public Money money;
    

    public Inventory() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToItems", items);
            possibleTransitions.put("moveToMoney", money);
        }};
    }

    @Override
	public ResponseObject getStateMessages() {
		var response = new ResponseObject();

        var itemsMessageObject = new MessageObject();
        itemsMessageObject.addInlineKeybordButton(getInlineKeybordButton("Предметы", "moveToItems"));
        response.addMessageObject(itemsMessageObject);
        
        var moneyMessageObject = new MessageObject();
        moneyMessageObject.addInlineKeybordButton(getInlineKeybordButton("Монеты", "moveToMoney"));
        response.addMessageObject(moneyMessageObject);
        
        return response;
	}
}
