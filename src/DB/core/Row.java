package DB.core;

import java.util.HashMap;
import java.util.Map;

public class Row {
    private Map<String, Object> values;

   public Row()
   {
       this.values =new HashMap<>();
   }
   public void setValue(String key,Object value)
   {
       values.put(key,value);
   }
   public Object getValue(String key)
   {
        return values.get(key);
    }
    public Map<String, Object> getValues()
    {
        return this.values;
    }
    public Row cloneRow() {
        Row newRow = new Row();
        for (Map.Entry<String, Object> entry : this.values.entrySet()) {
            newRow.setValue(entry.getKey(), entry.getValue());
        }
        return newRow;
    }
    @Override
    public String toString()
    {
        return values.toString();
    }
    }

