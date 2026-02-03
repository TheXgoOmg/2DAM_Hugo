package org.dam;

import com.mongodb.client.*;
import org.bson.Document;

public class Main {
    public static void main(String[] args) {
	conectorBasico();
    }

    public static void conectorBasico() {
	System.out.println("=== CONECTOR BÁSICO MONGODB ===");

	String connectionUri = "mongodb://root:root@localhost:27017/Cine1_V1?authSource=admin";

	try (MongoClient mongoClient = MongoClients.create(connectionUri)) {
	    System.out.println("Conexión establecida exitosamente");

	    MongoDatabase database = mongoClient.getDatabase("Cine1_V1");
	    System.out.println("Base de datos: " + database.getName());

	    MongoCollection<Document> collecion = database.getCollection("Peli");
	    System.out.println("Colección: " + collecion.getNamespace());
	    System.out.println("Documentos en colección: " + collecion.countDocuments());
	}
    }
}
