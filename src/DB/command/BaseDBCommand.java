package DB.command;

import DB.core.Table;
import DB.core.Row;

import java.util.ArrayList;
import java.util.List;

public  abstract class BaseDBCommand implements DBcommands {
    protected Table table;
    protected List<Row> affectedRows = new ArrayList<>();
    public BaseDBCommand(Table table) {
        this.table = table;
    }


    @Override
    public final List<Row> execute() {
        try {
            validate();      // 1. שלב הולידציה האחיד
            applyLogic();    // 2. הפעולה הספציפית (Insert/Delete...)
            onSuccess();     // 3. לוג או הודעה
            return affectedRows;
        } catch (Exception e) {
            handleError(e);
            return new ArrayList<>(); // במקרה של שגיאה מחזירים רשימה ריקה
        }
    }

    // פעולות שהבנים חייבים לממש
    protected abstract void validate() throws Exception;
    protected abstract void applyLogic();

    // פעולות אופציונליות עם מימוש ברירת מחדל
    protected void onSuccess() {

    }

    protected void handleError(Exception e) {
        System.err.println("Error executing command: " + e.getMessage());
    }
}


