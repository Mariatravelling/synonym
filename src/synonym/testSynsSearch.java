package synonym;

import java.io.IOException;

import org.apache.lucene.wordnet.SynLookup;

public class testSynsSearch {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String[] strs={"/Users/wangzehui/Downloads/prolog/wordnetIndex","like"};
		SynLookup.main(strs);
	}

}
