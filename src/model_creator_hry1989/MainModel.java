package model_creator_hry1989;

import java.util.Scanner;

import org.apache.jena.query.Dataset;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.tdb.TDBFactory;

public class MainModel {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// the base IRI for the ontology
		System.out.println("Give your model a base IRI");
		String iriBase = scanner.nextLine() + "/";

		// variables to store resources, properties and object values
		String mRes = "";
		String mProp = "";
		String mObj = "";

		System.out.println("Give your dataset a name:");
		String datasetName = scanner.nextLine();

		// generates a dataset and resets it
		Dataset dataset = TDBFactory.createDataset(datasetName);
		dataset.getDefaultModel().removeAll();

		Model model = dataset.getDefaultModel();

		boolean donebuilding = false;
		
		System.out.println("If you want to exit, you can write 'exitnow' after finishing a triple\n");

		while (!donebuilding) {
			System.out.println("Name your resource:");
			mRes = scanner.nextLine();
			if (mRes.toLowerCase().equals("exitnow")) {
				break;
			}

			System.out.println("Name your property:");
			mProp = scanner.nextLine();

			System.out.println("Enter the object value (currently just strings):");
			mObj = scanner.nextLine();

			// inserts the information into the model
			Resource res = model.createResource(iriBase + mRes);
			Property prop = model.createProperty(iriBase + mProp);
			res.addProperty(prop, mObj);
		}
		
		System.out.println("Your model: \n");
		model.write(System.out, "TURTLE");
		System.out.println("Would you like to save it as a file? (Y/N)");
		String save = scanner.nextLine().toLowerCase();
		
		if (save.equals("y")) {
			filesaver();
		}
		
		dataset.close();
		scanner.close();
	}

	public static void filesaver() {
		System.out.println("Not implemented yet!");
	}

}
