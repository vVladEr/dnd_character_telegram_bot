package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.SpellsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;
import dnd.bot.maven.eclipse.db.repos.MongoGradesRepository;

public class SpellsGenerator extends BaseGenerator {
    private Combinekey parameters;
    private MongoGradesRepository repo;
    private ArrayList<GradeDBo> grades;

    public SpellsGenerator(GeneratorManager manager) {
        this.repo = manager.getReposStorage().getGradesRepository();
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Выберите уровень заклинания", "");
        
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        possibleTransitions.put("addspell", parameters);
        return new SpellsState(parameters, fields, buttons, possibleTransitions);
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();

        this.grades = this.repo.getCharacterGrades(parameters.getObjectIdKey());
        
        for (var grade : this.grades) {
            buttons.put(String.format("%s", grade.grade), String.format("gotogrades:%s", grade.grade));
        }

        var stateName = new HashMap<String, String>();
        stateName.put("Спелы", "");

        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();

        for (var grade : this.grades) {
            possibleTransitions.put(
                String.format("%s", grade.grade), 
                new Combinekey(this.parameters.getUserIdKey(), this.parameters.getObjectIdKey(), grade.grade)
            );
        }
        
        possibleTransitions.put("gotocharacter", this.parameters);

        return possibleTransitions;
    }
}
