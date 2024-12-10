package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.PossessionsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class PossessionsGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public PossessionsGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getPossesionsRepository();
        this.parameters = parameters;
        var possessions = repo.getCharacterPossesions(parameters.getObjectIdKey());
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Владения", "");

        for (var possession : possessions) {
            fields.put(possession.name, possession.description);
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        possibleTransitions.put("addpossession", parameters);
        return new PossessionsState(parameters, fields, buttons, possibleTransitions);
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Назад", "gotodescription");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotodescription", this.parameters);

        return possibleTransitions;
    }
}
