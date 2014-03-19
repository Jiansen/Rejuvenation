package uk.ac.ed.inf.lfcs.rejuvenation.example

import java.util.Date
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import java.io.File
import uk.ac.ed.inf.lfcs.rejuvenation.model.Simulator
import uk.ac.ed.inf.lfcs.rejuvenation.model.SimpleTerminalConfig
import uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution.ConstantFailureDistribution

object Huang1995C{
  val MTBF = 3 * 30 * 24 * 6 // 3 months
  val failure_repair = 2 * 6  // 2 hours
  val base_longevity_interval = 10 * 24 * 6 // 10 days
  val rejeneation_time = 1 // 10 minutes
  
  val cost_down = 5000 / 6
  val cost_reju = 5 / 6
  
  val cfd  =   new ConstantFailureDistribution(base_longevity_interval, MTBF)   
  
}

object Huang1995CTest extends App{
  val worker = new Worker(14 * 24 * 6, Huang1995C.failure_repair, Huang1995C.rejeneation_time, Huang1995C.cfd)
//  worker.report_ptm  
  
  val startStates = Array.ofDim[Double](worker.full_period)
  startStates(0) = 1  

  println()
  val simu = new Simulator(SimpleTerminalConfig)
  simu.simulate(worker, startStates, 12 *30 * 24 * 6, 0, Huang1995C.cost_down, Huang1995C.cost_reju)
}

/*
 * 
 * 2* 7 * 24 * 6 = 2016
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
 * Hours of Downtime: 6.36 hours
 * $Cost of Downtime: $1110
 * 
 * 
 * Simulation Result:
 * total down time: 40.4324799081384 / 6 = 6.74 hours
 * total cost: $12678.700237815397
 * simulation elapse: 3705629 ms = 61.76 minutes
 * 
 * 
 */