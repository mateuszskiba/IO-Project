package pl.put.poznan.transformer.logic;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * This is just an example to show that the logic should be outside the REST service.
 */
public class ScenarioController
{

    private Scenario mainScenario; // list of lines in scenario
    private final String[] KEYWORDS = {"IF", "ELSE", "FOR EACH"};

    public ScenarioController()
    {
        Scenario mainScenario = new Scenario();
        mainScenario.addStep(new Step("Bibliotekarz wybiera opcje dodania nowej pozycji książkowej"));
        mainScenario.addStep(new Step("Wyświetla się formularz."));
        mainScenario.addStep(new Step("Bibliotekarz podaje dane książki."));

        Scenario subScenario = new Scenario();
        subScenario.addStep(new Step("Bibliotekarz wybiera opcję definiowania egzemplarzy"));
        subScenario.addStep(new Step("System prezentuje zdefiniowane egzemplarze"));

        Scenario subSubScenario = new Scenario();
        subSubScenario.addStep(new Step("Bibliotekarz wybiera opcję dodania egzemplarza"));
        subSubScenario.addStep(new Step("System prosi o podanie danych egzemplarza"));
        subSubScenario.addStep(new Step("Bibliotekarz podaje dane egzemplarza i zatwierdza."));
        subSubScenario.addStep(new Step("System informuje o poprawnym dodaniu egzemplarza i prezentuje zaktualizowaną listę egzemplarzy."));

        subScenario.addStep(new Step("FOR EACH egzemplarz:", subSubScenario));
        mainScenario.addStep(new Step("IF: Bibliotekarz pragnie dodać egzemplarze książki", subScenario));

        mainScenario.addStep(new Step("Bibliotekarz zatwierdza dodanie książki."));
        mainScenario.addStep(new Step("System informuje o poprawnym dodaniu książki."));

        setMainScenario(mainScenario);
    }

    /*public void loadFromJson(String json)
    {
        mainScenario = new Gson().fromJson(json, Scenario.class);
    }

    public String saveAsJson()
    {
        return new Gson().toJson(mainScenario);
    }*/

    public void setMainScenario(Scenario mainScenario)
    {
        this.mainScenario = mainScenario;
    }

    public Scenario getMainScenario()
    {
        return mainScenario;
    }

    public ScenarioController(String[] scenario)
    {
        // zostawilem aby czesc restowa dzialala
    }

    public ScenarioController(String fileName) throws FileNotFoundException
    {
        //TODO
    }

    /*public String transform(String text)
    {
        // of course normally it would to something based on transforms
        return text.toUpperCase();
    }*/

    /**
     * Counts the number of steps in the main scenario
     *
     * @return the number of steps in the main scenario
     */
    public int countSteps()
    {
        CountStepsScenarioVisitor countStepsScenarioVisitor = new CountStepsScenarioVisitor();
        mainScenario.acceptVisitor(countStepsScenarioVisitor);
        return countStepsScenarioVisitor.getCounter();
    }

    /**
     * Counts number of keywords in the main scenario
     *
     * @return the number of keywords in scenario
     */
    public int countKeywords()
    {
        CountKeywordsScenarioVisitor countKeywordsScenarioVisitor = new CountKeywordsScenarioVisitor();
        mainScenario.acceptVisitor(countKeywordsScenarioVisitor);
        return countKeywordsScenarioVisitor.getCounter();
    }


    /**
     * numbers scenario with nested numbers
     *
     * @param scenario
     * @param addon    inital prefix - default blank
     * @return Scenario with numbered lines
     */

    private Scenario getNumbered(Scenario scenario, String addon)
    {
        List<Step> stepList = scenario.getSteps();
        Scenario numberedScenario = new Scenario();
        int licznik = 1;
        for (Step step : stepList)
        {
            Step numberedStep = new Step(addon + Integer.toString(licznik) + '.' + step.getContent());
            licznik++;
            if (step.getSubScenario() != null)
            {
                numberedScenario.addStep(new Step(addon + Integer.toString(licznik - 1) + '.' + step.getContent() +
                        step.getContent(), getNumbered(step.getSubScenario(), addon + Integer.toString(licznik - 1) + '.')));
            } else
                numberedScenario.addStep(numberedStep);
        }
        return numberedScenario;
    }

    public Scenario getNumbered()
    {
        return getNumbered(mainScenario, "");
    }


    /**
     * returns scenario up to given depth
     *
     * @param scenario
     * @param maxDepth
     * @return scenario cut below given maxDepth
     */

    private Scenario getWithDepth(Scenario scenario, int maxDepth)
    {
        List<Step> stepList = scenario.getSteps();
        Scenario scenarioWithDepth = new Scenario();
        for (Step step : stepList)
        {
            if (maxDepth == 1)
                step.setSubScenario(null);
            scenarioWithDepth.addStep(step);
            if (step.getSubScenario() != null && maxDepth > 1)
                getWithDepth(step.getSubScenario(), maxDepth - 1);
        }
        return scenarioWithDepth;
    }

    public Scenario getWithDepth(int maxDepth)
    {
        return getWithDepth(mainScenario, maxDepth);
    }


    /**
     * Lists the steps that don't begin with an actor
     *
     * @return the ArrayList with steps that doesn't begin with an actor
     */

    public List<Step> stepsNoActor()
    {
        StepsNoActorScenarioVisitor visitor = new StepsNoActorScenarioVisitor();
        mainScenario.acceptVisitor(visitor);
        return visitor.getStepsNoActorList();
    }
}
