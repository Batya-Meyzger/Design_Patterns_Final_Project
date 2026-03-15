package DB.command.Condition;

import DB.core.Row;

public class GreaterThanCondition implements Condition {
    private String columnName;
    private Comparable expectedValue;

        // אנחנו משתמשים ב-Comparable כדי שנוכל להשוות גם מספרים וגם תאריכים למשל
        public GreaterThanCondition(String columnName, Comparable expectedValue) {
            this.columnName = columnName;
            this.expectedValue = expectedValue;
        }

        @Override
        public boolean evaluate(Row row) {
            Object actualValue = row.getValue(columnName);

            // אם הערך בטבלה הוא null, הוא לא יכול להיות "גדול מ-" משהו
            if (actualValue == null || expectedValue == null) {
                return false;
            }

            try {
                // השוואה גנרית: compareTo מחזיר מספר חיובי אם האובייקט גדול מהפרמטר
                return ((Comparable) actualValue).compareTo(expectedValue) > 0;
            } catch (ClassCastException e) {
                // אם הטיפוסים לא ניתנים להשוואה (למשל השוואת מחרוזת למספר)
                return false;
            }
        }
    }

