package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class NotNullOperator extends Operator {

    @Override
    protected String getFormat() {
        return " is not null";
    }
}
