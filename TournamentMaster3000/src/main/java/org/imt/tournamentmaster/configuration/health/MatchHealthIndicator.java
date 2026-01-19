package org.imt.tournamentmaster.configuration.health;

import org.apache.commons.lang3.time.DateUtils;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Component
public class MatchHealthIndicator implements HealthIndicator {

    private MatchService matchService;

    public MatchHealthIndicator(MatchService matchService) {
        this.matchService = matchService;
    }

    @Override
    public @Nullable Health health() {
        List<Match> matches = matchService.getAll();

        if (matches == null || matches.isEmpty()) {
            return Health.down().withDetail("error", "No matches found").build();
        }

        final Date oneMonthBefore = DateUtils.addMonths(Date.from(Instant.now()), -1);
        final boolean noMatchPlayedSinceAMonth = matches.stream().noneMatch(match -> match.getDate().after(oneMonthBefore));
        if (noMatchPlayedSinceAMonth) {
            return Health.down().withDetail("error", "No match played since over a month").build();
        } else {
            return Health.up().withDetail("matchCount", matches.size()).build();
        }
    }
}
