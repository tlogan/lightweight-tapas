// Generated from /Users/thomas/tlogan@github.com/lightweight-tapas/src/tapas/slim/Slim.g4 by ANTLR 4.13.1

from dataclasses import dataclass
from typing import *
from tapas.util_system import box, unbox
from contextlib import contextmanager

from tapas.slim.analyzer import * 

from pyrsistent import m, pmap, v
from pyrsistent.typing import PMap 


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class SlimParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, ID=31, INT=32, 
		WS=33;
	public static final int
		RULE_ids = 0, RULE_typ_base = 1, RULE_typ = 2, RULE_negchain = 3, RULE_qualification = 4, 
		RULE_subtyping = 5, RULE_expr = 6, RULE_base = 7, RULE_function = 8, RULE_record = 9, 
		RULE_argchain = 10, RULE_pipeline = 11, RULE_keychain = 12, RULE_target = 13, 
		RULE_pattern = 14, RULE_pattern_base = 15, RULE_pattern_record = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"ids", "typ_base", "typ", "negchain", "qualification", "subtyping", "expr", 
			"base", "function", "record", "argchain", "pipeline", "keychain", "target", 
			"pattern", "pattern_base", "pattern_record"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'TOP'", "'BOT'", "'@'", "'~'", "':'", "'('", "')'", "'|'", "'&'", 
			"'->'", "','", "'EXI'", "'['", "']'", "'ALL'", "'<:'", "'LFP'", "'\\'", 
			"';'", "'if'", "'then'", "'else'", "'let'", "'fix'", "'case'", "'=>'", 
			"'_.'", "'='", "'|>'", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "ID", "INT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Slim.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	_solver : Solver 
	_cache : dict[int, str] = {}

	_guidance : Guidance 
	_overflow = False  

	def init(self): 
	    self._solver = Solver() 
	    self._cache = {}
	    self._guidance = nt_default 
	    self._overflow = False  

	def reset(self): 
	    self._guidance = nt_default
	    self._overflow = False
	    # self.getCurrentToken()
	    # self.getTokenStream()



	def getGuidance(self):
	    return self._guidance

	def tokenIndex(self):
	    return self.getCurrentToken().tokenIndex

	def guide_nonterm(self, f : Callable, *args) -> Optional[Nonterm]:
	    for arg in args:
	        if arg == None:
	            self._overflow = True

	    nt_result = None
	    if not self._overflow:
	        nt_result = f(*args)
	        self._guidance = nt_result

	        tok = self.getCurrentToken()
	        if tok.type == self.EOF :
	            self._overflow = True 

	    return nt_result 



	def guide_lex(self, guidance : Union[Symbol, Terminal]):   
	    if not self._overflow:
	        self._guidance = guidance 

	        tok = self.getCurrentToken()
	        if tok.type == self.EOF :
	            self._overflow = True 


	def guide_symbol(self, text : str):
	    self.guide_lex(Symbol(text))

	def guide_terminal(self, text : str):
	    self.guide_lex(Terminal(text))



	def collect(self, f : Callable, *args):

	    if self._overflow:
	        return None
	    else:

	        clean = next((
	            False
	            for arg in args
	            if arg == None
	        ), True)

	        if clean:
	            return f(*args)
	        else:
	            return None
	        # TODO: caching is broken; tokenIndex does not change 
	        # index = self.tokenIndex() 
	        # cache_result = self._cache.get(index)
	        # print(f"CACHE: {self._cache}")
	        # if False: # cache_result:
	        #     return cache_result
	        # else:
	        #     result = f(*args)
	        #     self._cache[index] = result
	        #     return result


	public SlimParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdsContext extends ParserRuleContext {
		public tuple[str, ...] combo;
		public Token ID;
		public IdsContext ids;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public IdsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ids; }
	}

	public final IdsContext ids() throws RecognitionException {
		IdsContext _localctx = new IdsContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_ids);
		try {
			setState(41);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(35);
				((IdsContext)_localctx).ID = match(ID);

				_localctx.combo = tuple([(((IdsContext)_localctx).ID!=null?((IdsContext)_localctx).ID.getText():null)])

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(37);
				((IdsContext)_localctx).ID = match(ID);
				setState(38);
				((IdsContext)_localctx).ids = ids();

				_localctx.combo = tuple([(((IdsContext)_localctx).ID!=null?((IdsContext)_localctx).ID.getText():null)]) + ((IdsContext)_localctx).ids.combo

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Typ_baseContext extends ParserRuleContext {
		public Typ combo;
		public Token ID;
		public Typ_baseContext typ_base;
		public TypContext typ;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public Typ_baseContext typ_base() {
			return getRuleContext(Typ_baseContext.class,0);
		}
		public TypContext typ() {
			return getRuleContext(TypContext.class,0);
		}
		public Typ_baseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typ_base; }
	}

	public final Typ_baseContext typ_base() throws RecognitionException {
		Typ_baseContext _localctx = new Typ_baseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_typ_base);
		try {
			setState(67);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(44);
				match(T__0);

				_localctx.combo = Top() 

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(46);
				match(T__1);

				_localctx.combo = Bot() 

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(48);
				((Typ_baseContext)_localctx).ID = match(ID);

				_localctx.combo = TVar((((Typ_baseContext)_localctx).ID!=null?((Typ_baseContext)_localctx).ID.getText():null)) 

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(50);
				match(T__2);

				_localctx.combo = TUnit() 

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(52);
				match(T__3);
				setState(53);
				((Typ_baseContext)_localctx).ID = match(ID);
				setState(54);
				((Typ_baseContext)_localctx).typ_base = typ_base();

				_localctx.combo = TTag((((Typ_baseContext)_localctx).ID!=null?((Typ_baseContext)_localctx).ID.getText():null), ((Typ_baseContext)_localctx).typ_base.combo) 

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(57);
				((Typ_baseContext)_localctx).ID = match(ID);
				setState(58);
				match(T__4);
				setState(59);
				((Typ_baseContext)_localctx).typ_base = typ_base();

				_localctx.combo = TField((((Typ_baseContext)_localctx).ID!=null?((Typ_baseContext)_localctx).ID.getText():null), ((Typ_baseContext)_localctx).typ_base.combo) 

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(62);
				match(T__5);
				setState(63);
				((Typ_baseContext)_localctx).typ = typ();
				setState(64);
				match(T__6);

				_localctx.combo = ((Typ_baseContext)_localctx).typ.combo   

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypContext extends ParserRuleContext {
		public Typ combo;
		public Typ_baseContext typ_base;
		public TypContext typ;
		public Typ_baseContext context;
		public NegchainContext acc;
		public IdsContext ids;
		public QualificationContext qualification;
		public Token ID;
		public TypContext body;
		public TypContext upper;
		public Typ_baseContext typ_base() {
			return getRuleContext(Typ_baseContext.class,0);
		}
		public List<TypContext> typ() {
			return getRuleContexts(TypContext.class);
		}
		public TypContext typ(int i) {
			return getRuleContext(TypContext.class,i);
		}
		public NegchainContext negchain() {
			return getRuleContext(NegchainContext.class,0);
		}
		public IdsContext ids() {
			return getRuleContext(IdsContext.class,0);
		}
		public QualificationContext qualification() {
			return getRuleContext(QualificationContext.class,0);
		}
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public TypContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typ; }
	}

	public final TypContext typ() throws RecognitionException {
		TypContext _localctx = new TypContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_typ);
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				((TypContext)_localctx).typ_base = typ_base();

				_localctx.combo = ((TypContext)_localctx).typ_base.combo

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(73);
				((TypContext)_localctx).typ_base = typ_base();
				setState(74);
				match(T__7);
				setState(75);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Unio(((TypContext)_localctx).typ_base.combo, ((TypContext)_localctx).typ.combo) 

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(78);
				((TypContext)_localctx).typ_base = typ_base();
				setState(79);
				match(T__8);
				setState(80);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Inter(((TypContext)_localctx).typ_base.combo, ((TypContext)_localctx).typ.combo) 

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(83);
				((TypContext)_localctx).context = typ_base();
				setState(84);
				((TypContext)_localctx).acc = negchain(((TypContext)_localctx).context.combo);

				_localctx.combo = ((TypContext)_localctx).acc.combo 

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(87);
				((TypContext)_localctx).typ_base = typ_base();
				setState(88);
				match(T__9);
				setState(89);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Imp(((TypContext)_localctx).typ_base.combo, ((TypContext)_localctx).typ.combo) 

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(92);
				((TypContext)_localctx).typ_base = typ_base();
				setState(93);
				match(T__10);
				setState(94);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Inter(TField('head', ((TypContext)_localctx).typ_base.combo), TField('tail', ((TypContext)_localctx).typ.combo)) 

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(97);
				match(T__11);
				setState(98);
				match(T__12);
				setState(99);
				((TypContext)_localctx).ids = ids();
				setState(100);
				match(T__13);
				setState(101);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Exi(((TypContext)_localctx).ids.combo, [], ((TypContext)_localctx).typ.combo) 

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(104);
				match(T__11);
				setState(105);
				match(T__12);
				setState(106);
				((TypContext)_localctx).ids = ids();
				setState(107);
				((TypContext)_localctx).qualification = qualification();
				setState(108);
				match(T__13);
				setState(109);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = Exi(((TypContext)_localctx).ids.combo, ((TypContext)_localctx).qualification.combo, ((TypContext)_localctx).typ.combo) 

				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(112);
				match(T__14);
				setState(113);
				match(T__12);
				setState(114);
				((TypContext)_localctx).ID = match(ID);
				setState(115);
				match(T__13);
				setState(116);
				((TypContext)_localctx).body = typ();

				_localctx.combo = All((((TypContext)_localctx).ID!=null?((TypContext)_localctx).ID.getText():null), Top(), ((TypContext)_localctx).body.combo) 

				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(119);
				match(T__14);
				setState(120);
				match(T__12);
				setState(121);
				((TypContext)_localctx).ID = match(ID);
				setState(122);
				match(T__15);
				setState(123);
				((TypContext)_localctx).upper = typ();
				setState(124);
				match(T__13);
				setState(125);
				((TypContext)_localctx).body = typ();

				_localctx.combo = All((((TypContext)_localctx).ID!=null?((TypContext)_localctx).ID.getText():null), ((TypContext)_localctx).upper.combo, ((TypContext)_localctx).body.combo) 

				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(128);
				match(T__16);
				setState(129);
				((TypContext)_localctx).ID = match(ID);
				setState(130);
				((TypContext)_localctx).typ = typ();

				_localctx.combo = LeastFP((((TypContext)_localctx).ID!=null?((TypContext)_localctx).ID.getText():null), ((TypContext)_localctx).typ.combo) 

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NegchainContext extends ParserRuleContext {
		public Typ context;
		public Diff combo;
		public TypContext negation;
		public NegchainContext tail;
		public TypContext typ() {
			return getRuleContext(TypContext.class,0);
		}
		public NegchainContext negchain() {
			return getRuleContext(NegchainContext.class,0);
		}
		public NegchainContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public NegchainContext(ParserRuleContext parent, int invokingState, Typ context) {
			super(parent, invokingState);
			this.context = context;
		}
		@Override public int getRuleIndex() { return RULE_negchain; }
	}

	public final NegchainContext negchain(Typ context) throws RecognitionException {
		NegchainContext _localctx = new NegchainContext(_ctx, getState(), context);
		enterRule(_localctx, 6, RULE_negchain);
		try {
			setState(146);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(136);
				match(T__17);
				setState(137);
				((NegchainContext)_localctx).negation = typ();

				_localctx.combo = Diff(context, ((NegchainContext)_localctx).negation.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(140);
				match(T__17);
				setState(141);
				((NegchainContext)_localctx).negation = typ();

				context_tail = Diff(context, ((NegchainContext)_localctx).negation.combo)

				setState(143);
				((NegchainContext)_localctx).tail = negchain(context_tail);

				_localctx.combo = Diff(context, ((NegchainContext)_localctx).negation.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class QualificationContext extends ParserRuleContext {
		public tuple[Subtyping, ...] combo;
		public SubtypingContext subtyping;
		public QualificationContext qualification;
		public SubtypingContext subtyping() {
			return getRuleContext(SubtypingContext.class,0);
		}
		public QualificationContext qualification() {
			return getRuleContext(QualificationContext.class,0);
		}
		public QualificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualification; }
	}

	public final QualificationContext qualification() throws RecognitionException {
		QualificationContext _localctx = new QualificationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_qualification);
		try {
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				match(T__18);
				setState(150);
				((QualificationContext)_localctx).subtyping = subtyping();

				_localctx.combo = tuple([((QualificationContext)_localctx).subtyping.combo])

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(153);
				match(T__18);
				setState(154);
				((QualificationContext)_localctx).subtyping = subtyping();
				setState(155);
				((QualificationContext)_localctx).qualification = qualification();

				_localctx.combo = tuple([((QualificationContext)_localctx).subtyping.combo]) + ((QualificationContext)_localctx).qualification.combo

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SubtypingContext extends ParserRuleContext {
		public Subtyping combo;
		public TypContext strong;
		public TypContext weak;
		public List<TypContext> typ() {
			return getRuleContexts(TypContext.class);
		}
		public TypContext typ(int i) {
			return getRuleContext(TypContext.class,i);
		}
		public SubtypingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subtyping; }
	}

	public final SubtypingContext subtyping() throws RecognitionException {
		SubtypingContext _localctx = new SubtypingContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_subtyping);
		try {
			setState(166);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__13:
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__5:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case T__11:
			case T__14:
			case T__15:
			case T__16:
			case T__17:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				((SubtypingContext)_localctx).strong = typ();
				setState(162);
				match(T__15);
				setState(163);
				((SubtypingContext)_localctx).weak = typ();

				_localctx.combo = Subtyping(((SubtypingContext)_localctx).strong.combo, ((SubtypingContext)_localctx).weak.combo)

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public Nonterm nt;
		public Typ combo;
		public BaseContext base;
		public BaseContext head;
		public ExprContext tail;
		public ExprContext condition;
		public ExprContext branch_true;
		public ExprContext branch_false;
		public BaseContext cator;
		public KeychainContext keychain;
		public ArgchainContext argchain;
		public PipelineContext pipeline;
		public Token ID;
		public TargetContext target;
		public ExprContext contin;
		public ExprContext body;
		public BaseContext base() {
			return getRuleContext(BaseContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public KeychainContext keychain() {
			return getRuleContext(KeychainContext.class,0);
		}
		public ArgchainContext argchain() {
			return getRuleContext(ArgchainContext.class,0);
		}
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public TargetContext target() {
			return getRuleContext(TargetContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ExprContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr(Nonterm nt) throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState(), nt);
		enterRule(_localctx, 12, RULE_expr);
		try {
			setState(231);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(169);
				((ExprContext)_localctx).base = base(nt);

				_localctx.combo = ((ExprContext)_localctx).base.combo

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{

				nt_head = self.guide_nonterm(ExprRule(self._solver, nt).distill_tuple_head)

				setState(173);
				((ExprContext)_localctx).head = base(nt_head);

				self.guide_symbol(',')

				setState(175);
				match(T__10);

				nt_tail = self.guide_nonterm(ExprRule(self._solver, nt).distill_tuple_tail, ((ExprContext)_localctx).head.combo)

				setState(177);
				((ExprContext)_localctx).tail = expr(nt_tail);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_tuple, ((ExprContext)_localctx).head.combo, ((ExprContext)_localctx).tail.combo) 

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(180);
				match(T__19);

				nt_condition = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_condition)

				setState(182);
				((ExprContext)_localctx).condition = expr(nt_condition);

				self.guide_symbol('then')

				setState(184);
				match(T__20);

				nt_branch_true = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_branch_true, ((ExprContext)_localctx).condition.combo)

				setState(186);
				((ExprContext)_localctx).branch_true = expr(nt_branch_true);

				self.guide_symbol('else')

				setState(188);
				match(T__21);

				nt_branch_false = self.guide_nonterm(ExprRule(self._solver, nt).distill_ite_branch_false, ((ExprContext)_localctx).condition.combo, ((ExprContext)_localctx).branch_true.combo)

				setState(190);
				((ExprContext)_localctx).branch_false = expr(nt_branch_false);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_ite, ((ExprContext)_localctx).condition.combo, ((ExprContext)_localctx).branch_true.combo, ((ExprContext)_localctx).branch_false.combo) 

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{

				nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_projection_cator)

				setState(194);
				((ExprContext)_localctx).cator = base(nt_cator);

				nt_keychain = self.guide_nonterm(ExprRule(self._solver, nt).distill_projection_keychain, ((ExprContext)_localctx).cator.combo)

				setState(196);
				((ExprContext)_localctx).keychain = keychain(nt_keychain);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_projection, ((ExprContext)_localctx).cator.combo, ((ExprContext)_localctx).keychain.combo) 

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{

				nt_cator = self.guide_nonterm(ExprRule(self._solver, nt).distill_application_cator)

				setState(200);
				((ExprContext)_localctx).cator = base(nt_cator);

				nt_argchain = self.guide_nonterm(ExprRule(self._solver, nt).distill_application_argchain, ((ExprContext)_localctx).cator.combo)

				setState(202);
				((ExprContext)_localctx).argchain = argchain(nt_argchain);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_application, ((ExprContext)_localctx).cator.combo, ((ExprContext)_localctx).argchain.combo)

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{

				nt_arg = self.guide_nonterm(ExprRule(self._solver, nt).distill_funnel_arg)

				setState(206);
				((ExprContext)_localctx).cator = base(nt_arg);

				nt_pipeline = self.guide_nonterm(ExprRule(self._solver, nt).distill_funnel_pipeline, ((ExprContext)_localctx).cator.combo)

				setState(208);
				((ExprContext)_localctx).pipeline = pipeline(nt_pipeline);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_funnel, ((ExprContext)_localctx).cator.combo, ((ExprContext)_localctx).pipeline.combo)

				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(211);
				match(T__22);

				self.guide_terminal('ID')

				setState(213);
				((ExprContext)_localctx).ID = match(ID);

				nt_target = self.guide_nonterm(ExprRule(self._solver, nt).distill_let_target, (((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null))

				setState(215);
				((ExprContext)_localctx).target = target(nt_target);

				self.guide_symbol(';')

				setState(217);
				match(T__18);

				nt_contin = self.guide_nonterm(ExprRule(self._solver, nt).distill_let_contin, (((ExprContext)_localctx).ID!=null?((ExprContext)_localctx).ID.getText():null), ((ExprContext)_localctx).target.combo)

				setState(219);
				((ExprContext)_localctx).contin = expr(nt_contin);

				_localctx.combo = ((ExprContext)_localctx).contin.combo

				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(222);
				match(T__23);

				self.guide_symbol('(')

				setState(224);
				match(T__5);

				nt_body = self.guide_nonterm(ExprRule(self._solver, nt).distill_fix_body)

				setState(226);
				((ExprContext)_localctx).body = expr(nt_body);

				self.guide_symbol(')')

				setState(228);
				match(T__6);

				_localctx.combo = self.collect(ExprRule(self._solver, nt).combine_fix, ((ExprContext)_localctx).body.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BaseContext extends ParserRuleContext {
		public Nonterm nt;
		public Typ combo;
		public Token ID;
		public BaseContext body;
		public RecordContext record;
		public FunctionContext function;
		public ArgchainContext argchain;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public BaseContext base() {
			return getRuleContext(BaseContext.class,0);
		}
		public RecordContext record() {
			return getRuleContext(RecordContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public ArgchainContext argchain() {
			return getRuleContext(ArgchainContext.class,0);
		}
		public BaseContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public BaseContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_base; }
	}

	public final BaseContext base(Nonterm nt) throws RecognitionException {
		BaseContext _localctx = new BaseContext(_ctx, getState(), nt);
		enterRule(_localctx, 14, RULE_base);
		try {
			setState(255);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(234);
				match(T__2);

				_localctx.combo = self.collect(BaseRule(self._solver, nt).combine_unit)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(236);
				match(T__3);

				self.guide_terminal('ID')

				setState(238);
				((BaseContext)_localctx).ID = match(ID);

				nt_body = self.guide_nonterm(BaseRule(self._solver, nt).distill_tag_body, (((BaseContext)_localctx).ID!=null?((BaseContext)_localctx).ID.getText():null))

				setState(240);
				((BaseContext)_localctx).body = base(nt_body);

				_localctx.combo = self.collect(BaseRule(self._solver, nt).combine_tag, (((BaseContext)_localctx).ID!=null?((BaseContext)_localctx).ID.getText():null), ((BaseContext)_localctx).body.combo)

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(243);
				((BaseContext)_localctx).record = record(nt);

				_localctx.combo = ((BaseContext)_localctx).record.combo

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{


				setState(247);
				((BaseContext)_localctx).function = function(nt);

				_localctx.combo = self.collect(BaseRule(self._solver, nt).combine_function, ((BaseContext)_localctx).function.combo)

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(250);
				((BaseContext)_localctx).ID = match(ID);

				_localctx.combo = self.collect(BaseRule(self._solver, nt).combine_var, (((BaseContext)_localctx).ID!=null?((BaseContext)_localctx).ID.getText():null))

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(252);
				((BaseContext)_localctx).argchain = argchain(nt);

				_localctx.combo = self.collect(BaseRule(self._solver, nt).combine_assoc, ((BaseContext)_localctx).argchain.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public Nonterm nt;
		public list[Imp] combo;
		public PatternContext pattern;
		public ExprContext body;
		public FunctionContext tail;
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public FunctionContext function() {
			return getRuleContext(FunctionContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public FunctionContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_function; }
	}

	public final FunctionContext function(Nonterm nt) throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState(), nt);
		enterRule(_localctx, 16, RULE_function);
		try {
			setState(278);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(258);
				match(T__24);

				nt_pattern = self.guide_nonterm(FunctionRule(self._solver, nt).distill_single_pattern)

				setState(260);
				((FunctionContext)_localctx).pattern = pattern(nt_pattern);

				self.guide_symbol('=>')

				setState(262);
				match(T__25);

				nt_body = self.guide_nonterm(FunctionRule(self._solver, nt).distill_single_body, ((FunctionContext)_localctx).pattern.combo)

				setState(264);
				((FunctionContext)_localctx).body = expr(nt_body);

				_localctx.combo = self.collect(FunctionRule(self._solver, nt).combine_single, ((FunctionContext)_localctx).pattern.combo, ((FunctionContext)_localctx).body.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(267);
				match(T__24);

				nt_pattern = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_pattern)

				setState(269);
				((FunctionContext)_localctx).pattern = pattern(nt_pattern);

				self.guide_symbol('=>')

				setState(271);
				match(T__25);

				nt_body = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_body, ((FunctionContext)_localctx).pattern.combo)

				setState(273);
				((FunctionContext)_localctx).body = expr(nt_body);

				nt_tail = self.guide_nonterm(FunctionRule(self._solver, nt).distill_cons_tail, ((FunctionContext)_localctx).pattern.combo, ((FunctionContext)_localctx).body.combo)

				setState(275);
				((FunctionContext)_localctx).tail = function(nt_tail);

				_localctx.combo = self.collect(FunctionRule(self._solver, nt).combine_cons, ((FunctionContext)_localctx).pattern.combo, ((FunctionContext)_localctx).body.combo, ((FunctionContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RecordContext extends ParserRuleContext {
		public Nonterm nt;
		public Typ combo;
		public Token ID;
		public ExprContext body;
		public RecordContext tail;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public RecordContext record() {
			return getRuleContext(RecordContext.class,0);
		}
		public RecordContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public RecordContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_record; }
	}

	public final RecordContext record(Nonterm nt) throws RecognitionException {
		RecordContext _localctx = new RecordContext(_ctx, getState(), nt);
		enterRule(_localctx, 18, RULE_record);
		try {
			setState(301);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(281);
				match(T__26);

				self.guide_terminal('ID')

				setState(283);
				((RecordContext)_localctx).ID = match(ID);

				self.guide_symbol('=')

				setState(285);
				match(T__27);

				nt_body = self.guide_nonterm(RecordRule(self._solver, nt).distill_single_body, (((RecordContext)_localctx).ID!=null?((RecordContext)_localctx).ID.getText():null))

				setState(287);
				((RecordContext)_localctx).body = expr(nt_body);

				_localctx.combo = self.collect(RecordRule(self._solver, nt).combine_single, (((RecordContext)_localctx).ID!=null?((RecordContext)_localctx).ID.getText():null), ((RecordContext)_localctx).body.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(290);
				match(T__26);

				self.guide_terminal('ID')

				setState(292);
				((RecordContext)_localctx).ID = match(ID);

				self.guide_symbol('=')

				setState(294);
				match(T__27);

				nt_body = self.guide_nonterm(RecordRule(self._solver, nt).distill_cons_body, (((RecordContext)_localctx).ID!=null?((RecordContext)_localctx).ID.getText():null))

				setState(296);
				((RecordContext)_localctx).body = expr(nt_body);

				nt_tail = self.guide_nonterm(RecordRule(self._solver, nt).distill_cons_tail, (((RecordContext)_localctx).ID!=null?((RecordContext)_localctx).ID.getText():null), ((RecordContext)_localctx).body.combo)

				setState(298);
				((RecordContext)_localctx).tail = record(nt_tail);

				_localctx.combo = self.collect(RecordRule(self._solver, nt).combine_cons, (((RecordContext)_localctx).ID!=null?((RecordContext)_localctx).ID.getText():null), ((RecordContext)_localctx).body.combo, ((RecordContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgchainContext extends ParserRuleContext {
		public Nonterm nt;
		public list[Typ] combo;
		public ExprContext content;
		public ExprContext head;
		public ArgchainContext tail;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArgchainContext argchain() {
			return getRuleContext(ArgchainContext.class,0);
		}
		public ArgchainContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public ArgchainContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_argchain; }
	}

	public final ArgchainContext argchain(Nonterm nt) throws RecognitionException {
		ArgchainContext _localctx = new ArgchainContext(_ctx, getState(), nt);
		enterRule(_localctx, 20, RULE_argchain);
		try {
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				match(T__5);

				nt_content = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_single_content) 

				setState(306);
				((ArgchainContext)_localctx).content = expr(nt_content);

				self.guide_symbol(')')

				setState(308);
				match(T__6);

				_localctx.combo = self.collect(ArgchainRule(self._solver, nt).combine_single, ((ArgchainContext)_localctx).content.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(311);
				match(T__5);

				nt_head = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_cons_head) 

				setState(313);
				((ArgchainContext)_localctx).head = expr(nt_head);

				self.guide_symbol(')')

				setState(315);
				match(T__6);

				nt_tail = self.guide_nonterm(ArgchainRule(self._solver, nt).distill_cons_tail, ((ArgchainContext)_localctx).head.combo) 

				setState(317);
				((ArgchainContext)_localctx).tail = argchain(nt_tail);

				_localctx.combo = self.collect(ArgchainRule(self._solver, nt).combine_cons, ((ArgchainContext)_localctx).head.combo, ((ArgchainContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PipelineContext extends ParserRuleContext {
		public Nonterm nt;
		public list[Typ] combo;
		public ExprContext content;
		public ExprContext head;
		public PipelineContext tail;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public PipelineContext pipeline() {
			return getRuleContext(PipelineContext.class,0);
		}
		public PipelineContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PipelineContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_pipeline; }
	}

	public final PipelineContext pipeline(Nonterm nt) throws RecognitionException {
		PipelineContext _localctx = new PipelineContext(_ctx, getState(), nt);
		enterRule(_localctx, 22, RULE_pipeline);
		try {
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(323);
				match(T__28);

				nt_content = self.guide_nonterm(PipelineRule(self._solver, nt).distill_single_content) 

				setState(325);
				((PipelineContext)_localctx).content = expr(nt_content);

				_localctx.combo = self.collect(PipelineRule(self._solver, nt).combine_single, ((PipelineContext)_localctx).content.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(328);
				match(T__28);

				nt_head = self.guide_nonterm(PipelineRule(self._solver, nt).distill_cons_head) 

				setState(330);
				((PipelineContext)_localctx).head = expr(nt_head);

				nt_tail = self.guide_nonterm(PipelineRule(self._solver, nt).distill_cons_tail, ((PipelineContext)_localctx).head.combo) 

				setState(332);
				((PipelineContext)_localctx).tail = pipeline(nt_tail);

				_localctx.combo = self.collect(ArgchainRule(self._solver, nt).combine_cons, ((PipelineContext)_localctx).head.combo, ((PipelineContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class KeychainContext extends ParserRuleContext {
		public Nonterm nt;
		public list[str] combo;
		public Token ID;
		public KeychainContext tail;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public KeychainContext keychain() {
			return getRuleContext(KeychainContext.class,0);
		}
		public KeychainContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public KeychainContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_keychain; }
	}

	public final KeychainContext keychain(Nonterm nt) throws RecognitionException {
		KeychainContext _localctx = new KeychainContext(_ctx, getState(), nt);
		enterRule(_localctx, 24, RULE_keychain);
		try {
			setState(349);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				match(T__29);

				self.guide_terminal('ID')

				setState(340);
				((KeychainContext)_localctx).ID = match(ID);

				_localctx.combo = self.collect(KeychainRule(self._solver, nt).combine_single, (((KeychainContext)_localctx).ID!=null?((KeychainContext)_localctx).ID.getText():null))

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(342);
				match(T__29);

				self.guide_terminal('ID')

				setState(344);
				((KeychainContext)_localctx).ID = match(ID);

				nt_tail = self.guide_nonterm(KeychainRule(self._solver, nt).distill_cons_tail, (((KeychainContext)_localctx).ID!=null?((KeychainContext)_localctx).ID.getText():null)) 

				setState(346);
				((KeychainContext)_localctx).tail = keychain(nt_tail);

				_localctx.combo = self.collect(KeychainRule(self._solver, nt).combine_cons, (((KeychainContext)_localctx).ID!=null?((KeychainContext)_localctx).ID.getText():null), ((KeychainContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TargetContext extends ParserRuleContext {
		public Nonterm nt;
		public Typ combo;
		public ExprContext expr;
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TargetContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TargetContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_target; }
	}

	public final TargetContext target(Nonterm nt) throws RecognitionException {
		TargetContext _localctx = new TargetContext(_ctx, getState(), nt);
		enterRule(_localctx, 26, RULE_target);
		try {
			setState(357);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__18:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case T__27:
				enterOuterAlt(_localctx, 2);
				{
				setState(352);
				match(T__27);

				nt_expr = self.guide_nonterm(lambda: nt)

				setState(354);
				((TargetContext)_localctx).expr = expr(nt_expr);

				_localctx.combo = ((TargetContext)_localctx).expr.combo

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PatternContext extends ParserRuleContext {
		public Nonterm nt;
		public PatternAttr combo;
		public Pattern_baseContext pattern_base;
		public Pattern_baseContext head;
		public PatternContext tail;
		public Pattern_baseContext pattern_base() {
			return getRuleContext(Pattern_baseContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public PatternContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
	}

	public final PatternContext pattern(Nonterm nt) throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState(), nt);
		enterRule(_localctx, 28, RULE_pattern);
		try {
			setState(371);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				((PatternContext)_localctx).pattern_base = pattern_base(nt);

				_localctx.combo = ((PatternContext)_localctx).pattern_base.combo

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{

				nt_head = self.guide_nonterm(PatternRule(self._solver, nt).distill_tuple_head)

				setState(364);
				((PatternContext)_localctx).head = pattern_base(nt_head);

				self.guide_symbol(',')

				setState(366);
				match(T__10);

				nt_tail = self.guide_nonterm(PatternRule(self._solver, nt).distill_tuple_tail, ((PatternContext)_localctx).head.combo)

				setState(368);
				((PatternContext)_localctx).tail = pattern(nt_tail);

				_localctx.combo = self.collect(PatternRule(self._solver, nt).combine_tuple, ((PatternContext)_localctx).head.combo, ((PatternContext)_localctx).tail.combo) 

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pattern_baseContext extends ParserRuleContext {
		public Nonterm nt;
		public PatternAttr combo;
		public Token ID;
		public Pattern_baseContext body;
		public Pattern_recordContext pattern_record;
		public PatternContext pattern;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public Pattern_baseContext pattern_base() {
			return getRuleContext(Pattern_baseContext.class,0);
		}
		public Pattern_recordContext pattern_record() {
			return getRuleContext(Pattern_recordContext.class,0);
		}
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public Pattern_baseContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Pattern_baseContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_pattern_base; }
	}

	public final Pattern_baseContext pattern_base(Nonterm nt) throws RecognitionException {
		Pattern_baseContext _localctx = new Pattern_baseContext(_ctx, getState(), nt);
		enterRule(_localctx, 30, RULE_pattern_base);
		try {
			setState(395);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(374);
				((Pattern_baseContext)_localctx).ID = match(ID);

				_localctx.combo = self.collect(PatternBaseRule(self._solver, nt).combine_var, (((Pattern_baseContext)_localctx).ID!=null?((Pattern_baseContext)_localctx).ID.getText():null))

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(376);
				((Pattern_baseContext)_localctx).ID = match(ID);

				_localctx.combo = self.collect(PatternBaseRule(self._solver, nt).combine_var, (((Pattern_baseContext)_localctx).ID!=null?((Pattern_baseContext)_localctx).ID.getText():null))

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				match(T__2);

				_localctx.combo = self.collect(PatternBaseRule(self._solver, nt).combine_unit)

				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(380);
				match(T__3);

				self.guide_terminal('ID')

				setState(382);
				((Pattern_baseContext)_localctx).ID = match(ID);

				nt_body = self.guide_nonterm(PatternBaseRule(self._solver, nt).distill_tag_body, (((Pattern_baseContext)_localctx).ID!=null?((Pattern_baseContext)_localctx).ID.getText():null))

				setState(384);
				((Pattern_baseContext)_localctx).body = pattern_base(nt_body);

				_localctx.combo = self.collect(PatternBaseRule(self._solver, nt).combine_tag, (((Pattern_baseContext)_localctx).ID!=null?((Pattern_baseContext)_localctx).ID.getText():null), ((Pattern_baseContext)_localctx).body.combo)

				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(387);
				((Pattern_baseContext)_localctx).pattern_record = pattern_record(nt);

				_localctx.combo = ((Pattern_baseContext)_localctx).pattern_record.combo

				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(390);
				match(T__5);
				setState(391);
				((Pattern_baseContext)_localctx).pattern = pattern(nt);
				setState(392);
				match(T__6);

				_localctx.combo = ((Pattern_baseContext)_localctx).pattern.combo   

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Pattern_recordContext extends ParserRuleContext {
		public Nonterm nt;
		public PatternAttr combo;
		public Token ID;
		public PatternContext body;
		public Pattern_recordContext tail;
		public TerminalNode ID() { return getToken(SlimParser.ID, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public Pattern_recordContext pattern_record() {
			return getRuleContext(Pattern_recordContext.class,0);
		}
		public Pattern_recordContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public Pattern_recordContext(ParserRuleContext parent, int invokingState, Nonterm nt) {
			super(parent, invokingState);
			this.nt = nt;
		}
		@Override public int getRuleIndex() { return RULE_pattern_record; }
	}

	public final Pattern_recordContext pattern_record(Nonterm nt) throws RecognitionException {
		Pattern_recordContext _localctx = new Pattern_recordContext(_ctx, getState(), nt);
		enterRule(_localctx, 32, RULE_pattern_record);
		try {
			setState(418);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(398);
				match(T__26);

				self.guide_terminal('ID')

				setState(400);
				((Pattern_recordContext)_localctx).ID = match(ID);

				self.guide_symbol('=')

				setState(402);
				match(T__27);

				nt_body = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_single_body, (((Pattern_recordContext)_localctx).ID!=null?((Pattern_recordContext)_localctx).ID.getText():null))

				setState(404);
				((Pattern_recordContext)_localctx).body = pattern(nt_body);

				_localctx.combo = self.collect(PatternRecordRule(self._solver, nt).combine_single, (((Pattern_recordContext)_localctx).ID!=null?((Pattern_recordContext)_localctx).ID.getText():null), ((Pattern_recordContext)_localctx).body.combo)

				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(407);
				match(T__26);

				self.guide_terminal('ID')

				setState(409);
				((Pattern_recordContext)_localctx).ID = match(ID);

				self.guide_symbol('=')

				setState(411);
				match(T__27);

				nt_body = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_cons_body, (((Pattern_recordContext)_localctx).ID!=null?((Pattern_recordContext)_localctx).ID.getText():null))

				setState(413);
				((Pattern_recordContext)_localctx).body = pattern(nt_body);

				nt_tail = self.guide_nonterm(PatternRecordRule(self._solver, nt).distill_cons_tail, (((Pattern_recordContext)_localctx).ID!=null?((Pattern_recordContext)_localctx).ID.getText():null), ((Pattern_recordContext)_localctx).body.combo)

				setState(415);
				((Pattern_recordContext)_localctx).tail = pattern_record(nt_tail);

				_localctx.combo = self.collect(PatternRecordRule(self._solver, nt).combine_cons, (((Pattern_recordContext)_localctx).ID!=null?((Pattern_recordContext)_localctx).ID.getText():null), ((Pattern_recordContext)_localctx).body.combo, ((Pattern_recordContext)_localctx).tail.combo)

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001!\u01a5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0003\u0000*\b\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"D\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"\u0086\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003\u0093\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0003\u0004\u009f\b\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005\u00a7\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0003\u0006\u00e8\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0003\u0007\u0100\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u0117\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\t\u012e\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0003\n\u0141\b\n\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
		"\u0003\u000b\u0150\b\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u015e\b\f\u0001"+
		"\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0003\r\u0166\b\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e"+
		"\u0174\b\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u018c\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u01a3\b\u0010"+
		"\u0001\u0010\u0000\u0000\u0011\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \u0000\u0000\u01cf\u0000)\u0001"+
		"\u0000\u0000\u0000\u0002C\u0001\u0000\u0000\u0000\u0004\u0085\u0001\u0000"+
		"\u0000\u0000\u0006\u0092\u0001\u0000\u0000\u0000\b\u009e\u0001\u0000\u0000"+
		"\u0000\n\u00a6\u0001\u0000\u0000\u0000\f\u00e7\u0001\u0000\u0000\u0000"+
		"\u000e\u00ff\u0001\u0000\u0000\u0000\u0010\u0116\u0001\u0000\u0000\u0000"+
		"\u0012\u012d\u0001\u0000\u0000\u0000\u0014\u0140\u0001\u0000\u0000\u0000"+
		"\u0016\u014f\u0001\u0000\u0000\u0000\u0018\u015d\u0001\u0000\u0000\u0000"+
		"\u001a\u0165\u0001\u0000\u0000\u0000\u001c\u0173\u0001\u0000\u0000\u0000"+
		"\u001e\u018b\u0001\u0000\u0000\u0000 \u01a2\u0001\u0000\u0000\u0000\""+
		"*\u0001\u0000\u0000\u0000#$\u0005\u001f\u0000\u0000$*\u0006\u0000\uffff"+
		"\uffff\u0000%&\u0005\u001f\u0000\u0000&\'\u0003\u0000\u0000\u0000\'(\u0006"+
		"\u0000\uffff\uffff\u0000(*\u0001\u0000\u0000\u0000)\"\u0001\u0000\u0000"+
		"\u0000)#\u0001\u0000\u0000\u0000)%\u0001\u0000\u0000\u0000*\u0001\u0001"+
		"\u0000\u0000\u0000+D\u0001\u0000\u0000\u0000,-\u0005\u0001\u0000\u0000"+
		"-D\u0006\u0001\uffff\uffff\u0000./\u0005\u0002\u0000\u0000/D\u0006\u0001"+
		"\uffff\uffff\u000001\u0005\u001f\u0000\u00001D\u0006\u0001\uffff\uffff"+
		"\u000023\u0005\u0003\u0000\u00003D\u0006\u0001\uffff\uffff\u000045\u0005"+
		"\u0004\u0000\u000056\u0005\u001f\u0000\u000067\u0003\u0002\u0001\u0000"+
		"78\u0006\u0001\uffff\uffff\u00008D\u0001\u0000\u0000\u00009:\u0005\u001f"+
		"\u0000\u0000:;\u0005\u0005\u0000\u0000;<\u0003\u0002\u0001\u0000<=\u0006"+
		"\u0001\uffff\uffff\u0000=D\u0001\u0000\u0000\u0000>?\u0005\u0006\u0000"+
		"\u0000?@\u0003\u0004\u0002\u0000@A\u0005\u0007\u0000\u0000AB\u0006\u0001"+
		"\uffff\uffff\u0000BD\u0001\u0000\u0000\u0000C+\u0001\u0000\u0000\u0000"+
		"C,\u0001\u0000\u0000\u0000C.\u0001\u0000\u0000\u0000C0\u0001\u0000\u0000"+
		"\u0000C2\u0001\u0000\u0000\u0000C4\u0001\u0000\u0000\u0000C9\u0001\u0000"+
		"\u0000\u0000C>\u0001\u0000\u0000\u0000D\u0003\u0001\u0000\u0000\u0000"+
		"E\u0086\u0001\u0000\u0000\u0000FG\u0003\u0002\u0001\u0000GH\u0006\u0002"+
		"\uffff\uffff\u0000H\u0086\u0001\u0000\u0000\u0000IJ\u0003\u0002\u0001"+
		"\u0000JK\u0005\b\u0000\u0000KL\u0003\u0004\u0002\u0000LM\u0006\u0002\uffff"+
		"\uffff\u0000M\u0086\u0001\u0000\u0000\u0000NO\u0003\u0002\u0001\u0000"+
		"OP\u0005\t\u0000\u0000PQ\u0003\u0004\u0002\u0000QR\u0006\u0002\uffff\uffff"+
		"\u0000R\u0086\u0001\u0000\u0000\u0000ST\u0003\u0002\u0001\u0000TU\u0003"+
		"\u0006\u0003\u0000UV\u0006\u0002\uffff\uffff\u0000V\u0086\u0001\u0000"+
		"\u0000\u0000WX\u0003\u0002\u0001\u0000XY\u0005\n\u0000\u0000YZ\u0003\u0004"+
		"\u0002\u0000Z[\u0006\u0002\uffff\uffff\u0000[\u0086\u0001\u0000\u0000"+
		"\u0000\\]\u0003\u0002\u0001\u0000]^\u0005\u000b\u0000\u0000^_\u0003\u0004"+
		"\u0002\u0000_`\u0006\u0002\uffff\uffff\u0000`\u0086\u0001\u0000\u0000"+
		"\u0000ab\u0005\f\u0000\u0000bc\u0005\r\u0000\u0000cd\u0003\u0000\u0000"+
		"\u0000de\u0005\u000e\u0000\u0000ef\u0003\u0004\u0002\u0000fg\u0006\u0002"+
		"\uffff\uffff\u0000g\u0086\u0001\u0000\u0000\u0000hi\u0005\f\u0000\u0000"+
		"ij\u0005\r\u0000\u0000jk\u0003\u0000\u0000\u0000kl\u0003\b\u0004\u0000"+
		"lm\u0005\u000e\u0000\u0000mn\u0003\u0004\u0002\u0000no\u0006\u0002\uffff"+
		"\uffff\u0000o\u0086\u0001\u0000\u0000\u0000pq\u0005\u000f\u0000\u0000"+
		"qr\u0005\r\u0000\u0000rs\u0005\u001f\u0000\u0000st\u0005\u000e\u0000\u0000"+
		"tu\u0003\u0004\u0002\u0000uv\u0006\u0002\uffff\uffff\u0000v\u0086\u0001"+
		"\u0000\u0000\u0000wx\u0005\u000f\u0000\u0000xy\u0005\r\u0000\u0000yz\u0005"+
		"\u001f\u0000\u0000z{\u0005\u0010\u0000\u0000{|\u0003\u0004\u0002\u0000"+
		"|}\u0005\u000e\u0000\u0000}~\u0003\u0004\u0002\u0000~\u007f\u0006\u0002"+
		"\uffff\uffff\u0000\u007f\u0086\u0001\u0000\u0000\u0000\u0080\u0081\u0005"+
		"\u0011\u0000\u0000\u0081\u0082\u0005\u001f\u0000\u0000\u0082\u0083\u0003"+
		"\u0004\u0002\u0000\u0083\u0084\u0006\u0002\uffff\uffff\u0000\u0084\u0086"+
		"\u0001\u0000\u0000\u0000\u0085E\u0001\u0000\u0000\u0000\u0085F\u0001\u0000"+
		"\u0000\u0000\u0085I\u0001\u0000\u0000\u0000\u0085N\u0001\u0000\u0000\u0000"+
		"\u0085S\u0001\u0000\u0000\u0000\u0085W\u0001\u0000\u0000\u0000\u0085\\"+
		"\u0001\u0000\u0000\u0000\u0085a\u0001\u0000\u0000\u0000\u0085h\u0001\u0000"+
		"\u0000\u0000\u0085p\u0001\u0000\u0000\u0000\u0085w\u0001\u0000\u0000\u0000"+
		"\u0085\u0080\u0001\u0000\u0000\u0000\u0086\u0005\u0001\u0000\u0000\u0000"+
		"\u0087\u0093\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u0012\u0000\u0000"+
		"\u0089\u008a\u0003\u0004\u0002\u0000\u008a\u008b\u0006\u0003\uffff\uffff"+
		"\u0000\u008b\u0093\u0001\u0000\u0000\u0000\u008c\u008d\u0005\u0012\u0000"+
		"\u0000\u008d\u008e\u0003\u0004\u0002\u0000\u008e\u008f\u0006\u0003\uffff"+
		"\uffff\u0000\u008f\u0090\u0003\u0006\u0003\u0000\u0090\u0091\u0006\u0003"+
		"\uffff\uffff\u0000\u0091\u0093\u0001\u0000\u0000\u0000\u0092\u0087\u0001"+
		"\u0000\u0000\u0000\u0092\u0088\u0001\u0000\u0000\u0000\u0092\u008c\u0001"+
		"\u0000\u0000\u0000\u0093\u0007\u0001\u0000\u0000\u0000\u0094\u009f\u0001"+
		"\u0000\u0000\u0000\u0095\u0096\u0005\u0013\u0000\u0000\u0096\u0097\u0003"+
		"\n\u0005\u0000\u0097\u0098\u0006\u0004\uffff\uffff\u0000\u0098\u009f\u0001"+
		"\u0000\u0000\u0000\u0099\u009a\u0005\u0013\u0000\u0000\u009a\u009b\u0003"+
		"\n\u0005\u0000\u009b\u009c\u0003\b\u0004\u0000\u009c\u009d\u0006\u0004"+
		"\uffff\uffff\u0000\u009d\u009f\u0001\u0000\u0000\u0000\u009e\u0094\u0001"+
		"\u0000\u0000\u0000\u009e\u0095\u0001\u0000\u0000\u0000\u009e\u0099\u0001"+
		"\u0000\u0000\u0000\u009f\t\u0001\u0000\u0000\u0000\u00a0\u00a7\u0001\u0000"+
		"\u0000\u0000\u00a1\u00a2\u0003\u0004\u0002\u0000\u00a2\u00a3\u0005\u0010"+
		"\u0000\u0000\u00a3\u00a4\u0003\u0004\u0002\u0000\u00a4\u00a5\u0006\u0005"+
		"\uffff\uffff\u0000\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a0\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a1\u0001\u0000\u0000\u0000\u00a7\u000b\u0001"+
		"\u0000\u0000\u0000\u00a8\u00e8\u0001\u0000\u0000\u0000\u00a9\u00aa\u0003"+
		"\u000e\u0007\u0000\u00aa\u00ab\u0006\u0006\uffff\uffff\u0000\u00ab\u00e8"+
		"\u0001\u0000\u0000\u0000\u00ac\u00ad\u0006\u0006\uffff\uffff\u0000\u00ad"+
		"\u00ae\u0003\u000e\u0007\u0000\u00ae\u00af\u0006\u0006\uffff\uffff\u0000"+
		"\u00af\u00b0\u0005\u000b\u0000\u0000\u00b0\u00b1\u0006\u0006\uffff\uffff"+
		"\u0000\u00b1\u00b2\u0003\f\u0006\u0000\u00b2\u00b3\u0006\u0006\uffff\uffff"+
		"\u0000\u00b3\u00e8\u0001\u0000\u0000\u0000\u00b4\u00b5\u0005\u0014\u0000"+
		"\u0000\u00b5\u00b6\u0006\u0006\uffff\uffff\u0000\u00b6\u00b7\u0003\f\u0006"+
		"\u0000\u00b7\u00b8\u0006\u0006\uffff\uffff\u0000\u00b8\u00b9\u0005\u0015"+
		"\u0000\u0000\u00b9\u00ba\u0006\u0006\uffff\uffff\u0000\u00ba\u00bb\u0003"+
		"\f\u0006\u0000\u00bb\u00bc\u0006\u0006\uffff\uffff\u0000\u00bc\u00bd\u0005"+
		"\u0016\u0000\u0000\u00bd\u00be\u0006\u0006\uffff\uffff\u0000\u00be\u00bf"+
		"\u0003\f\u0006\u0000\u00bf\u00c0\u0006\u0006\uffff\uffff\u0000\u00c0\u00e8"+
		"\u0001\u0000\u0000\u0000\u00c1\u00c2\u0006\u0006\uffff\uffff\u0000\u00c2"+
		"\u00c3\u0003\u000e\u0007\u0000\u00c3\u00c4\u0006\u0006\uffff\uffff\u0000"+
		"\u00c4\u00c5\u0003\u0018\f\u0000\u00c5\u00c6\u0006\u0006\uffff\uffff\u0000"+
		"\u00c6\u00e8\u0001\u0000\u0000\u0000\u00c7\u00c8\u0006\u0006\uffff\uffff"+
		"\u0000\u00c8\u00c9\u0003\u000e\u0007\u0000\u00c9\u00ca\u0006\u0006\uffff"+
		"\uffff\u0000\u00ca\u00cb\u0003\u0014\n\u0000\u00cb\u00cc\u0006\u0006\uffff"+
		"\uffff\u0000\u00cc\u00e8\u0001\u0000\u0000\u0000\u00cd\u00ce\u0006\u0006"+
		"\uffff\uffff\u0000\u00ce\u00cf\u0003\u000e\u0007\u0000\u00cf\u00d0\u0006"+
		"\u0006\uffff\uffff\u0000\u00d0\u00d1\u0003\u0016\u000b\u0000\u00d1\u00d2"+
		"\u0006\u0006\uffff\uffff\u0000\u00d2\u00e8\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0005\u0017\u0000\u0000\u00d4\u00d5\u0006\u0006\uffff\uffff\u0000"+
		"\u00d5\u00d6\u0005\u001f\u0000\u0000\u00d6\u00d7\u0006\u0006\uffff\uffff"+
		"\u0000\u00d7\u00d8\u0003\u001a\r\u0000\u00d8\u00d9\u0006\u0006\uffff\uffff"+
		"\u0000\u00d9\u00da\u0005\u0013\u0000\u0000\u00da\u00db\u0006\u0006\uffff"+
		"\uffff\u0000\u00db\u00dc\u0003\f\u0006\u0000\u00dc\u00dd\u0006\u0006\uffff"+
		"\uffff\u0000\u00dd\u00e8\u0001\u0000\u0000\u0000\u00de\u00df\u0005\u0018"+
		"\u0000\u0000\u00df\u00e0\u0006\u0006\uffff\uffff\u0000\u00e0\u00e1\u0005"+
		"\u0006\u0000\u0000\u00e1\u00e2\u0006\u0006\uffff\uffff\u0000\u00e2\u00e3"+
		"\u0003\f\u0006\u0000\u00e3\u00e4\u0006\u0006\uffff\uffff\u0000\u00e4\u00e5"+
		"\u0005\u0007\u0000\u0000\u00e5\u00e6\u0006\u0006\uffff\uffff\u0000\u00e6"+
		"\u00e8\u0001\u0000\u0000\u0000\u00e7\u00a8\u0001\u0000\u0000\u0000\u00e7"+
		"\u00a9\u0001\u0000\u0000\u0000\u00e7\u00ac\u0001\u0000\u0000\u0000\u00e7"+
		"\u00b4\u0001\u0000\u0000\u0000\u00e7\u00c1\u0001\u0000\u0000\u0000\u00e7"+
		"\u00c7\u0001\u0000\u0000\u0000\u00e7\u00cd\u0001\u0000\u0000\u0000\u00e7"+
		"\u00d3\u0001\u0000\u0000\u0000\u00e7\u00de\u0001\u0000\u0000\u0000\u00e8"+
		"\r\u0001\u0000\u0000\u0000\u00e9\u0100\u0001\u0000\u0000\u0000\u00ea\u00eb"+
		"\u0005\u0003\u0000\u0000\u00eb\u0100\u0006\u0007\uffff\uffff\u0000\u00ec"+
		"\u00ed\u0005\u0004\u0000\u0000\u00ed\u00ee\u0006\u0007\uffff\uffff\u0000"+
		"\u00ee\u00ef\u0005\u001f\u0000\u0000\u00ef\u00f0\u0006\u0007\uffff\uffff"+
		"\u0000\u00f0\u00f1\u0003\u000e\u0007\u0000\u00f1\u00f2\u0006\u0007\uffff"+
		"\uffff\u0000\u00f2\u0100\u0001\u0000\u0000\u0000\u00f3\u00f4\u0003\u0012"+
		"\t\u0000\u00f4\u00f5\u0006\u0007\uffff\uffff\u0000\u00f5\u0100\u0001\u0000"+
		"\u0000\u0000\u00f6\u00f7\u0006\u0007\uffff\uffff\u0000\u00f7\u00f8\u0003"+
		"\u0010\b\u0000\u00f8\u00f9\u0006\u0007\uffff\uffff\u0000\u00f9\u0100\u0001"+
		"\u0000\u0000\u0000\u00fa\u00fb\u0005\u001f\u0000\u0000\u00fb\u0100\u0006"+
		"\u0007\uffff\uffff\u0000\u00fc\u00fd\u0003\u0014\n\u0000\u00fd\u00fe\u0006"+
		"\u0007\uffff\uffff\u0000\u00fe\u0100\u0001\u0000\u0000\u0000\u00ff\u00e9"+
		"\u0001\u0000\u0000\u0000\u00ff\u00ea\u0001\u0000\u0000\u0000\u00ff\u00ec"+
		"\u0001\u0000\u0000\u0000\u00ff\u00f3\u0001\u0000\u0000\u0000\u00ff\u00f6"+
		"\u0001\u0000\u0000\u0000\u00ff\u00fa\u0001\u0000\u0000\u0000\u00ff\u00fc"+
		"\u0001\u0000\u0000\u0000\u0100\u000f\u0001\u0000\u0000\u0000\u0101\u0117"+
		"\u0001\u0000\u0000\u0000\u0102\u0103\u0005\u0019\u0000\u0000\u0103\u0104"+
		"\u0006\b\uffff\uffff\u0000\u0104\u0105\u0003\u001c\u000e\u0000\u0105\u0106"+
		"\u0006\b\uffff\uffff\u0000\u0106\u0107\u0005\u001a\u0000\u0000\u0107\u0108"+
		"\u0006\b\uffff\uffff\u0000\u0108\u0109\u0003\f\u0006\u0000\u0109\u010a"+
		"\u0006\b\uffff\uffff\u0000\u010a\u0117\u0001\u0000\u0000\u0000\u010b\u010c"+
		"\u0005\u0019\u0000\u0000\u010c\u010d\u0006\b\uffff\uffff\u0000\u010d\u010e"+
		"\u0003\u001c\u000e\u0000\u010e\u010f\u0006\b\uffff\uffff\u0000\u010f\u0110"+
		"\u0005\u001a\u0000\u0000\u0110\u0111\u0006\b\uffff\uffff\u0000\u0111\u0112"+
		"\u0003\f\u0006\u0000\u0112\u0113\u0006\b\uffff\uffff\u0000\u0113\u0114"+
		"\u0003\u0010\b\u0000\u0114\u0115\u0006\b\uffff\uffff\u0000\u0115\u0117"+
		"\u0001\u0000\u0000\u0000\u0116\u0101\u0001\u0000\u0000\u0000\u0116\u0102"+
		"\u0001\u0000\u0000\u0000\u0116\u010b\u0001\u0000\u0000\u0000\u0117\u0011"+
		"\u0001\u0000\u0000\u0000\u0118\u012e\u0001\u0000\u0000\u0000\u0119\u011a"+
		"\u0005\u001b\u0000\u0000\u011a\u011b\u0006\t\uffff\uffff\u0000\u011b\u011c"+
		"\u0005\u001f\u0000\u0000\u011c\u011d\u0006\t\uffff\uffff\u0000\u011d\u011e"+
		"\u0005\u001c\u0000\u0000\u011e\u011f\u0006\t\uffff\uffff\u0000\u011f\u0120"+
		"\u0003\f\u0006\u0000\u0120\u0121\u0006\t\uffff\uffff\u0000\u0121\u012e"+
		"\u0001\u0000\u0000\u0000\u0122\u0123\u0005\u001b\u0000\u0000\u0123\u0124"+
		"\u0006\t\uffff\uffff\u0000\u0124\u0125\u0005\u001f\u0000\u0000\u0125\u0126"+
		"\u0006\t\uffff\uffff\u0000\u0126\u0127\u0005\u001c\u0000\u0000\u0127\u0128"+
		"\u0006\t\uffff\uffff\u0000\u0128\u0129\u0003\f\u0006\u0000\u0129\u012a"+
		"\u0006\t\uffff\uffff\u0000\u012a\u012b\u0003\u0012\t\u0000\u012b\u012c"+
		"\u0006\t\uffff\uffff\u0000\u012c\u012e\u0001\u0000\u0000\u0000\u012d\u0118"+
		"\u0001\u0000\u0000\u0000\u012d\u0119\u0001\u0000\u0000\u0000\u012d\u0122"+
		"\u0001\u0000\u0000\u0000\u012e\u0013\u0001\u0000\u0000\u0000\u012f\u0141"+
		"\u0001\u0000\u0000\u0000\u0130\u0131\u0005\u0006\u0000\u0000\u0131\u0132"+
		"\u0006\n\uffff\uffff\u0000\u0132\u0133\u0003\f\u0006\u0000\u0133\u0134"+
		"\u0006\n\uffff\uffff\u0000\u0134\u0135\u0005\u0007\u0000\u0000\u0135\u0136"+
		"\u0006\n\uffff\uffff\u0000\u0136\u0141\u0001\u0000\u0000\u0000\u0137\u0138"+
		"\u0005\u0006\u0000\u0000\u0138\u0139\u0006\n\uffff\uffff\u0000\u0139\u013a"+
		"\u0003\f\u0006\u0000\u013a\u013b\u0006\n\uffff\uffff\u0000\u013b\u013c"+
		"\u0005\u0007\u0000\u0000\u013c\u013d\u0006\n\uffff\uffff\u0000\u013d\u013e"+
		"\u0003\u0014\n\u0000\u013e\u013f\u0006\n\uffff\uffff\u0000\u013f\u0141"+
		"\u0001\u0000\u0000\u0000\u0140\u012f\u0001\u0000\u0000\u0000\u0140\u0130"+
		"\u0001\u0000\u0000\u0000\u0140\u0137\u0001\u0000\u0000\u0000\u0141\u0015"+
		"\u0001\u0000\u0000\u0000\u0142\u0150\u0001\u0000\u0000\u0000\u0143\u0144"+
		"\u0005\u001d\u0000\u0000\u0144\u0145\u0006\u000b\uffff\uffff\u0000\u0145"+
		"\u0146\u0003\f\u0006\u0000\u0146\u0147\u0006\u000b\uffff\uffff\u0000\u0147"+
		"\u0150\u0001\u0000\u0000\u0000\u0148\u0149\u0005\u001d\u0000\u0000\u0149"+
		"\u014a\u0006\u000b\uffff\uffff\u0000\u014a\u014b\u0003\f\u0006\u0000\u014b"+
		"\u014c\u0006\u000b\uffff\uffff\u0000\u014c\u014d\u0003\u0016\u000b\u0000"+
		"\u014d\u014e\u0006\u000b\uffff\uffff\u0000\u014e\u0150\u0001\u0000\u0000"+
		"\u0000\u014f\u0142\u0001\u0000\u0000\u0000\u014f\u0143\u0001\u0000\u0000"+
		"\u0000\u014f\u0148\u0001\u0000\u0000\u0000\u0150\u0017\u0001\u0000\u0000"+
		"\u0000\u0151\u015e\u0001\u0000\u0000\u0000\u0152\u0153\u0005\u001e\u0000"+
		"\u0000\u0153\u0154\u0006\f\uffff\uffff\u0000\u0154\u0155\u0005\u001f\u0000"+
		"\u0000\u0155\u015e\u0006\f\uffff\uffff\u0000\u0156\u0157\u0005\u001e\u0000"+
		"\u0000\u0157\u0158\u0006\f\uffff\uffff\u0000\u0158\u0159\u0005\u001f\u0000"+
		"\u0000\u0159\u015a\u0006\f\uffff\uffff\u0000\u015a\u015b\u0003\u0018\f"+
		"\u0000\u015b\u015c\u0006\f\uffff\uffff\u0000\u015c\u015e\u0001\u0000\u0000"+
		"\u0000\u015d\u0151\u0001\u0000\u0000\u0000\u015d\u0152\u0001\u0000\u0000"+
		"\u0000\u015d\u0156\u0001\u0000\u0000\u0000\u015e\u0019\u0001\u0000\u0000"+
		"\u0000\u015f\u0166\u0001\u0000\u0000\u0000\u0160\u0161\u0005\u001c\u0000"+
		"\u0000\u0161\u0162\u0006\r\uffff\uffff\u0000\u0162\u0163\u0003\f\u0006"+
		"\u0000\u0163\u0164\u0006\r\uffff\uffff\u0000\u0164\u0166\u0001\u0000\u0000"+
		"\u0000\u0165\u015f\u0001\u0000\u0000\u0000\u0165\u0160\u0001\u0000\u0000"+
		"\u0000\u0166\u001b\u0001\u0000\u0000\u0000\u0167\u0174\u0001\u0000\u0000"+
		"\u0000\u0168\u0169\u0003\u001e\u000f\u0000\u0169\u016a\u0006\u000e\uffff"+
		"\uffff\u0000\u016a\u0174\u0001\u0000\u0000\u0000\u016b\u016c\u0006\u000e"+
		"\uffff\uffff\u0000\u016c\u016d\u0003\u001e\u000f\u0000\u016d\u016e\u0006"+
		"\u000e\uffff\uffff\u0000\u016e\u016f\u0005\u000b\u0000\u0000\u016f\u0170"+
		"\u0006\u000e\uffff\uffff\u0000\u0170\u0171\u0003\u001c\u000e\u0000\u0171"+
		"\u0172\u0006\u000e\uffff\uffff\u0000\u0172\u0174\u0001\u0000\u0000\u0000"+
		"\u0173\u0167\u0001\u0000\u0000\u0000\u0173\u0168\u0001\u0000\u0000\u0000"+
		"\u0173\u016b\u0001\u0000\u0000\u0000\u0174\u001d\u0001\u0000\u0000\u0000"+
		"\u0175\u018c\u0001\u0000\u0000\u0000\u0176\u0177\u0005\u001f\u0000\u0000"+
		"\u0177\u018c\u0006\u000f\uffff\uffff\u0000\u0178\u0179\u0005\u001f\u0000"+
		"\u0000\u0179\u018c\u0006\u000f\uffff\uffff\u0000\u017a\u017b\u0005\u0003"+
		"\u0000\u0000\u017b\u018c\u0006\u000f\uffff\uffff\u0000\u017c\u017d\u0005"+
		"\u0004\u0000\u0000\u017d\u017e\u0006\u000f\uffff\uffff\u0000\u017e\u017f"+
		"\u0005\u001f\u0000\u0000\u017f\u0180\u0006\u000f\uffff\uffff\u0000\u0180"+
		"\u0181\u0003\u001e\u000f\u0000\u0181\u0182\u0006\u000f\uffff\uffff\u0000"+
		"\u0182\u018c\u0001\u0000\u0000\u0000\u0183\u0184\u0003 \u0010\u0000\u0184"+
		"\u0185\u0006\u000f\uffff\uffff\u0000\u0185\u018c\u0001\u0000\u0000\u0000"+
		"\u0186\u0187\u0005\u0006\u0000\u0000\u0187\u0188\u0003\u001c\u000e\u0000"+
		"\u0188\u0189\u0005\u0007\u0000\u0000\u0189\u018a\u0006\u000f\uffff\uffff"+
		"\u0000\u018a\u018c\u0001\u0000\u0000\u0000\u018b\u0175\u0001\u0000\u0000"+
		"\u0000\u018b\u0176\u0001\u0000\u0000\u0000\u018b\u0178\u0001\u0000\u0000"+
		"\u0000\u018b\u017a\u0001\u0000\u0000\u0000\u018b\u017c\u0001\u0000\u0000"+
		"\u0000\u018b\u0183\u0001\u0000\u0000\u0000\u018b\u0186\u0001\u0000\u0000"+
		"\u0000\u018c\u001f\u0001\u0000\u0000\u0000\u018d\u01a3\u0001\u0000\u0000"+
		"\u0000\u018e\u018f\u0005\u001b\u0000\u0000\u018f\u0190\u0006\u0010\uffff"+
		"\uffff\u0000\u0190\u0191\u0005\u001f\u0000\u0000\u0191\u0192\u0006\u0010"+
		"\uffff\uffff\u0000\u0192\u0193\u0005\u001c\u0000\u0000\u0193\u0194\u0006"+
		"\u0010\uffff\uffff\u0000\u0194\u0195\u0003\u001c\u000e\u0000\u0195\u0196"+
		"\u0006\u0010\uffff\uffff\u0000\u0196\u01a3\u0001\u0000\u0000\u0000\u0197"+
		"\u0198\u0005\u001b\u0000\u0000\u0198\u0199\u0006\u0010\uffff\uffff\u0000"+
		"\u0199\u019a\u0005\u001f\u0000\u0000\u019a\u019b\u0006\u0010\uffff\uffff"+
		"\u0000\u019b\u019c\u0005\u001c\u0000\u0000\u019c\u019d\u0006\u0010\uffff"+
		"\uffff\u0000\u019d\u019e\u0003\u001c\u000e\u0000\u019e\u019f\u0006\u0010"+
		"\uffff\uffff\u0000\u019f\u01a0\u0003 \u0010\u0000\u01a0\u01a1\u0006\u0010"+
		"\uffff\uffff\u0000\u01a1\u01a3\u0001\u0000\u0000\u0000\u01a2\u018d\u0001"+
		"\u0000\u0000\u0000\u01a2\u018e\u0001\u0000\u0000\u0000\u01a2\u0197\u0001"+
		"\u0000\u0000\u0000\u01a3!\u0001\u0000\u0000\u0000\u0011)C\u0085\u0092"+
		"\u009e\u00a6\u00e7\u00ff\u0116\u012d\u0140\u014f\u015d\u0165\u0173\u018b"+
		"\u01a2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}