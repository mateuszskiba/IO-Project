package pl.put.poznan.transformer.logic;

import java.util.ArrayList;
import java.util.List;

public class Scenario {
    private List<Step> steps = new ArrayList<Step>();

    public Scenario() {
    }

    public List<Step> getSteps() {
        return steps;
    }
    public void addStep(Step step) {
        steps.add(step);
    }
    public void acceptVisitor(ScenarioVisitor scenarioVisitor)
    {
        scenarioVisitor.visitScenario(this);
    }
}
