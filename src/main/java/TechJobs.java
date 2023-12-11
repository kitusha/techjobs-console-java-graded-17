import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by LaunchCode
 */
public class TechJobs {

    static Scanner in = new Scanner(System.in);

    public static void main (String[] args) {

        // Initialize our field map with key/name pairs
        HashMap<String, String> columnChoices = new HashMap<>();
        columnChoices.put("core competency", "Skill");
        columnChoices.put("employer", "Employer");
        columnChoices.put("location", "Location");
        columnChoices.put("position type", "Position Type");
        columnChoices.put("all", "All");

        // Top-level menu options
        HashMap<String, String> actionChoices = new HashMap<>();
        actionChoices.put("search", "Search");
        actionChoices.put("list", "List");

        System.out.println("Welcome to LaunchCode's TechJobs App!");

        // Allow the user to search until they manually quit
        while (true) {

            String actionChoice = getUserSelection("View jobs by (type 'x' to quit):", actionChoices);

            if (actionChoice == null) {
                break;
            } else if (actionChoice.equals("list")) {

                String columnChoice = getUserSelection("List", columnChoices);

                if (columnChoice.equals("all")) {
                    printJobs(JobData.findAll());
                } else {

                    ArrayList<String> results = JobData.findAll(columnChoice);

                    System.out.println("\n*** All " + columnChoices.get(columnChoice) + " Values ***");

                    // Print list of skills, employers, etc
                    for (String item : results) {
                        System.out.println(item);
                    }
                }

            } else { // choice is "search"

                // How does the user want to search (e.g. by skill or employer)
                String searchField = getUserSelection("Search by:", columnChoices);

                // What is their search term?
                System.out.println("\nSearch term:");
                String searchTerm = in.nextLine();

                if (searchField.equals("all")) printJobs( JobData.findByValue( searchTerm.toLowerCase() ) );
                else {
                    printJobs(JobData.findByColumnAndValue(searchField.toLowerCase(), searchTerm.toLowerCase()));
                }
            }
        }
    }

    // ﻿Returns the key of the selected item from the choices Dictionary
    private static String getUserSelection(String menuHeader, HashMap<String, String> choices) {

        int choiceIdx = -1;
        Boolean validChoice = false;
        String[] choiceKeys = new String[choices.size()];

        // Put the choices in an ordered structure so we can
        // associate an integer with each one
        int i = 0;
        for (String choiceKey : choices.keySet()) {
            choiceKeys[i] = choiceKey;
            i++;
        }

        do {

            System.out.println("\n" + menuHeader);

            // Print available choices
            for (int j = 0; j < choiceKeys.length; j++) {
                System.out.println("" + j + " - " + choices.get(choiceKeys[j]));
            }

            if (in.hasNextInt()) {
                choiceIdx = in.nextInt();
                in.nextLine();
            } else {
                String line = in.nextLine();
                boolean shouldQuit = line.equals("x");
                if (shouldQuit) {
                    return null;
                }
            }

            // Validate user's input
            if (choiceIdx < 0 || choiceIdx >= choiceKeys.length) {
                System.out.println("Invalid choice. Try again.");
            } else {
                validChoice = true;
            }

        } while(!validChoice);

        return choiceKeys[choiceIdx];
    }

    // Print a list of jobs
    //public class TechJobs {



    private static void printJobs(ArrayList<HashMap<String, String>> someJobs) {
        // loop through the array
        if(someJobs.size() == 0){
            System.out.print("No Results");

        }else{
            for(HashMap<String, String> job: someJobs){
                String jobo = "\n*****\n";

                for(Map.Entry<String, String> categories: job.entrySet()){
                    jobo += categories.getKey() + ": " + categories.getValue() + "\n";
                }
                jobo += "*****\n";
                System.out.print(jobo);
            }
        }
//

        //System.out.println("printJobs is not implemented yet");
    }
}



        //class JobSearch {

//            // Assuming you have an ArrayList of jobs
//           // private ArrayList<HashMap<String, String>> jobs;
//
//            // Other methods and class members
//
//            // Method to print jobs
//            //public void printJobs() {
//                // Check if there are jobs to print
//                if (jobs.isEmpty()) {
//                    System.out.println("Search term:");
//                    System.out.println("Example Search Term with No Results");
//                    System.out.println("No Results");
//                } else {
//                    // Iterate over the ArrayList of jobs
//                    for (HashMap<String, String> job : jobs) {
//                        // Print asterisks before each job
//                        System.out.println("*****");
//
//                        // Print job details
//                        System.out.println("position type: " + job.get("PositionType"));
//                        System.out.println("name: " + job.get("Name"));
//                        System.out.println("employer: " + job.get("Employer"));
//                        System.out.println("location: " + job.get("location"));
//                        System.out.println("core competency: " + job.get("Skill"));
//
//                        // Print asterisks after each job and add a blank line
//                        System.out.println("*****\n");
//                    }
//                }
//            }
//
//            // Other methods and class members
//        }
//
//        System.out.println("printJobs is not implemented yet");
//        JobData jobData = new JobData();
//
//        // Your search term
//        String searchTerm = "JavaScript";
//
//        // Call the non-static method on the instance
//        jobData.printJobs(jobData.findByValue(searchTerm));
//    }
//    }
//
