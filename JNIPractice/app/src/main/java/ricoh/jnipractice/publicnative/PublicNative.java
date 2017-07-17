package ricoh.jnipractice.publicnative;

/**
 * Created by bigT on 2017/7/17.
 */

public class PublicNative {
    static {
        System.loadLibrary("public");
    }
    public static native String getStringJNI();
}
