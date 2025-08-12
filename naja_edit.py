# Load merged.lib and 1_synth.v, printa report, then
# write it out again.
from najaeda import netlist
import sys

print("Args:", sys.argv)

liberty_files = sys.argv[1]
print("Loading liberty files:", liberty_files)
netlist.load_liberty(liberty_files)
print("Loading netlist from:", sys.argv[3:])
top = netlist.load_verilog(sys.argv[3:])
print("Dumping netlist to:", sys.argv[2])
top.dump_verilog(sys.argv[2])
