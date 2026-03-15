package DB.command.Condition;

import DB.core.Row;
import java.util.ArrayList;
import java.util.List;

public class OrCondition implements Condition {
    private List<Condition> conditions = new ArrayList<>();

    // בנאי המאפשר הוספת כמה תנאים בבת אחת (Varargs)
    public OrCondition(Condition... conds) {
        for (Condition c : conds) {
            conditions.add(c);
        }
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

    @Override
    public boolean evaluate(Row row) {
        // ב-OR, אנחנו עוברים על כל התנאים.
        // ברגע שמצאנו תנאי אחד שמתקיים (true), אנחנו עוצרים ומחזירים true.
        for (Condition condition : conditions) {
            if (condition.evaluate(row)) {
                return true;
            }
        }
        // אם עברנו על כל התנאים ואף אחד מהם לא התקיים, נחזיר false.
        return false;
    }
}