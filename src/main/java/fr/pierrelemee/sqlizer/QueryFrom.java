package fr.pierrelemee.sqlizer;

public class QueryFrom extends From {

    protected Query query;

    public QueryFrom(Query query) {
        this.query = query;
    }

    @Override
    public String getSubquery() {
        return String.format("(%s) `q0`", this.query.toSQL());
    }
}
