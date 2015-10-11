package fr.pierrelemee.sqlizer.operators;

public class NotNullOperator extends Operator {

    @Override
    protected String getFormat() {
        return " is not null";
    }
}
