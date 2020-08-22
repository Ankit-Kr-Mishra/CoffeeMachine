package mishra.ankit

import scala.collection.immutable.HashMap

/**
 * Created by AnX on 22/08/20
 */
case class Beverage(name: String, ingredients: HashMap[Ingredient, Int]) {

  // this constructor will be used to give custom timeToPrepare to a beverage
  def this(name: String, ingredients: HashMap[Ingredient, Int], timeToPrepareInSeconds: Int) = {
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
