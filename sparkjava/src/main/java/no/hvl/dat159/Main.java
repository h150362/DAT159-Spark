package no.hvl.dat159;

import static spark.Spark.*;

import java.io.IOException;

import org.eclipse.jetty.io.ssl.ALPNProcessor.Client;

import com.google.gson.*;

public class Main {
	
	static Temperature temp = null;
    static RoomState state = null;
	public static void main(String[] args) throws InterruptedException, IOException {

 
    	temp = new Temperature();
    	state = new RoomState();
    	
    	
    	
   
    	port(8080);
    	after((req, res) -> {
    		  res.type("application/json");
    		});
    	
       get("/tempsensor/current", (req, res) -> tempToJson());
    	  
       
       put("/tempsensor/current", (req,res) -> {
        
        	Gson gson = new Gson();
        	temp = gson.fromJson(req.body(), Temperature.class);
            return tempToJson();
        });
       
        get("/state/current", (req, res) -> stateToJson());
        
        
        put("/state/current", (req,res) -> {
        	Gson gson = new Gson();
        	state = gson.fromJson(req.body(), RoomState.class);
            
            return stateToJson();
        	
        });
   
    }
    
    static String tempToJson () {
    	
    	Gson gson = new Gson();
    	    
    	String jsonInString = gson.toJson(temp);
    	
    	return jsonInString;
    }
    
static String stateToJson () {
    	
    	Gson gson = new Gson();
    	
    	String jsonInString = gson.toJson(state);
    	
    	return jsonInString;
    }
}
