package org.gx.chronorun.service.contestant;

import java.io.IOException;
import java.io.Reader;

public interface ImportContestantUseCase {

    void importContestant(Reader contestantReader, String raceId) throws IOException;
}
