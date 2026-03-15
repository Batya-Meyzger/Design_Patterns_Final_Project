package DB.core;
import DB.core.DataType;

public class Column {
    private String name;
    private DataType datatype;

    public Column(String name,DataType datatype)
    {
        this.name=name;
        this.datatype=datatype;
    }

    public  String getname()
    {
       return this.name;
    }
    public DataType getdatatype()
    {
        return this.datatype;
    }
}
