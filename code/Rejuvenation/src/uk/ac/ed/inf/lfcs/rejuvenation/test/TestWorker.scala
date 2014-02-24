package uk.ac.ed.inf.lfcs.rejuvenation.test

import uk.ac.ed.inf.lfcs.rejuvenation.model.Worker
import java.io.File


object TestPMF {
  
  val constantPMF: Int=>Double =  {
    case t => {
      if (0 < t && t< 30){ 1.0/30 }
      else{ 0 }
    }
  }  
}



object TestWorkder extends App{
  val worker = new Worker(30, 3, 2, TestPMF.constantPMF)
  val startStates = Array.ofDim[Double](30+3+2)
  startStates(0) = 1  
  worker.report_ptm
 
  println()
  
      var out:java.io.PrintStream = null
    try{
         out = new java.io.PrintStream(new File("output")) 

    }
//    var out = System.out   
  
  worker.simulate(startStates, 200, 0, 0, out)
}

/*
Observations:

the availiability reaches lowest value at t = full_period

0:	1.0	1.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0
1:	1.0	0.0	1.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0
2:	0.97	0.0	0.0	0.97	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.03	0.0	0.0
3:	0.93	0.0	0.0	0.0	0.93	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.0	0.03	0.03	0.0
...
28:	0.72	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.0	0.0	0.0	0.1	0.0	0.05	0.05	0.06	0.06	0.06
29:	0.71	0.05	0.05	0.05	0.05	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.0	0.0	0.0	0.07	0.06	0.05	0.06	0.06	0.06
30:	0.67	0.05	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.0	0.0	0.0	0.12	0.06	0.03	0.06	0.06
31:	0.7	0.06	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.0	0.0	0.06	0.12	0.03	0.03	0.06
32:	0.79	0.12	0.06	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.0	0.06	0.06	0.03	0.03	0.03
33:	0.81	0.06	0.12	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.0	0.03	0.06	0.03	0.03	0.03
...
198:	0.77	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.05	0.05	0.04	0.04	0.04
199:	0.77	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.05	0.05	0.04	0.04	0.04
200:	0.77	0.05	0.05	0.05	0.04	0.04	0.04	0.04	0.04	0.04	0.03	0.03	0.03	0.03	0.03	0.03	0.03	0.02	0.02	0.02	0.02	0.02	0.02	0.01	0.01	0.01	0.01	0.01	0.01	0.0	0.0	0.05	0.05	0.04	0.04	0.04


  */
