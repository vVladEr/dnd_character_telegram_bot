package dnd.bot.maven.eclipse.User.Character.Inventory.Items.Item;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;

public abstract class Item extends State {
    private ItemRepository itemRepository;

    @Override
	public ResponseObject getStateMessages() {
        var data = itemRepository.getData();

        var category = data.category;
        var name = data.name;
        var weight = data.weight;
        var amount = data.amount;
        var isMagic = data.isMagic;
        var isEquiped = data.isEquiped;
        var description = data.description;

		var response = new ResponseObject();	

        var nameMessageObject = new MessageObject("Название", name);
        response.addMessageObject(nameMessageObject);

        var categoryMessageObject = new MessageObject("Категория", category);
        response.addMessageObject(categoryMessageObject);

        var weightMessageObject = new MessageObject("Вес", String.format("%d", weight));
        response.addMessageObject(weightMessageObject);

        var amountMessageObject = new MessageObject("Количество", String.format("%d", amount));
        response.addMessageObject(amountMessageObject);

        var isMagicMessageObject = new MessageObject("Магическое", (isMagic) ? "+" : "-");
        response.addMessageObject(isMagicMessageObject);

        var isEquipedMessageObject = new MessageObject("Экипировано", (isEquiped) ? "+" : "-");
        response.addMessageObject(isEquipedMessageObject);

        var descriptionMessageObject = new MessageObject("Описание", description);
        response.addMessageObject(descriptionMessageObject);

        return response;
	}
}
