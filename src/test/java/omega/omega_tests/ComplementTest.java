package omega.omega_tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.function.Function;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.slice.ComplementOrder;
import omega.test.nbagen.NBAGen;
import roll.words.Alphabet;

public class ComplementTest {
	
	public static void main(String []args) {
		
		while(true) {
			NBA nba = NBAGen.getRandomNBA(3, 2);
			ComplementCommandOptions opts = new ComplementCommandOptions();
			ComplementOrder complement = new ComplementOrder(opts, nba);
			complement.explore();
			if(!spotTestComplement(nba, complement)) {
				System.out.println(nba.toDot());
				break;
			}
		}
	}
	
    // use spot to check whether A is included by B
    public static boolean isIncludedSpot(NBA A, NBA B) {
        String command = "autfilt --included-in=";
        boolean result = executeTool(command, true, "HOA", A, B);
        return result;
    }

    public static boolean isEquivalentSpot(NBA A, NBA B) {
        String command = "autfilt --equivalent-to=";
        boolean result = executeTool(command, true, "HOA", A, B);
        return result;
    }

    public static boolean isIncludedGoal(String goal, NBA A, NBA B) {
        String command = goal + " containment ";
        boolean result = executeTool(command, false, "(true", A, B);
        return result;
    }

    public static boolean isEquivalentGoal(String goal, NBA A, NBA B) {
        String command = goal + " equivalence ";
        boolean result = executeTool(command, false, "true", A, B);
        return result;
    }
    
    private static void executeSpot(String command, String output) {
    	final Runtime rt = Runtime.getRuntime();
    	Process proc = null;
    	String cmd = command + " > " + output;
        try {
            proc = rt.exec(cmd);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(cmd);
        while (true) {
            if (!proc.isAlive()) {
                break;
            }
        }
    }
    
    public static boolean spotTestComplement(NBA A, NBA complement) {
    	File fileA = new File("A.hoa");
    	try {
            outputHOAStream(A, new PrintStream(new FileOutputStream(fileA)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	String D = "D.hoa";
    	File fileD = new File(D);
    	// determinize
        String command = "autfilt --deterministic " + fileA.getAbsolutePath();
        executeSpot(command, fileD.getAbsolutePath());
        
        // complement
        command = "autfilt --complement -B -S " + fileD.getAbsolutePath();
        String C = "C.hoa";
        File fileC = new File(C);
//        command = "autfilt --complement " + fileD.getAbsolutePath();
        executeSpot(command, fileC.getAbsolutePath());
        
        // check equivalence of C and complement
        File fileCC = new File("CC.hoa");
    	try {
            outputHOAStream(complement, new PrintStream(new FileOutputStream(fileCC)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    	command = "autfilt --equivalent-to=" + fileC.getAbsolutePath() + " " + fileCC.getAbsolutePath();
    	final Runtime rt = Runtime.getRuntime();
    	Process proc = null;
        try {
            proc = rt.exec(command);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(command);
        while (true) {
            if (!proc.isAlive()) {
                break;
            }
        }

        final BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        boolean result = false;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains("HOA")) {
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static boolean executeTool(String cmd, boolean reverse, String pattern, NBA A, NBA B) {
        File fileA = new File("/tmp/A.hoa");
        File fileB = new File("/tmp/B.hoa");
        try {
            outputHOAStream(A, new PrintStream(new FileOutputStream(fileA)));
            outputHOAStream(B, new PrintStream(new FileOutputStream(fileB)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        final Runtime rt = Runtime.getRuntime();
        String command = null;
        // add files
        if (reverse) {
            command = cmd + fileB.getAbsolutePath() + " " + fileA.getAbsolutePath();
        } else {
            command = cmd + fileA.getAbsolutePath() + " " + fileB.getAbsolutePath();
        }
        Process proc = null;
        try {
            proc = rt.exec(command);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        System.out.println(command);
        while (true) {
            if (!proc.isAlive()) {
                break;
            }
        }

        final BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = null;
        boolean result = false;
        try {
            while ((line = reader.readLine()) != null) {
                if (line.contains(pattern)) {
                    result = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void outputHOAStream(NBA nba, PrintStream out) {
        int numBits = Integer.highestOneBit(nba.getAlphabetSize());
        Function<Integer, String> labelFunc = x -> translateInteger(x, numBits);
        Function<Integer, String> apList = x -> "a" + x;
        outputHOAStream(nba, out, numBits, apList, labelFunc);
    }

    public static void outputHOAStream(NBA nba, PrintStream out, int numAp, Function<Integer, String> apList,
            Function<Integer, String> labelFunc) {
        out.println("HOA: v1");
        out.println("tool: \"omega\"");
        out.println("properties: explicit-labels state-acc trans-labels ");

        out.println("States: " + nba.getStateSize());
        for(int init : nba.getInitialStates()) {
        	out.println("Start: " + init);
        }
        out.println("acc-name: Buchi");
        out.println("Acceptance: 1 Inf(0)");
        out.print("AP: " + numAp);
        for (int index = 0; index < numAp; index++) {
            out.print(" \"" + apList.apply(index) + "\"");
        }
        out.println();
        out.println("--BODY--");

        for (int stateNr = 0; stateNr < nba.getStateSize(); stateNr++) {
            out.print("State: " + stateNr);
            if (nba.isFinal(stateNr))
                out.print(" {0}");
            out.println();
            for (int letter = 0; letter < nba.getAlphabetSize(); letter++) {
                if (nba.getAlphabet().indexOf(Alphabet.DOLLAR) == letter)
                    continue;
                for (int succNr : nba.getState(stateNr).getSuccessors(letter)) {
                    out.println("[" + labelFunc.apply(letter) + "]  " + succNr);
                }
            }
        }
        out.println("--END--");
    }

    private static String translateInteger(int value, int numBits) {
        StringBuilder builder = new StringBuilder();
        int bit = 1;
        for (int index = 0; index < numBits; index++) {
            if ((bit & value) == 0) {
                builder.append("!");
            }
            builder.append("" + index);
            if (index != numBits - 1) {
                builder.append("&");
            }
            bit <<= 1;
        }
        return builder.toString();
    }

}
