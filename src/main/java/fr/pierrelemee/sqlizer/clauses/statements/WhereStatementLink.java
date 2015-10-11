package fr.pierrelemee.sqlizer.clauses.statements;

public enum WhereStatementLink {

    FIRST,
    AND,
    OR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
