import circt.stage.ChiselStage
import chisel3._
import chisel3.util._
import chisel3.experimental._

// Large reset fan-out. Retiming needed for reset and
// multiplication.
class RetimeFanout extends Module {
  val io = IO(new Bundle {
    val in = Input(Vec(2, UInt(16.W)))
    val out = Output(UInt(16.W))
  })

  io.out := ShiftRegister(io.in(0) * io.in(1), 3, 0.U, true.B)
}

object GenerateRetimeFanout extends App {
  val (chiselArgs, tail) = args.span(_ != "--")
  val firtoolArgs = tail.drop(1)
  println(s"Chisel args: ${chiselArgs.mkString(",")}")
  println(s"Firtool args: ${firtoolArgs.mkString(",")}")

  ChiselStage.emitSystemVerilog(
    new RetimeFanout(),
    chiselArgs,
    firtoolArgs
  )
}
