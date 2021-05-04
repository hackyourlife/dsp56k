package org.graalvm.vm.trcview.arch.dsp56k.io;

public class DSPCpuSmall64DeltaState extends DSPCpuState {
	private final long step;
	private final DSPCpuState last;
	private final int pc;
	private final int ictr;
	private final byte bit;
	private final long value;

	public DSPCpuSmall64DeltaState(long step, DSPCpuState last, int insn0, int insn1, int pc, int ictr, byte bit,
			long value) {
		super(insn0, insn1);
		this.step = step;
		this.last = last;
		this.pc = pc;
		this.ictr = ictr;
		this.bit = bit;
		this.value = value;
	}

	@Override
	public int getICTR() {
		return ictr;
	}

	@Override
	public long getX() {
		return (bit == DSPCpuDeltaState.TRACE_REG_X) ? value : last.getX();
	}

	@Override
	public long getY() {
		return (bit == DSPCpuDeltaState.TRACE_REG_Y) ? value : last.getY();
	}

	@Override
	public long getA() {
		return (bit == DSPCpuDeltaState.TRACE_REG_A) ? value : last.getA();
	}

	@Override
	public long getB() {
		return (bit == DSPCpuDeltaState.TRACE_REG_B) ? value : last.getB();
	}

	@Override
	public int getR(int reg) {
		return last.getR(reg);
	}

	@Override
	public int getN(int reg) {
		return last.getN(reg);
	}

	@Override
	public int getM(int reg) {
		return last.getM(reg);
	}

	@Override
	public int getSR() {
		return last.getSR();
	}

	@Override
	public int getOMR() {
		return last.getOMR();
	}

	@Override
	public int getLA() {
		return last.getLA();
	}

	@Override
	public int getLC() {
		return last.getLC();
	}

	@Override
	public int getSP() {
		return last.getSP();
	}

	@Override
	public int getSC() {
		return last.getSC();
	}

	@Override
	public int getSZ() {
		return last.getSZ();
	}

	@Override
	public int getVBA() {
		return last.getVBA();
	}

	@Override
	public int getEP() {
		return last.getEP();
	}

	@Override
	public long getPC() {
		return Integer.toUnsignedLong(pc);
	}

	@Override
	public long getStep() {
		return step;
	}
}
