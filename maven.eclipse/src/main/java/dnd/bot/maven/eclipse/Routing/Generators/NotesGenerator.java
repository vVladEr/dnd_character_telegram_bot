package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.NotesState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class NotesGenerator extends BaseGenerator {
    private Combinekey parameters;

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Заметки", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new NotesState(parameters, fields, buttons, possibleTransitions, "Notes");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("add", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Notes"));
        possibleTransitions.put("gotocharacter", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Character"));

        return possibleTransitions;
    }
}
