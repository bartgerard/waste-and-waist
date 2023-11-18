package be.ww.household.command.domain;

import be.ww.shared.type.MemberId;
import be.ww.shared.type.UserId;

import static org.apache.commons.lang3.Validate.notNull;

public record Member(
        MemberId memberId,
        UserId userId
) {
    public Member {
        notNull(memberId, "memberId is required");
    }

    public Member(MemberId memberId) {
        this(memberId, null); // Set UserId to null or a default value
    }
}
