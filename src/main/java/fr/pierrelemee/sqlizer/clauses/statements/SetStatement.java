package fr.pierrelemee.sqlizer.clauses.statements;

import fr.pierrelemee.sqlizer.SQLable;
import fr.pierrelemee.sqlizer.Value;

public class SetStatement implements SQLable {

    protected String name;
    protected Value value;

    public SetStatement(String name, String value) {
        this(name, Value.fromString(value));
    }

    public SetStatement(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public String toSQL() throws Exception {
        return String.format(
                "%s = %s",
                this.name,
                this.value.toSQL()
        );
    }
}
