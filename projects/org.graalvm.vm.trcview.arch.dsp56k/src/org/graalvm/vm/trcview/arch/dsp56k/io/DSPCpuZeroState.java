package org.graalvm.vm.trcview.arch.dsp56k.io;

public class DSPCpuZeroState extends DSPCpuState {
	public DSPCpuZeroState() {
		super(0, 0);
	}

	@Override
	public long getX() {
		return 0;
	}

	@Override
	public long getY() {
		return 0;
	}

	@Override
	public long getA() {
		return 0;
	}

	@Override
	public long getB() {
		return 0;
	}

	@Override
	public int getR(int reg) {
		return 0;
	}

	@Override
	public int getN(int reg) {
		return 0;
	}

	@Override
	public int getM(int reg) {
		return 0;
	}

	@Override
	public int getSR() {
		return 0;
	}

	@Override
	public int getOMR() {
		return 0;
	}

	@Override
	public int getLA() {
		return 0;
	}

	@Override
	public int getLC() {
		return 0;
	}

	@Override
	public int getSP() {
		return 0;
	}

	@Override
	public int getSC() {
		return 0;
	}

	@Override
	public int getSZ() {
		return 0;
	}

	@Override
	public int getVBA() {
		return 0;
	}

	@Override
	public int getEP() {
		return 0;
	}

	@Override
	public int getICTR() {
		return 0;
	}

	@Override
	public long getPC() {
		return 0;
	}

	@Override
	public long getStep() {
		return 0;
	}
}
