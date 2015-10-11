package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.values.LitteralValue;
import fr.pierrelemee.sqlizer.values.NumericValue;
import fr.pierrelemee.sqlizer.values.ParameterValue;

public abstract class Value implements SQLable {

    public static Value fromString(String value) {
        try {
            return new NumericValue(Double.parseDouble(value));
        } catch (NumberFormatException e) {
            try {
                return new NumericValue(Float.parseFloat(value));
            } catch (NumberFormatException e1) {
                try {
                    return new NumericValue(Long.parseLong(value));
                } catch (NumberFormatException e2) {
                    try {
                        return new NumericValue(Integer.parseInt(value));
                    } catch (NumberFormatException e3) {
                        return value.trim().equals("?") ? new ParameterValue() : new LitteralValue(value);
                    }
                }
            }
        }
    }

    public static Value[] fromStrings(String... litterals) {
        Value[] values = new Value[litterals.length];
        for (int i = 0; i < litterals.length; i++) {
            values[i] = (Value.fromString(litterals[i]));
        }
        return values;
    }

    public static Value[] fromNumbers(Number... numbers) {
        Value[] values = new Value[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            values[i] = (new NumericValue(numbers[i]));
        }
        return values;
    }
}
