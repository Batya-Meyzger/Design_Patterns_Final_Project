package DB.command;

import java.util.List;
import DB.core.Row;

public interface DBcommands {

    List<Row> execute() throws Exception;

}
