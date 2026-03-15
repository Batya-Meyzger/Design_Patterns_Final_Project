package DB.command;

import DB.command.Condition.Condition;
import DB.core.Database;
import DB.core.Row;
import DB.core.Table;

public class QueryCommand extends BaseDBCommand{
    private Condition condition;

    public QueryCommand(Table table, Condition condition)
    {
        super(table);
        this.condition=condition;

    }
    protected void validate() throws Exception
    {
     if(condition==null)
         throw new Exception("Query Error: Condition cannot be null");
    }



    protected void applyLogic()
    {
        for (Row row : table.getRow()) {
            if (condition.evaluate(row)) {
                affectedRows.add(row);
                Database.getDb().notifyObservers(" qwery ",table.getName(),"");
            }
        }

    }
}
