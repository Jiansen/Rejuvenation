package uk.ac.ed.inf.lfcs.rejuvenation.model

class Worker(reju_schedule:Int, recovery_time:Int, reju_time:Int, f:Int=>Double) {
  // reju_schedule: rejuvenation schedule, the maximum time in working status
  // recovery_time: recovery time, the time for recovery from unexpected failure, reju_time excluded
  // reju_time:  the time for rejuvenation process
  // f: Let f(t) be the probability mass function (pmf) which
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
      sum += f(i)
    }    
    return 1-sum
  }
  
  // failure rate at time t
  private def lambda(t:Int):Double = {
    return f(t)/reliability(t)
  }

  private val vector_length:Int = recovery_time + recovery_time + reju_time
  
  // probability transtion matrix
  private val ptm:Array[Array[Double]] = Array.ofDim[Double](vector_length, vector_length)
  for (i <- 0 until vector_length){
          for (j <- 0 until vector_length){
            // fake
            ptm(i)(j) = 0.1
          }      
    }
  
  
  def report_ptm:Unit= {
    for (i <- 0 until vector_length){
          for (j <- 0 until vector_length){
            System.out.print(ptm(i)(j)+"\t")
          }      
      System.out.println()
    }
  }
  

}