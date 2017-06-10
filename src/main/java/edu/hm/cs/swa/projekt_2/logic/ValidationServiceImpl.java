package edu.hm.cs.swa.projekt_2.logic;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import edu.hm.Configuration;
import edu.hm.cs.swa.projekt_2.datamodel.Token;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ValidationServiceImpl implements ValidationService {

    private static final Logger LOGGER = LogManager.getLogger(ValidationServiceImpl.class);

    public static ValidationServiceImpl INSTANCE = new ValidationServiceImpl();

    public ValidationResult validateToken(String token, AuthorizationIDEnum authorizationID) {
        LOGGER.info("validate token: " + token + " with authId: " + authorizationID);

        if (token == null || token.length() != 12) {
            LOGGER.info("token invalide");
            return ValidationResult.AUTHORIZATION_MISSING;
        }

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://" + Configuration.AUTH_SERVER_URL + ":" + Configuration.AUTH_SERVER_PORT + "/shareit/validate/token")
                    .queryString("tokenID", token)
                    .queryString("authID", authorizationID.getAuthorizationID())
                    .asJson();

            JSONObject jsonObj = jsonResponse.getBody().getObject();
            int code = jsonObj.getInt("code");


            ValidationResult myObject;
            if (code == 200) {
                myObject = ValidationResult.AUTHORIZATION_OK;
            } else if (code == 403) {
                myObject = ValidationResult.AUTHORIZATION_MISSING;
            } else {
                myObject = ValidationResult.TOKEN_INVALIDE;
            }

            LOGGER.info("result: " + myObject.toString());
            return myObject;

        } catch (UnirestException e) {
            LOGGER.error("Fehler beim Holen des Tokens", e);
        }

        return null;
    }

    public Token getToken(String username, String password) {

        try {
            HttpResponse<JsonNode> jsonResponse = Unirest.get("http://" + Configuration.AUTH_SERVER_URL + ":" + Configuration.AUTH_SERVER_PORT + "/shareit/auth/user")
                    .queryString("username", username)
                    .queryString("password", password)
                    .asJson();


            JSONObject jsonObj = jsonResponse.getBody().getObject();

            Token token = new Token(jsonObj.getString("id"),
                    null,
                    null);

            return token;

        } catch (UnirestException e) {
            LOGGER.error("Fehler beim Holen des Tokens!", e);
        }


        return null;


    }
}
