package org.imt.tournamentmaster.controller.compte;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@SecurityRequirement(name = "basicAuth")
@RequestMapping("/compte")
public class CompteController {

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/whoami")
    public ResponseEntity<String> whoAmI(Principal principal) {
        return ResponseEntity.ok(principal.getName());
    }

    @PreAuthorize("hasRole('ADMIN') && principal.username == 'admin'")
    @GetMapping("/secret")
    public ResponseEntity<String> secretAdminPage() {
        return ResponseEntity.ok("This is a secret admin page!");
    }
}
