package dnd.bot.maven.eclipse.db.Models.dbo;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import java.util.HashMap;

public class GradeDBo {
    @BsonProperty
    public ObjectId characterId;

    @BsonProperty
    public int grade;

    @BsonProperty
    public int maxCount;

    @BsonProperty 
    public int CurrentCount;

    @BsonProperty
    public HashMap<String, String> spells;

    public GradeDBo(){}

	public GradeDBo(ObjectId characterId, int grade)
	{
		this.characterId = characterId;
		this.grade = grade;
		spells = new HashMap<String, String>();
	}
}
