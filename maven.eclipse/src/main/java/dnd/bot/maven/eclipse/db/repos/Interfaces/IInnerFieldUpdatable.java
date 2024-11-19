package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface IInnerFieldUpdatable<TKey> {
    public void UpdateInnerField(TKey key, String fieldName, String innerFieldName, Object newValue);
}
