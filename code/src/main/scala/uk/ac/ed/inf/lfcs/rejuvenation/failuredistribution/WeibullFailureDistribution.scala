package uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution

case class WeibullFailureDistribution(val theta:Double, val beta:Double) extends FailureDistribution{

  // accumulate failure rate bwteen 0 to t
  private def cdf(t:Int): Double =  {
    if (t < 0) {0}
    else {
      1 - Math.exp(-(t/theta)*beta)
    }
  }     
  
  // accumulate failure rate bwteen t-1 to t
  private def pmf(t:Int): Double =  {
    cdf(t) - cdf(t-1)
  }   
    
  def reliability(t:Int):Double = {
    var sum = 0.0
    var i = 0;
    while(i<t){
      sum += pmf(i)
      i +=1
    }    
    return 1-sum
  }
  
  def lambda(t:Int):Double = {
    return pmf(t)/reliability(t)
  }

}