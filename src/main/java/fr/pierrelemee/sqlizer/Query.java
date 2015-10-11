package fr.pierrelemee.sqlizer;

public abstract class Query implements SQLable {

    /*
    public static Query from(String table) {
        return new Query(new TableFrom(table));
    }

    public static Query from(From from) {
        return new Query(from);
    }

    public static Query from(Query query) {
        return new Query(new QueryFrom(query));
    }

    public static Query fromAll(List<String> tables) {
        return fromAll((String[]) tables.toArray());
    }

    public static Query fromAll(String... tables) {
        Query query = new Query(TableFrom.from(tables[0]));
        for (int i = 1; i < tables.length; i++) {
            query.unionAll(tables[i]);
        }
        return query;
    }

    public static Query fromAll(TableFrom... tables) {
        Query query = new Query(tables[0]);
        for (int i = 1; i < tables.length; i++) {
            query.unionAll(tables[i]);
        }
        return query;
    }
    */

    public static Select select() {
        return new Select();
    }

    public static Insert insert() {
        return new Insert();
    }

    protected abstract void check() throws Exception;

    protected abstract String generate() throws Exception;

    public String toSQL() throws Exception {
        this.check();
        return this.generate();
    }
}
