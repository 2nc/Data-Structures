package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int[] spread = new int[M];
        for(int i = 0; i < M; i++){
            spread[i] = 0;
        }
        for(int i = 0; i < oomages.size(); i++){
            Oomage o = oomages.get(i);
            int bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            spread[bucketNum] = spread[bucketNum] + 1;
        }
        for(int i = 0; i < M; i++){
            if(spread[i] < oomages.size() / 50){
                return false;
            }
            if(spread[i] > oomages.size() / 2.5){
                return false;
            }
        }
        return true;
    }
}
