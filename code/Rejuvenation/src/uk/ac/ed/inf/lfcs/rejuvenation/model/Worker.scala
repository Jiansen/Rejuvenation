package uk.ac.ed.inf.lfcs.rejuvenation.model

import java.io.PrintWriter
import java.util.Date
import java.io.IOException
import java.io.BufferedWriter
import java.io.FileWriter

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
  
  
  /*
   * x_i = 
   *         W_i           0 <= i < T
   *         R_(i-T)       T <= i < T + t_r
   *         F_(i-R-t_r)   T + t_r <=i < T + t_r + t_f
   */
  final val w_0 = 0
  val r_0 = w_0 + reju_schedule
  val f_0 =  r_0 + reju_time
  val full_period:Int = reju_schedule + recovery_time + reju_time
  
  
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

  // probability transtion matrix
  private val ptm:Array[Array[Double]] = Array.ofDim[Double](full_period, full_period)
  
  initPTM()
  private def initPTM(){
    for (i <- 0 until full_period){
          for (j <- 0 until full_period){
            if (j == i+1 && j<=r_0-1 ){
              // W_k -> W_(k+1)
              ptm(i)(j) = 1 - lambda(i)
            }            
            else if(j == f_0 && i < r_0-1){
              // W_k -> F_0
              ptm(i)(j) = lambda(i)              
            }else if( ( i == r_0-1 && j == r_0 )  ||  // end of work
                      ( j == i+1 &&  r_0 <= i && j <=  f_0 -1) || // during rejuvenation
                      ( i == f_0 -1 && j == w_0 )  || // end of rejuvenation
                      ( j == i+1 &&  f_0 <= i && j <=  full_period -1)  || // during repair
                      ( i == full_period -1 && j == r_0 )  // end of repair
                    ){
              ptm(i)(j) = 1
            }else {
              ptm(i)(j) = 0.0
            }            
//            println("("+i+","+j+") = "+ptm(i)(j))
          }      
    }    
  }

  
  def run1step(states:Array[Double]):Array[Double] = {    
    if (states.length == full_period){
      val result = Array.ofDim[Double](full_period)
      for (i <- 0 until full_period){
        var sum:Double = 0
        for (k <- 0 until full_period){
          sum += states(k) * ptm(k)(i)          
        }
        result(i) = sum
      }
      return result
    }else{
      // throw exception
      return null;
    }
  }
  
  
  
  
  def report_ptm:Unit= {
    // first line
    print("j\\i")
    for (i <- 0 until full_period){
      if (w_0 <= i && i < r_0){ print("\tW"+(i-w_0)) }
      else if ( r_0 <= i && i < f_0 ) { print("\tR"+(i-r_0)) }
      else if ( f_0 <= i && i < full_period ) { print("\tF"+(i-f_0)) }
    }    
    println()
    
    // content
    for (j <- 0 until full_period){
      if (w_0 <= j && j < r_0){ print("W"+(j-w_0)) }
      else if ( r_0 <= j && j < f_0 ) { print("R"+(j-r_0)) }
      else if ( f_0 <= j && j < full_period ) { print("F"+(j-f_0)) }
      
      for (i <- 0 until full_period){
        print("\t"+(math round ptm(i)(j) * 100) / 100.0 )
      }      
      println()
    }
  }
  
  
  def simulate(init:Array[Double], time:Int):Unit = {
//    var out:PrintWriter = null
//    try{
//         out = new PrintWriter(new BufferedWriter(new FileWriter("writePath", true))) 
//
//    }
    var out = System.out         
    
    if(init.length == this.full_period){
      var s_t = init
      val start = new Date()
      for (t <- 0 to time){
        out.print(t+":")
        
        var live_prop = 0.0
        for (i <- 0 until this.r_0){
          live_prop+=s_t(i)
        }
        out.print("\t"+(math round live_prop * 100) / 100.0)
    
        for (i <- 0 until this.full_period){
          out.print("\t"+(math round s_t(i) * 10000) / 10000.0 )
//        print("+"+s_t(i) )      
        }
        out.println
        s_t = this.run1step(s_t)
      }
      val end = new Date()  
      out.println("elapse: "+ (end.getTime() - start.getTime()) );  
    }else{
      out.println("full period("+this.full_period+") is not equal to status length ("+init.length+")");
    }    
  }
  
  
  
}