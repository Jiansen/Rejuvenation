package uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution

abstract class FailureDistribution {  
  // failure rate at time t
  def lambda(t:Int):Double  
}