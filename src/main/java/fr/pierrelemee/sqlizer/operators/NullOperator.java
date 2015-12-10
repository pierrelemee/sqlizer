package fr.pierrelemee.sqlizer.operators;

public class NullOperator extends Operator {

    @Override
    protected String getFormat() {
        return " is null";
    }
}
