package mishra.ankit

import java.util.concurrent.atomic.AtomicInteger

/**
 * Created by AnX on 22/08/20
 */
/**
 * CoffeeMachine will store ingredients using this wrapper.
 * So that consumption and addition can be handle atomically.
 * @param ingredient ingredient
 * @param amount amount
 */
class IngredientAmount(val ingredient: Ingredient, private val amount: Int) {

  private val currentAmount: AtomicInteger = new AtomicInteger(amount)

  /**
   * adds given amount atomically
   * @param amount amount to add
   */
  def addAmount(amount: Int): Unit = {
    currentAmount.getAndAdd(amount)
  }

  def getAmount: Int = {
    currentAmount.intValue()
  }

  /**
   * consumes given amount atomically
   * @param amount amount to consume
   */
  def consume(amount: Int): Unit = {
    if (ableToDispense(amount)) {
      currentAmount.getAndUpdate(x => x - amount)
    }
    else println(s"only $getAmount ml of ${ingredient.name} is available")
  }

  /**
   * checks whether requested amount can be dispensed
   * @param amount requested amount to dispense
   * @return
   */
  def ableToDispense(amount: Int): Boolean = {
    amount <= getAmount
  }
}

