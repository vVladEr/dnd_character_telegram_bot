package dnd.bot.maven.eclipse.User.Character.Stats.Stat;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.Back;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.Stat.Skills.Skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Stat extends State {
	public String name;
	public int value;
	public List<Skill> skills = new ArrayList<Skill>();
	public int checkBonus;
	public int saveThrowBonus;

    public Stat() {
        possibleTransitions = new HashMap<>();
        possibleTransitions.put("back", new Back());
    }

	@Override
	public ResponseObject getStateMessages() {
		// var data = skillRepository.getData();

        // var name = data.name;
        // var value = data.value;
		// var checkBonus = data.checkBonus;
		// var totalBonus = data.totalBonus;
		// var saveThrowBonus = data.saveThrowBonus;

		var response = new ResponseObject();

		var nameMessageObject = new MessageObject(name);
        response.addMessageObject(nameMessageObject);

		var valueMessageObject = new MessageObject("Значение статы", String.format("%d", value));
        response.addMessageObject(valueMessageObject);

		var checkBonusMessageObject = new MessageObject("Бонус проверки", String.format("%d", checkBonus));
        response.addMessageObject(checkBonusMessageObject);

		var saveThrowBonusMessageObject = new MessageObject("Бонус спасброска", String.format("%d", saveThrowBonus));
        response.addMessageObject(saveThrowBonusMessageObject);

		var skillMessageObject = new MessageObject("Выберите навык", "");

		for (var i = 0; i < skills.size(); i++) {
			skillMessageObject.addInlineKeybordButton(getInlineKeybordButton(skills.get(i).name, skills.get(i).name));
		}
		var backInlineKeybordButton = getInlineKeybordButton("Назад", "back");
		skillMessageObject.addInlineKeybordButton(backInlineKeybordButton);

		response.addMessageObject(skillMessageObject);

		return response;
	}
}
