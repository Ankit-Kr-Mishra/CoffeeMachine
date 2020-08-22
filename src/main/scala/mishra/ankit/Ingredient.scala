package mishra.ankit

/**
 * Created by AnX on 22/08/20
 */
/**
 * Represents ingredients to be used by CoffeeMachine.
 * It can be used to customise ingredients as per requirement.
 * E.g. We can have different vendors for different ingredients.
 * @param name name of the beverage
 * @param indicatorThreshold threshold for indicator
 */
case class Ingredient(name: String, indicatorThreshold: Int = 10) {
  
  //hashCode has been overridden as we have used Ingredient as
  //a key in map
  override def hashCode(): Int = name.hashCode
}
