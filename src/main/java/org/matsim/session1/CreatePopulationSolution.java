package org.matsim.session1;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.TransportMode;
import org.matsim.api.core.v01.population.*;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.scenario.ScenarioUtils;

public class CreatePopulationSolution {


    public static void main(String[] args) {


        //Create population and plans
        //Create 500 People designated by the number of iteration.
        //Create same plan for all people.
        Config config = ConfigUtils.createConfig();
        Scenario scenario = ScenarioUtils.createScenario(config);
        Population population = scenario.getPopulation();
        PopulationFactory factory = population.getFactory();

        //Complete the for loop
        for (int i = 0; i<100; i++){

            //Create person
            Person person = factory.createPerson(Id.createPersonId(i));

            //Create plans.
            Plan plan = factory.createPlan();

            //Create "home" activity for the i^th Person.
            Coord homeCoordinate = new Coord(-10, 0);
            Activity activity1 = factory.createActivityFromCoord("home", homeCoordinate);
            activity1.setEndTime(8*60*60);
            plan.addActivity(activity1);

            //Add leg
             Leg leg = factory.createLeg(TransportMode.car);
             plan.addLeg(leg);


            //Create "work" activity for the i^th Person.
            Coord workCoordinate = new Coord(6000,0);
            Activity activity2 = factory.createActivityFromCoord("work", workCoordinate);
            activity2.setEndTime(16*60*60);
            plan.addActivity(activity2);

            //Add leg
            Leg leg2 = factory.createLeg(TransportMode.car);
            plan.addLeg(leg2);

            //Create another home activity
            Activity activity3 = factory.createActivityFromCoord("home", homeCoordinate);
            activity3.setEndTime(8*60*60);
            plan.addActivity(activity3);

            person.addPlan(plan);
            population.addPerson(person);

        }


        // Write the population to a file.
        PopulationWriter writer = new PopulationWriter(population);
        writer.write("population.xml");

    }



}
