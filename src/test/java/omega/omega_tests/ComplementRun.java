package omega.omega_tests;

import omega.automata.NBA;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.slice.ComplementSliceBreakpoint;
import roll.words.Alphabet;

public class ComplementRun {
	
	public static void main(String[] args) {
		
		NBA nba = getTest4();
		System.out.println(nba.toDot());
		ComplementCommandOptions opts = new ComplementCommandOptions();
		opts.madjd = false;
		ComplementSliceBreakpoint bpk = new ComplementSliceBreakpoint(opts, nba);
		bpk.explore();
		System.out.println(bpk.toDot());
		System.out.println(bpk.toBA());
		opts.madjd = true;
		bpk = new ComplementSliceBreakpoint(opts, nba);
		bpk.explore();
		System.out.println(bpk.toDot());
		System.out.println(bpk.toBA());
	}
	
	public static NBA getTest1() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		
		nba.getState(0).addSuccessor(0, 0);
		nba.getState(0).addSuccessor(1, 0);

		nba.getState(0).addSuccessor(1, 2);
		
		nba.getState(2).addSuccessor(1, 1);
		
		nba.getState(1).addSuccessor(0, 1);
		nba.getState(1).addSuccessor(0, 0);
		
		nba.setInitial(0);
		nba.setFinal(1);

		return nba;
	}
	
	public static NBA getTest2() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		nba.getState(0).addSuccessor(0, 1);
		nba.getState(0).addSuccessor(0, 2);
		nba.getState(0).addSuccessor(1, 0);
		nba.getState(1).addSuccessor(0, 0);
		nba.getState(1).addSuccessor(0, 1);
		nba.getState(1).addSuccessor(0, 2);
		nba.getState(1).addSuccessor(1, 1);
		nba.getState(1).addSuccessor(1, 2);
		nba.getState(2).addSuccessor(1, 1);
		nba.getState(2).addSuccessor(1, 2);
		nba.setInitial(0);
		nba.setFinal(1);
		return nba;
	}
	
	// test madjs
	public static NBA getTest3() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		nba.getState(0).addSuccessor(0, 0);
		nba.getState(0).addSuccessor(1, 0);
		nba.getState(0).addSuccessor(1, 1);
		nba.getState(1).addSuccessor(0, 2);
		nba.getState(1).addSuccessor(1, 1);
		nba.getState(2).addSuccessor(0, 2);
		nba.getState(2).addSuccessor(1, 2);
		nba.setInitial(0);
		nba.setFinal(1);
		return nba;
	}
	//test madjd, likely to be a bug in the LICS 18 paper
	// the complementation may generate a complement without the word 0(1)
	public static NBA getTest4() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		nba.getState(0).addSuccessor(0, 1);
		nba.getState(0).addSuccessor(0, 2);
		nba.getState(1).addSuccessor(0, 1);
		nba.getState(1).addSuccessor(1, 0);
		nba.getState(2).addSuccessor(1, 1);
		nba.getState(2).addSuccessor(1, 2);
		nba.setInitial(0);
		nba.setFinal(1);
		return nba;
	}

}
