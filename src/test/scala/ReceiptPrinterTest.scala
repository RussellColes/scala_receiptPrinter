// src/test/scala/ReceiptPrinterTest.scala

//For this challenge you'll have to:
//[X] Create a new Scala project, adding a testing library as a dependency
//[X] Create a ReceiptPrinter class (see skeleton code below)
//[X] Use TDD to write the code of the receipt method
//[X] Create private methods to extract some logic from the receipt method
//
//stretch Use TDD to create a new class called Till which takes a CafeDetail instance at initialisation.
//It should have methods that:
//[X] Show the menu
//[ ] Allow the user to add an item to their order or throw an error if what they enter is not on the menu
//[ ] Finalise the order and print the statement (by calling on the receipt printer)


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ReceiptPrinterSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new Main.CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("The Coffee Connection")
      }
      // add more tests here.
      "contains the item purchased" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 1)
        )
        printer.receipt should include ("Cafe Latte")
      }
      "contains the total price per number of items purchased" in{
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 2)
        )
        printer.receipt should include ("9.50")
      }
      "contains the total price for all items purchased" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe,
          Map("Cafe Latte" -> 2, "Tea" -> 1)
        )
        printer.receipt should include ("13.15")
      }
    }
  }
}
class TillSpec extends AnyWordSpec with Matchers {
  val coffeeConnectionCafe = new Main.CafeDetails(
    "The Coffee Connection",
    "123 Lakeside Way",
    "16503600708",
    Map(
      "Cafe Latte" -> 4.75,
      "Flat White" -> 4.75,
      "Cappuccino" -> 3.85,
      "Single Espresso" -> 2.05,
      "Double Espresso" -> 3.75,
      "Americano" -> 3.75,
      "Cortado" -> 4.55,
      "Tea" -> 3.65,
      "Choc Mudcake" -> 6.40,
      "Choc Mousse" -> 8.20,
      "Affogato" -> 14.80,
      "Tiramisu" -> 11.40,
      "Blueberry Muffin" -> 4.05,
      "Chocolate Chip Muffin" -> 4.05,
      "Muffin Of The Day" -> 4.55
    )
  )

  "A Till" should {
    "display a menu" which {
      "contains an item from the menu" in {
        val till = new Main.Till(
          coffeeConnectionCafe,
        )
        till.displayMenu should include ("Affogato")
      }
    }

  }
}