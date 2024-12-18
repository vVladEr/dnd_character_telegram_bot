package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.SpellsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;

public class SpellsGenerator extends BaseGenerator {
    private CombineKey parameters;
    private MongoGradesRepository repo;

    public SpellsGenerator(GeneratorManager manager) {
        this.repo = manager.getReposStorage().getGradesRepository();
    }

    @Override
    public BaseState generateState(CombineKey parameters) {
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
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();
        
        possibleTransitions.put("add", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Spells", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotograde", this.parameters);

        return possibleTransitions;
    }
}
