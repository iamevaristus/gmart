package g.store.utils;

import java.nio.file.Path;

public class CommonUtils {
    /**
     * To help with debugging of necessary things
     * @param debug The error statement to be printed
     */
    public static void error(String debug) {
        System.err.println(debug);
    }

    /**
     * To help with debugging of necessary things
     * @param debug The statement to be printed
     */
    public static void print(String debug) {
        System.out.println(debug);
    }

    /**
     * This is the path name where the product.csv file is stored
     */
    private static final String PRODUCT_PATH_NAME = "src/main/resources/G_Store_Products.csv";

    /**
     * This is the productPath with the path type
     */
    public static final Path productPath = Path.of(PRODUCT_PATH_NAME);
}
