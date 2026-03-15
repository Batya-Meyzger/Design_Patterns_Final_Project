package DB.core;

import DB.Observer.DBObserver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Database
{
    private static Database instance;
    private Map<String,Table> tables;
    private List<DBObserver> observers = new ArrayList<>();

    public Database()
    {
        tables = new HashMap<>();
    }

    public static Database getDb()
    {
        if(instance==null)
            instance= new Database();
       return instance;
    }

    public void addtable(String name,Table table)
    {
        tables.put(name,  table);
    }
    public Table getTable(String name) {
        return tables.get(name);
    }

    public void removeTable(String name) {
        this.tables.remove(name);
    }
    public void addObserver(DBObserver observer)
    {
        observers.add(observer);
    }
    public void notifyObservers(String action,String tablename,String details)
    {
        for (DBObserver observer: observers)
        {
            observer.onDBChange(action,tablename,details);
        }
    }
}
