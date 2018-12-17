package omega.omega_tests;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import omega.automata.NBA;
import omega.automata.TNBA;
import omega.automata.Transition;
import omega.main.options.CmdLineParser.ComplementCommandOptions;
import omega.operations.complement.ncsb.ComplementNcsbOtf;
import omega.operations.complement.slice.ComplementOrder;
import omega.operations.complement.slice.ComplementSliceBreakpoint;
import omega.operations.convert.NBA2TNBA;
import omega.operations.convert.TNBA2NBA;
import omega.operations.determinize.bracket.DeterminizeBacket;
import omega.operations.determinize.ldba.LDBA2DPA;
import omega.operations.determinize.ldba.TLDBA2TDPA;
import omega.operations.determinize.safra.DeterminizePiterman;
import omega.operations.determinize.safra.DeterminizeTPiterman;
import omega.util.parser.Format;
import omega.util.parser.UtilParserNBA;
import omega.util.parser.hoa.HOAParser;
import roll.words.Alphabet;

public class TLDBA2TDPATest {
	
	public static void main(String[] args) {
		Alphabet alphabet = UtilAphabet.getAlphabet();
		TNBA tnba = new TNBA(alphabet);
		tnba.addState();
		tnba.addState();
		tnba.addState();
		tnba.addState();
		
		tnba.getState(0).addSuccessor(0, 0);
		tnba.getState(0).addSuccessor(1, 0);
		tnba.getState(0).addSuccessor(0, 1);
		tnba.getState(0).addSuccessor(1, 2);
		
		tnba.getState(1).addSuccessor(0, 1);
		tnba.getState(1).addSuccessor(1, 3);
		
		tnba.getState(2).addSuccessor(0, 3);
		tnba.getState(2).addSuccessor(1, 2);
		
		tnba.getState(3).addSuccessor(0, 3);
		tnba.getState(3).addSuccessor(1, 3);
		
		tnba.setInitial(0);
		tnba.setFinal(new Transition(1, 0, 1));
		tnba.setFinal(new Transition(2, 1, 2));
//		tnba.setFinal(new Transition(0, 0, 1));
//		tnba.setFinal(new Transition(0, 1, 2));
		
		TLDBA2TDPA tconv = new TLDBA2TDPA(tnba);
		tconv.explore();
		
		System.out.println(tnba.toDot());
		System.out.println(tconv.toDot());
		
		NBA nba = new NBA(alphabet);
		nba.addState();
		nba.addState();
		nba.addState();
		nba.addState();
		
		nba.getState(0).addSuccessor(0, 0);
		nba.getState(0).addSuccessor(1, 0);
		nba.getState(0).addSuccessor(0, 1);
		nba.getState(0).addSuccessor(1, 2);
		
		nba.getState(1).addSuccessor(0, 1);
		nba.getState(1).addSuccessor(1, 3);
		
		nba.getState(2).addSuccessor(0, 3);
		nba.getState(2).addSuccessor(1, 2);
		
		nba.getState(3).addSuccessor(0, 3);
		nba.getState(3).addSuccessor(1, 3);
		
		nba.setInitial(0);
		nba.setFinal(1);
		nba.setFinal(2);
		
		LDBA2DPA conv = new LDBA2DPA(nba);
		conv.explore();
		System.out.println(conv.toDot());
		
		tnba = new NBA2TNBA(nba);
		tnba.explore();
		
		System.out.println(tnba.toDot());
		
		nba = new TNBA2NBA(tnba);
		nba.explore();
		
		System.out.println(nba.toDot());
		
		DeterminizeTPiterman tdeterminize = new DeterminizeTPiterman(tnba);
		tdeterminize.explore();
		System.out.println(tdeterminize.toDot());
		
		DeterminizePiterman determinize = new DeterminizePiterman(nba);
		determinize.explore();
		System.out.println(determinize.toDot());
		
		String file = "/home/liyong/tools/ldba/exp14.hoa";
		HOAParser parser = (HOAParser) UtilParserNBA.getParserNBA(Format.HOA);
		parser.parse(file);
		nba = parser.get();
		
//		DeterminizeBacket dete = new DeterminizeBacket(nba);
//		dete.explore();
//		System.out.println("Done...");
//		try {
//			parser.print(dete, new FileOutputStream("/home/liyong/tools/ldba/dpa14.hoa"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		nba.getAcceptance().maximizeFinalSet();
		ComplementCommandOptions opts = new ComplementCommandOptions();
		opts.lazyB = true;
		opts.madjd = true;
		opts.madjs = true;
		ComplementSliceBreakpoint bk = new ComplementSliceBreakpoint(opts, nba);
		bk.explore();
		System.out.println("Done...");
		try {
		parser.print(bk, new FileOutputStream("/home/liyong/tools/ldba/c14.hoa"));
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//		tnba = new NBA2TNBA(nba);
//		tnba.explore();
//		tdeterminize = new DeterminizeTPiterman(tnba);
//		tdeterminize.explore();
//		
//		try {
//			parser.print(tdeterminize, new FileOutputStream("/home/liyong/tools/ldba/dpa14.hoa"));
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		tnba = new TNBA(alphabet);
		tnba.addState();
		tnba.addState();
		tnba.addState();
		tnba.addState();
		tnba.addState();
		
		tnba.getState(0).addSuccessor(0, 0);
		tnba.getState(0).addSuccessor(0, 1);
		tnba.getState(0).addSuccessor(1, 0);
		
		tnba.getState(1).addSuccessor(0, 3);
		tnba.getState(1).addSuccessor(1, 2);
		
		tnba.getState(2).addSuccessor(0, 4);
		
		tnba.getState(3).addSuccessor(0, 3);
		
		tnba.getState(4).addSuccessor(1, 2);
		
		tnba.setInitial(0);
		
		tnba.setFinal(new Transition(0, 0, 1));
		tnba.setFinal(new Transition(1, 0, 3));
		tnba.setFinal(new Transition(3, 0, 3));
		tnba.setFinal(new Transition(2, 0, 4));
		
		tdeterminize = new DeterminizeTPiterman(tnba);
		tdeterminize.explore();
		System.out.println("Done...");
		System.out.println(tdeterminize.toDot());

	}

}
