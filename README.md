# ShipmentTracker

For testing Shipment tracker run the ShipmentTrackerTester class.

This class has two methods namely displayResult and automatedTest apart from the main method
Use displayResult to display the output for each test cases neatly. This method accepts a boolean which 
determines wheather the output generated should be only the output returned by the display method of 
ShipmentTracker class or the output will be generated along side the input. 

Use automatedTest to compare test results of the program with the expected results. This method will check
to see if the output generated is same as the expected result. If yes test case passes. If no then the
programs output and expected output are displayed.

Both the methods need an input into the console which contains the order list and the list of warehouses
and inventory having format similar to "{ apple: 1 }, [{ name: owd, inventory: { apple: 1 } }]". To end
test input "end" to the console.
