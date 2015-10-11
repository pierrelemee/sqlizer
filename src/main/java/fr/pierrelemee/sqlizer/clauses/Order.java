package fr.pierrelemee.sqlizer.clauses;

import fr.pierrelemee.sqlizer.SQLable;
import fr.pierrelemee.sqlizer.clauses.order.OrderType;

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

    public String toSQL() {
        return "`" + this.field + "` " + this.type;
    }
}
