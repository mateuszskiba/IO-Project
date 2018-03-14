package pl.put.poznan.transformer.logic;

import java.util.List;

public class CountStepsScenarioVisitor implements ScenarioVisitor
{
    private int counter = 0;

    /**
     * Visits all steps in scenario
     *
     * @param scenario
     * @return void
     */
    @Override
    public void visitScenario(Scenario scenario)
    {
        List<Step> stepList = scenario.getSteps();
        for(Step step : stepList)
        {
            visitStep(step);
        }
    }
    /**
     * Visits all subscenarios in step
     *
     * @param step
     * @return void
     */
    @Override
    public void visitStep(Step step)
    {
       counter++;
       Scenario subScenario = step.getSubScenario();
       if(subScenario != null)
       {
           visitScenario(subScenario);
       }
    }

    public int getCounter()
    {
        return counter;
    }
}

