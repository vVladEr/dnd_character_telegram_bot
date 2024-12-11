package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.StatsState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.IDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.SkillDbo;

public class StatsGenerator extends BaseGenerator {
    private Combinekey parameters;
    private GeneratorManager manager;

    public StatsGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        this.parameters = parameters;
        var repo = this.manager.getReposStorage().getStatRepository();
        var stats = repo.getCharacterStats(parameters.getObjectIdKey());
        var fieldsArray = new ArrayList<LinkedHashMap<String, String>>();

        var fields = new LinkedHashMap<String, String>();
        fields.put("Характеристики:", "");
        fieldsArray.add(fields);

        for (var stat : stats) {
            fieldsArray.add(getFormattedFields(stat));
        }

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new StatsState(fieldsArray, buttons, possibleTransitions, "Stats");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedFields(IDbo dbo) {
        var formattedFields = new LinkedHashMap<String, String>();
    
        for (var field : dbo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getName().equals("skills")) {
                    var skillsValue = field.get(dbo);
                    if (skillsValue instanceof LinkedHashMap<?, ?>) {
                        var skillsMap = (LinkedHashMap<String, SkillDbo>) skillsValue;
                        for (var skillEntry : skillsMap.entrySet()) {
                            var skill = skillEntry.getValue();
                            formattedFields.put(
                                skill.name,
                                String.format("%s", skill.totalBonuses)
                            );
                        }
                    } else {
                        formattedFields.put(field.getName(), "Invalid skills type");
                    }
                } else {
                    formattedFields.put(
                        field.getName(),
                        String.format("%s", field.get(dbo))
                    );
                }
    
            } catch (IllegalAccessException e) {
                formattedFields.put(field.getName(), "Access denied");
            } catch (ClassCastException e) {
                formattedFields.put(field.getName(), "Type casting error");
            } catch (NullPointerException e) {
                formattedFields.put(field.getName(), "Value is null");
            }
        }
    
        return formattedFields;
    }
    

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
    
        buttons.put("Назад", "gotocharacter");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotocharacter", this.parameters);

        return possibleTransitions;
    }
}
