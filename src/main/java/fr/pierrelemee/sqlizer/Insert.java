package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.insert.InsertValue;

import java.util.LinkedList;
import java.util.List;

public class Insert extends Query {

    protected String table;
    protected List<InsertValue> values;

    Insert() {
        this.values = new LinkedList<InsertValue>();
    }

    public Insert into(String table) {
        this.table = table;
        return this;
    }

    public Insert value(String value) {
        this.values.add(new InsertValue(value));
        return this;
    }

    public Insert value(Value value) {
        this.values.add(new InsertValue(value));
        return this;
    }

    public Insert value(String name, Value value) {
        this.values.add(new InsertValue(name, value));
        return this;
    }

    public Insert value(String name, String value) {
        this.values.add(new InsertValue(name, value));
        return this;
    }

    protected String generateTable() throws Exception {
        StringBuilder buffer = new StringBuilder();
        buffer.append(this.table);
        buffer.append("(");
        for (int index = 0; index < this.values.size(); index++) {
            buffer.append(this.values.get(index).getName());
            if (index < (this.values.size() - 1)) {
                buffer.append(", ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    protected String generateValues() throws Exception {
        StringBuilder buffer = new StringBuilder();
        buffer.append("(");
        for (int index = 0; index < this.values.size(); index++) {
            buffer.append(this.values.get(index).getValue().toSQL());
            if (index < (this.values.size() - 1)) {
                buffer.append(", ");
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    @Override
    protected void check() throws Exception {
        //
    }

    @Override
    protected String generate() throws Exception {
        return String.format(
                "insert into %s values %s",
                this.generateTable(),
                this.generateValues()
        );
    }
}
