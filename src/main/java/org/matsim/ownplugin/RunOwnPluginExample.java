/**
 * 
 */
package org.matsim.ownplugin;

import org.matsim.api.core.v01.Scenario;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.controler.AbstractModule;
import org.matsim.core.controler.Controler;
import org.matsim.core.scenario.ScenarioUtils;

/**
 * @author kainagel
 *
 */
final class RunOwnPluginExample {

	public static void main(String[] args) {
		
		String filename = null ;

		Config config = ConfigUtils.loadConfig(filename);
		
		Scenario scenario = ScenarioUtils.loadScenario( config ) ;
		
		Controler controler = new Controler( scenario ) ;
		
		controler.addOverridingModule(new AbstractModule(){
			@Override public void install() {
				// put your own plugin here.  Methods to plug material can be found in the IDE by
				//   this.bind<tab completion> ;
				// this will show all MATSim standard extension points.
				
				// E.g.:
				this.bindScoringFunctionFactory() ;
				// For documentation, then look into that method (F3 in eclipse), and find the class
				// that it actually binds (ScoringFunctionFactory in this example).  Check the javadoc
				// for that class, it should give additional information.
			}
		}) ;
		
		controler.run();
	}

}
