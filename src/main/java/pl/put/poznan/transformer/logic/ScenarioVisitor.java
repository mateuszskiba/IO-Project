package pl.put.poznan.transformer.logic;

/**
 * Created by ns on 08.01.18.
 */
public interface ScenarioVisitor
{
    /**
     * Visits scenario and performs abstract action
     *
     * @param scenario
     * @return void
     */
    public void visitScenario(Scenario scenario);

    /**
     * Visits step and performs abstract action
     *
     * @param step
     * @return void
     */
    public void visitStep(Step step);
}
