package fr.pierrelemee.sqlizer.clauses;

import fr.pierrelemee.sqlizer.SQLable;

public class Limit implements SQLable {

    protected Long limit;
    protected Integer offset;

    public Limit() {
        this.limit = null;
        this.offset = null;
    }

    public void setLimit(Long limit) {
        this.limit = limit;
    }

    public void setLimit(Long limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }

    public boolean isEnabled() {
        return null != this.limit;
    }

    public String toSQL() throws Exception {
        return this.isEnabled() ? String.format(
                " limit %d%s", this.limit, this.offset != null ? "," + this.offset : "") : "";
    }
}
