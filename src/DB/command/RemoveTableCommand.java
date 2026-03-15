package DB.command;

import DB.core.Database;
import DB.core.Table;

public class RemoveTableCommand extends BaseDBCommand {
    private String tablename;
    private Database db;

    public RemoveTableCommand(String tablename, Database db)
    {
        super(null);
        this.tablename=tablename;
        this.db=db;
    }
    @Override
    protected void validate()throws Exception
    {
        if(db.getTable(tablename)==null)
        {
            throw new Exception("RemoveTable Error: Table '\" + tableName + \"' does not exist.");
        }
    }


    protected void applyLogic()
    {
        Table tableToRemove = db.getTable(tablename);


        db.removeTable(tablename);


        affectedRows.addAll(tableToRemove.getRow());

        Database.getDb().notifyObservers(" deletetable ",table.getName()," deleted ");
    }
}
