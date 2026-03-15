package DB.Observer;

public class DBLogger implements DBObserver{
    @Override
   public void onDBChange(String action,String tablename,String details)
    {
        System.out.println("[log]:action"+action+"on tablename"+tablename+"| info"+details);
    }
}
