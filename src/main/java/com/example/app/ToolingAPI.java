package com.example.app;

import org.gradle.tooling.BuildLauncher;
import org.gradle.tooling.GradleConnector;
import org.gradle.tooling.ProjectConnection;

import java.io.File;

public class ToolingAPI
{
    private static final String GRADLE_INSTALLATION = "/home/maria/.sdkman/candidates/gradle/6.3";
    private static final String GRADLE_PROJECT_DIRECTORY = "/home/maria/Desktop/play";


    private GradleConnector connector;

    public ToolingAPI(String gradleInstallationDir, String projectDir)
    {
        connector = GradleConnector.newConnector();
        connector.useInstallation(new File(gradleInstallationDir));
        connector.forProjectDirectory(new File(projectDir));
    }

    public void executeTask(String... tasks)
    {
        ProjectConnection connection = connector.connect();
        BuildLauncher build = connection.newBuild();
        build.forTasks(tasks);

        build.run();
        connection.close();
    }

    public static void execute(String task)
    {
        ToolingAPI toolingAPI = new ToolingAPI(GRADLE_INSTALLATION, GRADLE_PROJECT_DIRECTORY);
        toolingAPI.executeTask(task);
    }
}