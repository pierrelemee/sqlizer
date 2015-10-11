package fr.pierrelemee.sqlizer.operators;

public enum OperatorType {

    BETWEEN(BetweenOperator.class),
    EQUALS(EqualsOperator.class),
    GREATER_OR_EQUALS(GreaterOrEqualsOperator.class),
    IN(InListOperator.class),
    IS_NOT_NULL(NotNullOperator.class),
    LIKE(LikeOperator.class),
    LOWER(LowerOperator.class),
    LOWER_OR_EQUALS(LowerOrEqualsOperator.class);


    protected Class<? extends Operator> clazz;

    private OperatorType(Class<? extends Operator> clazz) {
        this.clazz = clazz;
    }

    public static Operator getOperator(OperatorType type) throws Exception {
        return type.clazz.newInstance();
    }
}
