package main;

/**
 * This class contains static functions and values that
 * help keep track of errors throught the program.
 */
public class errno {

    //global error that can be set throughout the program.
    private static int ERRNO = -1;
    //global error warnings that should'nt be terminated after being set.
    private static int WARN = -1;

    /**
     * Get a error description for a integer or errno
     * @param errno errno that maps to an error
     * @return error string
     */
    public static String getError(int errno){
        switch (errno){
            case 0:
                return "There was no ? found in the expression stack.";
            case 1:
                return "Cant run operation because nothing was in the stack.";
            case 2:
                return "Cannot run since there are not two or more elements in the stack.";
            case 3:
                return "\"\" is not a valid expression stack input.";
            case 4:
                return "[GET ERROR]";
            case 5:
                return "[GET Warning]";
            default:
                return "unexpected error found";
        }
    }

    /**
     * Prints an error with respect to an error number
     * @param errno err number
     */
    static void pError(int errno){
        System.out.println("WMUsearchEngine: "+getError(errno));
        System.exit(0);
    }

    /**
     * Prints an error with respect to an error number and has another msg attached at the end.
     * @param errno
     * @param msg
     */
    static void pError(int errno,String msg){
        System.out.println("WMUsearchEngine: "+getError(errno)+" "+msg);
        System.exit(0);
    }

    /**
     * Prints error msg that is already set
     */
    static void pError(){
        System.out.println("WMUsearchEngine: "+getError(ERRNO));
        System.exit(0);
    }

    /**
     * Prints a basic error
     * @param err
     */
    static void pError(String err){
        System.out.println("WMUsearchEngine: "+err);
        System.exit(0);
    }

    /**
     * getter and setter
     * @param warn
     */
    static void setWARN(int warn){WARN = warn;}

    /**
     * getter and setter
     * @return
     */
    static int getWARN(){return WARN;}

    /**
     * getter and setter
     * @param errno
     */
    static void setERRNO(int errno){
        ERRNO = errno;
    }

    /**
     * getter and setter
     * @return
     */
    static int getERRNO(){ return ERRNO;}

    /**
     * getter and setter
     */
    static void resetErr(){ ERRNO = -1; }

    /**
     * getter and setter
     */
    static void resetWarn(){ WARN = -1; }

}
