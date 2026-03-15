package DB.Observer;

public interface DBObserver {
    void onDBChange(String action,String tablename,String details);
}
