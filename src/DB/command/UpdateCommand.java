package DB.command;

import DB.core.*;


public class UpdateCommand extends BaseDBCommand {

    public Row oldrow;
    public Row newrow;
    public String targetColumn;

    public UpdateCommand(Table table, Row oldrow, Row newrow, String targetColumn) {
        super(table);
        this.oldrow = oldrow;
        this.newrow = newrow;
        this.targetColumn = targetColumn;
    }


    @Override
    protected void validate() throws Exception {
        Schema schema = table.getSchema();

        // מעבר על כל הערכים החדשים שהמשתמש רוצה לעדכן
        for (String colName : newrow.getValues().keySet()) {
            Column col = schema.getColumnByName(colName);

            if (col == null) {
                throw new Exception("Column " + colName + " does not exist in schema!");
            }

            Object newValue = newrow.getValue(colName);
            if (!schema.isTypeMatch(newValue, col.getdatatype())) {
                throw new Exception("Value " + newValue + " does not match type " + col.getdatatype());
            }
        }
    }

    protected void applyLogic() {
        for (Row row : table.getRow()) {
            if (row.getValue(targetColumn).equals(oldrow)) {
                row.setValue(targetColumn, newrow);
                affectedRows.add(row); // מוסיפים לרשימת המושפעים
                Database.getDb().notifyObservers(" Update ",table.getName()," row updated ");
            }
        }
    }
}
