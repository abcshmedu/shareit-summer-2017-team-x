package edu.hm.cs.swa.projekt_2.logic;

import org.json.JSONObject;

import com.fasterxml.jackson.core.json.JsonReadContext;
import com.fasterxml.jackson.databind.cfg.ConfigFeature;
import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import edu.hm.Configuration;
import edu.hm.cs.swa.projekt_2.datamodel.Token;

public class ValidationService {

	public static ValidationService INSTANCE = new ValidationService();
	
	public ValidationResult validateToken(Token token, AuthorizationIDEnum authorizationID){
		
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get("http://"+Configuration.AUTH_SERVER_URL+":"+Configuration.AUTH_SERVER_PORT+"/shareit/validate/token")
					  .queryString("tokenID", token)
					  .queryString("authID",authorizationID.getAuthorizationID())
					  .asJson();
			
			JSONObject jsonObj =  jsonResponse.getBody().getObject();
			String code = jsonObj.getString("code");
			
			
			ValidationResult myObject;
			if(code.equals("200")){
				myObject = ValidationResult.AUTHORIZATION_OK;
			}
			else{
				myObject = ValidationResult.AUTHORIZATION_MISSING;
			}
			
			
			return myObject;
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Token getToken(String username, String password){
		
		try {
			HttpResponse<JsonNode> jsonResponse = Unirest.get("http://"+Configuration.AUTH_SERVER_URL+":"+Configuration.AUTH_SERVER_PORT+"/shareit/auth/user")
					  .queryString("username", username)
					  .queryString("password",password)
					  .asJson();
			
		
			
			JSONObject jsonObj = jsonResponse.getBody().getObject();
			
			Token token = new Token(jsonObj.getString("id"),
									null,
									null);

			return token;
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
		
		
	}
}
