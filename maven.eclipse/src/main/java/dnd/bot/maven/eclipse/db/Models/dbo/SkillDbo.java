package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonProperty;

import dnd.bot.maven.eclipse.db.repos.KnowledgeLevel;


public class SkillDbo implements IDbo {

    @BsonProperty
    public String name;

    @BsonProperty
    public int totalBonuses;
    
    @BsonProperty 
    public KnowledgeLevel knowledgeLevel;

    public SkillDbo(String skillName)
    {
        name = skillName;
        knowledgeLevel = KnowledgeLevel.BASIC;
    }

    public SkillDbo(){}
}
