package fr.pierrelemee.sqlizer.operators;

public class EqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " = %s";
    }
}
