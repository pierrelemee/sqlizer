package fr.pierrelemee.sqlizer;

public enum FilterLink {

    FIRST,
    AND,
    OR;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
