package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class GreaterOrEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " >= %s";
    }
}
