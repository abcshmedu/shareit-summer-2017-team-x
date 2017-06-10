package edu.hm.cs.swa.projekt_2.logic;

/**
 * Created by christian on 10.06.17.
 */
public interface ValidationService {
    ValidationResult validateToken(String token, AuthorizationIDEnum authorizationID);

}
