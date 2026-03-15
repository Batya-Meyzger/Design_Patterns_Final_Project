package DB.core;
import DB.core.Row;
import java.util.ArrayList;
import java.util.List;

public class Table {
    private String name;
    private Schema schema;
    private List<Row> rows;


    public Table(String name,Schema schema)
    {
        this.name=name;
        this.schema=schema;
        this.rows= new ArrayList<>();
    }

    public String getName()
    {
        return this.name;
    }
    public Schema getSchema()
    {
        return this.schema;
    }
    public List<Row>  getRow()
    {
        return this.rows;
    }
    public boolean exists(String columnName, Object value) {
        return rows.stream()
                .anyMatch(row -> row.getValue(columnName) != null &&
                        row.getValue(columnName).equals(value));
    }
    public void addRow(Row row) {
        if (row != null) {
            this.rows.add(row);
        }
    }
    public Table cloneTable(String newName) {

        Schema clonedSchema = this.schema.cloneSchema();


        Table clonedTable = new Table(newName, clonedSchema);


        if (this.rows != null) {
            for (Row row : this.rows) {
                clonedTable.addRow(row.cloneRow());
            }
        }

        return clonedTable;
    }
    @Override
    public String toString()
    {
        return this.name + rows.toString();
    }


}
