package DB.core;

import java.util.ArrayList;
import DB.core.Column;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Schema {
    private List <Column> columns;

    public Schema()
    {
this.columns = new ArrayList<>();
    }

    public Schema(List<Column>columns)
    {
        this.columns = columns;
    }
    public List<Column> getColumns()
    {
        return this.columns;
    }
    public void addCulumn(Column c)
    {
        columns.add(c);
    }
    public Column getColumnByName(String columnName)
    {

        for(Column col: this.columns) {
            if (col.getname().equals(columnName)) {
                return col;
            }

        }
        return null;


    }




    // פונקציית עזר לבדיקת סוגים
    public boolean isTypeMatch(Object value, DataType dt) {
        String Datatype=dt.name().toLowerCase();
        switch (Datatype) {
            case "string":
                return value instanceof String;
            case "integer":
                return value instanceof Integer;
            case "double":
                return value instanceof Double;
            case "boolean":
                return value instanceof Boolean;
            default:
                return true; // אם הסוג לא מוכר, נאפשר כברירת מחדל או נחמיר לפי הצורך
        }
    }
    public Schema cloneSchema() {
        Schema newSchema = new Schema();
        // בהנחה שהעמודות שמורות ב-Map או List של אובייקטי Column
        for (Column col : this.columns) {
            newSchema.addCulumn(col);
        }
        return newSchema;
    }
    public List<String> getColumnsNames() {
        List<String> names = new ArrayList<>();

        // מעבר בלולאה על כל אובייקט Column ברשימה
        for (Column col : this.columns) {
            names.add(col.getname()); // שליפת השם של העמודה והוספה לרשימה החדשה
        }

        return names;
    }


}
