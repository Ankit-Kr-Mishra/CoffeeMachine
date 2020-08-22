package mishra.ankit

import scala.collection.immutable.HashMap

/**
 * Created by AnX on 22/08/20
 */
object ObjectsUtil {

  val NUM_OUTLETS = 3

  val hotWater: Ingredient = Ingredient("hot_water")
  val hotMilk: Ingredient = Ingredient("hot_milk")
  val gingerSyrup: Ingredient = Ingredient("ginger_syrup")
  val sugarSyrup: Ingredient = Ingredient("sugar_syrup")
  val teaLeavesSyrup: Ingredient = Ingredient("tea_leaves_syrup")
  val greenMixture: Ingredient = Ingredient("green_mixture")

  // instantiating hot_tea beverage
  val hotTea: Beverage = Beverage("hot_tea", HashMap(
    hotWater -> 200,
    hotMilk -> 100,
    gingerSyrup -> 10,
    sugarSyrup -> 10,
    teaLeavesSyrup -> 30))

  // instantiating black_tea beverage
  val blackTea: Beverage = Beverage("black_tea", HashMap(
    hotWater -> 300,
    gingerSyrup -> 30,
    sugarSyrup -> 50,
    teaLeavesSyrup -> 30))

  // instantiating hot_coffee beverage
  val hotCoffee: Beverage = Beverage("hot_coffee", HashMap(
    hotWater -> 100,
    hotMilk -> 400,
    gingerSyrup -> 30,
    sugarSyrup -> 50,
    teaLeavesSyrup -> 30))

  // instantiating green_tea beverage
  val greenTea: Beverage = Beverage("green_tea", HashMap(
    hotWater -> 100,
    gingerSyrup -> 30,
    sugarSyrup -> 50,
    greenMixture -> 30))
  
}
