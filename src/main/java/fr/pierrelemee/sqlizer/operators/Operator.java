package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Value;

public abstract class Operator {

    public String format(Value[] values) throws Exception {
        return String.format(this.getFormat(), getValues(values));
    }

    private static String[] getValues(Value[] values) throws Exception {
        String[] res = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = values[i].toSQL();
        }
        return res;
    }

    protected abstract String getFormat();
}
