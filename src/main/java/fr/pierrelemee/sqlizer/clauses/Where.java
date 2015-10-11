package fr.pierrelemee.sqlizer.clauses;

import fr.pierrelemee.sqlizer.SQLable;
import fr.pierrelemee.sqlizer.Value;
import fr.pierrelemee.sqlizer.clauses.statements.WhereStatement;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import fr.pierrelemee.sqlizer.values.ParameterValue;

import java.util.LinkedList;
import java.util.List;

public class Where implements SQLable {

    protected List<WhereStatement> statements;

    public Where() {
        this.statements = new LinkedList<WhereStatement>();
    }

    public void where(String name, OperatorType type) throws Exception {
        this.addFilter(new WhereStatement(name, OperatorType.getOperator(type)));
    }

    public void where(String name, OperatorType type, String... values) throws Exception {
        this.addFilter(new WhereStatement(name, OperatorType.getOperator(type), Value.fromStrings(values)));
    }

    public void where(String name, OperatorType type, Number... values) throws Exception {
        this.statements.add(new WhereStatement(name, OperatorType.getOperator(type), Value.fromNumbers(values)));
    }

    public void where(String name, OperatorType type, ParameterValue... values) throws Exception {
        this.statements.add(new WhereStatement(name, OperatorType.getOperator(type), values));
    }

    protected void addFilter(WhereStatement filter) {
        this.statements.add(filter);
    }

    public String toSQL() throws Exception {
        if (this.statements.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(" where ");
        boolean isFirst = true;
        for (WhereStatement statement : this.statements) {
            if (!isFirst) {
                builder.append(" and ");
            }
            builder.append(statement.toSQL());
            isFirst = false;
        }
        return builder.toString();
    }
}
