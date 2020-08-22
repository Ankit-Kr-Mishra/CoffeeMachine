package mishra.ankit

import ObjectsUtil._

/**
 * Created by AnX on 22/08/20
 */
object Driver {
  
  def main(args: Array[String]): Unit = {
    val machine = new CoffeeMachine(NUM_OUTLETS)

    //add various ingredients in machine
    machine.addIngredient(hotWater, 500)
    machine.addIngredient(hotMilk, 500)
    machine.addIngredient(gingerSyrup, 100)
    machine.addIngredient(sugarSyrup, 100)
    machine.addIngredient(teaLeavesSyrup, 100)

    try {
      machine.dispenseBeverage(hotTea)
    } catch {
      case e: Exception => println(e.getMessage)
    }

    try {
      machine.dispenseBeverage(hotCoffee)
    } catch {
      case e: Exception => println(e.getMessage)
    }

    try {
      machine.dispenseBeverage(blackTea)
    } catch {
      case e: Exception => println(e.getMessage)
    }

    try {
      machine.dispenseBeverage(greenTea)
    } catch {
      case e: Exception => println(e.getMessage)
    }

    // shut down machine so that program is terminated
    machine.shutDown()

  }

}
