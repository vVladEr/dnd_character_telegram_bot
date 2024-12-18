package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class DescriptionGenerator extends BaseGenerator {
    private Combinekey parameters;

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Описание персонажа", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions, "Description");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Личность", "gotopersonality");
        buttons.put("Особенности", "gotofeatures");
        buttons.put("Владения", "gotopossessions");
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotopersonality", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Personality", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotofeatures", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Features", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotopossessions", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Possessions", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotocharacter", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Character", parameters.getGradeCompositeKey()));

        return possibleTransitions;
    }
}
