package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;


import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;

public class UserState extends BaseState implements IAddable {
    private CharacterCreater creater;
    private String userId;

    public UserState(
        CharacterCreater creater, 
        String userId,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions,
        String stateName
    ) {
        this.creater = creater;
        this.userId = userId;
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
        this.stateName = stateName;
    }

    public void addElement(GeneratorManager manager, HashMap<String, String> necessaryFields) {
        var name = necessaryFields.get("имя");

        var characterId = creater.createCharacter(this.userId, name);
        var combineKey = new Combinekey(this.userId, characterId);
        buttons.put(name, String.format("gotocharacter:%s", characterId));
        possibleTransitions.put(String.format("%s", characterId), combineKey);
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
        
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Изменить", "update"));
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Добавить", "add"));

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
