package org.imt.tournamentmaster.configuration.health;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.service.match.MatchService;
import org.jspecify.annotations.Nullable;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.stereotype.Component;

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
        } else {
            return Health.up().withDetail("matchCount", matches.size()).build();
        }
    }
}
