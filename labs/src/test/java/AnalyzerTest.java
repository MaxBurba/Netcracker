import analyzer.Analyzer;
import org.junit.Test;

public class AnalyzerTest {

    private Analyzer analyzer = new Analyzer();

    @Test(timeout = 1000)
    public void analyzerTimeoutTest(){
        analyzer.analyze();
    }
}
