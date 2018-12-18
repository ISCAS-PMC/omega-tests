package omega.omega_tests;

import omega.automata.NBA;
import omega.automata.TNBA;
import omega.util.parser.AutomataPrinter;

public class PrinterTest {
	
	public static void main(String[] args) {
		NBA nba = ComplementRun.getTest3();
		
		AutomataPrinter printer = new AutomataPrinter(nba.getAlphabet());
		printer.printHOA(nba, System.out);
		printer.close();
	}

}
