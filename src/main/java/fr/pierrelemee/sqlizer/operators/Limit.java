package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.SQLable;

public class Limit implements SQLable {

    protected Long limit;
    protected Integer offset;

    public Limit(Long limit) {
        this(limit, null);
    }

    public Limit(Long limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public String toSQL() {
        return String.format(" limit %d%s", this.limit, this.offset != null ? "," + this.offset : "");
    }
}
