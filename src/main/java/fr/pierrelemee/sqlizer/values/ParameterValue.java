package fr.pierrelemee.sqlizer.values;

import fr.pierrelemee.sqlizer.Value;

public class ParameterValue extends Value {

    public static final ParameterValue VALUE = new ParameterValue();

    public String toSQL() throws Exception {
        return "?";
    }
}
