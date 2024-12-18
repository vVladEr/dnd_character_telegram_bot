package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class DescriptionGenerator extends BaseGenerator {
    private CombineKey parameters;

    @Override
    public BaseState generateState(CombineKey parameters) {
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
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();
        
        possibleTransitions.put("gotopersonality", this.parameters);
        possibleTransitions.put("gotofeatures", this.parameters);
        possibleTransitions.put("gotopossessions", this.parameters);
        possibleTransitions.put("gotocharacter", this.parameters);

        return possibleTransitions;
    }
}
