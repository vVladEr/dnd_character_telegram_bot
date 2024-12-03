package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class GradesGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public GradesGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getGradesRepository();
        this.parameters = parameters;
        var grade = repo.GetDocumentByKey(parameters.getGradeCompositeKey());
        var fields = getFormattedFields(grade);
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions);
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Назад", "gotospells");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotospells", this.parameters);

        return possibleTransitions;
    }
}
