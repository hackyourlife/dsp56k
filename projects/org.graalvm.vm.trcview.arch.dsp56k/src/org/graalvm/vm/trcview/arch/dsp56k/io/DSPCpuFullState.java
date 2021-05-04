package org.graalvm.vm.trcview.arch.dsp56k.io;

public class DSPCpuFullState extends DSPCpuState {
	private final long step;
	private final long x;
	private final long y;
	private final long a;
	private final long b;
	private final int[] r = new int[8];
	private final int[] n = new int[8];
	private final int[] m = new int[8];
	private final int sr;
	private final int omr;
	private final int pc;
	private final int la;
	private final int lc;
	private final int sp;
	private final int sc;
	private final int sz;
	private final int vba;
	private final int ep;
	private final int ictr;

	public DSPCpuFullState(DSPCpuState state) {
		super(state.getInstructionWord0(), state.getInstructionWord1());
		step = state.getStep();
		x = state.getX();
		y = state.getY();
		a = state.getA();
		b = state.getB();
		for(int i = 0; i < 8; i++) {
			r[i] = state.getR(i);
			n[i] = state.getN(i);
			m[i] = state.getM(i);
		}
		sr = state.getSR();
		omr = state.getOMR();
		pc = (int) state.getPC();
		la = state.getLA();
		lc = state.getLC();
		sp = state.getSP();
		sc = state.getSC();
		sz = state.getSZ();
		vba = state.getVBA();
		ep = state.getVBA();
		ictr = state.getICTR();
	}

	@Override
	public long getX() {
		return x;
	}

	@Override
	public long getY() {
		return y;
	}

	@Override
	public long getA() {
		return a;
	}

	@Override
	public long getB() {
		return b;
	}

	@Override
	public int getR(int reg) {
		return r[reg];
	}

	@Override
	public int getN(int reg) {
		return n[reg];
	}

	@Override
	public int getM(int reg) {
		return m[reg];
	}

	@Override
	public int getSR() {
		return sr;
	}

	@Override
	public int getOMR() {
		return omr;
	}

	@Override
	public int getLA() {
		return la;
	}

	@Override
	public int getLC() {
		return lc;
	}

	@Override
	public int getSP() {
		return sp;
	}

	@Override
	public int getSC() {
		return sc;
	}

	@Override
	public int getSZ() {
		return sz;
	}

	@Override
	public int getVBA() {
		return vba;
	}

	@Override
	public int getEP() {
		return ep;
	}

	@Override
	public int getICTR() {
		return ictr;
	}

	@Override
	public long getPC() {
		return pc;
	}

	@Override
	public long getStep() {
		return step;
	}
}
