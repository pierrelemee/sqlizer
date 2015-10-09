package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.operators.Limit;
import fr.pierrelemee.sqlizer.select.From;
import fr.pierrelemee.sqlizer.select.QueryFrom;
import fr.pierrelemee.sqlizer.select.TableFrom;
import fr.pierrelemee.sqlizer.values.NumericValue;
import fr.pierrelemee.sqlizer.values.ParameterValue;

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
    protected List<Filter> filters;
    protected List<Order> orders;
    protected Limit limit;

    Select() {
        this.unions = new LinkedList<From>();
        this.fields = new LinkedList<Field>();
        this.filters = new LinkedList<Filter>();
        this.orders = new LinkedList<Order>();
        this.limit = null;
    }

    public Select from(String table) {
        this.from = TableFrom.from(table);
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
        this.filters.add(new Filter(name, OperatorType.getOperator(type)));
        return this;
    }

    public Query where(String name, OperatorType type, String... values) throws Exception {
        this.filters.add(new Filter(name, OperatorType.getOperator(type), fromStrings(values)));
        return this;
    }

    public Query where(String name, OperatorType type, Number... values) throws Exception {
        this.filters.add(new Filter(name, OperatorType.getOperator(type), fromNumbers(values)));
        return this;
    }

    public Query where(String name, OperatorType type, ParameterValue... values) throws Exception {
        this.filters.add(new Filter(name, OperatorType.getOperator(type), values));
        return this;
    }

    public Query orderBy(String field) {
        this.orders.add(new Order(field));
        return this;
    }

    public Query orderBy(String field, OrderType type) {
        this.orders.add(new Order(field, type));
        return this;
    }

    public Query limit(Long limit) {
        this.limit = new Limit(limit);
        return this;
    }

    public Query limit(Long limit, Integer offset) {
        this.limit = new Limit(limit, offset);
        return this;
    }

    private static Value[] fromStrings(String... litterals) {
        Value[] values = new Value[litterals.length];
        for (int i = 0; i < litterals.length; i++) {
            values[i] = (Value.fromString(litterals[i]));
        }
        return values;
    }

    private static Value[] fromNumbers(Number... numbers) {
        Value[] values = new Value[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            values[i] = (new NumericValue(numbers[i]));
        }
        return values;
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

    protected String getWheres() throws Exception {
        if (this.filters.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        builder.append(" where ");
        boolean isFirst = true;
        for (Filter filter : this.filters) {
            if (!isFirst) {
                builder.append(" and ");
            }
            builder.append(filter.toSQL());
            isFirst = false;
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
                this.limit != null && this.unions.size() > 0 ? "(" : "",
                this.getFields(),
                from.getSubquery(),
                this.getWheres(),
                this.getOrders(),
                this.limit != null ? this.limit.toSQL() : "",
                this.limit != null && this.unions.size() > 0 ? ")" : ""
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
