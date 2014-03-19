package uk.ac.ed.inf.lfcs.rejuvenation.model

import java.util.Date

class Simulator(conf:SimulationConfig) {

  def simulate(worker:Worker, initState:Array[Double], time:Int, reve_work:Double, cost_down:Double, cost_reju:Double):Unit = {    
    val out = conf.out
    
    var accu_down_time = 0.0
    var accu_reju_time = 0.0
    var accu_cost = 0.0
    var accu_revenue = 0.0
    
    val sig = 10^conf.significance;
  
    if(initState.length == worker.full_period){
      var s_t = initState
      
      // t, availability, accumulated cost, accumulated revenue, accumulated profit, accumulated downtime, accumulated rejuvenation time , status*
      out.print("time\tavailability\taccumulated cost\taccumulated revenue\taccumulated profit\taccumulated downtime\taccumulated rejuvenation time\t")
      if(conf.print_states){
      for (i <- 0 until worker.full_period){
        if (worker.w_0 <= i && i < worker.r_0){ out.print("\tW"+(i-worker.w_0)) }
        else if ( worker.r_0 <= i && i < worker.f_0 ) { out.print("\tR"+(i-worker.r_0)) }
        else if ( worker.f_0 <= i && i < worker.full_period ) { out.print("\tF"+(i-worker.f_0)) }
      }            
      }

      out.println()      
      
      
      val start = new Date()
      for (t <- 0 to time){
        out.print(t+":")
//        if(t % 10000 == 0){         out.println(t+":") }

        // probability of liveness
        var availibility = 0.0
        for (i <- 0 until worker.r_0){
          availibility+=s_t(i)
        }
        out.print("\t"+(math round availibility * sig)*1.0 / sig)
    
        
        // expected downtime and profit
        var cost = 0.0
        for (i <- worker.r_0 until worker.f_0){
          accu_reju_time += s_t(i) // * 1
          accu_cost+= s_t(i) * cost_reju
        }
        for (i <- worker.f_0 until worker.full_period){
          accu_down_time += s_t(i) // * 1
          accu_cost+= s_t(i) * cost_down
        }
               
        out.print("\t"+(math round accu_cost * sig)*1.0 / sig)   

        var revenue = 0.0
        for (i <- worker.w_0 until worker.r_0){
          revenue += s_t(i) * reve_work
        }
        accu_revenue += revenue
        
        out.print("\t"+(math round accu_revenue * sig)*1.0 / sig)           
        out.print("\t"+(math round (accu_revenue - accu_cost) * sig * 1.0 ) / sig)           
        out.print("\t"+(math round (accu_revenue - accu_cost) * sig * 1.0) / sig)
        
        out.print("\t"+(math round (accu_down_time * sig*1.0) / sig))          
        out.print("\t"+(math round (accu_reju_time * sig*1.0) / sig))
        
        
        if(conf.print_states){
          for (i <- 0 until worker.full_period){
            out.print("\t"+(math round (s_t(i) * sig*1.0) / sig))
          }
        }
        out.println                  
        s_t = worker.run1step(s_t)        
      }
              
      val end = new Date()  
        
      if(conf.print_elapse){
        out.println("simulation elapse: "+ (end.getTime() - start.getTime()) + " ms" );            
      }
    }else{
      out.println("full period("+worker.full_period+") is not equal to initial status length ("+initState.length+")");
    }    
  }  
  
}