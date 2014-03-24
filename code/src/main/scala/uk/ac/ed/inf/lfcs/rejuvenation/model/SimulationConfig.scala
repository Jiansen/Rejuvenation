package uk.ac.ed.inf.lfcs.rejuvenation.model

import java.io.PrintStream
import java.io.File

class SimulationConfig(val out:PrintStream, val significance:Int, val print_states:Boolean, val print_elapse:Boolean)


object SimpleTerminalConfig extends SimulationConfig(System.out, 6, false, true)
case class SimpleFileConfig(filename:String) extends SimulationConfig(new java.io.PrintStream(new File(filename)), 6, false, true)