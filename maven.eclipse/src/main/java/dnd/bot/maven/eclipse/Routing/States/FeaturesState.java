package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.BasicDescriptionDbo;

public class FeaturesState extends BaseState {
    private Combinekey parameters;

    public FeaturesState(
        Combinekey parameters,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions
    ) {
        this.parameters = parameters;
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
    }

    public void addFeature(GeneratorManager manager) {
        var repo = manager.getReposStorage().getFeaturesRepository();
        var description = new BasicDescriptionDbo(this.parameters.getObjectIdKey(), "1", "1");
        repo.insertDocument(description);
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
        
        messageObject.addInlineKeybordButton(getInlineKeybordButton("Добавить", "addfeature"));

        responseObject.addMessageObject(messageObject);

        return responseObject;
    }
}
