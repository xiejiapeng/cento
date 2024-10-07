package sulwish;

/*
有个内含单词的超大文本文件，给定任意两个不同的单词，找出在这个文件中这两个单词的最短距离(相隔单词数)。
如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 */


public class M1711 {
    public int findClosest(String[] words, String word1, String word2) {
        int a = -1;
        int b = -1;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++){
            if(words[i].equals(word1)) {
                a = i;
                if(b != -1){
                    min = Math.min(min, Math.abs(a-b));
                }
            } else if(words[i].equals(word2)) {
                b = i;
                if(a != -1) {
                    min = Math.min(min, Math.abs(a-b));
                }
            }
        }
        return min;
    }
}
