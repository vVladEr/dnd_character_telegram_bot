package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class CharacterGenerator extends BaseGenerator {
    private CombineKey parameters;

    @Override
    public BaseState generateState(CombineKey parameters) {
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
    public LinkedHashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new LinkedHashMap<String, CombineKey>();
        
        possibleTransitions.put("gotostats", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Stats"));
        possibleTransitions.put("gotodescription", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Description"));
        possibleTransitions.put("gotoinventory", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Inventory"));
        possibleTransitions.put("gotogrades", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Grades"));
        possibleTransitions.put("gotonotes", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Notes"));
        possibleTransitions.put("gotouser", new CombineKey(parameters.getUserIdKey(), "User"));

        return possibleTransitions;
    }
}
