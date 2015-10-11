package fr.pierrelemee.sqlizer.clauses.from;

import fr.pierrelemee.sqlizer.Query;
import fr.pierrelemee.sqlizer.clauses.From;

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
