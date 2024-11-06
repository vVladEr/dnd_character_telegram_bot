package dnd.bot.maven.eclipse.User.Character;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Description;
import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
import dnd.bot.maven.eclipse.User.Character.Notes.Notes;
import dnd.bot.maven.eclipse.User.Character.Stats.Stats;

import java.util.HashMap;

public class Character extends State {
    public String name;

    public Character(
            Stats stats, 
            Description description, 
            Inventory inventory,
            Grade grade,
            Notes notes
        ) {
        possibleTransitions = new HashMap<String, State>();
        possibleTransitions.put("moveToStats", stats);
        possibleTransitions.put("moveToDescription", description);
        possibleTransitions.put("moveToInventory", inventory);
        possibleTransitions.put("moveToGrade", grade);
        possibleTransitions.put("moveToNotes", notes);
        possibleTransitions.put("back", new Back());
    }

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

		var characterMessageObject = new MessageObject("Имя", name);
        var statsInlineKeybordButton = getInlineKeybordButton("Статы персонажа", "moveToStats");
        var descriptionInlineKeybordButton = getInlineKeybordButton("Описание персонажа", "moveToDescription");
        var inventoryInlineKeybordButton = getInlineKeybordButton("Инвентарь", "moveToInventory");
        var gradesInlineKeybordButton = getInlineKeybordButton("Ячейки заклинаний", "moveToGrade");
        var notesInlineKeybordButton = getInlineKeybordButton("Заметки", "moveToNotes");
        var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
        characterMessageObject.addInlineKeybordButton(statsInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(descriptionInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(inventoryInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(gradesInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(notesInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(backInlineKeybordButton);
        response.addMessageObject(characterMessageObject);

		return response;
    }
}
