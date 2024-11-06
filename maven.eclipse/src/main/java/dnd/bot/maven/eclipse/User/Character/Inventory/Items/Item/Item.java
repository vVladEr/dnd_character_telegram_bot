package dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item;

import java.util.HashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;

public class Item extends State {
    public String category;
    public String name;
    public int weight;
    public int amount;
    public boolean isMagic;
    public boolean isEquiped;
    public String description;

    public Item() {
        possibleTransitions = new HashMap<>();
        possibleTransitions.put("back", new Back());
    }

    @Override
	public ResponseObject getStateMessages() {
        // var data = itemRepository.getData();

        // var category = data.category;
        // var name = data.name;
        // var weight = data.weight;
        // var amount = data.amount;
        // var isMagic = data.isMagic;
        // var isEquiped = data.isEquiped;
        // var description = data.description;

		var response = new ResponseObject();	

        var nameMessageObject = new MessageObject("Название", name);
        response.addMessageObject(nameMessageObject);

        var categoryMessageObject = new MessageObject("Категория", category);
        response.addMessageObject(categoryMessageObject);

        var weightMessageObject = new MessageObject("Вес", String.format("%d фнт", weight));
        response.addMessageObject(weightMessageObject);

        var amountMessageObject = new MessageObject("Количество", String.format("%d", amount));
        response.addMessageObject(amountMessageObject);

        var isMagicMessageObject = new MessageObject("Магическое", (isMagic) ? "да" : "нет");
        response.addMessageObject(isMagicMessageObject);

        var isEquipedMessageObject = new MessageObject("Экипировано", (isEquiped) ? "да" : "нет");
        response.addMessageObject(isEquipedMessageObject);

        var descriptionMessageObject = new MessageObject("Описание", description);
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		descriptionMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(descriptionMessageObject);

        return response;
	}
}
