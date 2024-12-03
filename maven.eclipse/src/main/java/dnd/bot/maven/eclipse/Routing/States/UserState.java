package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;

public class UserState extends BaseState {
    private CharacterCreater creater;
    private String userId;

    public UserState(
        CharacterCreater creater, 
        String userId,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions
    ) {
        this.creater = creater;
        this.userId = userId;
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
    }

    public void addCharacter(GeneratorManager manager) {
        var characterId = creater.CreateCharacter(this.userId);
        var combineKey = new Combinekey(this.userId, characterId);
        possibleTransitions.put(String.format("gotocharacter:%s", characterId), combineKey);
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
        
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Добавить", "addcharacter"));

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
