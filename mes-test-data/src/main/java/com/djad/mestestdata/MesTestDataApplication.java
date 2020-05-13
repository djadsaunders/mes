package com.djad.mestestdata;

import com.djad.mestestdata.scenario.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;

public class MesTestDataApplication {

	private static final Logger logger = LoggerFactory.getLogger(MesTestDataApplication.class);

	private Scenario scenario;

	public MesTestDataApplication(String scenarioName, String httpHelperName, String urlPrefix) {
		this.init(scenarioName, httpHelperName, urlPrefix);
	}

	private void init(String scenarioName, String httpHelperName, String urlPrefix) {
		HttpHelper httpHelper = HttpHelperFactory.createHttpHelper(httpHelperName, urlPrefix);
		scenario = (Scenario)getInstanceByClassName(scenarioName, httpHelper);
	}

	private void run() {
		this.scenario.runScenario();
	}

	public static void main(String[] args) {

		String httpHelperName = "default";

		if (args.length < 2) {
			System.out.println("Usage: MesTestDataApplication scenarioClassName rtServiceUrlPrefix [httpHelperName]");
			System.out.println("Example: MesTestDataApplication com.djad.mestestdata.scenario.SimulationScenario http://localhost:8082 default");
			System.exit(-1);
		}

		String scenarioName = args[0];
		String urlPrefix = args[1];

		if (args.length == 3) {
			httpHelperName = args[2];
		}

		MesTestDataApplication app = new MesTestDataApplication(scenarioName, httpHelperName, urlPrefix);
		app.run();
	}

	private Object getInstanceByClassName(String classToLoad, Object arg) {
		Object instance;

		// FIXME: is there a better/correct way to deal with generics in reflection?
		try {
			Class clazz = Class.forName(classToLoad);
			if (arg != null) {
				Constructor<?> constructor = clazz.getConstructor(HttpHelper.class);
				instance = constructor.newInstance(arg);
			}
			else {
				Constructor<?> constructor = clazz.getConstructor();
				instance = constructor.newInstance();
			}
		}
		catch (Exception e) {
			logger.error("Failed to load " + classToLoad + ", cannot continue");
			throw new RuntimeException(e);
		}

		return instance;
	}
}