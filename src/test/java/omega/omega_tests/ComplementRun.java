package omega.omega_tests;

import omega.automata.NBA;
import omega.automata.TNBA;
import omega.automata.Transition;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.slice.ComplementSliceBreakpoint;
import omega.operations.complement.slice.ComplementTSliceBreakpoint;
import omega.operations.convert.NBA2TNBA;
import omega.operations.determinize.safra.DeterminizeTPiterman;
import roll.words.Alphabet;

public class ComplementRun {
	
	public static void main(String[] args) {
		
		TNBA tnba = new NBA2TNBA(getTest1());
		tnba.explore();
		DeterminizeTPiterman pit = new DeterminizeTPiterman(tnba);
		pit.explore();
		System.out.println(pit.toDot());
		System.out.println(UtilTest.spotTestEquivalence(tnba, pit));
	}
	
	public static void testComplement() {
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
		
		nba = getTestS1();
		bpk = new ComplementSliceBreakpoint(opts, nba);
		bpk.explore();
		System.out.println(bpk.toDot());
		
		TNBA tnba = getTestT1();
		ComplementTSliceBreakpoint tbpk = new ComplementTSliceBreakpoint(opts, tnba);
		tbpk.explore();
		System.out.println(tnba.toDot());
		System.out.println(tbpk.toDot());
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
	
	public static TNBA getTestT1() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		TNBA tnba = new TNBA(alphabet);
		tnba.addState();
		tnba.getState(0).addSuccessor(0, 0);
		tnba.getState(0).addSuccessor(1, 0);
		tnba.setInitial(0);
		tnba.setFinal(new Transition(0, 1, 0));
		return tnba;
	}
	
	public static NBA getTestS1() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.getState(0).addSuccessor(0, 0);
//		nba.getState(0).addSuccessor(1, 0);
		nba.getState(0).addSuccessor(1, 1);
		nba.getState(1).addSuccessor(1, 1);
		nba.getState(1).addSuccessor(0, 0);
		nba.setInitial(0);
		nba.setFinal(1);
		return nba;
	}
	
	public static TNBA getTestT2() {
		Alphabet alphabet = new Alphabet();
		alphabet.addLetter('a');
		alphabet.addLetter('b');
		TNBA nba = new TNBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		nba.addState();
		nba.getState(0).addSuccessor(1, 2);
		nba.getState(0).addSuccessor(1, 3);
		nba.getState(1).addSuccessor(0, 2);
		nba.setInitial(0);
		nba.setFinal(new Transition(0, 1, 3));
		return nba;
	}

}
