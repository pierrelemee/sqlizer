package fr.pierrelemee.sqlizer.operators;

public class LessOrEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " <= %s";
    }
}
