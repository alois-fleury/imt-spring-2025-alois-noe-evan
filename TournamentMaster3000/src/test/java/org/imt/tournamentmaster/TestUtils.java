package org.imt.tournamentmaster;

import org.imt.tournamentmaster.model.equipe.Equipe;
import org.imt.tournamentmaster.model.equipe.Joueur;
import org.imt.tournamentmaster.model.match.Match;
import org.imt.tournamentmaster.model.match.Round;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class TestUtils {

    public static Match generateMatch() {
        // create 6 joueurs
        Joueur joueur1 = new Joueur(1L, "Doe", "John", 1);
        Joueur joueur2 = new Joueur(2L, "Doe", "Jane", 2);
        Joueur joueur3 = new Joueur(3L, "Doe", "Jack", 3);
        Joueur joueur4 = new Joueur(4L, "Doe", "Jill", 4);
        Joueur joueur5 = new Joueur(5L, "Doe", "Jim", 5);
        Joueur joueur6 = new Joueur(6L, "Doe", "Jenny", 6);

        // create 2 equipes
        Equipe equipe1 = new Equipe(1L, "Equipe 1", List.of(joueur1, joueur2, joueur3));
        Equipe equipe2 = new Equipe(2L, "Equipe 2", List.of(joueur4, joueur5, joueur6));

        // create several rounds
        Round round1 = new Round(1L, 1, 2, 1);
        Round round2 = new Round(2L, 3, 4, 2);
        Round round3 = new Round(3L, 5, 6, 3);

        // linked list
        List<Round> rounds = new ArrayList<>();
        rounds.add(round1);
        rounds.add(round2);
        rounds.add(round3);

        // create a match
        return new Match(1L, equipe1, equipe2, rounds, Match.Status.TERMINE, new Date());
    }
}
