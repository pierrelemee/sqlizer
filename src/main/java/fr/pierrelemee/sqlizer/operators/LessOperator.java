package fr.pierrelemee.sqlizer.operators;

public class LessOperator extends Operator {

    @Override
    protected String getFormat() {
        return " < %s";
    }
}
