package mishra.ankit

/**
 * Created by AnX on 22/08/20
 */
case class BeverageServer(beverage: Beverage) extends Runnable {

  /**
   * prepares and serves beverage
   */
  override def run(): Unit = {
    Thread.sleep(beverage.getTimeToPrepare * 1000)
    println(s"${beverage.name} has been prepared")
  }

}
