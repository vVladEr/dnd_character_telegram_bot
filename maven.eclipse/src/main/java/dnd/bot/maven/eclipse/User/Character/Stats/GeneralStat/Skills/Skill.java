package dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills;

import dnd.bot.maven.eclipse.Response.MessageObject;
import dnd.bot.maven.eclipse.Response.ResponseObject;
import dnd.bot.maven.eclipse.Routing.State;
import dnd.bot.maven.eclipse.User.Character.Stats.GeneralStat.Skills.Knowledge.KnowledgeLevel;

public class Skill extends State {
    private SkillRepository skillRepository;
    public String name;
    KnowledgeLevel knowledgeLevel;
    public int totalBonus;

    @Override
    public ResponseObject getStateMessages() {
        var data = skillRepository.getData();

        var name = data.name;
        var knowledgeLevel = data.knowledgeLevel;
        var totalBonus = data.totalBonus;

        var response = new ResponseObject();

        var nameMessageObject = new MessageObject("", name);
        response.addMessageObject(nameMessageObject);

        var knowledgeLevelMessageObject = new MessageObject();
        switch (knowledgeLevel) {
            case BASIC:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "не владеет");
                break;
            case INTERMEDIATE:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "владеет");
                break;
            case ADVANCED:
                knowledgeLevelMessageObject.addMessagePart("Уровень владения", "компетенция");
                break;
            default:
                break;
        }
        response.addMessageObject(knowledgeLevelMessageObject);
        
        var totalBonusMessageObject = new MessageObject("Общий бонус", String.format("%d", totalBonus));
        response.addMessageObject(totalBonusMessageObject);
        
        return response;
    }
}
    
