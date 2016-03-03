package synonym;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.ReusableAnalyzerBase.TokenStreamComponents;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.Version;


public class SynonymAnalyzer extends Analyzer {

	private SynonymEngine engine;
	public SynonymAnalyzer(SynonymEngine engine)
	{
		this.engine=engine;
	}
	
	public TokenStream tokenStream(String fieldName, Reader reader) {
		// TODO Auto-generated method stub
		TokenStream result=new SynonymFilter(new StopFilter(true,new LowerCaseFilter(new StandardFilter
		(new StandardTokenizer(Version.LUCENE_35,reader))),StopAnalyzer.ENGLISH_STOP_WORDS_SET),engine);
		return result;
	}
	protected TokenStreamComponents createComponents(String arg0, Reader arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static void displayTokensWithPositions
	(Analyzer analyzer, String text) throws IOException {

	TokenStream stream = analyzer.tokenStream("contents",
	                                          new StringReader(text));
	TermAttribute term = stream.addAttribute(TermAttribute.class);
	PositionIncrementAttribute posIncr =
	     stream.addAttribute(PositionIncrementAttribute.class);

	int position = 0;
	while(stream.incrementToken()) {
	  int increment = posIncr.getPositionIncrement();
	  if (increment > 0) {
	    position = position + increment;
	    System.out.println();
	    System.out.print(position + ": ");
	  }

	  System.out.print("[" + term.term() + "] ");
	}
	System.out.println();
	}
}

