package mishra.ankit

/**
 * Created by AnX on 22/08/20
 */
case class BeverageServer(beverage: Beverage) extends Runnable {

  /**
   * prepares and serves beverage
   */
  override def run(): Unit = {
    println(s"${beverage.name} is being prepared. It will take ${beverage.getTimeToPrepare}s")
    Thread.sleep(beverage.getTimeToPrepare * 1000)
    println(s"${beverage.name} has been prepared")
  }

}
