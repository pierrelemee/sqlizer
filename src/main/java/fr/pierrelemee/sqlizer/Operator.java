package fr.pierrelemee.sqlizer;

public abstract class Operator {

    public String format(Value[] values) {
        return String.format(this.getFormat(), getValues(values));
    }

    private static String[] getValues(Value[] values) {
        String[] res = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            res[i] = values[i].toSQL();
        }
        return res;
    }

    protected abstract String getFormat();
}
