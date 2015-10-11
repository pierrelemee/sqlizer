package fr.pierrelemee.sqlizer.clauses.from;

import fr.pierrelemee.sqlizer.clauses.From;

import java.util.LinkedList;
import java.util.List;

public class TableFrom extends From {

    protected String table;
    protected String alias;
    protected List<LeftJoin> joins;

    public TableFrom(String table) {
        this(table, null);
    }

    public TableFrom(String table, String alias) {
        this.table = table;
        this.alias = alias;
        this.joins = new LinkedList<LeftJoin>();
    }

    @Override
    public String getSubquery() {
        StringBuilder builder = new StringBuilder(this.toString());
        for (LeftJoin join : this.joins) {
            builder.append(join.toString());
        }
        return builder.toString();
    }

    public TableFrom leftJoin(String outTable, String inField, String outField) {
        this.joins.add(new LeftJoin(TableFrom.from(outTable), inField, outField));
        return this;
    }

    public TableFrom leftJoin(String outTable, String alias, String inField, String outField) {
        this.joins.add(new LeftJoin(TableFrom.from(outTable, alias), inField, outField));
        return this;
    }

    public static TableFrom from(String table) {
        return new TableFrom(table);
    }

    public static TableFrom from(String table, String alias) {
        return new TableFrom(table, alias);
    }

    @Override
    public String toString() {
        return this.table + (this.alias != null ? " " + this.alias : "");
    }
}
