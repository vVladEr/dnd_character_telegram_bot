package dnd.bot.maven.eclipse.Routing.Generators;

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
        return new GeneralState(fields, buttons, possibleTransitions, "Character");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Характеристики", "gotostats");
        buttons.put("Описание персонажа", "gotodescription");
        buttons.put("Инвентарь", "gotoinventory");
        buttons.put("Уровни заклинаний", "gotogrades");
        buttons.put("Заметки", "gotonotes");
        buttons.put("Назад", "gotouser");

        return buttons;
    }

    @Override
    public LinkedHashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new LinkedHashMap<String, Combinekey>();
        
        possibleTransitions.put("gotostats", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Stats"));
        possibleTransitions.put("gotodescription", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Description"));
        possibleTransitions.put("gotoinventory", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Inventory"));
        possibleTransitions.put("gotospells", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Spells"));
        possibleTransitions.put("gotonotes", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Notes"));
        possibleTransitions.put("gotouser", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "User"));

        return possibleTransitions;
    }
}
