package org.graalvm.vm.trcview.arch.dsp56k;

import java.io.InputStream;

import org.graalvm.vm.trcview.arch.Architecture;
import org.graalvm.vm.trcview.arch.dsp56k.io.DSPTraceReader;
import org.graalvm.vm.trcview.arch.io.ArchTraceReader;
import org.graalvm.vm.trcview.arch.io.StepFormat;
import org.graalvm.vm.trcview.decode.CallDecoder;
import org.graalvm.vm.trcview.decode.GenericCallDecoder;
import org.graalvm.vm.trcview.decode.GenericSyscallDecoder;
import org.graalvm.vm.trcview.decode.SyscallDecoder;

public class DSP56k extends Architecture {
	public static final short ID = (short) 0xDBEC;
	public static final StepFormat FORMAT = new StepFormat(StepFormat.NUMBERFMT_HEX, 8, 8, 1, false);

	private static final SyscallDecoder syscallDecoder = new GenericSyscallDecoder();
	private static final CallDecoder callDecoder = new GenericCallDecoder();

	@Override
	public short getId() {
		return ID;
	}

	@Override
	public String getName() {
		return "DSP56300";
	}

	@Override
	public String getDescription() {
		return "Motorola DSP56300";
	}

	@Override
	public ArchTraceReader getTraceReader(InputStream in) {
		return new DSPTraceReader(in);
	}

	@Override
	public SyscallDecoder getSyscallDecoder() {
		return syscallDecoder;
	}

	@Override
	public CallDecoder getCallDecoder() {
		return callDecoder;
	}

	@Override
	public int getTabSize() {
		return 10;
	}

	@Override
	public StepFormat getFormat() {
		return FORMAT;
	}

	@Override
	public boolean isSystemLevel() {
		return true;
	}

	@Override
	public boolean isStackedTraps() {
		return false;
	}

	@Override
	public boolean isTaggedState() {
		return true;
	}
}
