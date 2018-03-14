package pl.put.poznan.transformer.logic;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class StepsNoActorScenarioVisitor implements ScenarioVisitor {
    private List<Step> stepsNoActorList = new ArrayList<Step>();;
    private final String[] KEYWORDS = {"IF", "ELSE", "FOR EACH"};

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
        for (Step step : stepList)
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
        String[] actors = {"Bibliotekarz"};
        if (step.getSubScenario() != null)
        {
            visitScenario(step.getSubScenario());
        }
        else
        {
            String content = step.getContent();
            String firstWord = content.substring(0, content.indexOf(" "));
            if (Arrays.asList(KEYWORDS).contains(firstWord)) return;
            else if (!Arrays.asList(actors).contains(firstWord)) getStepsNoActorList().add(step);
        }

    }

    /**
     * Lists the steps that don't begin with an actor
     *
     * @return List<Step>
     */
    public List<Step> getStepsNoActorList() {
        return stepsNoActorList;
    }
}
