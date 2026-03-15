package DB.Builder;

import DB.core.*;
import DB.core.Column;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {
    private String tablename;
    private List<Column> columns= new ArrayList<>();
 public TableBuilder(String tablename)
 {
     this.tablename=tablename;
 }
  public TableBuilder addCulumns(String culomnname, DataType datatype)
  {
      columns.add(new Column(culomnname,datatype));
      return this;
  }

  public Table Build()
  {
      Schema schema = new Schema(columns);
      return new Table(tablename,schema);
  }
}
