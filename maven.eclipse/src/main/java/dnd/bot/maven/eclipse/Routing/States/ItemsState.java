package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.ItemDBO;

public class ItemsState extends BaseState implements IAddable {
    private Combinekey parameters;

    public ItemsState(
        Combinekey parameters,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions,
        String stateName
    ) {
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
        this.parameters = parameters;
        this.stateName = stateName;
    }

    public void addElement(GeneratorManager manager, HashMap<String, String> necessaryFields) {
        var name = necessaryFields.get("название");
        var description = necessaryFields.get("описание");
        var amount = necessaryFields.get("количество");

        var repo = manager.getReposStorage().getItemsRepository();
        var item = new ItemDBO(parameters.getObjectIdKey(), name, description, Integer.parseInt(amount));
        fields.put(item.name, item.description + " " + item.amount + "\n");
        repo.insertDocument(item);
    }

    @Override
    public ResponseObject getStateMessages() {
        var responseObject = new ResponseObject();
        
        var messageObject = new MessageObject();
        for (var name : fields.keySet()) {
            messageObject.addMessagePart(name, fields.get(name));
        }

        for (var name : buttons.keySet()) {
            messageObject.addInlineKeybordButton(getInlineKeybordButton(name, buttons.get(name)));
        }
        
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Добавить", "add"));

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
