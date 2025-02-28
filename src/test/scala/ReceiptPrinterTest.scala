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
//[X] Allow the user to add an item to their order or throw an error if what they enter is not on the menu
//[ ] Finalise the order and print the statement (by calling on the receipt printer)


import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

trait TestData {
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
}

class ReceiptPrinterSpec extends AnyWordSpec with Matchers with TestData {
  "A ReceiptPrinter" should {
    "format a receipt" which {
      "contains the name of the cafe" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe, Map("Cafe Latte" -> 1))
        printer.receipt should include ("The Coffee Connection")
      }
      // add more tests here.
      "contains the item purchased" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe, Map("Cafe Latte" -> 1))
        printer.receipt should include ("Cafe Latte")
      }
      "contains the total price for 2 of the same item" in{
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe, Map("Cafe Latte" -> 2))
        printer.receipt should include ("9.50")
      }
      "contains the total price for all items purchased" in {
        val printer = new Main.ReceiptPrinter(
          coffeeConnectionCafe, Map("Cafe Latte" -> 2, "Tea" -> 1))
        printer.receipt should include ("13.15")
      }
    }
  }
}
class TillSpec extends AnyWordSpec with Matchers with TestData {
  "A Till" should {
    "display a menu" which {
      "contains an item from the menu" in {
        val till = new Main.Till(coffeeConnectionCafe)
        till.displayMenu should include("Affogato")
      }
    }
    "create an order" which {
      "adds an item from the menu" in {
        val till = new Main.Till(coffeeConnectionCafe)
        till.orderItem("Affogato", 1)
        till.displayOrder() should include("Affogato")
      }
    }
    "return a confirmation message when creating an order" in {
      val till = new Main.Till(coffeeConnectionCafe)
      till.orderItem("Affogato", 1) shouldBe ("New order created: Affogato x1")
    }


    "update an order" which {
      "adds a new item to the existing order" in {
        val till = new Main.Till(coffeeConnectionCafe)
        till.orderItem("Affogato", 1)
        till.orderItem("Muffin Of The Day", 1)
        val orderSummary = till.displayOrder()
        orderSummary should include("Affogato x1")
        orderSummary should include("Muffin Of The Day x1")
      }

      "increases the 'quantity' when adding multiple of the same items" when {
        "multiple items added" in {
          val till = new Main.Till(coffeeConnectionCafe)
          till.orderItem("Affogato", 1)
          till.orderItem("Affogato", 3)
          till.displayOrder() should include("Affogato x4")
        }
      }
    }


    "reject invalid items" which {
      "throws an error when creating an order with an invalid item" in {
        val till = new Main.Till(coffeeConnectionCafe)
        till.orderItem("Gingerbread Latte", 1) shouldBe ("Error: 'Gingerbread Latte' is not available on the menu.")
      }
    }

    "throw an update menu error" which {
      "states the item is not on the menu" when {
        "a non menu item is added to update an order" in {
          val till = new Main.Till(coffeeConnectionCafe)
          till.orderItem("Affogato", 1)
          till.orderItem("Gingerbread Latte", 1) should include("Error: 'Gingerbread Latte' is not available on the menu.")
          till.displayOrder() should include ("Affogato x1")
        }
      }
    }
    "return a formatted receipt" which {
      "itemises the order and prices with a total price at the end" in {
        val till = new Main.Till(coffeeConnectionCafe)
        till.orderItem("Affogato", 1)
        till.orderItem("Blueberry Muffin", 2)
        till.orderItem("Cortado", 1)
        val printedReceipt = till.finaliseOrder(coffeeConnectionCafe)
        printedReceipt should include ("123 Lakeside Way")
        printedReceipt should include ("Blueberry Muffin")
        printedReceipt should include ("Blueberry Muffin")
        printedReceipt should include ("27.45")
      }
    }
  }
}