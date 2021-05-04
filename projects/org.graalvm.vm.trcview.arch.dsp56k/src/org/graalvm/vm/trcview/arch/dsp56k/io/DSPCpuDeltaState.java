package org.graalvm.vm.trcview.arch.dsp56k.io;

import java.io.IOException;

import org.graalvm.vm.util.BitTest;
import org.graalvm.vm.util.io.Endianess;
import org.graalvm.vm.util.io.WordInputStream;

public class DSPCpuDeltaState extends DSPCpuState {
	public static final byte TRACE_REG_X = 0;
	public static final byte TRACE_REG_Y = 1;
	public static final byte TRACE_REG_A = 2;
	public static final byte TRACE_REG_B = 3;
	public static final byte TRACE_REG_SR = 4;
	public static final byte TRACE_REG_OMR = 5;
	public static final byte TRACE_REG_LA = 6;
	public static final byte TRACE_REG_LC = 7;
	public static final byte TRACE_REG_SP = 8;
	public static final byte TRACE_REG_EP = 9;
	public static final byte TRACE_REG_SZ = 10;
	public static final byte TRACE_REG_SC = 11;
	public static final byte TRACE_REG_VBA = 12;
	public static final byte TRACE_REG_BCR = 13;
	public static final byte TRACE_REG_DCR = 14;
	public static final byte TRACE_REG_AAR0 = 15;
	public static final byte TRACE_REG_AAR1 = 16;
	public static final byte TRACE_REG_AAR2 = 17;
	public static final byte TRACE_REG_AAR3 = 18;
	public static final byte TRACE_REG_RBASE = 20;
	public static final byte TRACE_REG_NBASE = 28;
	public static final byte TRACE_REG_MBASE = 36;
	public static final byte TRACE_REG_SSBASE = 44;

	private final long step;
	private final DSPCpuState last;
	private final int pc;
	private final int ictr;
	private final long mask;
	private final byte[] data;

	protected DSPCpuDeltaState(long step, DSPCpuState last, int insn0, int insn1, int pc, int ictr, long mask,
			byte[] data) {
		super(insn0, insn1);
		this.step = step;
		this.last = last;
		this.pc = pc;
		this.ictr = ictr;
		this.mask = mask;
		this.data = data;
	}

	private static int getOffset(long mask, int bit) {
		int size = 0;
		if(bit == TRACE_REG_X) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_X)) {
			size += 6;
		}

		if(bit == TRACE_REG_Y) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_Y)) {
			size += 6;
		}

		if(bit == TRACE_REG_A) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_A)) {
			size += 7;
		}

		if(bit == TRACE_REG_B) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_B)) {
			size += 7;
		}

		if(bit == TRACE_REG_SR) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_SR)) {
			size += 3;
		}

		if(bit == TRACE_REG_OMR) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_OMR)) {
			size += 3;
		}

		if(bit == TRACE_REG_LA) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_LA)) {
			size += 3;
		}

		if(bit == TRACE_REG_LC) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_LC)) {
			size += 3;
		}

		if(bit == TRACE_REG_SP) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_SP)) {
			size += 3;
		}

		if(bit == TRACE_REG_SC) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_SC)) {
			size++;
		}

		if(bit == TRACE_REG_SZ) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_SZ)) {
			size += 3;
		}

		if(bit == TRACE_REG_VBA) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_VBA)) {
			size += 3;
		}

		if(bit == TRACE_REG_EP) {
			return size;
		} else if(BitTest.testBit(mask, TRACE_REG_EP)) {
			size += 3;
		}

		for(int i = 0; i < 8; i++) {
			if(bit == TRACE_REG_RBASE + i) {
				return size;
			} else if(BitTest.testBit(mask, TRACE_REG_RBASE + i)) {
				size += 3;
			}
		}

		for(int i = 0; i < 8; i++) {
			if(bit == TRACE_REG_NBASE + i) {
				return size;
			} else if(BitTest.testBit(mask, TRACE_REG_NBASE + i)) {
				size += 3;
			}
		}

		for(int i = 0; i < 8; i++) {
			if(bit == TRACE_REG_MBASE + i) {
				return size;
			} else if(BitTest.testBit(mask, TRACE_REG_MBASE + i)) {
				size += 3;
			}
		}

		return size;
	}

	public static DSPCpuState deltaState(WordInputStream in, DSPCpuState last, long step) throws IOException {
		int pc = in.read24bit();
		int insn0 = in.read24bit();
		int insn1 = in.read24bit();
		int ictr = in.read24bit();
		long mask = in.read64bit();
		int size = getOffset(mask, -1);
		if(mask == 0) {
			return new DSPCpuNullDeltaState(last, insn0, insn1, step, pc, ictr);
		} else {
			int count = Long.bitCount(mask);
			byte[] data = new byte[size];
			in.read(data);
			if(count == 1) {
				byte bit = (byte) Long.numberOfTrailingZeros(mask);
				if(size == 1) {
					return new DSPCpuSmall32DeltaState(step, last, insn0, insn1, pc, ictr, bit,
							Byte.toUnsignedInt(data[0]));
				} else if(size == 3) {
					return new DSPCpuSmall32DeltaState(step, last, insn0, insn1, pc, ictr, bit,
							Endianess.get24bitBE(data));
				} else if(size == 6) {
					return new DSPCpuSmall64DeltaState(step, last, insn0, insn1, pc, ictr, bit,
							Endianess.get48bitBE(data));
				} else if(size == 7) {
					return new DSPCpuSmall64DeltaState(step, last, insn0, insn1, pc, ictr, bit,
							Endianess.get48bitBE(data));
				} else {
					throw new IOException("Invalid size: " + size);
				}
			} else {
				return new DSPCpuDeltaState(step, last, insn0, insn1, pc, ictr, mask, data);
			}
		}
	}

	@Override
	public long getStep() {
		return step;
	}

	@Override
	public long getPC() {
		return Integer.toUnsignedLong(pc);
	}

	@Override
	public int getICTR() {
		return ictr;
	}

	@Override
	public long getX() {
		if(BitTest.testBit(mask, TRACE_REG_X)) {
			int offset = getOffset(mask, TRACE_REG_X);
			return Endianess.get48bitBE(data, offset);
		} else {
			return last.getX();
		}
	}

	@Override
	public long getY() {
		if(BitTest.testBit(mask, TRACE_REG_Y)) {
			int offset = getOffset(mask, TRACE_REG_Y);
			return Endianess.get48bitBE(data, offset);
		} else {
			return last.getY();
		}
	}

	@Override
	public long getA() {
		if(BitTest.testBit(mask, TRACE_REG_A)) {
			int offset = getOffset(mask, TRACE_REG_A);
			return Endianess.get56bitBE(data, offset);
		} else {
			return last.getA();
		}
	}

	@Override
	public long getB() {
		if(BitTest.testBit(mask, TRACE_REG_B)) {
			int offset = getOffset(mask, TRACE_REG_B);
			return Endianess.get56bitBE(data, offset);
		} else {
			return last.getB();
		}
	}

	@Override
	public int getR(int reg) {
		if(BitTest.testBit(mask, TRACE_REG_RBASE + reg)) {
			int offset = getOffset(mask, TRACE_REG_RBASE + reg);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getR(reg);
		}
	}

	@Override
	public int getN(int reg) {
		if(BitTest.testBit(mask, TRACE_REG_NBASE + reg)) {
			int offset = getOffset(mask, TRACE_REG_NBASE + reg);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getN(reg);
		}
	}

	@Override
	public int getM(int reg) {
		if(BitTest.testBit(mask, TRACE_REG_MBASE + reg)) {
			int offset = getOffset(mask, TRACE_REG_MBASE + reg);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getM(reg);
		}
	}

	@Override
	public int getSR() {
		if(BitTest.testBit(mask, TRACE_REG_SR)) {
			int offset = getOffset(mask, TRACE_REG_SR);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getSR();
		}
	}

	@Override
	public int getOMR() {
		if(BitTest.testBit(mask, TRACE_REG_OMR)) {
			int offset = getOffset(mask, TRACE_REG_OMR);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getOMR();
		}
	}

	@Override
	public int getLA() {
		if(BitTest.testBit(mask, TRACE_REG_LA)) {
			int offset = getOffset(mask, TRACE_REG_LA);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getLA();
		}
	}

	@Override
	public int getLC() {
		if(BitTest.testBit(mask, TRACE_REG_LC)) {
			int offset = getOffset(mask, TRACE_REG_LC);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getLC();
		}
	}

	@Override
	public int getSP() {
		if(BitTest.testBit(mask, TRACE_REG_SP)) {
			int offset = getOffset(mask, TRACE_REG_SP);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getSP();
		}
	}

	@Override
	public int getSC() {
		if(BitTest.testBit(mask, TRACE_REG_SC)) {
			int offset = getOffset(mask, TRACE_REG_SC);
			return Byte.toUnsignedInt(data[offset]);
		} else {
			return last.getSC();
		}
	}

	@Override
	public int getSZ() {
		if(BitTest.testBit(mask, TRACE_REG_SZ)) {
			int offset = getOffset(mask, TRACE_REG_SZ);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getSZ();
		}
	}

	@Override
	public int getVBA() {
		if(BitTest.testBit(mask, TRACE_REG_VBA)) {
			int offset = getOffset(mask, TRACE_REG_VBA);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getVBA();
		}
	}

	@Override
	public int getEP() {
		if(BitTest.testBit(mask, TRACE_REG_EP)) {
			int offset = getOffset(mask, TRACE_REG_EP);
			return Endianess.get24bitBE(data, offset);
		} else {
			return last.getEP();
		}
	}
}
