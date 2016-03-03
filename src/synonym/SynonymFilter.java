package synonym;

import java.io.IOException;
import java.util.Stack;

import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TermAttribute;
import org.apache.lucene.util.AttributeSource;

public class SynonymFilter extends TokenFilter {

	public static final String Token_TYPE_SYNONYM="SYNONYM";
	private Stack<String> synonymStack;
	private SynonymEngine engine;
	private AttributeSource.State current;
	private final TermAttribute termAtt;
	private final PositionIncrementAttribute posIncrAtt;
	
	protected SynonymFilter(TokenStream input,SynonymEngine engine) {
		super(input);
		synonymStack=new Stack<String>();
		this.engine=engine;
		this.termAtt=addAttribute(TermAttribute.class);
		this.posIncrAtt=addAttribute(PositionIncrementAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		if(synonymStack.size()>0)
		{
			String syn=synonymStack.pop();
			restoreState(current);
			termAtt.setTermBuffer(syn);
			return true;
		}
		
		if(!input.incrementToken())
			return false;
		
		if(addAliasesToStack())
		{
			current=captureState();
		}
		return true;
	}
	private boolean addAliasesToStack() throws IOException {
		// TODO Auto-generated method stub
		String[] synonyms=engine.getSynonyms(termAtt.term());
		if(synonyms==null)
		{
			return false;
		}
		for(String synonym:synonyms)
		{
			synonymStack.push(synonym);
		}
		return true;
	}
	

}
