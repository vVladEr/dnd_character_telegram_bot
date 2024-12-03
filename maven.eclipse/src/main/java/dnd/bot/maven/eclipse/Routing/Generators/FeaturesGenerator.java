package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.FeaturesState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class FeaturesGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public FeaturesGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getFeaturesRepository();
        this.parameters = parameters;
        var features = repo.GetCharacterFeatures(parameters.getObjectIdKey());
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Особенности", "");

        for (var feature : features) {
            fields.put(feature.name, feature.description);
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        possibleTransitions.put("addfeature", parameters);
        return new FeaturesState(parameters, fields, buttons, possibleTransitions);
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
