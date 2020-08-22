package mishra.ankit

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, ThreadPoolExecutor, TimeUnit}

import scala.collection.mutable

/**
 * Created by AnX on 22/08/20
 */
class Indicator(corePoolSize: Int = 1,
                maximumPoolSize: Int = 1,
                keepAliveTime: Long = 0,
                unit: TimeUnit = TimeUnit.SECONDS,
                queue: BlockingQueue[Runnable] = new ArrayBlockingQueue[Runnable](1)
               ) extends ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue){
  
}

object Indicator {
  
  def indicate(availableIngredients: mutable.HashMap[Ingredient, IngredientAmount]): Unit = {
    availableIngredients.foreach[Unit]( p => {
      if(p._2.getAmount < p._1. indicatorThreshold){
        println(s"Coffee Machine is running low on ${p._1.name} only ${p._2.getAmount}ml left")
      }
    })
  }
}
