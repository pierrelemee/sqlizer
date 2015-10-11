package fr.pierrelemee.sqlizer.operators;

public class BetweenOperator extends Operator {

    @Override
    protected String getFormat() {
        return " between %s and %s";
    }
}
