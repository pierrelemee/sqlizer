package fr.pierrelemee.sqlizer;

public enum OrderType {

    ASC,
    DESC;

    public String toString() {
        return name().toLowerCase();
    }
}
