package omega.omega_tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

import omega.automata.Automata;
import omega.util.parser.AutomataPrinter;

public class Spot {
	
	private Spot() {
		
	}
	
	public static boolean spotTestEquivalence(Automata A, Automata B) {
		File fileA = new File("E1.hoa");
    	AutomataPrinter printer = new AutomataPrinter(A.getAlphabet());
    	try {
    		printer.printHOA(A, new FileOutputStream(fileA));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	File fileB = new File("E2.hoa");
    	try {
    		printer.printHOA(B, new FileOutputStream(fileB));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	return spotEquiv(fileA.getAbsolutePath(), fileB.getAbsolutePath());
	}
	
	public static boolean spotTestComplement(Automata A, Automata complement) {
    	File fileA = new File("A.hoa");
    	AutomataPrinter printer = new AutomataPrinter(A.getAlphabet());
    	try {
    		printer.printHOA(A, new PrintStream(new FileOutputStream(fileA)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	String D = "D.hoa";
    	File fileD = new File(D);
    	// determinize
        String command = "autfilt --deterministic " + fileA.getAbsolutePath() + " -o " + fileD.getAbsolutePath();
        executeSpot(command);
        
        // complement
        String C = "C.hoa";
        File fileC = new File(C);
        command = "autfilt --complement -B -S " + fileD.getAbsolutePath() + " -o " + fileC.getAbsolutePath();
        executeSpot(command);
        
        // check equivalence of C and complement
        File fileCC = new File("CC.hoa");
    	try {
    		printer.printHOA(complement, new PrintStream(new FileOutputStream(fileCC)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	
        return spotEquiv(fileC.getAbsolutePath(), fileCC.getAbsolutePath());
    }
	
    private static boolean spotEquiv(String A1, String A2) {
    	String command = "autfilt --equivalent-to=" + A1 + " " + A2;
    	Process proc = executeSpot(command);
        final BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        boolean result = false;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("HOA")) {
                    result = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
	
    private static Process executeSpot(String command) {
    	final Runtime rt = Runtime.getRuntime();
    	Process proc = null;
        try {
            proc = rt.exec(command);
            proc.waitFor();
        } catch (IOException | InterruptedException e1) {
            e1.printStackTrace();
        }
        System.out.println(command);
        return proc;
    }


}
