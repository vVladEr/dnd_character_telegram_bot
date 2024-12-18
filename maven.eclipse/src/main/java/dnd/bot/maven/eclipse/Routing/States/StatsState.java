package dnd.bot.maven.eclipse.Routing.States;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class StatsState extends BaseState {
    private ArrayList<LinkedHashMap<String, String>> fieldsArray;

    public StatsState(
        ArrayList<LinkedHashMap<String, String>> fieldsArray, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, CombineKey> possibleTransitions,
        String stateName
    ) {
        this.fieldsArray = fieldsArray;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
        this.stateName = stateName;
    }

    @Override
    public ResponseObject getStateMessages() {
        var responseObject = new ResponseObject();

        for (var name : this.fieldsArray.get(0).keySet()) {
            responseObject.addMessageObject(new MessageObject(name));
        }
        
        for (var i = 1; i < this.fieldsArray.size(); i++) {
            var messageObject = new MessageObject();
            var fields = this.fieldsArray.get(i);

            for (var name : fields.keySet()) {
                messageObject.addMessagePart(name, fields.get(name) + "\n");
            }

            if (i < this.fieldsArray.size() - 1) {
                responseObject.addMessageObject(messageObject);
                continue;
            }

            for (var name : buttons.keySet()) {
                messageObject.addInlineKeybordButton(getInlineKeybordButton(name, buttons.get(name)));
            }

            responseObject.addMessageObject(messageObject);
        }

        return responseObject;
    }
}
