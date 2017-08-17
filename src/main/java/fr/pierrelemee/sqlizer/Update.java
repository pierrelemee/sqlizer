package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.clauses.Limit;
import fr.pierrelemee.sqlizer.clauses.Set;
import fr.pierrelemee.sqlizer.clauses.Where;
import fr.pierrelemee.sqlizer.clauses.statements.SetStatement;
import fr.pierrelemee.sqlizer.operators.OperatorType;
import fr.pierrelemee.sqlizer.values.ParameterValue;

public class Update extends Query {

    protected String table;
    protected Set set;
    protected Where where;
    protected Limit limit;

    public Update() {
        this.set = new Set();
        this.where = new Where();
        this.limit = new Limit();
    }

    public Update table(String table) {
        this.table = table;
        return this;
    }

    public Update set(String field, String value) {
        this.set.addStatement(new SetStatement(field, value));
        return this;
    }

    public Update set(String field, Value value) {
        this.set.addStatement(new SetStatement(field, value));
        return this;
    }

    public Update where(String name, OperatorType type) {
        this.where.where(name, type);
        return this;
    }

    public Update where(String name, OperatorType type, String... values) {
        this.where.where(name, type, values);
        return this;
    }

    public Update where(String name, OperatorType type, Number... values) {
        this.where.where(name, type, values);
        return this;
    }

    public Update where(String name, OperatorType type, ParameterValue... values) {
        this.where.where(name, type, values);
        return this;
    }

    public Update orderBy(String name) {
        return this;
    }

    public Update limit(Long limit) {
        this.limit.setLimit(limit);
        return this;
    }

    public Update limit(Long limit, Integer offset) {
        this.limit.setLimit(limit, offset);
        return this;
    }

    @Override
    protected void check() throws Exception {

    }

    @Override
    protected String generate() throws Exception {
        return String.format(
                "update %s %s %s",
                this.table,
                this.set.toSQL(),
                this.where.toSQL()
        );
    }
}
