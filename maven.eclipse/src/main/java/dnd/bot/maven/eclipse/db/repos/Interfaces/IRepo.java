package dnd.bot.maven.eclipse.db.repos.Interfaces;

public interface IRepo<TDbo> {

    public TDbo GetDocumentByKey(Object key);

    public Object InsertDocument(TDbo dbo);
}
