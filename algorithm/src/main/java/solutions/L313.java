package solutions;

import java.util.*;

public class L313 {
    public int nthSuperUglyNumber(int n, int[] primes) {
        if(n == 1)return 1;
        List<Long> ug = new ArrayList();
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingLong(o -> primes[o[0]] * ug.get(o[1])));
        ug.add(1l);
        for(int i = 0; i < primes.length; i++){
            q.add(new int[]{i,0});
        }
        long last = -1;
        int c = 0;
        for(;;){
            int[] poll = q.poll();
            long x = primes[poll[0]] * ug.get(poll[1]);
            if(x == last){
                q.add(new int[]{poll[0],poll[1]+1});
                continue;
            } else{
                c++;
                last = x;
                ug.add(x);
                if(c == n - 1)return (int)x;
                q.add(new int[]{poll[0],poll[1]+1});
            }

        }
    }

    public static void main(String[] args) {
        for(int i = 1; i <= 10; i++){
            System.out.println(new L313().nthSuperUglyNumber(i,new int[]{2,3,5}));
        }

        System.out.println(new L313().nthSuperUglyNumber(124,new int[]{2,3,5}));

    }
}
