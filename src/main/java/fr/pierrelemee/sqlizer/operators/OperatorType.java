package fr.pierrelemee.sqlizer.operators;

public enum OperatorType {

    BETWEEN(BetweenOperator.class),
    LESS(LessOperator.class),
    LESS_OR_EQUALS(LessOrEqualsOperator.class),
    EQUALS(EqualsOperator.class),
    GREATER(GreaterOperator.class),
    GREATER_OR_EQUALS(GreaterOrEqualsOperator.class),
    NOT_EQUALS(NotEqualsOperator.class),
    IN(InListOperator.class),
    IS_NULL(NullOperator.class),
    IS_NOT_NULL(NotNullOperator.class),
    LIKE(LikeOperator.class),
    LOWER(LowerOperator.class),
    LOWER_OR_EQUALS(LowerOrEqualsOperator.class);


    protected Class<? extends Operator> clazz;

    OperatorType(Class<? extends Operator> clazz) {
        this.clazz = clazz;
    }

    public static Operator getOperator(OperatorType type) {
        try {
            return type.clazz.newInstance();
        } catch (Exception e) {
            return new EqualsOperator();
        }
    }
}
