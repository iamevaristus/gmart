package g.store.manager;

public class Commons {
    /**
     * To help with debugging of necessary things
     * @param isError Whether what we are debugging is an error statement
     * @param debug The debug statement to be printed
     */
    public static void debugAction(boolean isError, String debug) {
        if(isError) {
            System.err.println(debug);
        } else {
            System.out.println(debug);
        }
    }
}
