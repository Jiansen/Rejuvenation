package uk.ac.ed.inf.lfcs.rejuvenation.example

import java.util.Date
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import java.io.File
import uk.ac.ed.inf.lfcs.rejuvenation.model.Simulator
import uk.ac.ed.inf.lfcs.rejuvenation.model.SimpleTerminalConfig

object Huang1995BCTest extends App{
  val workerB = new Worker(14 * 24 * 6, Huang1995B.failure_repair, Huang1995B.rejeneation_time, Huang1995B.cfd)
//  worker.report_ptm    
  val startStatesB = Array.ofDim[Double](workerB.full_period)
  startStatesB(0) = 1  

  println()  
  
  val simu = new Simulator(SimpleTerminalConfig)  
  simu.simulate(workerB, startStatesB, 12 *30 * 24 * 6, 0, Huang1995B.cost_down, Huang1995B.cost_reju)

  println()  

  val workerC = new Worker(14 * 24 * 6, Huang1995C.failure_repair, Huang1995C.rejeneation_time, Huang1995C.cfd)  
  val startStatesC = Array.ofDim[Double](workerC.full_period)
  startStatesC(0) = 1  

  println()  
  simu.simulate(workerC, startStatesC, 12 *30 * 24 * 6, 0, Huang1995C.cost_down, Huang1995C.cost_reju)
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
