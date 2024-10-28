package dnd.bot.maven.eclipse.db.dbo;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.User.Character.Description.Personality.HP.HP;

public class HPDbo {
    @BsonId
    public ObjectId characterId;

    @BsonProperty
    public int maxHP;

    @BsonProperty
    public int bonusMaxHP;

    @BsonProperty
    public String hpDice;

    @BsonProperty
    public int tempHP;

    public HPDbo() {}

    public HPDbo(HP characterHp)
    {
        maxHP = characterHp.maxHP;
        bonusMaxHP = characterHp.bonusMaxHP;
        hpDice = characterHp.hpDice;
        tempHP = characterHp.tempHP;
    }
}
