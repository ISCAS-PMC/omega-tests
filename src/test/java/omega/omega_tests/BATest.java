package omega.omega_tests;

import omega.automata.NBA;
import omega.util.parser.Format;
import omega.util.parser.ISingleParser;
import omega.util.parser.UtilParserNBA;

public class BATest {
    
    public static void main(String[] args) {
        String file = "/home/liyong/git/omega-tests/examples/A.ba";
        ISingleParser<NBA> parser = UtilParserNBA.getParserNBA(Format.BA);
        parser.parse(file);
        System.out.println(parser.get().toDot());
        System.out.println(parser.get().toBA());
        parser.print(parser.get(), System.out);
        
        
    }

}
