package DB.command.Condition;

import DB.core.Row;

public class NotCondition implements Condition {
    private Condition originalCondition;

    public NotCondition(Condition condition) {
        this.originalCondition = condition;
    }

    @Override
    public boolean evaluate(Row row) {
        // פשוט מחזיר את ההיפך מהתנאי המקורי
        return !originalCondition.evaluate(row);
    }
}
