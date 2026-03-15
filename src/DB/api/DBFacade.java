package DB.api;

import DB.command.*;
import DB.command.Condition.Condition;
import DB.core.Database;
import DB.core.Row;
import DB.core.Table;

import java.util.ArrayList;
import java.util.List;

public class DBFacade {
   private  Database db= Database.getDb();

   public void Insert(String tablename,Row row)
   {
       Table table = db.getTable(tablename);
       InsertCommand Ic=new InsertCommand(table,row);
       try {
           Ic.execute();
       } catch (Exception e) {
           System.out.println("Insert failed:"+ e.getMessage());
       }
   }
   public void Update(String tablename,Row oldrow,Row newrow,String targetculomn)
   {
       Table table =db.getTable(tablename);
       UpdateCommand Uc= new UpdateCommand(table,oldrow,newrow,targetculomn);
       try {
          Uc.execute();
       }
       catch (Exception e) {
           System.out.println("Update failed:" + e.getMessage());
       }
   }
   public void Delete(String tablename,Row rowtodelete,String targetculomn)
   {
       Table table = db.getTable(tablename);
       DeleteCommand Dc = new DeleteCommand(table,rowtodelete,targetculomn);
       try{
           Dc.execute();
       } catch (Exception e) {
           System.out.println("Delete failed:"+ e.getMessage());
       }
   }
   public void RemoveTable(String tablename)
   {
       RemoveTableCommand Rtc = new RemoveTableCommand(tablename,db);
       try{
           Rtc.execute();
       } catch (Exception e) {
           System.out.println("RemoveTable failed"+ e.getMessage());
       }
   }
   public void  CreateTable(Table table)
   {
       CreateTableCommand Ctc = new CreateTableCommand(db,table);
       try{
           Ctc.execute();
       }
       catch (Exception e)
       {
           System.out.println("Create table failed"+ e.getMessage());
       }
   }
    public List<Row> select(String tableName, Condition condition) {
        Table table = db.getTable(tableName);
        if (table == null) {
            System.out.println("Error: Table " + tableName + " not found!");
            return new ArrayList<>();
        }
        QueryCommand Qc = new QueryCommand(table, condition);
        return Qc.execute();
    }

    public void cloneTable(String sourceTableName, String targetTableName) {
       Table sourceTable = db.getTable(sourceTableName);
        if (sourceTable == null) {
            System.err.println("Source table '" + sourceTableName + "' not found!");
            return;
        }

        if (db.getTable(targetTableName) != null) {
            System.err.println("Target table name '" + targetTableName + "' already exists!");
            return;
        }

        // 3. יצירת העותק העצמאי (Deep Copy)
        Table clonedTable = sourceTable.cloneTable(targetTableName);

        // 4. הוספה ישירה ל-Database ללא שימוש ב-Command
        db.addtable(targetTableName, clonedTable);
        db.notifyObservers("CLONE", targetTableName, "Cloned from " + sourceTableName);

        System.out.println("Table '" + sourceTableName + "' was successfully cloned to '" + targetTableName + "'.");
    }


}
