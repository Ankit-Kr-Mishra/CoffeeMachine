package mishra.ankit

/**
 * Created by AnX on 22/08/20
 */
object Driver {

  private final val NUM_OUTLETS = 3

  val hotWater: Ingredient = Ingredient("hot_water")
  val hotMilk: Ingredient = Ingredient("hot_milk")
  val gingerSyrup: Ingredient = Ingredient("ginger_syrup")
  val sugarSyrup: Ingredient = Ingredient("sugar_syrup")
  val teaLeavesSyrup: Ingredient = Ingredient("tea_leaves_syrup")
  val greenMixture: Ingredient = Ingredient("green_mixture")

  val hotTea: Beverage = Beverage("hot_tea", Traversable(
    new IngredientAmount(hotWater, 200),
    new IngredientAmount(hotMilk, 100),
    new IngredientAmount(gingerSyrup, 10),
    new IngredientAmount(sugarSyrup, 10),
    new IngredientAmount(teaLeavesSyrup, 30)))

  val blackTea: Beverage = Beverage("black_tea", Traversable(
    new IngredientAmount(hotWater, 300),
    new IngredientAmount(gingerSyrup, 30),
    new IngredientAmount(sugarSyrup, 50),
    new IngredientAmount(teaLeavesSyrup, 30)))

  val hotCoffee: Beverage = Beverage("hot_coffee", Traversable(
    new IngredientAmount(hotWater, 100),
    new IngredientAmount(hotMilk, 400),
    new IngredientAmount(gingerSyrup, 30),
    new IngredientAmount(sugarSyrup, 50),
    new IngredientAmount(teaLeavesSyrup, 30)))

  val greenTea: Beverage = Beverage("green_tea", Traversable(
    new IngredientAmount(hotWater, 200),
    new IngredientAmount(hotMilk, 100),
    new IngredientAmount(gingerSyrup, 10),
    new IngredientAmount(sugarSyrup, 10),
    new IngredientAmount(greenMixture, 30)))

  def main(args: Array[String]): Unit = {
    val machine = new CoffeeMachine(NUM_OUTLETS)

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

    machine.shutDown()

  }

}
