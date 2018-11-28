package omega.omega_tests;

import omega.automata.NBA;
import omega.util.parser.hoa.HOAPairParser;
import omega.util.parser.hoa.HOAParser;

public class HOATest {
    
    public static void main(String[] args) {
        HOAParser parser = new HOAParser();
        parser.parse("/home/liyong/workspace-neon/omega-tests/examples/A.hoa");
        NBA nba = parser.get();
        parser.print(nba, System.out);
        
        parser = new HOAParser();
        parser.parse("/home/liyong/workspace-neon/omega-tests/examples/A1.hoa");
        nba = parser.get();
        parser.print(nba, System.out);
        System.out.println("-----------------------------");
        HOAPairParser pairParser = new HOAPairParser("/home/liyong/workspace-neon/omega-tests/examples/A.hoa", "/home/liyong/workspace-neon/omega-tests/examples/A1.hoa");
        nba = pairParser.getLeft();
        pairParser.print(nba, System.out);
        nba = pairParser.getRight();
        pairParser.print(nba, System.out);
    }

}
