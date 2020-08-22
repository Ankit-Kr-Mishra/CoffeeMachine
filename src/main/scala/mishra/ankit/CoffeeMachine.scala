package mishra.ankit

import mishra.ankit.exceptions.{IngredientNotAvailableException, InsufficientIngredientAmountException}

import scala.collection.mutable


/**
 * Created by AnX on 22/08/20
 */
class CoffeeMachine(numOutlets: Int) {

  //stores available ingredients with their amount
  private val availableIngredients = new mutable.HashMap[Ingredient, IngredientAmount]()
  private val outlets = new Outlets(numOutlets)
  private val indicator = new Indicator()
  
  def getAvailableIngredients: mutable.HashMap[Ingredient, IngredientAmount] = {
    availableIngredients
  }

  /**
   * method to add ingredients to coffee machine
   *
   * @param ingredient ingredient to add
   * @param amount     amount to add
   */
  def addIngredient(ingredient: Ingredient, amount: Int): Unit = {
    if (availableIngredients.contains(ingredient)) {
      availableIngredients(ingredient).addAmount(amount)
    }
    else availableIngredients(ingredient) = new IngredientAmount(ingredient, amount)
  }

  /**
   * more of a wrapper around addIngredient method which allows to 
   * add ingredients in bulk
   * @param ingredients ingredients to add
   */
  def addIngredientInBulk(ingredients: Traversable[IngredientAmount]): Unit = {
    for(ingredient <- ingredients){
      addIngredient(ingredient.ingredient, ingredient.getAmount)
    }
  }

  /**
   * checks whether machine has required ingredients to dispense
   * requested beverage and then prepares and dispenses it
   * @param beverage beverage to be dispensed
   */
  def dispenseBeverage(beverage: Beverage): Unit = {
    checkAndConsumeAmount(beverage)
    outlets.dispense(beverage)
    indicator.execute(() => Indicator.indicate(availableIngredients))
  }

  /**
   * checks if required amount of ingredients is available
   * 1. consumes them if each ingredient is sufficient
   * 2. throws relevant exceptions otherwise
   * this method needs to be synchronised in order to maintain consistent
   * state of available ingredients across various dispenses
   * @param beverage beverage for which ingredients are to be consumed
   */
  @throws(classOf[IngredientNotAvailableException])
  @throws(classOf[InsufficientIngredientAmountException])
  private def checkAndConsumeAmount(beverage: Beverage): Unit = this.synchronized {
    val requiredIngredients = beverage.ingredients

    //check if every required ingredient is available
    for (requiredIngredientAmount <- requiredIngredients) {
      if (!availableIngredients.contains(requiredIngredientAmount.ingredient)) {
        throw IngredientNotAvailableException(s"${beverage.name} cannot be prepared because " +
          s"${requiredIngredientAmount.ingredient.name} is not available", requiredIngredientAmount.ingredient.name)
      }
    }

    //check if every required ingredient is available in sufficient amount
    for (requiredIngredientAmount <- requiredIngredients) {
      if (!availableIngredients(requiredIngredientAmount.ingredient).ableToDispense(requiredIngredientAmount.getAmount)) {
        throw InsufficientIngredientAmountException(s"${beverage.name} cannot be prepared because item" +
          s" ${requiredIngredientAmount.ingredient.name} is not sufficient")
      }
    }

    //consume amounts of ingredients required for this beverage
    for (requiredIngredientAmount <- requiredIngredients) {
      availableIngredients(requiredIngredientAmount.ingredient).consume(requiredIngredientAmount.getAmount)
    }
  }

  /**
   * shuts down coffee machine. We can serialize our machine
   * and persist it so that when machine restarts, it retains info about
   * the available ingredients. Currently, its only purpose is to terminate
   * our program.
   */
  def shutDown(): Unit = {
    outlets.shutdown()
    indicator.shutdown()
  }
}
