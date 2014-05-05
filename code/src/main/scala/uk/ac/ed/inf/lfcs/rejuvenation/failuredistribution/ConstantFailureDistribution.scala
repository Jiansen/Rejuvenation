package uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution

case class ConstantFailureDistribution(val start:Int, val rate:Double) extends FailureDistribution{  
  def lambda(t:Int):Double = {
    if (start < t ){ 0}
    else{rate}
  }
}