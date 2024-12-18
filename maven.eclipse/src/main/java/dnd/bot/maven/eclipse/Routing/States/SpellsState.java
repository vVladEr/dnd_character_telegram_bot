package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class SpellsState extends BaseState implements IAddable {
    private CombineKey parameters;

    public SpellsState(
        CombineKey parameters,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, CombineKey> possibleTransitions,
        String stateName
    ) {
        this.parameters = parameters;
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
        this.stateName = stateName;
    }

    public void addElement(GeneratorManager manager, HashMap<String, String> necessaryFields) {
        var name = necessaryFields.get("название заклинания");
        var description = necessaryFields.get("описание");

        var repo = manager.getReposStorage().getGradesRepository();
        fields.put(name, description + "\n");
        repo.addSpell(parameters.getGradeCompositeKey(), name, description);
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
