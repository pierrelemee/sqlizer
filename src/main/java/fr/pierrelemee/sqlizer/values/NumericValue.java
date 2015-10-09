package fr.pierrelemee.sqlizer.values;

import fr.pierrelemee.sqlizer.Value;

public class NumericValue extends Value {

    protected Number number;

    public NumericValue(Number number) {
        this.number = number;
    }

    public String toSQL()throws Exception {
        return this.number.toString();
    }
}
