package synonym;

import org.apache.lucene.wordnet.Syns2Index;

public class testSyns {

	public static void main(String[] args) throws Throwable {
    	 String[] strs={"/Users/wangzehui/Downloads/prolog/wn_s.pl", "/Users/wangzehui/Downloads/prolog/wordnetIndex"};
		 Syns2Index.main(strs);
	}

}
