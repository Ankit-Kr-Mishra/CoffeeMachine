package mishra.ankit

import java.util.concurrent.{Executors, RejectedExecutionException}

/**
 * Created by AnX on 22/08/20
 */
class Outlets(numOutlets: Int) {

  /**
   * insures that maximum 'numOutlets' beverages can be served in parallel
   */
  private val beverageServer = Executors.newFixedThreadPool(numOutlets)

  /**
   * submits the beverage dispensing task to beverageServer
   * @param beverage beverage to be served
   */
  def dispense(beverage: Beverage): Unit = {
    try {
      beverageServer.submit(BeverageServer(beverage))
    } catch {
      case e: RejectedExecutionException => {
        println(s"${beverage.name} cannot be dispensed as no outlet is available")
      }
    }
  }

  /**
   * shuts down the beverageServer
   */
  def shutdown(): Unit = {
    beverageServer.shutdown()
  }
}
