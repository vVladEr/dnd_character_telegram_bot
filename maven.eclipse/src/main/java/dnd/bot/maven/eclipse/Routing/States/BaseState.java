package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public abstract class BaseState {
    public HashMap<String, Combinekey> possibleTransitions;
    public LinkedHashMap<String, String> fields;
    public LinkedHashMap<String, String> buttons;
    public String stateName;

    public ResponseObject getStateMessages() {
        var responseObject = new ResponseObject();
        
        var messageObject = new MessageObject();
        for (var name : fields.keySet()) {
            messageObject.addMessagePart(name, fields.get(name) + "\n");
        }

        for (var name : buttons.keySet()) {
            messageObject.addInlineKeybordButton(getInlineKeybordButton(name, buttons.get(name)));
        }

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }

    public HashMap<String, Combinekey> getPossibleTransitions() {
        return possibleTransitions;
    }

    public InlineKeyboardButton getInlineKeybordButton(String name, String callbackData) {
        var inlineKeybordButton = new InlineKeyboardButton();
        inlineKeybordButton.setText(String.format("%s", name));
        inlineKeybordButton.setCallbackData(callbackData);

        return inlineKeybordButton;
    }
}
