package mishra.ankit

import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue, ThreadPoolExecutor, TimeUnit}

import scala.collection.mutable

/**
 * Created by AnX on 22/08/20
 */
/**
 * Creates a new Indicator with the given initial parameters. The default
 * parameters is recommended as these have been assigned keeping in mind
 * that Indicator doesn't need to be triggered in parallel. For every
 * trigger, indicator checks for all the ingredients, so no need to trigger
 * it for individual ingredients.
 * @param corePoolSize the number of threads to keep in the pool, even
 *                     if they are idle
 * @param maximumPoolSize the maximum number of threads to allow in the
 *                        pool
 * @param keepAliveTime   when the number of threads is greater than
 *                        the core, this is the maximum time that excess idle threads
 *                        will wait for new tasks before terminating.
 * @param unit            the time unit for the { @code keepAliveTime} argument
 * @param queue the queue to use for holding tasks before they are
 *        executed.  This queue will hold only the { @code Runnable}
 *                                                         tasks submitted by the { @code execute} method.
 * @throws IllegalArgumentException if one of the following holds:<br>
 *                                  { @code corePoolSize < 0}<br>
 *                                          { @code keepAliveTime < 0}<br>
 *                                          { @code maximumPoolSize <= 0}<br>
 *                                          { @code maximumPoolSize < corePoolSize}
 * @throws NullPointerException if { @code workQueue} is null
 */
class Indicator(corePoolSize: Int = 1,
                maximumPoolSize: Int = 1,
                keepAliveTime: Long = 0,
                unit: TimeUnit = TimeUnit.SECONDS,
                queue: BlockingQueue[Runnable] = new ArrayBlockingQueue[Runnable](1)
               ) extends ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, queue){
  
}

object Indicator {

  /**
   * this method checks all ingredients and flags those which are running low on amount.
   * This method assumes that indicator is idempotent i.e. if an ingredient is already flagged,
   * nothing will happen. Foe now it will print for every trigger.
   * @param availableIngredients ingredients available in CoffeeMachine
   */
  def indicate(availableIngredients: mutable.HashMap[Ingredient, IngredientAmount]): Unit = {
    availableIngredients.foreach[Unit]( p => {
      if(p._2.getAmount < p._1. indicatorThreshold){
        println(s"INDICATOR ALERT: Coffee Machine running low on ${p._1.name} only ${p._2.getAmount}ml left !!")
      }
    })
  }
}
