package be.ww.kitchen.api.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import be.ww.shared.type.InstructionId;

public record AddInstructionCommand(
		@TargetAggregateIdentifier
		InstructionId instructionId

) {
}
