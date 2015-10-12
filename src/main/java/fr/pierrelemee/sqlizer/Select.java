package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.clauses.From;
import fr.pierrelemee.sqlizer.clauses.Limit;
import fr.pierrelemee.sqlizer.clauses.Order;
import fr.pierrelemee.sqlizer.clauses.Where;
import fr.pierrelemee.sqlizer.clauses.fields.Field;
import fr.pierrelemee.sqlizer.clauses.from.QueryFrom;
import fr.pierrelemee.sqlizer.clauses.from.TableFrom;
import fr.pierrelemee.sqlizer.clauses.order.OrderType;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import fr.pierrelemee.sqlizer.values.NumericValue;
import fr.pierrelemee.sqlizer.values.ParameterValue;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Select extends Query {

    /**
     * TODO:
     * - customize where with AND and OR operators
     */

    protected From from;
    protected List<From> unions;
    protected List<Field> fields;
    protected Where where;
    protected List<Order> orders;
    protected Limit limit;

    Select() {
        this.unions = new LinkedList<From>();
        this.fields = new LinkedList<Field>();
        this.where = new Where();
        this.orders = new LinkedList<Order>();
        this.limit = new Limit();
    }

    public Select from(String table) {
        this.from = TableFrom.from(table);
        return this;
    }

    public Select fromAll(String[] tables) {
        this.from = TableFrom.from(tables[0]);
        for (String table: Arrays.copyOfRange(tables, 1, tables.length)) {
            this.unionAll(table);
        }
        return this;
    }

    public Select field(String name) {
        this.fields.add(new Field(name));
        return this;
    }

    public Select field(String name, String label) {
        this.fields.add(new Field(name, label));
        return this;
    }

    public Select unionAll(String table) {
        this.unions.add(new TableFrom(table));
        return this;
    }

    public Select unionAll(TableFrom table) {
        this.unions.add(table);
        return this;
    }

    public Select unionAll(Query query) {
        this.unions.add(new QueryFrom(query));
        return this;
    }

    public Select where(String name, OperatorType type) throws Exception {
        this.where.where(name, type);
        return this;
    }

    public Select where(String name, OperatorType type, String... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Select where(String name, OperatorType type, Number... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Select where(String name, OperatorType type, ParameterValue... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Select orderBy(String field) {
        this.orders.add(new Order(field));
        return this;
    }

    public Select orderBy(String field, OrderType type) {
        this.orders.add(new Order(field, type));
        return this;
    }

    public Select limit(Long limit) {
        this.limit.setLimit(limit);
        return this;
    }

    public Select limit(Long limit, Integer offset) {
        this.limit.setLimit(limit, offset);
        return this;
    }

    protected String getFields() {
        if (this.fields.isEmpty()) {
            return "*";
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.fields.size(); i++) {
            if (i > 0) {
                builder.append(", ");
            }
            builder.append(this.fields.get(i).toSQL());
        }
        return builder.toString();
    }

    protected String getOrders() {
        if (this.orders.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(" order by ");
        boolean isFirst = true;
        for (Order order : this.orders) {
            builder.append(order.toSQL());
            if (!isFirst) {
                builder.append(", ");
            }
            isFirst = false;
        }
        return builder.toString();
    }


    protected String getBase(From from) throws Exception {
        return String.format(
                "%s select %s from %s %s %s %s %s",
                this.limit.isEnabled() && this.unions.size() > 0 ? "(" : "",
                this.getFields(),
                from.getSubquery(),
                this.where.toSQL(),
                this.getOrders(),
                this.limit.toSQL(),
                this.limit.isEnabled() && this.unions.size() > 0 ? ")" : ""
        );
    }

    @Override
    protected void check() throws Exception {
        if (this.from == null) {
            throw new Exception("Missing from statement");
        }
    }

    @Override
    protected String generate() throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(this.getBase(this.from));
        for (From from : this.unions) {
            builder.append(" union all ");
            builder.append(this.getBase(from));
        }
        return builder.toString();
    }
}
