package uk.ac.ed.inf.lfcs.rejuvenation.example

import uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution.UniformFailureDistribution
import uk.ac.ed.inf.lfcs.rejuvenation.model.SimpleTerminalConfig
import uk.ac.ed.inf.lfcs.rejuvenation.model.Simulator
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker

object Huang1995B{
  val MTBF = 3 * 30 * 24 * 6 // 3 months
  val failure_repair = 3  // 30 minutes
  val base_longevity_interval = 3 * 24 * 6 // 3 days
  val rejeneation_time = 1 // 10 minutes
  
  val cost_down = 5000 / 6
  val cost_reju = 5 / 6
  
  val fd  =   new UniformFailureDistribution(base_longevity_interval,  2*MTBF-base_longevity_interval)     
}

object Huang1995BTest1 extends App{
  // no rejuvenation  
  val worker = new Worker(2*Huang1995B.MTBF-Huang1995B.base_longevity_interval+1, Huang1995B.failure_repair, Huang1995B.rejeneation_time, Huang1995B.fd)
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  
  
  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 12*30  * 24 * 6, 0, Huang1995B.cost_down, Huang1995B.cost_reju)
}

object Huang1995BTest2 extends App{
  // once every 2 weeks 
  val worker = new Worker(14 * 24 * 6, Huang1995B.failure_repair, Huang1995B.rejeneation_time, Huang1995B.fd)
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  
  
  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 12*30  * 24 * 6, 0, Huang1995B.cost_down, Huang1995B.cost_reju)
}

object Huang1995BTest3 extends App{
  // once a week
  val worker = new Worker(7 * 24 * 6, Huang1995B.failure_repair, Huang1995B.rejeneation_time, Huang1995B.fd)
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  
  
  val simu = new Simulator(SimpleTerminalConfig);
  simu.simulate(worker, startStates, 12*30  * 24 * 6, 0, Huang1995B.cost_down, Huang1995B.cost_reju)
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


/*  L = 12 * 30 * 24 hours
 * when rejuvenation schedule is set to 14 days, i.e. once every two week
 * 
 * 
 * In paper:
 * 
 * Hours of Downtime: 5.70 hours
 * $Cost of Downtime: $7672.43
 * 
 * 
 * Simulation Result:
 * total down time: 36.74078490169142 / 6 = 6.12 hours
 * total cost: $8492.18471502917
 * simulation elapse: 3642367 ms = 60.7 minutes
 * 
 * 
 */
