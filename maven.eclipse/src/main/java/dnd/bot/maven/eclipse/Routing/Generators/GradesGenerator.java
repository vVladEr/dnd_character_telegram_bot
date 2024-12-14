package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GradesState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.GradeDBo;

public class GradesGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;
    private ArrayList<GradeDBo> grades;

    public GradesGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        this.grades = manager.getReposStorage().getGradesRepository().getCharacterGrades(parameters.getObjectIdKey());

        var fields = new LinkedHashMap<String, String>();
        fields.put("Выберите уровень заклинания", "");

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GradesState(parameters, fields, buttons, possibleTransitions, "Grades");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        for (var grade : grades) {
            buttons.put(String.format("%s", grade.grade), String.format("gotograde:%s", grade.grade));
        }
        
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();

        for (var grade : this.grades) {
            possibleTransitions.put(
                String.format("%s", grade.grade), 
                new Combinekey(this.parameters.getUserIdKey(), this.parameters.getObjectIdKey(), "Grade", grade.grade)
            );
        }
        
        possibleTransitions.put("add", new Combinekey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "Grades"));
        possibleTransitions.put("gotocharacter", this.parameters);

        return possibleTransitions;
    }
}
