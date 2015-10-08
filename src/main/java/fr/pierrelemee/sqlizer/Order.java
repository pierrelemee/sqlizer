package fr.pierrelemee.sqlizer;

public class Order implements SQLable {

    protected String field;
    protected OrderType type;

    public Order(String field) {
        this(field, OrderType.ASC);
    }

    public Order(String field, OrderType type) {
        this.field = field;
        this.type = type;
    }

    @Override
    public String toSQL() {
        return "`" + this.field + "` " + this.type;
    }
}
