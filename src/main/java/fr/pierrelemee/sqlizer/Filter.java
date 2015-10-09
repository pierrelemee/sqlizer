package fr.pierrelemee.sqlizer;

public class Filter implements SQLable {

    protected String name;
    protected Operator operator;
    protected Value[] values;

    public Filter(String name, Operator operator) {
        this(name, operator, new Value[]{});
    }

    public Filter(String name, Operator operator, Value... values) {
        this.name = name;
        this.operator = operator;
        this.values = values;
    }

    public String toSQL() throws Exception {
        return '`' + this.name + '`' + this.operator.format(this.values);
    }
}
