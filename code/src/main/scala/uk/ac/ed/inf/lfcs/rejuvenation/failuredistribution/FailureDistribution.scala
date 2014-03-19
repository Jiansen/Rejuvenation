package uk.ac.ed.inf.lfcs.rejuvenation.failuredistribution

abstract class FailureDistribution {
  
  // reliability of running for t time
  def reliability(t:Int):Double 
  
  // failure rate at time t
  def lambda(t:Int):Double  
}