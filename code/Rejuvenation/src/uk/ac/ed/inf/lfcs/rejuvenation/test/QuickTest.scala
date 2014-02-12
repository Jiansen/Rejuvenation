package uk.ac.ed.inf.lfcs.rejuvenation.test

import java.util.Date

object QuickTest extends App{
//  System.out.println("Hello");
  
  val worker = new TestWorker
  
  worker.report_ptm
 
  // start simulation
  
  println()
  
  var s_t = worker.startStates
  
  val start = new Date()
  for (t <- 0 to 10000){
    print(t+":")
    
    for (i <- 0 until 15){
      print("\t"+(math round s_t(i) * 100) / 100.0 )
//        print("+"+s_t(i) )      
    }
    println
    s_t = worker.run1step(s_t)
  }
  val end = new Date()
  
  println("elapse: "+ (end.getTime() - start.getTime()) );  
  //  896 ms for 10000, with I/O, so 8.96% time
}