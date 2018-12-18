import analyzer.Analyzer;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

public class AnalyzerTest {

    private Analyzer analyzer = new Analyzer();

    @Test(timeout = 10000)
    public void analyzerTimeoutTest(){
        analyzer.analyze();
    }

    @Test
    public void analyzerNotNullTest(){
        Map<String, Table<String, Integer, Long>> map = analyzer.analyze();
        assertNotNull(map);
    }
}
