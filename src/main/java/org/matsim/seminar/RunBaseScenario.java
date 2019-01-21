/* *********************************************************************** *
 * project: org.matsim.*												   *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 * copyright       : (C) 2008 by the members listed in the COPYING,        *
 *                   LICENSE and WARRANTY file.                            *
 * email           : info at matsim dot org                                *
 *                                                                         *
 * *********************************************************************** *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *   See also COPYING, LICENSE and WARRANTY file                           *
 *                                                                         *
 * *********************************************************************** */
package org.matsim.seminar;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.Controler;
import org.matsim.core.controler.OutputDirectoryHierarchy.OverwriteFileSetting;
import org.matsim.core.gbl.Gbl;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author nagel
 *
 */
public class RunBaseScenario {

	public static void main(String[] args) {
		//Gbl.assertIf(args.length >=1 && args[0]!="" );

		//run(ConfigUtils.loadConfig(args[0]));
		// makes some sense to not modify the config here but in the run method to help  with regression testing.
		run(ConfigUtils.loadConfig("C:\\Users\\mahajan\\Documents\\git\\data\\democonfig.xml"));
	}
	
	static void run(Config config) {
		
		// possibly modify config here
		
		// ---
		config.controler().setOverwriteFileSetting( OverwriteFileSetting.deleteDirectoryIfExists );
		config.controler().setLastIteration(100);
		//config.network().setInputFile("networkFinished.xml");
		//config.plans().setInputFile("population.xml");
        config.qsim().setFlowCapFactor(0.01);
        config.qsim().setStorageCapFactor(0.01);
        config.global().setCoordinateSystem("EPSG:31468");

        Scenario scenario = ScenarioUtils.loadScenario(config) ;
		// possibly modify scenario here
		
		// ---
		
		Controler controler = new Controler( scenario ) ;
		
		// possibly modify controler here
		
		// ---
		
		controler.run();
	}
	
}
