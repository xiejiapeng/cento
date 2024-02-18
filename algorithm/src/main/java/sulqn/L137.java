package sulqn;

public class L137 {
    public int singleNumber(int[] nums) {
        int mask = 0x80000000;
        int cur = 0;
        for(int i = 0; i < 32; i++){
            int t = 0;
            for(int j = 0; j < nums.length; j++){
                t += ((nums[j] & mask) == 0 ? 0 : 1);
            }
            t %= 3;
            cur = cur * 2 + t;
            mask = mask >>> 1;
        }
        return cur;
    }

    public static void main(String[] args) {
        //10 10 11 10
        System.out.println(new L137().singleNumber(new int[]{0,0,0,1,1,1,99}));
    }
}
