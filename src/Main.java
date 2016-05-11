import java.util.PriorityQueue;

/**
 * Created by stephen on 08/05/16.
 */
public class Main {

    public static void main(String []args){
        PriorityQueue<Integer> test = new PriorityQueue<>();
        test.add(3);
        test.add(4);
        test.add(1);
        test.add(99);
        System.out.println(test.peek());
    }
}
