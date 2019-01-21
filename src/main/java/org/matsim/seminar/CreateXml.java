package org.matsim.seminar;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Comment;


public class CreateXml {

    public static void main(String argv[]) {

        String csvFile = "C:\\Users\\mahajan\\Documents\\git\\travel_demand\\testfile_fac1000.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int counter = 0;
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Comment comment = doc.createComment("<!DOCTYPE population SYSTEM \"http://www.matsim.org/files/dtd/population_v6.dtd\"");
            Element rootElement = doc.createElement("population");
            doc.appendChild(rootElement);
            doc.insertBefore(comment, rootElement);



            try {

                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {
                    if (counter != 0) {
                        // use comma as separator
                        String[] trip = line.split(cvsSplitBy);

                        Element person = doc.createElement("person");
                        rootElement.appendChild(person);

                        // set attribute to staff element
                        Attr attr_id = doc.createAttribute("id");
                        attr_id.setValue(trip[1]);
                        person.setAttributeNode(attr_id);

                        Element plan = doc.createElement("plan");
                        person.appendChild(plan);

                        Element activity1 = doc.createElement("activity");
                        plan.appendChild(activity1);

                        // set attribute to staff element
                        Attr attr_type = doc.createAttribute("type");
                        attr_type.setValue(trip[13]);
                        activity1.setAttributeNode(attr_type);

                        Attr attr_x = doc.createAttribute("x");
                        attr_x.setValue(trip[2]);
                        activity1.setAttributeNode(attr_x);

                        Attr attr_y = doc.createAttribute("y");
                        attr_y.setValue(trip[3]);
                        activity1.setAttributeNode(attr_y);

                        Attr attr_endtime = doc.createAttribute("end_time");
                        attr_endtime.setValue(trip[11]);
                        activity1.setAttributeNode(attr_endtime);

                        Element leg1 = doc.createElement("leg");
                        plan.appendChild(leg1);

                        Attr attr_mode1 = doc.createAttribute("mode");
                        attr_mode1.setValue(trip[10]);
                        leg1.setAttributeNode(attr_mode1);

                        Element activity2 = doc.createElement("activity");
                        plan.appendChild(activity2);

                        // set attribute to staff element
                        Attr attr_type2 = doc.createAttribute("type");
                        attr_type2.setValue(trip[14]);
                        activity2.setAttributeNode(attr_type2);

                        Attr attr_x2 = doc.createAttribute("x");
                        attr_x2.setValue(trip[4]);
                        activity2.setAttributeNode(attr_x2);

                        Attr attr_y2 = doc.createAttribute("y");
                        attr_y2.setValue(trip[5]);
                        activity2.setAttributeNode(attr_y2);

                        Attr attr_endtime2 = doc.createAttribute("end_time");
                        attr_endtime2.setValue(trip[12]);
                        activity2.setAttributeNode(attr_endtime2);

                        Element leg2 = doc.createElement("leg");
                        plan.appendChild(leg2);

                        Attr attr_mode2 = doc.createAttribute("mode");
                        attr_mode2.setValue(trip[10]);
                        leg2.setAttributeNode(attr_mode2);

                        Element activity3 = doc.createElement("activity");
                        plan.appendChild(activity3);

                        // set attribute to staff element
                        Attr attr_type3 = doc.createAttribute("type");
                        attr_type3.setValue(trip[13]);
                        activity3.setAttributeNode(attr_type3);

                        Attr attr_x3 = doc.createAttribute("x");
                        attr_x3.setValue(trip[2]);
                        activity3.setAttributeNode(attr_x3);

                        Attr attr_y3 = doc.createAttribute("y");
                        attr_y3.setValue(trip[3]);
                        activity3.setAttributeNode(attr_y3);

                        //System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

                    }
                    counter = counter + 1;
                }
            }catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                if (br != null) {
                    try {
                        br.close();
                        // write the content into xml file

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("C:\\Users\\mahajan\\Documents\\git\\data" +
                        "\\demoplansfact1000.xml"));

                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);

                transformer.transform(source, result);

                System.out.println("File saved!");

            }
            catch (TransformerException tfe) {
                tfe.printStackTrace();
            }
            catch (ParserConfigurationException pce) {
                    pce.printStackTrace();
            }
        }
    }




