package dnd.bot.maven.eclipse.Routing.Generators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import dnd.bot.maven.eclipse.Routing.GeneratorManager;
import dnd.bot.maven.eclipse.Routing.States.BaseState;
import dnd.bot.maven.eclipse.Routing.States.UserState;
import dnd.bot.maven.eclipse.db.Models.CompositeKeys.CombineKey;
import dnd.bot.maven.eclipse.db.Models.dbo.CharacterDbo;
import dnd.bot.maven.eclipse.db.Models.dbo.IDbo;
import dnd.bot.maven.eclipse.db.Services.CharacterCreater;

public class UserGenerator extends BaseGenerator {
    private ArrayList<CharacterDbo> characters;
    private CharacterCreater creater;
    private CombineKey parameters;
    private GeneratorManager manager;

    public UserGenerator(GeneratorManager manager) {
        this.manager = manager;
        this.creater = new CharacterCreater(manager.getReposStorage());
    }

    @Override
    public BaseState generateState(CombineKey parameters) {
        var repo = this.manager.getReposStorage().getCharacterRepository();
        this.parameters = parameters;
        var userId = parameters.getUserIdKey();
        this.characters = repo.getUserCharacters(userId);
        
        var fields = new LinkedHashMap<String, String>();
        fields.put("Твои персонажи", "");

        var buttons = getFormattedButtons();
        var possibleTransitions = getPossibleTransitions();
        possibleTransitions.put("add", new CombineKey(parameters.getUserIdKey(), parameters.getObjectIdKey(), "User"));
        return new UserState(creater, userId, fields, buttons, possibleTransitions, "User");
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

        for (var character : this.characters) {
            buttons.put(
                String.format("%s", character.name), 
                String.format("gotocharacter:%s", character.id)
            );
        }

        return buttons;
    }

    @Override
    public HashMap<String, CombineKey> getPossibleTransitions() {
        var possibleTransitions = new HashMap<String, CombineKey>();

        for (var character : this.characters) {
            var combineKey = new CombineKey(this.parameters.getUserIdKey(), character.id, "Character");
            possibleTransitions.put(String.format("%s", character.id), combineKey);
        }

        return possibleTransitions;
    }
}
