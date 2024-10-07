package index.heap;

/*
2 3 5
 */

import java.util.ArrayList;
import java.util.List;

public class L264 {
    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            System.out.println(new L264().nthUglyNumber(i));
        }

    }

    public int nthUglyNumber(int n) {
        List<Integer> all = new ArrayList<>();
        all.add(1);
        int a = 0;
        int b = 0;
        int c = 0;
        int ans = 1;
        int last = -1;
        while (n > 1) {
            if (all.get(a) * 2 <= all.get(b) * 3 && all.get(a) * 2 <= all.get(c) * 5) {
                ans = all.get(a) * 2;
                a++;
            } else if (all.get(b) * 3 <= all.get(a) * 2 && all.get(b) * 3 <= all.get(c) * 5) {
                ans = all.get(b) * 3;
                b++;
            } else {
                ans = all.get(c) * 5;
                c++;
            }
            if (ans != last) {
                all.add(ans);
                last = ans;
                n--;
            }
        }
        return ans;
    }
}
