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
import org.matsim.core.scoring.ScoringFunctionFactory;

/**
 * @author kainagel
 *
 */
final class RunOwnPluginExample {

	public static void main(String[] args) {
		
		Config config = ConfigUtils.loadConfig(args[0]);
		
		Scenario scenario = ScenarioUtils.loadScenario( config ) ;
		
		Controler controler = new Controler( scenario ) ;
		
		controler.addOverridingModule(new AbstractModule(){
			@Override public void install() {
				// put your own plugin here.  Methods to plug material can be found in the IDE by
				//   this.<tab completion> ;
				// this will show all MATSim standard extension points.
				
				// E.g.:
				this.bindScoringFunctionFactory() ;
				// Arguably easiest way to continue: 
				//    this.bindScoringFunctionFactory().toInstance( instance ) ;
				// and then try to generate that "instance".
				// For documentation, then look into bindScoringFunctionFactory() (F3 in eclipse), and find the class
				// that it actually binds (ScoringFunctionFactory in this example).  Check the javadoc
				// for that class, it should give additional information.
			}
		}) ;
		
		controler.run();
	}

}
