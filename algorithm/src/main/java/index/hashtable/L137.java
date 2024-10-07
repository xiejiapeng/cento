package index.hashtable;

public class L137 {
    public int singleNumber(int[] nums) {
        int flag = 1;
        int ret = 0;
        for (int i = 0; i < 32; i++) {
            int t = 0;
            for (int x : nums) {
                t += ((x & flag) == 0 ? 0 : 1);
            }
            if (t % 3 != 0) {
                ret = (ret | flag);
            }

            flag <<= 1;
        }
        return ret;
    }
}
