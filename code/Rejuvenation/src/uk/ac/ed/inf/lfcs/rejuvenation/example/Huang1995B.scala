package uk.ac.ed.inf.lfcs.rejuvenation.example

import java.util.Date
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import java.io.File

object Huang1995B{
  val MTBF = 3 * 30 * 24 * 6 // 3 months
  val failure_repair = 3  // 30 minutes
  val base_longevity_interval = 3 * 24 * 6 // 3 days
  val rejeneation_time = 1 // 10 minutes
  
  val cost_down = 5000 / 6
  val cost_reju = 5 / 6
  
  val constantPMF: Int=>Double =  {
    case t => {
      if (base_longevity_interval < t && t< MTBF){ 1.0/(MTBF - base_longevity_interval) }
      else{ 0 }
    }
  }    
  
}

object Huang1995BTest extends App{
  val worker = new Worker(7 * 24 * 6, Huang1995B.failure_repair, Huang1995B.rejeneation_time, Huang1995B.constantPMF)
//  worker.report_ptm  
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  
  
    var out:java.io.PrintStream = null
    try{
         out = new java.io.PrintStream(new File("output")) 

    }
//    var out = System.out     
  println()
  worker.simulate(startStates, 5 * 7 * 24 * 6 + 5, out)  
}

/*
 * 
 * 7 * 24 * 6 = 1008
 * 
 * 1006:	1.0		1.0E-4	1.0E-4	1.0E-4	1.0E-4 ...	1.0E-4	1.0E-4	1.0E-4	1.0E-4
 * 1007:	1.0		1.0E-4	1.0E-4	1.0E-4 ...	1.0E-4	1.0E-4	1.0E-4	1.0E-4
 * 1008:	0.05	1.0E-4	1.0E-4	1.0E-4 ...	0.9543	0.0	1.0E-4	1.0E-4
 * 1009:	1.0		0.9543	1.0E-4	1.0E-4 ...	1.0E-4	0.0	0.0	1.0E-4
 * 1010:	1.0		1.0E-4	0.9543	1.0E-4 ...	1.0E-4	0.0	0.0	0.0
 * 1011:	1.0		1.0E-4	1.0E-4	0.9543 ...	0.0	0.0	0.0	0.0
 * 1012:	1.0		0.0	1.0E-4	1.0E-4  0.9543   ...	0.0	0.0	0.0	0.0
 * 1013:	1.0		0.0	0.0	1.0E-4  1.0E-4 ...	0.0	0.0	0.0	0.0
 * ...
 * 5044:	0.21	3.0E-4	3.0E-4	3.0E-4	.... 0.7913	0.0	1.0E-4	1.0E-4
 * ...
 * 
 * The system only has 95% of chance to survive in the first period.
 * 
 * It takes more than ?? hours (? years) when the system is considered stable.
 */
