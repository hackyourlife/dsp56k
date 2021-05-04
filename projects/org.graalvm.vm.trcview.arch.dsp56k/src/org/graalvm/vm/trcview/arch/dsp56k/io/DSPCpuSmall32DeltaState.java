package org.graalvm.vm.trcview.arch.dsp56k.io;

public class DSPCpuSmall32DeltaState extends DSPCpuState {
	private final long step;
	private final DSPCpuState last;
	private final int pc;
	private final int ictr;
	private final byte bit;
	private final int value;

	public DSPCpuSmall32DeltaState(long step, DSPCpuState last, int insn0, int insn1, int pc, int ictr, byte bit,
			int value) {
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
		return last.getX();
	}

	@Override
	public long getY() {
		return last.getY();
	}

	@Override
	public long getA() {
		return last.getA();
	}

	@Override
	public long getB() {
		return last.getB();
	}

	@Override
	public int getR(int reg) {
		return (bit == DSPCpuDeltaState.TRACE_REG_RBASE + reg) ? value : last.getR(reg);
	}

	@Override
	public int getN(int reg) {
		return (bit == DSPCpuDeltaState.TRACE_REG_NBASE + reg) ? value : last.getN(reg);
	}

	@Override
	public int getM(int reg) {
		return (bit == DSPCpuDeltaState.TRACE_REG_MBASE + reg) ? value : last.getM(reg);
	}

	@Override
	public int getSR() {
		return (bit == DSPCpuDeltaState.TRACE_REG_SR) ? value : last.getSR();
	}

	@Override
	public int getOMR() {
		return (bit == DSPCpuDeltaState.TRACE_REG_OMR) ? value : last.getOMR();
	}

	@Override
	public int getLA() {
		return (bit == DSPCpuDeltaState.TRACE_REG_LA) ? value : last.getLA();
	}

	@Override
	public int getLC() {
		return (bit == DSPCpuDeltaState.TRACE_REG_LC) ? value : last.getLC();
	}

	@Override
	public int getSP() {
		return (bit == DSPCpuDeltaState.TRACE_REG_SP) ? value : last.getSP();
	}

	@Override
	public int getSC() {
		return (bit == DSPCpuDeltaState.TRACE_REG_SC) ? value : last.getSC();
	}

	@Override
	public int getSZ() {
		return (bit == DSPCpuDeltaState.TRACE_REG_SZ) ? value : last.getSZ();
	}

	@Override
	public int getVBA() {
		return (bit == DSPCpuDeltaState.TRACE_REG_VBA) ? value : last.getVBA();
	}

	@Override
	public int getEP() {
		return (bit == DSPCpuDeltaState.TRACE_REG_EP) ? value : last.getEP();
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
