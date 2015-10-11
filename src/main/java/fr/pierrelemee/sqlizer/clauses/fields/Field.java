package fr.pierrelemee.sqlizer.clauses.fields;

import fr.pierrelemee.sqlizer.SQLable;

public class Field implements SQLable {

    protected String name;
    protected String label;

    public Field(String name) {
        this(name, null);
    }

    public Field(String name, String label) {
        this.name = name;
        this.label = label;
    }

    @Override
    public String toSQL() {
        return this.name + (this.label != null ? " as `" + this.label + "`" : "");
    }
}
