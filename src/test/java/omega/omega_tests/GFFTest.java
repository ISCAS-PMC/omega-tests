package omega.omega_tests;

import omega.automata.NBA;
import omega.util.parser.gff.GFFFileParser;

public class GFFTest {
    
    public static void main(String[] args) {
        GFFFileParser parser = new GFFFileParser();
        parser.parse("/home/liyong/git/omega-tests/examples/A5.gff");
        NBA buchi = parser.get();
        System.out.println(buchi.toDot());
        System.out.println(buchi.toGFF());
        parser.print(buchi, System.out);
    }

}
