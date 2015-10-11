package fr.pierrelemee.sqlizer.insert;

import fr.pierrelemee.sqlizer.Value;

public class InsertValue {

    protected String name;
    protected Value value;

    public InsertValue(Value value) {
        this(null, value);
    }

    public InsertValue(String value) {
        this(null, Value.fromString(value));
    }

    public InsertValue(String name, String value) {
        this(name, Value.fromString(value));
    }

    public InsertValue(String name, Value value) {
        this.name = name;
        this.value = value;
    }

    public boolean hasName() {
        return null != this.name;
    }

    public String getName() {
        return this.name;
    }

    public Value getValue() {
        return this.value;
    }
}
