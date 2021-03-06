// Author: Rishabh Singh
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Generates the cheapest shipment and displays the shipment created for a particular order.
 */
public class ShipmentTracker {

    /**
     * To generate a shipment if the supplies present in the inventories are
     * enough to fulfil the order placed.
     *
     * @param order map containing the names as well as the quantity of the items ordered
     * @param inventory the map representing each warehouse along with the supplies in each of
     *                  these warehouses.
     * @return a map representing the cheapest shipment. This will contain the name of the warehouses and the
     *         total supplies taken from each of them to fulfill the order
     */
    public LinkedHashMap<String, HashMap<String, Integer>> shipOrder( LinkedHashMap<String, Integer> order, 
                                                                     LinkedHashMap<String, HashMap<String, Integer>> 
                                                                             inventory ){


        LinkedHashMap<String, HashMap<String, Integer>> shipment = new LinkedHashMap<>();// Store the final shipment

        // Iterate through each item in order and check if it is present in given warehouses
        for( Map.Entry<String, Integer> item: order.entrySet() ){

            int items_needed = item.getValue();// to store and manipulate the quantity of each item needed

            //edge case
            if( items_needed == 0 ) continue;

            // Iterate through all the warehouses to find the item needed
            for( Map.Entry<String, HashMap<String, Integer>> warehouse: inventory.entrySet() ){

                // If item present in current warehouse and the item is not out of stock
                if( warehouse.getValue().containsKey( item.getKey() ) && warehouse.getValue().get(item.getKey()) > 0 ){

                    // If quantity of item in our warehouse is sufficient for current item
                    // Update our shipment accordingly
                    if( warehouse.getValue().get( item.getKey() ) >= items_needed ){

                        HashMap<String, Integer> temp = new HashMap<>();
                        temp.put( item.getKey(),items_needed );

                        if( shipment.containsKey( warehouse.getKey() ) ){

                            shipment.get( warehouse.getKey() ).put(item.getKey(),items_needed);

                        }
                        else{

                            shipment.put(warehouse.getKey(),temp);

                        }

                        items_needed -= warehouse.getValue().get(item.getKey());
                        break;
                    }

                    // If the quantity of the item needed is more than what is present in the current warehouse
                    // Take all the items present in the current warehouse and decrease the items needed
                    else{


                        if(shipment.containsKey(warehouse.getKey())){

                            shipment.get(warehouse.getKey()).put(item.getKey(),warehouse.getValue().get(item.getKey()));

                        }
                        else{

                            HashMap<String, Integer> temp = new HashMap<>();
                            temp.put( item.getKey(),warehouse.getValue().get(item.getKey()) );
                            shipment.put(warehouse.getKey(), temp);

                        }
                        items_needed -= warehouse.getValue().get(item.getKey());
                    }
                }

            }
            // If all the warehouses are not able to fulfil the requirement of the current item
            // Return an empty shipment
            if(items_needed > 0)
                return new LinkedHashMap<>();
        }
        return shipment;

    }


    // To display the contents of the shipment generated neatly
    public void display(LinkedHashMap<String, Integer> order,
                        LinkedHashMap<String, HashMap<String, Integer>> inventory){

        LinkedHashMap<String, HashMap<String, Integer>> result = shipOrder(order,inventory);
        if(result.size() == 0) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        int counter1 = 1;
        for( Map.Entry<String, HashMap<String, Integer>> entry: result.entrySet() ){
            System.out.print("{ " + entry.getKey()+": {");
            int counter = 1;
            for(Map.Entry<String, Integer> order_details: entry.getValue().entrySet()){
                System.out.print(" " + order_details.getKey() + ": " + order_details.getValue());
                if(counter==entry.getValue().size())
                    break;
                counter++;
                System.out.print(",");

            }
            System.out.print(" } }");
            if(counter1 == result.size())
                break;
            counter1++;
            System.out.print(", ");

        }
        System.out.println("]");

    }


}
