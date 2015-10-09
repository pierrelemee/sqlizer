package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.select.TableFrom;

public class LeftJoin {

    protected TableFrom outTable;
    protected String inField;
    protected String outField;

    public LeftJoin(TableFrom outTable, String inField, String outField) {
        this.outTable = outTable;
        this.inField = inField;
        this.outField = outField;
    }

    @Override
    public String toString() {
        return " left join " + this.outTable.toString() + " on " + this.inField + " = " + this.outField;
    }
}
