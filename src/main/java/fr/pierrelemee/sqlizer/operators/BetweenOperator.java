package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class BetweenOperator extends Operator {

    @Override
    protected String getFormat() {
        return " between %s and %s";
    }
}
