package customDiff;

import customDiff.utils.VariationPointAnalysisUtils;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ExpressionParserTest {

    @Test
    public void extractAllFeaturesFromTheExpressionTest() {
        String expression1= "// PVSCL:IFCOND(User or (ApplicationBased and Feature2), LINE)";
        String expression2 = "// PVSCL:IFCOND(Storage->pv:SelectedChildren()->pv:Collect(p | IF p->pv:Name() = Storage->pv:Attribute('defaultStorage') THEN 1 ELSE 0 ENDIF)->pv:Contains(1), LINE)";
        String expression3= "";
        List<String> features =  new ArrayList<>();
         features = VariationPointAnalysisUtils.extractAllFeaturesFromTheExpression(expression1);
        assert(features.contains("User"));
        assert(features.contains("ApplicationBased"));
        assertEquals(3,features.size());
        features = VariationPointAnalysisUtils.extractAllFeaturesFromTheExpression(expression2);
        assert(features.contains("Storage"));
        assertEquals(features.size(),1);
    }
}
