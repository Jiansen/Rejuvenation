package uk.ac.ed.inf.lfcs.rejuvenation.example

import uk.ac.ed.inf.lfcs.rejuvenation.model.SimpleTerminalConfig
import uk.ac.ed.inf.lfcs.rejuvenation.model.Simulator
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution.UniformFailureDistribution
import uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution.ConstantFailureDistribution

object Huang1995A {
  val MTBF = 12 * 30 * 24 * 6 // 12 months
  val failure_repair = 3  // 30 minutes
  val base_longevity_interval = 7 * 24 * 6 // 7 days  = 1008 * 10 hour
  val rejeneation_time = 2 // 20 minutes
  
  val cost_down = 1000.0 / 6
  val cost_reju = 40.0 / 6 
  
  val fd = ConstantFailureDistribution(base_longevity_interval,  1.0/MTBF)
}

//object Huang1995ATest1 extends App{
//  // no rejuvenation
//  val worker = new Worker(2*Huang1995A.MTBF-Huang1995A.base_longevity_interval+1, Huang1995A.failure_repair, Huang1995A.rejeneation_time, Huang1995A.fd)
//  
//  val startStates = Array.ofDim[Double](worker.full_period)
//  startStates(0) = 1    
//
//  val simu = new Simulator(SimpleTerminalConfig);
//  simu.simulate(worker, startStates, 12 *30 * 24 * 6, 0, Huang1995A.cost_down, Huang1995A.cost_reju)
//}

object Huang1995ATest2 extends App{
//  once every 3 weeks
  val worker = new Worker(21 * 24 * 6, Huang1995A.failure_repair, Huang1995A.rejeneation_time, Huang1995A.fd)
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1    

  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 20* 12 *30 * 24 * 6, 0, Huang1995A.cost_down, Huang1995A.cost_reju)
}

object Huang1995ATest3 extends App{
//  once every two weeks
  val worker = new Worker(14 * 24 * 6, Huang1995A.failure_repair, Huang1995A.rejeneation_time, Huang1995A.fd)
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1    

  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 20* 12 *30 * 24 * 6, 0, Huang1995A.cost_down, Huang1995A.cost_reju)
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
