package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.GeneralState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.PersonalityDbo;

public class PersonalityGenerator extends BaseGenerator {
    private PersonalityDbo personalityDbo;
    private Combinekey parameters;
    private GeneratorManager manager;

    public PersonalityGenerator(GeneratorManager manager) {
        this.manager = manager;
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getPersonalityRepository();
        this.parameters = parameters;
        var characterId = parameters.getObjectIdKey();
        this.personalityDbo = repo.getDocumentByKey(characterId);
        
        var fields = getFormattedFields(this.personalityDbo);
        fields.put("Личность", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        return new GeneralState(fields, buttons, possibleTransitions, "Personality");
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        
        buttons.put("HP", "gotohp");
        buttons.put("Уровень", "gotolevel");
        buttons.put("Внешний вид", "gotoappearance");
        buttons.put("Общественность", "gotosocial");
        buttons.put("Назад", "gotodescription");

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        
        possibleTransitions.put("gotohp", this.parameters);
        possibleTransitions.put("gotolevel", this.parameters);
        possibleTransitions.put("gotosocial", this.parameters);
        possibleTransitions.put("gotoappearance", this.parameters);
        possibleTransitions.put("gotodescription", this.parameters);

        return possibleTransitions;
    }
}
