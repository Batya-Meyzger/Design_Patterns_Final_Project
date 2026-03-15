package DB.command.Condition;

import DB.core.Row;

public interface Condition {
    boolean evaluate (Row row);
}
