package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Value;

public class InListOperator extends Operator {

    @Override
    public String format(Value[] values) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(" in (");
        boolean isFirst = true;
        for (Value value : values) {
            if (!isFirst) {
                builder.append(", ");
            }
            builder.append(value.toSQL());
            isFirst = false;
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    protected String getFormat() {
        return null;
    }
}
