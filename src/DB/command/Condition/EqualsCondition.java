package DB.command.Condition;
import DB.core.Row;

public class EqualsCondition implements Condition {
    private String columnName;
    private Object expectedValue;

    // בנאי המקבל את שם העמודה ואת הערך שאנחנו מחפשים
    public EqualsCondition(String columnName, Object expectedValue) {
        this.columnName = columnName;
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean evaluate(Row row) {
        // שליפת הערך הנוכחי מהשורה
        Object actualValue = row.getValue(columnName);

        // אם הערך בשורה הוא null, נחזיר false (אלא אם גם הערך המצופה הוא null)
        if (actualValue == null) {
            return expectedValue == null;
        }

        // שימוש ב-equals כדי לתמוך בכל סוגי הנתונים (String, Integer, Boolean)
        return actualValue.equals(expectedValue);
    }
}
