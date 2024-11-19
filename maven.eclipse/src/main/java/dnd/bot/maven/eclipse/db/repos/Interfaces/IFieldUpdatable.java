package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface IFieldUpdatable<TKey> {
    public void UpdateField(TKey id, String fieldName, Object newFieldValue);
}