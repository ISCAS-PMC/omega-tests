package omega.omega_tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import omega.automata.NBA;
import omega.test.nbagen.NBAGen;
import omega.util.parser.AutomataPrinter;
import omega.util.parser.hoa.HOAPairParser;
import omega.util.parser.hoa.HOAParser;

public class HOATest {
    
    public static void main(String[] args) {
        HOAParser parser = new HOAParser();
        parser.parse("/home/liyong/git/omega-tests/examples/A.hoa");
        NBA nba = parser.get();
        parser.print(nba, System.out);
        
        parser = new HOAParser();
        parser.parse("/home/liyong/git/omega-tests/examples/A1.hoa");
        nba = parser.get();
        parser.print(nba, System.out);
        System.out.println("-----------------------------");
        HOAPairParser pairParser = new HOAPairParser("/home/liyong/git/omega-tests/examples/A.hoa", "/home/liyong/git/omega-tests/examples/A1.hoa");
        nba = pairParser.getLeft();
        pairParser.print(nba, System.out);
        nba = pairParser.getRight();
        pairParser.print(nba, System.out);
        
//        parser.parse("/home/liyong/git/omega-tests/examples/A2.hoa");
//        nba = parser.get();
//        
//        parser.parse("/home/liyong/git/omega-tests/examples/A3.hoa");
//        nba = parser.get();
        parser = new HOAParser();
        parser.parse("/home/liyong/tools/buchistore/exp239.hoa");
        nba = parser.get();
        try {
			parser.print(nba,  new FileOutputStream("A.hoa"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(nba.getAlphabetSize());
        System.out.println(nba.toDot());
        // test randomly
//        while (true) {
//        	nba = NBAGen.getRandomNBA(4, 5);
//        	AutomataPrinter printer = new AutomataPrinter(nba.getAlphabet());
//        	try {
//				printer.printHOA(nba, new FileOutputStream("H1.hoa"));
//				parser = new HOAParser();
//		        parser.parse("H1.hoa");
//		        nba = parser.get();
//		        parser.print(nba,  new FileOutputStream("H2.hoa"));
//		        if(! UtilTest.spotEquivalence("H1.hoa", "H2.hoa")) {
//		        	System.out.println("Error");
//		        	break;
//		        }
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//        	
//        }
    }

}
