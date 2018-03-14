package pl.put.poznan.transformer.logic;

import java.util.List;

/**
 * Created by ns on 09.01.18.
 */
public class CountKeywordsScenarioVisitor implements ScenarioVisitor
{
    private int counter = 0;
    private final String[] KEYWORDS = {"IF", "ELSE", "FOR EACH"};
    public int getCounter()
    {
        return counter;
    }

    @Override
    public void visitScenario(Scenario scenario)
    {
        List<Step> stepList = scenario.getSteps();
        for(Step step : stepList)
        {
            visitStep(step);
        }
    }

    @Override
    public void visitStep(Step step)
    {
        if(containKeyword(step))
        {
            counter++;
        }
        Scenario subScenario = step.getSubScenario();
        if(subScenario != null)
        {
            visitScenario(subScenario);
        }
    }

    private boolean containKeyword(Step step)
    {
        String content = step.getContent();
        for(String keyword : KEYWORDS)
        {
           if(content.startsWith(keyword))
           {
               return true;
           }
        }
        return false;
    }
}
