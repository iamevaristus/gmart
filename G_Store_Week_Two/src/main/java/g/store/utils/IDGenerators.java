package g.store.utils;

import java.util.UUID;

/**
 * This is a wrapper class for generating IDs in the G-Store
 *
 * @author G-Store
 */
public class IDGenerators {
    /**
     * For normal id assignments like User ID, Customer ID, etc.
     * @return It returns a UUID type
     */
    public static UUID assignId() {
        return UUID.randomUUID();
    }

    /**
     * For product identification.
     * @return It returns a String type
     */
    public static String assignProductId() {
        String uuid = UUID.randomUUID().toString().substring(0, 10);
        return "PR" + uuid;
    }
}
