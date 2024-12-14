package dnd.bot.maven.eclipse.Routing.States;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;

public class GradesState extends BaseState implements IAddable {
    private Combinekey parameters;

    public GradesState(
        Combinekey parameters,
        LinkedHashMap<String, String> fields, 
        LinkedHashMap<String, String> buttons, 
        HashMap<String, Combinekey> possibleTransitions,
        String stateName
    ) {
        this.parameters = parameters;
        this.fields = fields;
        this.buttons = buttons;
        this.possibleTransitions = possibleTransitions;
        this.stateName = stateName;
    }

    public void addElement(GeneratorManager manager, HashMap<String, String> necessaryFields) {
        var grade = necessaryFields.get("уровень");

        var repo = manager.getReposStorage().getGradesRepository();
        var gradeDbo = new GradeDBo(parameters.getObjectIdKey(), Integer.parseInt(grade));
        
        buttons.put(String.format("%s", grade), String.format("gotograde:%s", grade));
        possibleTransitions.put(
            String.format("%s", grade), 
            new Combinekey(this.parameters.getUserIdKey(), this.parameters.getObjectIdKey(), "Grade", Integer.parseInt(grade))
        );

        repo.insertDocument(gradeDbo);
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
