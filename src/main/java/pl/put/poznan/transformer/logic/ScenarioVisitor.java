package pl.put.poznan.transformer.logic;

public interface ScenarioVisitor
{
    /**
     * Visits scenario and performs abstract action
     *
     * @param scenario
     * @return void
     */
    void visitScenario(Scenario scenario);

    /**
     * Visits step and performs abstract action
     *
     * @param step
     * @return void
     */
    void visitStep(Step step);
}
