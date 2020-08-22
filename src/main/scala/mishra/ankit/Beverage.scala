package mishra.ankit

/**
 * Created by AnX on 22/08/20
 */
case class Beverage(name: String, ingredients: Traversable[IngredientAmount]) {

  def this(name: String, ingredients: Traversable[IngredientAmount], timeToPrepareInSeconds: Int) = {
    this(name, ingredients)
    this.setTimeToPrepare(timeToPrepareInSeconds)
  }

  //default time in seconds to prepare this beverage
  private var timeToPrepareInSeconds = 1

  def setTimeToPrepare(timeInSeconds: Int): Unit = {
    timeToPrepareInSeconds = timeInSeconds
  }

  def getTimeToPrepare: Int = {
    timeToPrepareInSeconds
  }

}
