package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
  @Test
  public void testThreeAddThreeRemove() {
      AListNoResizing<Integer> alnr = new AListNoResizing<>();
      BuggyAList<Integer> bal = new BuggyAList<>();

      alnr.addLast(4);
      alnr.addLast(5);
      alnr.addLast(6);
      bal.addLast(4);
      bal.addLast(5);
      bal.addLast(6);

      assertEquals(alnr.size(), bal.size());

      assertEquals(alnr.removeLast(), bal.removeLast());
      assertEquals(alnr.removeLast(), bal.removeLast());
      assertEquals(alnr.removeLast(), bal.removeLast());
  }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeB = B.size();
                //System.out.println("size: " + size);
                assertEquals(size, sizeB);
            }else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0){
                    continue;
                }
                var last = L.getLast();
                var lastB = B.getLast();
                //System.out.println("getLast = " + last);
                assertEquals(last, lastB);
            }else if (operationNumber == 3) {
                if (L.size() == 0){
                    continue;
                }
                var last = L.removeLast();
                var lastB = B.removeLast();
                //System.out.println("removeLast - " + last);
                assertEquals(last, lastB);
            }
        }
    }
}
