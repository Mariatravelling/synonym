package synonym;

import java.io.IOException;

public class SynonymAnalyzerViewer {

	public static void main(String[] args) throws IOException {

		SynonymEngine engine = new wordNetSynonymEngine();

		SynonymAnalyzer.displayTokensWithPositions(new SynonymAnalyzer(engine),
				"The quick brown fox jumps over the lazy dog");
	}
}
