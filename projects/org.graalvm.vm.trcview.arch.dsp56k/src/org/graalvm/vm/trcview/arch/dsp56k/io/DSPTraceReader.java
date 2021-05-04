package org.graalvm.vm.trcview.arch.dsp56k.io;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

import org.graalvm.vm.trcview.arch.io.ArchTraceReader;
import org.graalvm.vm.trcview.arch.io.Event;
import org.graalvm.vm.util.HexFormatter;
import org.graalvm.vm.util.io.BEInputStream;
import org.graalvm.vm.util.io.WordInputStream;

public class DSPTraceReader extends ArchTraceReader {
	private static final int STEP_LIMIT = 5_000;

	private static final byte TRACE_STEP = 0;

	private WordInputStream in;

	private long step;
	private long steps;

	private DSPCpuState lastState = new DSPCpuZeroState();

	public DSPTraceReader(InputStream in) {
		this.in = new BEInputStream(in);
	}

	@Override
	public Event read() throws IOException {
		byte type;
		try {
			type = (byte) in.read8bit();
		} catch(EOFException e) {
			return null;
		}
		switch(type) {
		case TRACE_STEP:
			step++;
			steps++;
			lastState = DSPCpuDeltaState.deltaState(in, lastState, step);
			if(steps >= STEP_LIMIT) {
				lastState = new DSPCpuFullState(lastState);
				steps = 0;
			}
			return lastState;
		default:
			throw new IOException("unknown record: " + HexFormatter.tohex(type, 8) + " [position " +
					tell() + "]");
		}
	}

	@Override
	public long tell() {
		return in.tell();
	}
}
