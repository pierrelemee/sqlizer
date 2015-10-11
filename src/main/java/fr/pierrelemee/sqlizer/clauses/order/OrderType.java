package fr.pierrelemee.sqlizer.clauses.order;

public enum OrderType {

    ASC,
    DESC;

    public String toString() {
        return name().toLowerCase();
    }
}
