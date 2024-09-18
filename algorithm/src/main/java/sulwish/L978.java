package sulwish;

public class L978 {
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        int[] f = new int[n];
        int max = 1;
        for (int i = n - 1; i > -1; i--){
            if(i == n - 1) {
                f[i] = 1;
            } else {
                if(arr[i] > arr[i+1]) {
                    if(i + 2 >= n || arr[i+1] < arr[i+2])f[i] = 1 + f[i+1];
                    else f[i] = 2;
                } else if(arr[i] < arr[i+1]) {
                    if(i + 2 >= n || arr[i+1] > arr[i+2])f[i] = 1 + f[i+1];
                    else f[i] = 2;
                } else {
                    f[i] = 1;
                }
            }
            max = Math.max(max, f[i]);
        }
        return max;
    }
}
