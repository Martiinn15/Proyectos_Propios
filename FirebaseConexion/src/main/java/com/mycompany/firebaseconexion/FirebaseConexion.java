package com.mycompany.firebaseconexion;

import com.google.api.core.ApiFuture;
import java.io.FileInputStream;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Martin
 */
public class FirebaseConexion {

    static Firestore bd;

    public static void conectar() throws IOException {
        
        System.out.println("VA BIEN");
        FileInputStream serviceAccount
                = new FileInputStream("hornolatino.json");
        FirebaseOptions firebaseOptions = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();
        
        FirebaseApp.initializeApp(firebaseOptions);
        bd = FirestoreClient.getFirestore();
        System.out.println("se conectó");

    }

    public static void main(String[] args) {

        try {
            conectar();
        } catch (IOException e) {

        }
        java.awt.EventQueue.invokeLater(() -> {
            new JFrame().setVisible(true);
        });

    }

    public static void metemeDatos(String coleccion, String documento, Map<String, Object> json) {

        try {
            DocumentReference docRef = bd.collection(coleccion).document(documento);
            ApiFuture<WriteResult> resultado = docRef.set(json);
            System.out.println("tiempo de carga " + resultado.get().getUpdateTime());

        } catch (InterruptedException | ExecutionException e) {
            System.out.print("error en subida: " + e);
        }
    }

}