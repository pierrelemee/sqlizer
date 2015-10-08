package fr.pierrelemee.sqlizer.values;

import fr.pierrelemee.sqlizer.Value;

public class LitteralValue extends Value {

    protected String litteral;

    public LitteralValue(String litteral) {
        this.litteral = litteral;
    }

    @Override
    public String toSQL() {
        return '"' + this.litteral + '"';
    }
}
