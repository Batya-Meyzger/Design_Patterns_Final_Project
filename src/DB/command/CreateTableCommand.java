package DB.command;

import DB.core.Database;
import DB.core.Table;

public class CreateTableCommand extends BaseDBCommand{
    private Database db;
    private Table tabletocreate;

    public CreateTableCommand(Database db, Table table)
    {
        super(table);
        this.db=db;
        this.tabletocreate=table;
    }
    @Override
    protected void validate() throws Exception
    {
     if(tabletocreate==null)
         throw new Exception("Create Table Error: Table object is null.");
     if(db.getTable(tabletocreate.getName())!=null)
         throw new Exception("Create Table Error: Table '\" + tableToCreate.getName() + \"' already exists.");

    }
    protected void applyLogic()
    {
        db.addtable(tabletocreate.getName(),tabletocreate);
        affectedRows.add(null);
        Database.getDb().notifyObservers(" Create table ",table.getName()," a new table creatad ");




    }
}
