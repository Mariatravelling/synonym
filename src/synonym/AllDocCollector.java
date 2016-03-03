package synonym;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Scorer;

public class AllDocCollector extends Collector {

	List<ScoreDoc> docs = new ArrayList<ScoreDoc>();
	private Scorer scorer;
	private int docBase;

	@Override
	public boolean acceptsDocsOutOfOrder() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void collect(int doc) throws IOException {
		// TODO Auto-generated method stub
		docs.add(new ScoreDoc(doc + docBase, scorer.score()));
	}

	public void setNextReader(IndexReader reader, int docBase)
			throws IOException {
		// TODO Auto-generated method stub
		this.docBase = docBase;
	}

	@Override
	public void setScorer(Scorer scorer) throws IOException {
		// TODO Auto-generated method stub
		this.scorer = scorer;
	}

	public void reset() {
		docs.clear();
	}

	public List<ScoreDoc> getHits() {
		return docs;
	}

}
