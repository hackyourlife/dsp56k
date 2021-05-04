package org.graalvm.vm.trcview.arch.dsp56k.io;

import org.graalvm.vm.trcview.arch.dsp56k.DSP56k;
import org.graalvm.vm.trcview.arch.io.InstructionType;
import org.graalvm.vm.trcview.arch.io.StepEvent;
import org.graalvm.vm.trcview.arch.io.StepFormat;

public abstract class DSPStepEvent extends StepEvent {
	private final int insn0;
	private final int insn1;

	protected DSPStepEvent(int insn0, int insn1) {
		super(0);
		this.insn0 = insn0;
		this.insn1 = insn1;
	}

	public int getInstructionWord0() {
		return insn0;
	}

	public int getInstructionWord1() {
		return insn1;
	}

	@Override
	public byte[] getMachinecode() {
		// TODO: use disassembler to get length
		return new byte[] { (byte) (insn0 >> 16), (byte) (insn0 >> 8), (byte) insn0 };
	}

	@Override
	public String[] getDisassemblyComponents() {
		return new String[] { "; unknown" };
	}

	@Override
	public String getMnemonic() {
		String[] asm = getDisassemblyComponents();
		if(asm != null) {
			return asm[0];
		} else {
			return null;
		}
	}

	@Override
	public long getPC() {
		return getState().getPC();
	}

	@Override
	public InstructionType getType() {
		return InstructionType.OTHER;
	}

	@Override
	public long getStep() {
		return getState().getStep();
	}

	@Override
	public abstract DSPCpuState getState();

	@Override
	public StepFormat getFormat() {
		return DSP56k.FORMAT;
	}
}
