package fr.pierrelemee.sqlizer.clauses.from;

import fr.pierrelemee.sqlizer.Query;
import fr.pierrelemee.sqlizer.Select;
import fr.pierrelemee.sqlizer.clauses.From;

public class SelectFrom extends From {

    protected Select select;

    public SelectFrom(Query query) {
        this.select = select;
    }

    @Override
    public String getSubquery() throws Exception {
        return String.format("(%s) `q0`", this.select.toSQL());
    }
}
