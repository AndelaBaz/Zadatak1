# Zadatak1
Maven project zadatak1 - has two test classes: AddRemoveItemBasketTest (deletes products from the basket and compares the total amount with expected value) and OrderCompleteTest (adds the product to the cart and fills out the order form).
It is created a new Maven project and used Page Object Model. 
There is BasePage class in project for storing reusable methods and .properties file for storing global variables. 
Page Object Model is used which improves code maintainability, Thread Local so that tests can be run in parallel.
Added ExtentReports.
