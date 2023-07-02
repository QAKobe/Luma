package com.luma.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/luma",
        glue = "com/luma/step_def",
        dryRun = false,
        tags = "@smoke",
        plugin = {"pretty", "html:target/uiReport.html","rerun:target/uiFailedTests.txt",
                "json:target/cucumber-reports/cucumber.json"}
        )
public class Runner{
}
