package cu.pdi.bookstore.extension.bdd;

import cu.pdi.bookstore.extension.annotation.bdd.ApplicationFeature;
import cu.pdi.bookstore.extension.annotation.bdd.Scenario;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.String.format;

public class BDDExtension implements BeforeAllCallback, BeforeTestExecutionCallback {

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        Logger logger = LoggerFactory.getLogger("BDDLogger");
        extensionContext.getTestClass()
                .flatMap(aClass -> AnnotationSupport.findAnnotation(aClass, ApplicationFeature.class))
                .ifPresent(applicationFeature -> {
                    extensionContext.getStore(ExtensionContext.Namespace.create("BDD")).put("feature", applicationFeature.value());
                    logger.info(format("Testing feature: %s", applicationFeature.value()));
                });
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) {
        Logger logger = LoggerFactory.getLogger(BDDExtension.class);
        extensionContext.getTestMethod()
                .flatMap(method -> AnnotationSupport.findAnnotation(method, Scenario.class))
                .ifPresent(scenario -> {
                    String feature = (String) extensionContext.getStore(ExtensionContext.Namespace.create("BDD")).get("feature");
                    logger.info(format("Scenario description: [%s]", scenario.value()).concat("\n\t\t\t\t\t ").concat(format("Of feature: [%s]", feature)));
                });
    }
}
