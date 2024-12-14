package dnd.bot.maven.eclipse.db.Models.CompositeKeys;

import java.util.HashMap;

import org.bson.types.ObjectId;

import dnd.bot.maven.eclipse.db.Models.StatNameEnum;

public class Combinekey {

    private final GradeCompositeKey gradeCompositeKey;

    private final StatCompositeKey statCompositeKey;

    private final String stateName;

    private final ObjectId objectIdKey;

    private final String userIdKey;

    private final HashMap<String, String> necessaryFields;

    public Combinekey(ObjectId characterId, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        objectIdKey = null;
        userIdKey = null;
        necessaryFields = null;
        stateName = null;
    }

    public Combinekey(String userId, ObjectId characterId, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        objectIdKey = characterId;
        userIdKey = userId;
        necessaryFields = null;
        stateName = null;
    }
    
    public Combinekey(ObjectId characterId, StatNameEnum statName)
    {
        gradeCompositeKey = null;
        statCompositeKey = new StatCompositeKey(characterId, statName);
        objectIdKey = null;
        userIdKey = null;
        necessaryFields = null;
        stateName = null;
    }

    public Combinekey(String userId, ObjectId characterId, String stateName)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = characterId;
        userIdKey = userId;
        necessaryFields = null;
        this.stateName = stateName;
    }

    public Combinekey(ObjectId characterId, StatNameEnum statName, HashMap<String, String> necessaryFields)
    {
        gradeCompositeKey = null;
        statCompositeKey = new StatCompositeKey(characterId, statName);
        this.necessaryFields = necessaryFields;
        objectIdKey = null;
        userIdKey = null;
        stateName = null;
    }

    public Combinekey(ObjectId objectId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = objectId;
        userIdKey = null;
        necessaryFields = null;
        stateName = null;
    }

    public Combinekey(String userId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = null;
        userIdKey = userId;
        necessaryFields = null;
        stateName = null;
    }

    public Combinekey(String userId, String stateName)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = null;
        userIdKey = userId;
        necessaryFields = null;
        this.stateName = stateName;
    }


    public Combinekey(String userId, ObjectId objectId)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        objectIdKey = objectId;
        userIdKey = userId;
        necessaryFields = null;
        stateName = null;
    }

    public Combinekey(String userId, ObjectId characterId, HashMap<String, String> necessaryFields)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        this.necessaryFields = necessaryFields;
        objectIdKey = characterId;
        userIdKey = userId;
        stateName = null;
    }

    public Combinekey(String userId, ObjectId characterId, String stateName, HashMap<String, String> necessaryFields)
    {
        gradeCompositeKey = null;
        statCompositeKey = null;
        this.necessaryFields = necessaryFields;
        objectIdKey = characterId;
        userIdKey = userId;
        this.stateName = stateName;
    }

    public Combinekey(String userId, ObjectId characterId, String stateName, GradeCompositeKey gradeCompositeKey, HashMap<String, String> necessaryFields)
    {
        this.gradeCompositeKey = gradeCompositeKey;
        statCompositeKey = null;
        this.necessaryFields = necessaryFields;
        objectIdKey = characterId;
        userIdKey = userId;
        this.stateName = stateName;
    }

    public Combinekey(String userId, ObjectId characterId, String stateName, int gradeNumber)
    {
        gradeCompositeKey = new GradeCompositeKey(characterId, gradeNumber);
        statCompositeKey = null;
        necessaryFields = null;
        objectIdKey = characterId;
        userIdKey = userId;
        this.stateName = stateName;
    }

    public Combinekey(String userId, ObjectId characterId, String stateName, GradeCompositeKey gradeCompositeKey)
    {
        this.gradeCompositeKey = gradeCompositeKey;
        statCompositeKey = null;
        necessaryFields = null;
        objectIdKey = characterId;
        userIdKey = userId;
        this.stateName = stateName;
    }

    public GradeCompositeKey getGradeCompositeKey() {
        return gradeCompositeKey;
    }

    public HashMap<String, String> getNecessaryFields() {
        assert necessaryFields != null;

        return necessaryFields;
    }

    public String getStateName() {
        assert stateName != null;

        return stateName;
    }

    public StatCompositeKey getStatCompositeKey() {
        assert statCompositeKey != null;

        return statCompositeKey;
    }

    public ObjectId getObjectIdKey() {

        return objectIdKey;
    }

    public String getUserIdKey() {
        assert userIdKey != null;

        return userIdKey;
    }
}
