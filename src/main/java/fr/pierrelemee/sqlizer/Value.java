package fr.pierrelemee.sqlizer;

import fr.pierrelemee.sqlizer.values.*;

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

}
