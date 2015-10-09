package fr.pierrelemee.sqlizer.operators;

import fr.pierrelemee.sqlizer.Operator;

public class LikeOperator extends Operator {

    @Override
    protected String getFormat() {
        return " like %s";
    }
}
