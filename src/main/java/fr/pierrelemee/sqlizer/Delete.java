package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.clauses.From;
import fr.pierrelemee.sqlizer.clauses.Limit;
import fr.pierrelemee.sqlizer.clauses.Where;
import fr.pierrelemee.sqlizer.clauses.from.TableFrom;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import fr.pierrelemee.sqlizer.values.ParameterValue;

public class Delete extends Query {

    protected From from;
    protected Where where;
    protected Limit limit;

    public Delete() {
        this.where = new Where();
        this.limit = new Limit();
    }

    public Delete from(String table) {
        this.from = TableFrom.from(table);
        return this;
    }

    public Delete where(String name, OperatorType type) throws Exception {
        this.where.where(name, type);
        return this;
    }

    public Delete where(String name, OperatorType type, String... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Delete where(String name, OperatorType type, Number... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Delete where(String name, OperatorType type, ParameterValue... values) throws Exception {
        this.where.where(name, type, values);
        return this;
    }

    public Delete order(String table) {
        return this;
    }

    public Delete limit(Long limit) {
        this.limit.setLimit(limit);
        return this;
    }

    public Delete limit(Long limit, Integer offset) {
        this.limit.setLimit(limit, offset);
        return this;
    }

    @Override
    protected void check() throws Exception {

    }

    @Override
    protected String generate() throws Exception {
        return String.format("delete from %s%s%s",
                this.from.toString(),
                this.where.toSQL(),
                this.limit.toSQL()
        );
    }
}
