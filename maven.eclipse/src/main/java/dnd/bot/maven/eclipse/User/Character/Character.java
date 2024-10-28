package dnd.bot.maven.eclipse.User.Character;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Description.Description;
import dnd.bot.maven.eclipse.User.Character.Grade.Grade;
import dnd.bot.maven.eclipse.User.Character.Inventory.Inventory;
import dnd.bot.maven.eclipse.User.Character.Notes.Notes;
import dnd.bot.maven.eclipse.User.Character.Stats.Stats;

import java.util.HashMap;

public class Character extends State {
    public String name;
    public Stats stats;
    public Description description;
    public Inventory inventory;
    public Grade grades;
    public Notes notes;

    public Character() {
        possibleTransitions = new HashMap<String, State>() {{
            possibleTransitions.put("moveToStats", stats);
            possibleTransitions.put("moveToDescription", description);
            possibleTransitions.put("moveToInventory", inventory);
            possibleTransitions.put("moveToGrade", grades);
            possibleTransitions.put("moveToNotes", notes);
        }};
    }

    @Override
    public ResponseObject getStateMessages() {
        var response = new ResponseObject();

		var characterMessageObject = new MessageObject("Имя", name);
        var statsInlineKeybordButton = getInlineKeybordButton("Статы персонажа", "moveToStats");
        var descriptionInlineKeybordButton = getInlineKeybordButton("Описание персонажа", "moveToDescription");
        var inventoryInlineKeybordButton = getInlineKeybordButton("Инвентарь", "moveToInventory");
        var gradesInlineKeybordButton = getInlineKeybordButton("Ячейки заклинаний", "moveToGrade");
        var notesInlineKeybordButton = getInlineKeybordButton("Общественность", "moveToNotes");
        characterMessageObject.addInlineKeybordButton(statsInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(descriptionInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(inventoryInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(gradesInlineKeybordButton);
        characterMessageObject.addInlineKeybordButton(notesInlineKeybordButton);
        response.addMessageObject(characterMessageObject);

		return response;
    }
}
