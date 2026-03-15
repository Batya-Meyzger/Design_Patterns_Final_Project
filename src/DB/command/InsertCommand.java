package DB.command;

import DB.core.*;

import java.util.Map;

public class InsertCommand extends BaseDBCommand{

     public Row rowtoinsert;

     public InsertCommand(Table table, Row rowtoinsert)
     {
         super(table);
         this.rowtoinsert=rowtoinsert;
     }
     @Override
     protected  void validate() throws Exception
     {
          Schema schema= table.getSchema();
          if (rowtoinsert == null) throw new Exception("Cannot insert a null row");

          // 2. מעבר על כל הערכים בשורה החדשה ובדיקת תקינותם מול הסכימה
          for (Map.Entry<String, Object> entry : rowtoinsert.getValues().entrySet()) {
               String colName = entry.getKey();
               Object value = entry.getValue();

               // בדיקה שהעמודה קיימת
               Column col = schema.getColumnByName(colName);
               if (col == null) {
                    throw new Exception("Column " + colName + " does not exist in schema");
               }

               // בדיקה שהטיפוס מתאים
               if (!schema.isTypeMatch(value, col.getdatatype())) {
                    throw new Exception("Type mismatch for column " + colName);
               }
          }



     }



     protected  void applyLogic()
     {
      table.getRow().add(rowtoinsert);
      affectedRows.add(rowtoinsert);
          Database.getDb().notifyObservers(" Insert ",table.getName()," new row inserted ");
     }
}
