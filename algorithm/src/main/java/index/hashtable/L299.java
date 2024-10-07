package index.hashtable;

/*
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：

写出一个秘密数字，并请朋友猜这个数字是多少。朋友每猜测一次，你就会给他一个包含下述信息的提示：

猜测数字中有多少位属于数字和确切位置都猜对了（称为 "Bulls"，公牛），
有多少位属于数字猜对了但是位置不对（称为 "Cows"，奶牛）。也就是说，这次猜测中有多少位非公牛数字可以通过重新排列转换成公牛数字。
给你一个秘密数字 secret 和朋友猜测的数字 guess ，请你返回对朋友这次猜测的提示。

提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B 表示奶牛。

请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 */

import java.util.HashMap;
import java.util.Map;

public class L299 {
    public static void main(String[] args) {
        System.out.println(new L299().getHint("11", "10"));
    }

    public String getHint(String secret, String guess) {
        Map<Character, Integer> count = new HashMap<>();
        int a = 0;
        int b = 0;
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) a++;
            else {
                count.put(secret.charAt(i), count.getOrDefault(secret.charAt(i), 0) + 1);

            }
        }
        for (int i = 0; i < guess.length(); i++) {
            if (count.containsKey(guess.charAt(i)) && secret.charAt(i) != guess.charAt(i)) {
                b++;
                count.put(guess.charAt(i), count.get(guess.charAt(i)) - 1);
                if (count.get(guess.charAt(i)) == 0) count.remove(guess.charAt(i));
            }
        }
        return a + "A" + b + "B";
    }
}
