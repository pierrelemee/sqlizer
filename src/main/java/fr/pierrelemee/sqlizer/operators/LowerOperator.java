package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class LowerOperator extends Operator {

    @Override
    protected String getFormat() {
        return " < %s";
    }
}
