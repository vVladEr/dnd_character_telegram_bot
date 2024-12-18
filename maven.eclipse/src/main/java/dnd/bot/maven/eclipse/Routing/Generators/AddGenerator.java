package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;

import dnd.bot.maven.eclipse.Routing.States.AddState;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;

public class AddGenerator extends BaseGenerator {

    @Override
    public BaseState generateState(CombineKey parameters) {
        var necessaryFields = new ArrayList<String>();
        var stateName = parameters.getStateName();
        
        switch (stateName) {
            case "User":
                necessaryFields.add("имя");
                break;
            case "Spells":
                necessaryFields.add("название заклинания");
                necessaryFields.add("описание");
                break;
            case "Possessions":
                necessaryFields.add("название");
                necessaryFields.add("описание");
                break;
            case "Notes":
                necessaryFields.add("название");
                necessaryFields.add("описание");
                break;
            case "Items":
                necessaryFields.add("название");
                necessaryFields.add("описание");
                necessaryFields.add("количество");
                break;
            case "Grades":
                necessaryFields.add("уровень");
                break;
            case "Features":
                necessaryFields.add("название");
                necessaryFields.add("описание");
                break;
            default:
                break;
        }
    
        return new AddState(necessaryFields, parameters);
    }

    @Override
    public HashMap<String, String> getFormattedButtons() {
        throw new UnsupportedOperationException("Unimplemented method 'getFormattedButtons'");
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        throw new UnsupportedOperationException("Unimplemented method 'getPossibleTransitions'");
    }
}
