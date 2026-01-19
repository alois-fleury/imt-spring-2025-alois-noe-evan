package org.imt.tournamentmaster.service.match;

import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.repository.match.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Match> getById(long id) {
        return matchRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Match> getAll() {
        return StreamSupport.stream(matchRepository.findAll().spliterator(), false)
                .toList();
    }

    @Transactional
    public Match create(Match match) {
        return matchRepository.save(match);
    }

    @Transactional
    public Optional<Match> updateStatus(long id, Match.Status newStatus) {
        Optional<Match> matchOpt = matchRepository.findById(id);

        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            if (newStatus.ordinal() <= match.getStatus().ordinal()) {
                throw new IllegalArgumentException("Changement de statut invalide");
            }
            match.setStatus(newStatus);
            return Optional.of(matchRepository.save(match));
        }

        return Optional.empty();
    }

    @Transactional
    public void delete(long id) {
        matchRepository.deleteById(id);
    }
}
