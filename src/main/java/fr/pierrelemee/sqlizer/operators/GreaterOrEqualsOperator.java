package fr.pierrelemee.sqlizer.operators;

public class GreaterOrEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " >= %s";
    }
}
