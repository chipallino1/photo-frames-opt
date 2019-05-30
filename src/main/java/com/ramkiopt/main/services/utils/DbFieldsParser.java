package com.ramkiopt.main.services.utils;

import java.math.BigInteger;

public class DbFieldsParser {
    private static final String BIG_INTEGER = "java.math.BigInteger";
    private static final String BOOLEAN = "java.lang.Boolean";
    private static final String UNDERSCORE = "_";

    public static String getDtoFieldFromDb(String dbField) {
        while (dbField.contains(UNDERSCORE)) {
            StringBuilder stringBuilder = new StringBuilder(dbField);
            String upCase = stringBuilder.substring(stringBuilder.indexOf(UNDERSCORE) + 1,
                    stringBuilder.indexOf(UNDERSCORE) + 2).toUpperCase();
            stringBuilder = stringBuilder.replace(stringBuilder.indexOf(UNDERSCORE) + 1,
                    stringBuilder.indexOf(UNDERSCORE) + 2, upCase);
            stringBuilder = stringBuilder.deleteCharAt(stringBuilder.indexOf(UNDERSCORE));
            return stringBuilder.toString();
        }
        return null;
    }

    public static Long bigIntIdtoLong(Object object) {
        if (object == null) {
            return null;
        }
        Class objectClass = object.getClass();
        if (objectClass.getTypeName().equals(BIG_INTEGER)) {
            return ((BigInteger) object).longValue();
        }
        return null;
    }

    public static Byte booleanToByte(Object object) {
        if (object == null) {
            return null;
        }
        Class objectClass = object.getClass();
        byte trueByte = 1;
        byte falseByte = 0;
        if (objectClass.getTypeName().equals(BOOLEAN)) {
            return (Boolean) object ? trueByte : falseByte;
        }
        return null;
    }
}
