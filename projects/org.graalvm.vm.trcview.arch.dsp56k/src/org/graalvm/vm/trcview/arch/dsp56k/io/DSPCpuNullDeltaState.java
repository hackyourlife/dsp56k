package org.graalvm.vm.trcview.arch.dsp56k.io;

public class DSPCpuNullDeltaState extends DSPCpuState {
	private final long step;
	private DSPCpuState state;
	private final int pc;
	private final int ictr;

	public DSPCpuNullDeltaState(DSPCpuState state, int insn0, int insn1, long step, int pc, int ictr) {
		super(insn0, insn1);
		this.state = state;
		this.step = step;
		this.pc = pc;
		this.ictr = ictr;
	}

	@Override
	public int getICTR() {
		return ictr;
	}

	@Override
	public long getX() {
		return state.getX();
	}

	@Override
	public long getY() {
		return state.getY();
	}

	@Override
	public long getA() {
		return state.getA();
	}

	@Override
	public long getB() {
		return state.getB();
	}

	@Override
	public int getR(int reg) {
		return state.getR(reg);
	}

	@Override
	public int getN(int reg) {
		return state.getN(reg);
	}

	@Override
	public int getM(int reg) {
		return state.getM(reg);
	}

	@Override
	public int getSR() {
		return state.getSR();
	}

	@Override
	public int getOMR() {
		return state.getOMR();
	}

	@Override
	public int getLA() {
		return state.getLA();
	}

	@Override
	public int getLC() {
		return state.getLC();
	}

	@Override
	public int getSP() {
		return state.getSP();
	}

	@Override
	public int getSC() {
		return state.getSC();
	}

	@Override
	public int getSZ() {
		return state.getSZ();
	}

	@Override
	public int getVBA() {
		return state.getVBA();
	}

	@Override
	public int getEP() {
		return state.getEP();
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
