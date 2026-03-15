package DB.command;

import DB.core.*;

public class DeleteCommand extends BaseDBCommand {

    public Row rowtodelete;
    private String columname;

    public DeleteCommand(Table table,Row rowtodelete,String columname)
    {
        super(table);
        this.rowtodelete=rowtodelete;
        this.columname=columname;
    }
    @Override
    protected  void validate () throws Exception{
        Schema schema=table.getSchema();
        Column col = schema.getColumnByName(columname);
        if (col == null) {
            throw new Exception("Column " + columname + " does not exist in schema");
        }
        Object valueToSearch = rowtodelete.getValue(columname);
        if (valueToSearch == null) {
            throw new Exception("The value to delete in column " + columname + " cannot be null.");
        }
        if (!table.exists(columname, valueToSearch)) {
            throw new Exception("Delete Error: Row with value '" + valueToSearch +
                    "' in column '" + columname + "' was not found.");
        }

    }
    protected  void applyLogic()
    {
       Object valueToSearch= rowtodelete.getValue(columname);
        for (Row row : table.getRow()) {
            if (row.getValue(columname).equals(valueToSearch)) {
                affectedRows.add(row);
            }
        }


        table.getRow().removeAll(affectedRows);
        Database.getDb().notifyObservers(" Delete ",table.getName()," a row deleted ");
    }

    }