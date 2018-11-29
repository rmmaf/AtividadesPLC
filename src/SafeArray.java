import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SafeArray extends Thread {
    public ArrayList<Integer> list;
    private ArrayList<Lock> locks;

    public SafeArray() {
        list = new ArrayList<>();
        locks = new ArrayList<>();
    }

    public int get(int i, Fred t){
        Boolean l1 = locks.get(i).tryLock();
        while (!l1) {
            if (l1) locks.get(i).unlock();
            l1 = locks.get(i).tryLock();
            System.out.println("wait to get: " + i + "from thread: " + t.num);
        }
        int v = list.get(i);
        locks.get(i).unlock();
        System.out.println("get: " + i);
        return v;
    }
   
    public void set(int v) {
        list.add(v);
        locks.add(new ReentrantLock());
        System.out.println("add: " + v);
    }

    public void swap(int i, int j) {
        Boolean l1 = locks.get(i).tryLock();
        Boolean l2 = locks.get(j).tryLock();

        while (!(l1 && l2)) {
            if (l1) locks.get(i).unlock();
            if (l2) locks.get(j).unlock();
            l1 = locks.get(i).tryLock();
            l2 = locks.get(j).tryLock();
        }
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
        System.out.println("swap: " + i + ", " + j);

        locks.get(i).unlock();
        locks.get(j).unlock();
    }
}