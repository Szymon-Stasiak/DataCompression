package utils.advancedGetters;

import java.lang.reflect.Field;

public class CharChainAdvancedGetter {

    public static char[] getCharChain(Object charChain) {
        String fieldName = "chain";
        try {
            Field field = charChain.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            return (char[]) field.get(charChain);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
