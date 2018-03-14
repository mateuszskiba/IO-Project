package pl.put.poznan.transformer.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.transformer.logic.Scenario;
import pl.put.poznan.transformer.logic.ScenarioController;

@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);
    private static final ScenarioController controller = new ScenarioController();


    @RequestMapping(value = "/load/", method = RequestMethod.POST)
    public void load(@RequestBody Scenario mainScenario)
    {
        controller.setMainScenario(mainScenario);
    }

    @RequestMapping(value = "/save/", method = RequestMethod.GET)
    public Scenario save()
    {
        return controller.getMainScenario();
    }

    @RequestMapping(value = "/count/steps/", method = RequestMethod.GET)
    public int countSteps() {
        return controller.countSteps();
    }

    @RequestMapping(value = "/count/keywords/", method = RequestMethod.GET)
    public int countKeywords()
    {
        return controller.countKeywords();
    }

    @RequestMapping(value = "/get/numbered/", method = RequestMethod.GET)
    public Scenario getNumbered() {
        return controller.getNumbered();
    }

    @RequestMapping(value = "/get/depth/", method = RequestMethod.POST)
    public Scenario getWithDepth(@RequestBody int depth) {
        return controller.getWithDepth(depth);
    }

}


