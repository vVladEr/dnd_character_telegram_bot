package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;


public class InventoryGenerator extends BaseGenerator {
    private CombineKey parameters;

    @Override
    public BaseState generateState(CombineKey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Инвентарь", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions, "Inventory");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Предметы", "gotoitems");
        buttons.put("Монеты", "gotomoney");;
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();
        
        possibleTransitions.put("gotoitems", this.parameters);
        possibleTransitions.put("gotomoney", this.parameters);
        possibleTransitions.put("gotocharacter", this.parameters);

        return possibleTransitions;
    }
}
