package mishra.ankit.exceptions

/**
 * Created by AnX on 22/08/20
 */
/**
 * exception to represent certain Ingredient is insufficient
 * @param msg message to show
 */
case class InsufficientIngredientAmountException(msg: String) extends Exception(msg) {

}

