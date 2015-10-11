package fr.pierrelemee.sqlizer.operators;

public class LikeOperator extends Operator {

    @Override
    protected String getFormat() {
        return " like %s";
    }
}
