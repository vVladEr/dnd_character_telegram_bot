package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.UserState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.Combinekey;
import dnd.bot.maven.eclipse.db.Models.dbo.IDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.UserDBO;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;

public class UserGenerator extends BaseGenerator {
    private UserDBO userDbo;
    private CharacterCreater creater;
    private Combinekey parameters;
    private GeneratorManager manager;

    public UserGenerator(GeneratorManager manager) {
        this.manager = manager;
        this.creater = new CharacterCreater(manager.getReposStorage());
    }

    @Override
    public BaseState generateState(Combinekey parameters) {
        var repo = this.manager.getReposStorage().getUserRepository();
        this.parameters = parameters;
        var userId = parameters.getUserIdKey();
        this.userDbo = repo.GetDocumentByKey(userId);
        if (this.userDbo == null) {
            repo.InsertDocument(new UserDBO(userId));
            this.userDbo = repo.GetDocumentByKey(userId);
        }
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Твои персонажи", "");
        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        possibleTransitions.put("addcharacter", parameters);
        return new UserState(creater, userId, fields, buttons, possibleTransitions);
    }

    @Override
    public LinkedHashMap<String, String> getFormattedFields(IDbo dbo) {
        var formattedFields = new LinkedHashMap<String, String>();

        for (var field : dbo.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                var className = field.getName();
                if (!(className.equals("characters") || className.equals("id"))) {
                    formattedFields.put(
                        className, 
                        String.format("%s", field.get(dbo))
                    );
                }
            } catch (IllegalAccessException e) {
                formattedFields.put(field.getName(), "Access denied"); 
            }
        }

        return formattedFields;
    }

    @Override
    public LinkedHashMap<String, String> getFormattedButtons() {
        var buttons = new LinkedHashMap<String, String>();
        var characterIds = userDbo.GetCharacters();
        
        for (var characterId : characterIds) {
            buttons.put(
                String.format("%s", characterId), 
                String.format("gotocharacter:%s", characterId)
            );
        }

        return buttons;
    }

    @Override
    public HashMap<String, Combinekey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, Combinekey>();
        var characterIds = userDbo.GetCharacters();

        for (var characterId : characterIds) {
            var combineKey = new Combinekey(this.parameters.getUserIdKey(), characterId);
            possibleTransitions.put(String.format("%s", characterId), combineKey);
        }

        return possibleTransitions;
    }
}
