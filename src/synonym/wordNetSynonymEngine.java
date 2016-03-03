package synonym;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class wordNetSynonymEngine implements SynonymEngine {
	
	public interface SynonymEngine {
		 String[] getSynonyms(String word) throws IOException;
	}

	IndexSearcher searcher;
	Directory fsDir;
	public void wordNetSynonymEngine(File index) throws IOException
	{
		fsDir=FSDirectory.open(index);
		searcher=new IndexSearcher(fsDir);
	}
	
	@Override
	public String[] getSynonyms(String word) throws IOException {
		List<String> synList=new ArrayList<String>();
		AllDocCollector collector=new AllDocCollector();
		searcher.search(new TermQuery(new Term("word",word)), collector);
		for(ScoreDoc hit:collector.getHits())
		{
			Document doc=searcher.doc(hit.doc);
			String[] values=doc.getValues("syn");
			for(String syn:values)
			{
				synList.add(syn);
			}
		}
		return synList.toArray(new String[0]);
	}
}







