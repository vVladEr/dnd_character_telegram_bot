package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.UpdateState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Services.UpdateManager;

public class UpdateGenerator extends BaseGenerator {
    private UpdateManager updateManager;

    public UpdateGenerator(UpdateManager updateManager) {
        this.updateManager = updateManager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var stateName = parameters.getStateName();

        var fields = new ArrayList<String>();

        switch (stateName) {
            case "User":
                fields.add("name");
                break;
            case "Grade":
                fields.add("grade");
                fields.add("maxCount");
                fields.add("currentCount");
                break;
            case "Spells":
                fields.add("name");
                fields.add("description");
                break;
            case "Possessions":
                fields.add("name");
                fields.add("description");
                break;
            case "Social":
                fields.add("race");
                fields.add("characterClass");
                break;
            case "Appearance":
                fields.add("alignment");
                fields.add("height");
                fields.add("age");
                fields.add("backGround");
                fields.add("characterBackground");
                fields.add("allies");
                fields.add("personality");
                fields.add("ideals");
                fields.add("bonds");
                fields.add("flaws");
                break;
            case "Features":
                fields.add("name");
                fields.add("description");
                break;
            case "HP":
                fields.add("maxHP");
                fields.add("bonusMaxHP");
                fields.add("hpDice");
                fields.add("tempHP");
                break;
            case "Money":
                break;
            case "Items":
                fields.add("name");
                fields.add("description");
                break;
            case "Level":
                fields.add("currentLevel");
                fields.add("currentExp");
                fields.add("necessaryExp");
                break;
            case "Personality":
                fields.add("armor");
                fields.add("speed");
                fields.add("possessionBonus");
                fields.add("inspiration");
                fields.add("exhaustion");
                break;
            default:
                break;
        }

        return new UpdateState(fields, parameters, updateManager);
    }

    @Override
    public HashMap<String, String> getFormattedButtons() {
        throw new UnsupportedOperationException("Unimplemented method 'getFormattedButtons'");
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleTransitions'");
    }
}