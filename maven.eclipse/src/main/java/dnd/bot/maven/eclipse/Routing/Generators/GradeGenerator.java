package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;

public class GradeGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public GradeGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getGradesRepository();
        this.parameters = parameters;
        var grade = repo.getDocumentByKey(parameters.getGradeCompositeKey());
        var fields = getFormattedFields(grade);
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions, "Grade");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("Спелы", "gotospells");
        buttons.put("Назад", "gotogrades");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotospells", new Combinekey(this.parameters.getUserIdKey(), this.parameters.getObjectIdKey(), "Spells", parameters.getGradeCompositeKey()));
        possibleTransitions.put("gotogrades", new Combinekey(this.parameters.getUserIdKey(), this.parameters.getObjectIdKey(), "Grades"));

        return possibleTransitions;
    }
    
}
