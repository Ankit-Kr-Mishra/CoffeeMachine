package mishra.ankit

import mishra.ankit.ObjectsUtil._
import mishra.ankit.exceptions.{IngredientNotAvailableException, InsufficientIngredientAmountException}
import org.junit.jupiter.api.Assertions.{assertDoesNotThrow, assertFalse, assertThrows, assertTrue}
import org.junit.jupiter.api.function.Executable
import org.junit.jupiter.api.{AfterEach, BeforeEach, DisplayName, Test}

/**
 * Created by AnX on 22/08/20
 */
class CoffeeMachineTest {

  var machine: CoffeeMachine = _

  @BeforeEach
  def initCoffeeMachine(): Unit = {
    machine = new CoffeeMachine(NUM_OUTLETS)
  }

  @DisplayName("test checkAndConsumeAmount() throws IngredientNotAvailableException")
  @Test
  def testCheckAndConsumeAmount1(): Unit = {
    assertThrows(classOf[IngredientNotAvailableException], () => machine.dispenseBeverage(hotTea))
    machine.addIngredientInBulk(Traversable(
      new IngredientAmount(hotWater, 200),
      new IngredientAmount(hotMilk, 100),
      new IngredientAmount(gingerSyrup, 10),
      new IngredientAmount(sugarSyrup, 10)))
    try{
      machine.dispenseBeverage(hotTea)
    } catch {
      case e: IngredientNotAvailableException => assertTrue(e.ingredientName.equals(teaLeavesSyrup.name))
    }
  }
  
  @DisplayName("test checkAndConsumeAmount() doesn't throw exception")
  @Test
  def testCheckAndConsumeAmount2(): Unit = {
    machine.addIngredientInBulk(Traversable(
      new IngredientAmount(hotWater, 200),
      new IngredientAmount(hotMilk, 100),
      new IngredientAmount(gingerSyrup, 10),
      new IngredientAmount(sugarSyrup, 10),
      new IngredientAmount(teaLeavesSyrup, 30)))
    assertDoesNotThrow(new Executable {
      override def execute(): Unit = machine.dispenseBeverage(hotTea)
    })
    val availableIngredients = machine.getAvailableIngredients
    assertTrue(availableIngredients(hotWater).getAmount == 200 - hotTea.ingredients(hotWater))
    assertTrue(availableIngredients(hotMilk).getAmount == 100 - hotTea.ingredients(hotMilk))
    assertTrue(availableIngredients(gingerSyrup).getAmount == 10 - hotTea.ingredients(gingerSyrup))
    assertTrue(availableIngredients(sugarSyrup).getAmount == 10 - hotTea.ingredients(sugarSyrup))
    assertTrue(availableIngredients(teaLeavesSyrup).getAmount == 30 - hotTea.ingredients(teaLeavesSyrup))
  }

  @DisplayName("test checkAndConsumeAmount() throws InsufficientIngredientAmountException")
  @Test
  def testCheckAndConsumeAmount3(): Unit = {
    machine.addIngredientInBulk(Traversable(
      new IngredientAmount(hotWater, 200),
      new IngredientAmount(hotMilk, 100),
      new IngredientAmount(gingerSyrup, 10),
      new IngredientAmount(sugarSyrup, 10),
      new IngredientAmount(teaLeavesSyrup, 30)))
    machine.dispenseBeverage(hotTea)
    assertThrows(classOf[InsufficientIngredientAmountException], () => machine.dispenseBeverage(hotTea))
  }

  @Test
  def testAddIngredient(): Unit = {
    machine.addIngredient(hotMilk, 100)
    machine.addIngredient(hotWater, 100)
    assertTrue(machine.getAvailableIngredients(hotMilk).getAmount == 100)
    assertTrue(machine.getAvailableIngredients(hotWater).getAmount == 100)
    machine.addIngredient(hotMilk, 100)
    assertTrue(machine.getAvailableIngredients(hotMilk).getAmount == 200)
    assertTrue(machine.getAvailableIngredients(hotWater).getAmount == 100)
  }

  @Test
  def testAddIngredientInBulk(): Unit = {
    machine.addIngredientInBulk(Traversable(
      new IngredientAmount(hotWater, 100),
      new IngredientAmount(hotMilk, 400),
      new IngredientAmount(gingerSyrup, 30)))
    val availableIngredients = machine.getAvailableIngredients
    assertTrue(availableIngredients(hotWater).getAmount == 100)
    assertTrue(availableIngredients(hotMilk).getAmount == 400)
    assertTrue(availableIngredients(gingerSyrup).getAmount == 30)
    assertFalse(availableIngredients.contains(teaLeavesSyrup))
  }

  @AfterEach
  def shutDownMachine(): Unit = {
    machine.shutDown()
  }

}
