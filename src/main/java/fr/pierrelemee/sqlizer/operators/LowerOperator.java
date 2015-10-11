package fr.pierrelemee.sqlizer.operators;

public class LowerOperator extends Operator {

    @Override
    protected String getFormat() {
        return " < %s";
    }
}
