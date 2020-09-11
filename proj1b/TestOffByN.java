/**
 * @author wnc
 */
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static CharacterComparator cc = new OffByN(5);
    @Test
    public void Testbyn(){
        assertTrue(cc.equalChars('a', 'f'));
        assertTrue(cc.equalChars('f', 'a'));
    }
}
