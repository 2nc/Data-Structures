public class OffByN implements CharacterComparator {

    int offN = 0;
    public OffByN(int n){
        offN = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = y - x;
        if(Math.abs(diff) == offN){
            return true;
        }
        return false;
    }
}
