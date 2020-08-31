// Author: Rishabh Singh
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


// Unit testing for ShipmentTracker
// You can choose from two options
// displayResult will display the output generated for a given input
// automatedTest will check to see if the output generated is same as
// the expected result. If yes test case passes.
// If no then the program's output and expected output are displayed.
// Uncomment line 24 and comment line 23 to use automatedTest()
public class ShipmentTrackerTester {

    private static LinkedHashMap<String, Integer> order_map = new LinkedHashMap<>();
    private static LinkedHashMap<String, HashMap<String, Integer>> inventory_map = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        displayResult(false);// To display the output in neat manner
//        automatedTest();// to test the contents of the shipment with the expected output

    }

    /*
    * Takes input from the console and calls the display method from the ShipmentTracker class to output result.
    * Provide input in the format similar to { apple: 1 }, [{ name: owd, inventory: { apple: 1 } }] (taken from the
    * problem statement). We can provide multiple lines (Check the sample test file attached).
    * To end the testing input "end" in any case to exit.
    * @param displayOnlyOutput: Set true to only display the output. Set false to display
    *                           the input along side the output generated.
    */
    public static void displayResult(boolean displayOnlyOutput) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ShipmentTracker st = new ShipmentTracker();


        while (!line.equalsIgnoreCase("END")) {
            if (line.trim().length() == 0) {
                line = br.readLine();
                continue;
            }

            if(!displayOnlyOutput) {
                System.out.println("For input " + line);
                System.out.print("Shipment created is: ");
            }

            try {

                String order = line.substring(0, line.indexOf("}"));
                order = order.replace("{", "");

                String[] order_arr = order.trim().split(",");

                for (String s : order_arr) {
                    String[] temp_arr = s.split(":");

                    String item = temp_arr[0].trim();
                    int quantity = Integer.parseInt(temp_arr[1].trim());
                    order_map.put(item, quantity);
                }

                String inventory = line.substring(line.indexOf("["));
                inventory = inventory.replace("[", "");
                inventory = inventory.replace("{", "");
                inventory = inventory.replace("}", "");
                inventory = inventory.replace("]", "");

                String inventory_arr[] = inventory.split("name:");

                for (String s : inventory_arr) {

                    if (s.trim().length() == 0) continue;
                    String name = s.substring(0, s.indexOf(",")).trim();
                    inventory_map.put(name, new HashMap<>());
                    String storage = s.substring(s.indexOf("inventory:") + 10).trim();
                    String[] inv_arr = storage.split(",");
                    for (String s1 : inv_arr) {
                        String[] values = s1.split(":");
                        String item_name = values[0].trim();
                        int amount = Integer.parseInt(values[1].trim());
                        inventory_map.get(name).put(item_name, amount);

                    }

                }


                st.display(order_map, inventory_map);
                order_map.clear();
                inventory_map.clear();
                line = br.readLine();
                System.out.println();

            } catch (Exception e) {
                System.out.println();
                System.err.println("Invalid INPUT!");
                System.err.println("Enter in a format similar to \"{ apple: 1 }, [{ name: owd, inventory:" +
                        " { apple: 1 } }]\" or enter \"end\" to exit");
                line = br.readLine();
            }
        }

    }

    // The goal here is to automate the testing process and display the result..
    public static void automatedTest() throws IOException {

        ArrayList<LinkedHashMap> testcases = new ArrayList<>();

        LinkedHashMap<String, HashMap<String, Integer>> test1 = new LinkedHashMap<>();
        test1.put("owd", new HashMap<>());
        test1.get("owd").put("apple", 1);
        testcases.add(test1);

        LinkedHashMap<String, HashMap<String, Integer>> test2 = new LinkedHashMap<>();
        test2.put("owd", new HashMap<>());
        test2.put("dm", new HashMap<>());
        test2.get("dm").put("apple", 5);
        test2.get("owd").put("apple", 5);
        testcases.add(test2);

        LinkedHashMap<String, HashMap<String, Integer>> test3 = new LinkedHashMap<>();
        testcases.add(test3);
        testcases.add(test3);

        LinkedHashMap<String, HashMap<String, Integer>> test = new LinkedHashMap<>();
        test.put("dm", new HashMap<>());
        test.get("dm").put("apple", 10);
        testcases.add(test);

        LinkedHashMap<String, HashMap<String, Integer>> test4 = new LinkedHashMap<>();
        test4.put("owd",new HashMap<>());
        test4.put("dm", new HashMap<>());
        test4.get("owd").put("apple", 5);
        test4.get("owd").put("orange", 5);
        test4.get("owd").put("grapes", 2);
        test4.get("dm").put("banana", 5);
        test4.get("dm").put("grapes", 3);
        testcases.add(test4);

        LinkedHashMap<String, HashMap<String, Integer>> test5 = new LinkedHashMap<>();
        test5.put("owd",new HashMap<>());
        test5.get("owd").put("apple", 5);
        testcases.add(test5);

        testcases.add(test3);

        LinkedHashMap<String, HashMap<String, Integer>> test6 = new LinkedHashMap<>();
        test6.put("owd",new HashMap<>());
        test6.put("dm", new HashMap<>());
        test6.get("owd").put("grapes", 2);
        test6.get("dm").put("grapes", 3);
        testcases.add(test6);

        LinkedHashMap<String, HashMap<String, Integer>> test7 = new LinkedHashMap<>();
        test7.put("owd",new HashMap<>());
        test7.put("dm", new HashMap<>());
        test7.get("owd").put("grapes", 2);
        test7.get("dm").put("grapes", 3);
        test7.get("dm").put("banana", 5);
        testcases.add(test7);

        LinkedHashMap<String, HashMap<String, Integer>> test8 = new LinkedHashMap<>();
        test8.put("owd",new HashMap<>());
        test8.put("dm", new HashMap<>());
        test8.get("owd").put("orange", 5);
        test8.get("owd").put("grapes", 2);
        test8.get("dm").put("grapes", 3);
        test8.get("dm").put("banana", 5);
        testcases.add(test8);


        testcases.add(test3);
        testcases.add(test3);
        testcases.add(test3);


        LinkedHashMap<String, HashMap<String, Integer>> test9 = new LinkedHashMap<>();
        test9.put("owd",new HashMap<>());
        test9.put("dm", new HashMap<>());
        test9.put("Tar", new HashMap<>());
        test9.get("owd").put("grapes", 2);
        test9.get("owd").put("orange", 10);
        test9.get("dm").put("orange", 10);
        test9.get("Tar").put("orange", 35);
        test9.get("dm").put("grapes", 3);
        testcases.add(test9);

        int testcase_counter = 0;




        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        ShipmentTracker st = new ShipmentTracker();


        while (!line.equalsIgnoreCase("END")) {
            if (line.trim().length() == 0) {
                line = br.readLine();
                continue;
            }

            System.out.println("For input " + line);
            System.out.print("Shipment created is: ");

            try {

                String order = line.substring(0, line.indexOf("}"));
                order = order.replace("{", "");

                String[] order_arr = order.trim().split(",");

                for (String s : order_arr) {
                    String[] temp_arr = s.split(":");

                    String item = temp_arr[0].trim();
                    int quantity = Integer.parseInt(temp_arr[1].trim());
                    order_map.put(item, quantity);
                }

                String inventory = line.substring(line.indexOf("["));
                inventory = inventory.replace("[", "");
                inventory = inventory.replace("{", "");
                inventory = inventory.replace("}", "");
                inventory = inventory.replace("]", "");

                String inventory_arr[] = inventory.split("name:");

                for (String s : inventory_arr) {

                    if (s.trim().length() == 0) continue;
                    String name = s.substring(0, s.indexOf(",")).trim();
                    inventory_map.put(name, new HashMap<>());
                    String storage = s.substring(s.indexOf("inventory:") + 10).trim();
                    String[] inv_arr = storage.split(",");
                    for (String s1 : inv_arr) {
                        String[] values = s1.split(":");
                        String item_name = values[0].trim();
                        int amount = Integer.parseInt(values[1].trim());
                        inventory_map.get(name).put(item_name, amount);

                    }

                }


                LinkedHashMap<String, HashMap<String, Integer>> result = st.shipOrder(order_map, inventory_map);
                if(result.equals(testcases.get(testcase_counter))){
                    System.out.println("Testcase "+ (testcase_counter+1) + " PASSED");
                }
                else{
                    System.out.println("Testcase "+ (testcase_counter+1) + " FAILED!!!");
                    System.out.println("Your output "+ result);
                    System.out.println("Expected output " + testcases.get(testcase_counter));
                }
                testcase_counter++;
                order_map.clear();
                inventory_map.clear();
                line = br.readLine();
                System.out.println();

            } catch (Exception e) {
                System.out.println();
                System.err.println("Invalid INPUT!");
                System.err.println("Enter in a format similar to \"{ apple: 1 }, [{ name: owd, inventory:" +
                        " { apple: 1 } }]\" or enter \"end\" to exit");
                line = br.readLine();
            }
        }

    }
}
