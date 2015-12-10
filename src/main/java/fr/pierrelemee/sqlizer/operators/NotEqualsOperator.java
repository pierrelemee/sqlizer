package fr.pierrelemee.sqlizer.operators;

public class NotEqualsOperator extends Operator {

    @Override
    protected String getFormat() {
        return " <> %s";
    }
}
