package uk.ac.ed.inf.lfcs.rejuvenation.example

import uk.ac.ed.inf.lfcs.rejuvenation.model.SimpleTerminalConfig
import uk.ac.ed.inf.lfcs.rejuvenation.model.Simulator
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker

object Huang1995A {
  val MTBF = 12 * 30 * 24 * 6 // 12 months
  val failure_repair = 3  // 30 minutes
  val base_longevity_interval = 7 * 24 * 6 // 7 days  = 1008 * 10 hour
  val rejeneation_time = 2 // 20 minutes
  
  val cost_down = 1000 / 6
  val cost_reju = 40 / 6
  
  val constantPMF: Int=>Double =  {
    case t => {
      if (base_longevity_interval < t && t< MTBF){ 1.0/(MTBF - base_longevity_interval) }
      else{ 0 }
    }
  }  
  
}

object Huang1995ATest extends App{
  val worker = new Worker(14 * 24 * 6, Huang1995A.failure_repair, Huang1995A.rejeneation_time, Huang1995A.constantPMF)
//  worker.report_ptm
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  

  println()
  
//      var out:java.io.PrintStream = null
//    try{
//         out = new java.io.PrintStream(new File("output")) 
//
//    }
    var out = System.out   
  

  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 12 *30 * 24 * 6, 0, Huang1995A.cost_down, Huang1995A.cost_reju)
  // 51840 = 12 *30 * 24 * 6
  // expect 61 minutes to simulate
}


/*
 * The system is ultra-reliable.
 * 
 * 7 * 24 * 6 = 1008
 * 
 * 1006:	1.0	0.0	0.0	0.0	0.0 ...
 * 1007:	1.0	0.0	0.0	0.0	0.0 ...
 * 1008:	0.0	0.0	0.0	0.0	0.0 ...
 * 1009:	0.0	0.0	0.0	0.0	0.0 ...
 * 1010:	1.0	1.0	0.0	0.0	0.0 ...
 * 
 * 
 * That is, the probability that the system will fail with-in 7 days can be nearly neglect
 * 
 * It takes more than ?? hours (? years) when the system is considered stable.
 * 
 */


/*  L = 12 * 30 * 24 hours
 * when rejuvenation schedule is set to 14 days, i.e. once every two week
 * 
 * 
 * In paper:
 * 
 * Hours of Downtime: 8.727 hours
 * $Cost of Downtime: $586
 * 
 * 
 * Simulation Result:
 * total down time: 51.9070432052514 / 6 = 8.65 hours
 * total cost: $554.2763037327691
 * simulation elapse: 3754945 ms = 62.58 minutes
 * 
 * 
 * 17 / (12 * 30  * 24 ) = 0.00196759259
 * 
 * 
total down time: 0.07052644003777081
total cost: 7.203139754485349
simulation elapse: 124226
 */
