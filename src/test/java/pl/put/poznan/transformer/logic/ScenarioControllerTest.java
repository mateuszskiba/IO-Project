package pl.put.poznan.transformer.logic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ns on 07.01.18.
 */
public class ScenarioControllerTest
{
    ScenarioController scenarioController = null;
    @Before
    public void setUp()
    {
        scenarioController = new ScenarioController();
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

        scenarioController.setMainScenario(mainScenario);
    }

    @Test
    public void testCountSteps() throws Exception
    {
        assertEquals(13, scenarioController.countSteps());
    }

    @Test
    public void testCountKeywords() throws Exception
    {
        assertEquals(2, scenarioController.countKeywords());
    }
    @Test
    public void testNoActor() throws Exception
    {
        assertEquals("Wyświetla się formularz.", scenarioController.stepsNoActor().get(0).getContent());
    }

    @Test
    public void testKeyWordStep() throws Exception
    {
        for (Step step : scenarioController.stepsNoActor())
        {
            assertNotEquals("FOR EACH egzemplarz:", step.getContent());
        }
    }

}