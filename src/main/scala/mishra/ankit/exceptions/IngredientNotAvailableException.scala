package mishra.ankit.exceptions

/**
 * Created by AnX on 22/08/20
 */
/**
 * exception to represent that certain ingredient is not available
 * @param msg message to show
 * @param ingredientName ingredient which is not available
 */
case class IngredientNotAvailableException(msg: String, ingredientName: String) extends Exception(msg) {

}
