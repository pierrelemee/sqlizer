package fr.pierrelemee.sqlizer.operators;

public class GreaterOperator extends Operator {

    @Override
    protected String getFormat() {
        return " > %s";
    }
}
