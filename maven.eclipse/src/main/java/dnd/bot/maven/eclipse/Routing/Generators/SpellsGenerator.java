package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.SpellsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;

public class SpellsGenerator extends BaseGenerator {
    private Combinekey parameters;
    private MongoGradesRepository repo;

    public SpellsGenerator(GeneratorManager manager) {
        this.repo = manager.getReposStorage().getGradesRepository();
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Заклинания", "\n");

        var grade = repo.getDocumentByKey(parameters.getGradeCompositeKey());
        
        for (var spell : grade.spells.keySet()) {
            var description = grade.spells.get(spell);
            fields.put(description.name, description.description + "\n");
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();

        return new SpellsState(parameters, fields, buttons, possibleTransitions, "Spells");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();

        buttons.put("Назад", "gotograde");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("add", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Spells", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotograde", this.parameters);

        return possibleTransitions;
    }
}
