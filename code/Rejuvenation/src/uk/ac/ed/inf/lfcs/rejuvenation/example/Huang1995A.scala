package uk.ac.ed.inf.lfcs.rejuvenation.example

import java.util.Date
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import uk.ac.ed.inf.lfcs.rejuvenation.test.TestPMF

object Huang1995A {
  val MTBF = 12 * 30 * 24 * 6 // 12 months
  val failure_repair = 3  // 30 minutes
  val base_longevity_interval = 7 * 24 * 6 // 7 days
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
  val worker = new Worker(7 * 24 * 6, Huang1995A.failure_repair, Huang1995A.rejeneation_time, Huang1995A.constantPMF)
  worker.report_ptm
  
//  val startStates = Array.ofDim[Double](worker.full_period)
//  startStates(0) = 1  

//  println()
//  worker.simulate(startStates, 200)
  
}