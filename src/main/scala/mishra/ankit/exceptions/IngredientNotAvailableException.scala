package mishra.ankit.exceptions

/**
 * Created by AnX on 22/08/20
 */
case class IngredientNotAvailableException(msg: String, ingredientName: String) extends Exception(msg) {

}
