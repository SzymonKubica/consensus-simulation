package org.simulation;

import lombok.Getter;

public class BallotNumber {
    public int leaderIndex;
    @Getter
    public int value;

    public BallotNumber(int leaderIndex, int value) {
        this.leaderIndex = leaderIndex;
        this.value = value;
    }
}
