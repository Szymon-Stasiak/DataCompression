package utils.advancedGetters;

import java.lang.reflect.Field;

public class CharChainAdvancedGetter {

    public static int[] getCharChain(Object charChain) {
        String fieldName = "chain";
        try {
            Field field = charChain.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);

            return (int[]) field.get(charChain);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
