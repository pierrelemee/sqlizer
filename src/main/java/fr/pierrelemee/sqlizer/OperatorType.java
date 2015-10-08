package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.operators.*;

public enum OperatorType {

    BETWEEN(BetweenOperator.class),
    IN(InListOperator.class),
    EQUALS(EqualsOperator.class),
    IS_NOT_NULL(NotNullOperator.class),
    LOWER(LowerOperator.class),
    LOWER_OR_EQUALS(LowerOrEqualsOperator.class),
    GREATER_OR_EQUALS(GreaterOrEqualsOperator.class);

    protected Class<? extends Operator> clazz;

    private OperatorType(Class<? extends Operator> clazz) {
        this.clazz = clazz;
    }

    public static Operator getOperator(OperatorType type) throws Exception {
        return type.clazz.newInstance();
    }
}
