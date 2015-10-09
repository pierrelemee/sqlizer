package fr.pierrelemee.sqlizer.select;

import fr.pierrelemee.sqlizer.Query;

public class QueryFrom extends From {

    protected Query query;

    public QueryFrom(Query query) {
        this.query = query;
    }

    @Override
    public String getSubquery() throws Exception {
        return String.format("(%s) `q0`", this.query.toSQL());
    }
}
