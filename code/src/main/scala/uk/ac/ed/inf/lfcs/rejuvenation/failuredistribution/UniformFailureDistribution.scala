package uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution

case class UniformFailureDistribution(val start:Int, val end:Int) extends FailureDistribution{

  // accumulate failure rate bwteen t-1 to t
  private def pmf(t:Int): Double =  {
    // TODO: check end > start
    if (start < t && t< end){ 1.0/(end - start) }
    else{0}
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