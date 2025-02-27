object Main extends App {

  class CafeDetails(
                     val shopName: String,
                     val address: String,
                     val phone: String,
                     val prices: Map[String, Double]
                   )

  class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {
//  VERSION 3 - ChatGPT's suggested implementation combining best bits of mine and theirs!
    private def header: String =
      s"${cafe.shopName}\n${cafe.address}\n${cafe.phone}"

    //  Private method to format individual item lines
    private def formatItemLine(item: String, quantity: Int): String = {
      val pricePerItem = cafe.prices.getOrElse(item, 0.0)
      val totalPrice = pricePerItem * quantity
      f"$item%-20s  $$${pricePerItem}%.2f x$quantity   $$${totalPrice}%.2f"
    }
    // Private method to get all the formatted item lines
    private def items: String =
      order.map { case (item, quantity) =>
        formatItemLine(item, quantity) }.mkString("\n")

    // Private method to calculate the total cost
    private def totalCost: Double = {
      order.map { case (item, quantity) =>
        cafe.prices.getOrElse(item, 0.0) * quantity
      }.sum
    }

    // Main receipt method
    def receipt: String = {
      s"$header\n$items\n\nTotal:  $$${totalCost}%.2f"
    }
  }
  class Till(val cafe: CafeDetails) {
    private def formatMenuItemLine(item: String, price: Double): String = {
      val pricePerItem = cafe.prices.getOrElse(item, 0.0)
      f"$item%-25s   $$${pricePerItem}%6.2f"
    }
    def displayMenu: String =
      cafe.prices.map { case (item, price) =>
        formatMenuItemLine(item, price)
      }.mkString("\n")

    def createOrder(item: String, quantity: Int): Map[String, Int] = {

      if (cafe.prices.contains(item)) {
        val order: Map[String, Int] = Map(item -> quantity)
        order
    }else{
        Map()
      }
    }

  }
}

//  VERSION 2 - My suggested implementation
//class ReceiptPrinter(val cafe: CafeDetails, var order: Map[String, Int] = Map()) {
//    private def header: String = {
//      s"${cafe.shopName}\n${cafe.address}\n${cafe.phone}"
//    }
//
//    private def items: String = {
//      order.map { case (item, quantity) =>
//        val pricePerItem = cafe.prices.getOrElse(item, 0.0)
//        val totalPrice = pricePerItem * quantity
//        f"$item%-20s  $$${pricePerItem}%.2f x$quantity   $$${totalPrice}%.2f"
//      }.mkString("\n")
//    }
//
//    private def totalCost: String = {
//      order.map {case (item, quantity) =>
//        cafe.prices.getOrElse(item, 0.0) * quantity
//      }.sum
//    }
//
//    def receipt: String = {
//      s"$header\n$items\n\nTotal:  $$${totalCost}%.2f"
//    }
//  }}

//    VERSION 1 - all functionality is in receipt
//    def receipt: String = {
//      val header = s"${cafe.shopName}\n${cafe.address}\n${cafe.phone}"
//
//      val items = order.map { case (item, quantity) =>
//        val pricePerItem = cafe.prices.getOrElse(item, 0.0)
//        val totalPrice = pricePerItem * quantity
//        f"$item%-20s  $$${pricePerItem}%.2f x$quantity   $$${totalPrice}%.2f"
//      }.mkString("\n")
//
//      val totalCost = order.map {case (item, quantity) =>
//        cafe.prices.getOrElse(item, 0.0) * quantity
//      }.sum
//      s"$header\n$items\n\nTotal:  $$${totalCost}%.2f"
//    }
//  }
