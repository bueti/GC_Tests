import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: bbuetikofer
 * Date: 24/01/13
 * Time: 08:08
 */
public class GCTest2 {
    private static ArrayList <Integer[]> array = new ArrayList<Integer[]>(150);

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(15000);
        for (int i=0; i<150; i++) {
            array.add(new Integer[1*1024*1024]);
            Thread.sleep(50);
        }

        for (int i = 0; i<150; i++) {
            array.remove(array.size() - 1);
            if (i%10 == 0) {
                System.gc();
            }
            Thread.sleep(50);
            System.gc();
            Thread.sleep(1000);
        }
    }
}
