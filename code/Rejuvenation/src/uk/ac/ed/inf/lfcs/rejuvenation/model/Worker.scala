package uk.ac.ed.inf.lfcs.rejuvenation.model

import java.text.DecimalFormat

class Worker(reju_schedule:Int, recovery_time:Int, reju_time:Int, pmf:Int=>Double) {
  // reju_schedule: rejuvenation schedule, the maximum time in working status
  // recovery_time: recovery time, the time for recovery from unexpected failure, reju_time excluded
  // reju_time:  the time for rejuvenation process
  // pmf: Let f(t) be the probability mass function (pmf) which
  //    represents the probability that, without software rejuvenation,
  //    the system will fail when being running for
  //    exactly t.
  
  
  // MaxValue: Int(2147483647) = 2^31-1
  // 2147483647 / 3600 / 24 / 365 = 68.09 ...
  // 2147483647 seconds = 68.1 year
  
  
  // reliability of running for t time
  private def reliability(t:Int):Double = {
    var sum = 0.0
    var i = 0;
    while(i<t){
      sum += pmf(i)
      i +=1
    }    
    return 1-sum
  }
  
  // failure rate at time t
  private def lambda(t:Int):Double = {
    return pmf(t)/reliability(t)
  }

  private val vector_length:Int = reju_schedule + recovery_time + reju_time
  
  /*
   * x_i = 
   *         W_i           0 <= i < T
   *         R_(i-T)       T <= i < T + t_r
   *         F_(i-R-t_r)   T + t_r <=i < T + t_r + t_f
   */
  
  // probability transtion matrix
  private val ptm:Array[Array[Double]] = Array.ofDim[Double](vector_length, vector_length)
  for (i <- 0 until vector_length){
          for (j <- 0 until vector_length){
            if (j == i+1 && j<=reju_schedule-1 ){
              // W_k -> W_(k+1)
              ptm(i)(j) = 1 - lambda(i)
            }            
            else if(j == reju_schedule + reju_time && i < reju_schedule-1){
              // W_k -> F_0
              ptm(i)(j) = lambda(i)              
            }else if( ( i == reju_schedule-1 && j == reju_schedule )  ||  // end of work
                      ( j == i+1 &&  reju_schedule <= i && i <=  reju_schedule + reju_time -2) || // during rejuvenation
                      ( i == reju_schedule + reju_time -1 && j == 0 )  || // end of rejuvenation
                      ( j == i+1 &&  reju_schedule + reju_time <= i && i <=  reju_schedule + reju_time + recovery_time -2)  || // during repair
                      ( i == reju_schedule + reju_time + recovery_time -1 && j == reju_schedule )  // end of repair
                    ){
              ptm(i)(j) = 1
            }else {
              ptm(i)(j) = 0.0
            }
            
//            println("("+i+","+j+") = "+ptm(i)(j))
          }      
    }
  
  
  def report_ptm:Unit= {
    // first line
    print("j\\i")
    
    
    // content
    for (j <- 0 until vector_length){
//      if
      
      
          for (i <- 0 until vector_length){
            print((math floor ptm(i)(j) * 100) / 100 +"\t")
          }      
      println()
    }
  }
  
//  def 

}