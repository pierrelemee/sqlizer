package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class LowerOrEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " <= %s";
    }
}
