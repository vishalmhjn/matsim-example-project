package org.matsim.session1;

import org.matsim.api.core.v01.Coord;
import org.matsim.api.core.v01.Id;
import org.matsim.api.core.v01.Scenario;
import org.matsim.api.core.v01.network.Link;
import org.matsim.api.core.v01.network.Network;
import org.matsim.api.core.v01.network.NetworkFactory;
import org.matsim.api.core.v01.network.Node;
import org.matsim.core.config.Config;
import org.matsim.core.config.ConfigUtils;
import org.matsim.core.network.io.NetworkWriter;
import org.matsim.core.scenario.ScenarioUtils;

public class CreateNetworkSolution {

    // define capacity of the links in suburban and urban area
    private static final long CAP_Suburban = 500; // [veh/h]
    private static final long CAP_Urban = 50; // [veh/h]

    // define freespeed for links in suburban and urban area
    private static final double LINK_SPEED_Urban = 13.88;
    private static final double LINK_SPEED_Suburban = 22.22; // [m/s]


    public static void main(String[] args) {

        Config config = ConfigUtils.createConfig();
        Scenario scenario = ScenarioUtils.createScenario(config);
        Network net = scenario.getNetwork();
        NetworkFactory fac = net.getFactory();

        // create nodes
        Node n0 = fac.createNode(Id.createNodeId(0), new Coord(0, 0));
        net.addNode(n0);
        Node n1 = fac.createNode(Id.createNodeId(1), new Coord(1000, 0));
        net.addNode(n1);
        Node n2 = fac.createNode(Id.createNodeId(2), new Coord(2000, 0));
        net.addNode(n2);
        Node n3 = fac.createNode(Id.createNodeId(3), new Coord(3000, 1000));
        net.addNode(n3);
        Node n4 = fac.createNode(Id.createNodeId(4), new Coord(3000, -1000));
        net.addNode(n4);
        Node n5 = fac.createNode(Id.createNodeId(5), new Coord(4000, 0));
        net.addNode(n5);
        Node n6 = fac.createNode(Id.createNodeId(6), new Coord(5000, 0));
        net.addNode(n6);
        Node n7 = fac.createNode(Id.createNodeId(7), new Coord(6000,0));
        net.addNode(n7);

        //
        //
        //add missing nodes here...
        //
        //

        // create links

        //link 0 <-> 1
        Link l_01 = fac.createLink(Id.createLinkId("0_1"), n0, n1);
        net.addLink(l_01);
        setSubUrbanLinkAttributes(l_01);

        Link l_10 = fac.createLink(Id.createLinkId("1_0"), n1, n0);
        net.addLink(l_10);
        setSubUrbanLinkAttributes(l_10);


        //link 1 <-> 2
        Link l_12 = fac.createLink(Id.createLinkId("1_2"), n1, n2);
        net.addLink(l_12);
        Link l_21 = fac.createLink(Id.createLinkId("2_1"), n2, n1);
        net.addLink(l_21);
        setSubUrbanLinkAttributes(l_12);
        setSubUrbanLinkAttributes(l_21);



        //link 2 <-> 3
        Link l_23 = fac.createLink(Id.createLinkId("2_3"), n2, n3);
        net.addLink(l_23);
        setUrbanLinkAttributes(l_23);

        Link l_32 = fac.createLink(Id.createLinkId("3_2"), n3, n2);
        net.addLink(l_32);
        setUrbanLinkAttributes(l_32);


        //link 2 <-> 4
        Link l_24 = fac.createLink(Id.createLinkId("2_4"), n2, n4);
        net.addLink(l_24);
        setUrbanLinkAttributes(l_24);

        Link l_42 = fac.createLink(Id.createLinkId("4_2"), n4, n2);
        net.addLink(l_42);
        setUrbanLinkAttributes(l_42);


        //link 3 <-> 5
        Link l_35 = fac.createLink(Id.createLinkId("3_5"), n3, n5);
        net.addLink(l_35);
        setUrbanLinkAttributes(l_35);

        Link l_53 = fac.createLink(Id.createLinkId("5_3"), n5, n3);
        net.addLink(l_53);
        setUrbanLinkAttributes(l_53);


        //link 4 <-> 5
        Link l_45 = fac.createLink(Id.createLinkId("4_5"), n4, n5);
        net.addLink(l_45);
        setUrbanLinkAttributes(l_45);

        Link l_54 = fac.createLink(Id.createLinkId("5_4"), n5, n4);
        net.addLink(l_54);
        setUrbanLinkAttributes(l_54);


        //link 5 <-> 6
        Link l_56 = fac.createLink(Id.createLinkId("5_6"), n5, n6);
        net.addLink(l_56);
        setSubUrbanLinkAttributes(l_56);

        Link l_65 = fac.createLink(Id.createLinkId("6_5"), n6, n5);
        net.addLink(l_65);
        setSubUrbanLinkAttributes(l_65);


        //link 6 <-> 7
        Link l_67 = fac.createLink(Id.createLinkId("6_7"), n6, n7);
        net.addLink(l_67);
        setSubUrbanLinkAttributes(l_67);

        Link l_76 = fac.createLink(Id.createLinkId("7_6"), n7, n6);
        net.addLink(l_76);
        setSubUrbanLinkAttributes(l_76);



        // write network
        new NetworkWriter(net).write("networkFinished.xml");
    }

    private static void setUrbanLinkAttributes(Link link) {
        link.setCapacity(CAP_Urban);
        link.setFreespeed(LINK_SPEED_Urban);
    }

    private static void setSubUrbanLinkAttributes(Link link) {
        link.setCapacity(CAP_Suburban);
        link.setFreespeed(LINK_SPEED_Suburban);
    }
}
