package uk.ac.ed.inf.lfcs.rejuvenation.test

import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker


object TestPMF {
  
  val constantPMF: Int=>Double =  {
    case t => {
      if (0 < t && t< 30){ 1.0/30 }
      else{ 0 }
    }
  }
  
  
  
}


class TestWorker extends Worker(10, 3, 2, TestPMF.constantPMF)
