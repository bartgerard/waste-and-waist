package be.ww.shared.type;

import java.util.UUID;

import be.ww.shared.domain.api.AggregateId;

public record InstructionId(String id
) implements AggregateId {
	public static InstructionId create() {
		return new InstructionId(
				"instruction-%s".formatted(UUID.randomUUID())
		);
	}

	public static InstructionId of(
			final String id
	) {
		return new InstructionId(id);
	}

	@Override
	public String toString() {
		return id;
	}
}
