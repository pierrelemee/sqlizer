package fr.pierrelemee.sqlizer.clauses;

import fr.pierrelemee.sqlizer.SQLable;
import fr.pierrelemee.sqlizer.clauses.statements.SetStatement;

import java.util.LinkedList;
import java.util.List;

public class Set implements SQLable {

    protected List<SetStatement> statements;

    public Set() {
        this.statements = new LinkedList<SetStatement>();
    }

    public void addStatement(SetStatement statement) {
        this.statements.add(statement);
    }

    public String toSQL() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("set ");
        for (int i = 0; i < this.statements.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(this.statements.get(i).toSQL());
        }
        return builder.toString();
    }
}
