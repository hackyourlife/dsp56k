package org.graalvm.vm.trcview.arch.dsp56k.io;

import org.graalvm.vm.trcview.arch.io.CpuState;
import org.graalvm.vm.util.HexFormatter;

public abstract class DSPCpuState extends DSPStepEvent implements CpuState {
	protected DSPCpuState(int insn0, int insn1) {
		super(insn0, insn1);
	}

	public abstract int getICTR();

	public abstract long getX();

	public abstract long getY();

	public abstract long getA();

	public abstract long getB();

	public abstract int getR(int reg);

	public abstract int getN(int reg);

	public abstract int getM(int reg);

	public abstract int getSR();

	public abstract int getOMR();

	public abstract int getLA();

	public abstract int getLC();

	public abstract int getSP();

	public abstract int getSC();

	public abstract int getSZ();

	public abstract int getVBA();

	public abstract int getEP();

	@Override
	public abstract long getPC();

	@Override
	public abstract long getStep();

	@Override
	public long get(String name) {
		switch(name) {
		case "pc":
			return getPC();
		case "x":
			return getX();
		case "y":
			return getY();
		case "a":
			return getA();
		case "b":
			return getB();
		case "r0":
			return getR(0);
		case "r1":
			return getR(1);
		case "r2":
			return getR(2);
		case "r3":
			return getR(3);
		case "r4":
			return getR(4);
		case "r5":
			return getR(5);
		case "r6":
			return getR(6);
		case "r7":
			return getR(7);
		case "n0":
			return getN(0);
		case "n1":
			return getN(1);
		case "n2":
			return getN(2);
		case "n3":
			return getN(3);
		case "n4":
			return getN(4);
		case "n5":
			return getN(5);
		case "n6":
			return getN(6);
		case "n7":
			return getN(7);
		case "m0":
			return getM(0);
		case "m1":
			return getM(1);
		case "m2":
			return getM(2);
		case "m3":
			return getM(3);
		case "m4":
			return getM(4);
		case "m5":
			return getM(5);
		case "m6":
			return getM(6);
		case "m7":
			return getM(7);
		case "sr":
			return getSR();
		case "omr":
			return getOMR();
		case "la":
			return getLA();
		case "lc":
			return getLC();
		case "sp":
			return getSP();
		case "sc":
			return getSC();
		case "sz":
			return getSZ();
		case "vba":
			return getVBA();
		case "ep":
			return getEP();
		case "ictr":
			return getICTR();
		default:
			throw new IllegalArgumentException("unknown register " + name);
		}
	}

	@Override
	public DSPCpuState getState() {
		return this;
	}

	@Override
	public String toString() {
		StringBuilder buf = new StringBuilder();
		buf.append("{{X}}S=  {{");
		buf.append(HexFormatter.tohex(getX(), 12));
		buf.append("}}x {{Y}}S=  {{");
		buf.append(HexFormatter.tohex(getY(), 12));
		buf.append("}}x\n");
		buf.append("{{A}}S={{");
		buf.append(HexFormatter.tohex(getA(), 14));
		buf.append("}}x {{B}}S={{");
		buf.append(HexFormatter.tohex(getB(), 14));
		buf.append("}}x\n");
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				int r = i * 2 + j;
				buf.append("{{R");
				buf.append((char) (r + '0'));
				buf.append("}}S={{");
				buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getR(r)), 6));
				if(j < 3) {
					buf.append("}}x ");
				}
			}
			buf.append("}}x\n");
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				int r = i * 2 + j;
				buf.append("{{N");
				buf.append((char) (r + '0'));
				buf.append("}}S={{");
				buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getN(r)), 6));
				if(j < 3) {
					buf.append("}}x ");
				}
			}
			buf.append("}}x\n");
		}
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 4; j++) {
				int r = i * 2 + j;
				buf.append("{{M");
				buf.append((char) (r + '0'));
				buf.append("}}S={{");
				buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getM(r)), 6));
				if(j < 3) {
					buf.append("}}x ");
				}
			}
			buf.append("}}x\n");
		}
		buf.append("{{SR}}S={{");
		buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getSR()), 6));
		buf.append("}}x {{OMR}}S={{");
		buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getOMR()), 6));
		buf.append("}}x {{LA}}S={{");
		buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getLA()), 6));
		buf.append("}}x {{LC}}S={{");
		buf.append(HexFormatter.tohex(Integer.toUnsignedLong(getLC()), 6));
		buf.append("}}x\n");
		return buf.toString();
	}
}
