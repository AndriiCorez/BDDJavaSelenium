Feature: Add Items into Bag
  I want to be able to add items to Bag,
  change their quantity in Bag and see correct total prices

  #IMPORTANT! When using PhantomJS the step
  #"I wait to advertisements to be loaded and I refresh the page" should be commented
Scenario: Add two items to Bag and increasing quantity in the Bag
  Given To avoid advertisement I go directly to Search page
  And I wait to advertisements to be loaded and I refresh the page
  And I ensure Bag is empty
  When I submit "shoes man" as a search criteria
  And I select first item in the list
  And I select first size for the item
  And I add the item to the Bag
  And I submit "t shirt lady" as a search criteria
  And I select first item in the list
  And I select first size for the item
  And I add the item to the Bag
  And I open Bag page
  And I increase first item '1' times
  And I update Bag
  Then I validate quantity and price data correctness

