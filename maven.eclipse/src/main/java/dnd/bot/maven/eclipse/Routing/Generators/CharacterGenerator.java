package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class CharacterGenerator extends BaseGenerator {
    private Combinekey parameters;

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Персонаж", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions);
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Характеристики", "gotostats");
        buttons.put("Описание персонажа", "gotodescription");
        buttons.put("Инвентарь", "gotoinventory");
        buttons.put("Спелы", "gotospells");
        //buttons.put("Заметки", "gotonotes");
        buttons.put("Назад", "gotouser");

        return buttons;
    }

    @Override
    public LinkedHashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new LinkedHashMap<String, Combinekey>();
        
        possibleTransitions.put("gotostats", this.parameters);
        possibleTransitions.put("gotodescription", this.parameters);
        possibleTransitions.put("gotoinventory", this.parameters);
        possibleTransitions.put("gotospells", this.parameters);
        possibleTransitions.put("gotonotes", this.parameters);
        possibleTransitions.put("gotouser", this.parameters);

        return possibleTransitions;
    }
}
