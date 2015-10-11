package fr.pierrelemee.sqlizer.operators;

public class LowerOrEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " <= %s";
    }
}
