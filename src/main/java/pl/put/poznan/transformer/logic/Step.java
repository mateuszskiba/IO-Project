package pl.put.poznan.transformer.logic;

public class Step {
    private String content;
    private Scenario subScenario; //can be null

    public Step() {
    }

    public Step(String content) {
        this.content = content;
    }

    public Step(String content, Scenario subScenario) {
        this.content = content;
        this.subScenario = subScenario;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Scenario getSubScenario() {
        return subScenario;
    }

    public void setSubScenario(Scenario subScenario) {
        this.subScenario = subScenario;
    }
}