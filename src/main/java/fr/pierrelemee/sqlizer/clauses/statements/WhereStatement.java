package fr.pierrelemee.sqlizer.clauses.statements;

import fr.pierrelemee.sqlizer.SQLable;
import fr.pierrelemee.sqlizer.Value;
import fr.pierrelemee.sqlizer.operators.Operator;

public class WhereStatement implements SQLable {

    protected String name;
    protected Operator operator;
    protected Value[] values;

    public WhereStatement(String name, Operator operator) {
        this(name, operator, new Value[]{});
    }

    public WhereStatement(String name, Operator operator, Value... values) {
        this.name = name;
        this.operator = operator;
        this.values = values;
    }

    public String toSQL() throws Exception {
        return '`' + this.name + '`' + this.operator.format(this.values);
    }
}
