package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.clauses.Where;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import fr.pierrelemee.sqlizer.values.ParameterValue;

public class Delete extends Query {

    protected Where where;

    public Delete() {
        this.where = new Where();
    }

    public Delete from(String table) {
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

    public Delete limit(String table) {
        return this;
    }

    @Override
    protected void check() throws Exception {

    }

    @Override
    protected String generate() throws Exception {
        return null;
    }
}
