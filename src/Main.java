import DB.Builder.TableBuilder;
import DB.Observer.DBLogger;
import DB.api.DBFacade;
import DB.command.Condition.Condition;
import DB.core.*;



import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
          Database db= Database.getDb();

        DBLogger logger=new DBLogger();
        db.addObserver(logger);

        DBFacade facade = new DBFacade();

        Table Users = new TableBuilder("Users")
                .addCulumns("FirstName",DataType.STRING)
                .addCulumns("LastName",DataType.STRING)
                .addCulumns("Age",DataType.INT)
                .addCulumns("isActive",DataType.BOOLEAN)
                .Build();
        Row user1 = new Row();
        user1.setValue("FirstName","Tzvi");
        user1.setValue("LastName","Sam");
        user1.setValue("Age",16);
        user1.setValue("isActive",true);

        Users.addRow(user1);

        Row user2 = new Row();
        user2.setValue("FirstName","Chaim");
        user2.setValue("LastName","Rut");
        user2.setValue("Age",14);
        user2.setValue("isActive",true);

        Users.addRow(user2);

        Row user3 = new Row();
        user3.setValue("FirstName","Avi");
        user3.setValue("LastName","Sam");
        user3.setValue("Age",18);
        user3.setValue("isActive",false);

        Users.addRow(user3);
        db.addtable("Users",Users);

        facade.Update("Users",user2,user3,"LastName");

        Condition ageCondition = new Condition() {
            @Override
            public boolean evaluate(Row row) {
                Integer age =(Integer)row.getValue("Age");
                return age!= null && age>16;
            }
        };

       List<Row> rezult=facade.select("Users",ageCondition);

       for (Row row: rezult)
       {
        System.out.println(" - " + row.getValue("FirstName") + " (Age: " + row.getValue("Age") + ")");
       }



        facade.cloneTable("Users", "newUsers");
        Table newUsers = db.getTable("newUsers");


        if (newUsers != null && !newUsers.getRow().isEmpty()) {
            Row firstRowInNewTable = newUsers.getRow().get(0);
            firstRowInNewTable.setValue("Age", 100);
            firstRowInNewTable.setValue("FirstName", "Modified_Name");
        }

        System.out.println("\n--- Proof of Deep Copy ---");


        System.out.println("Original Table (Users) - Should remain unchanged:");
        for (Row row : Users.getRow()) {
            System.out.println(" - " + row.getValue("FirstName") + " (Age: " + row.getValue("Age") + ")");
        }


        System.out.println("\nBackup Table (newUsers) - Should show the change:");
        if (newUsers != null) {
            for (Row row : newUsers.getRow()) {
                System.out.println(" - " + row.getValue("FirstName") + " (Age: " + row.getValue("Age") + ")");
            }
        }






           }}



//




