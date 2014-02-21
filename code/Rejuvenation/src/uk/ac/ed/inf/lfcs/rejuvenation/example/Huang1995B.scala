package uk.ac.ed.inf.lfcs.rejuvenation.example

import java.util.Date
import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker

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

  println()
  worker.simulate(startStates, 12 * 30 * 24 * 6)  
}