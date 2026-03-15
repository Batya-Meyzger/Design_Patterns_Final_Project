package DB.command.Condition;

import DB.core.Row;
import java.util.ArrayList;
import java.util.List;

public class AndCondition implements Condition {
    private List<Condition> conditions = new ArrayList<>();


    public AndCondition(Condition... conds) {
        for (Condition c : conds) {
            conditions.add(c);
        }
    }

    public void addCondition(Condition cond) {
        conditions.add(cond);
    }

    @Override
    public boolean evaluate(Row row) {

        for (Condition condition : conditions) {
            if (!condition.evaluate(row)) {
                return false;
            }
        }

        return true;
    }
}
