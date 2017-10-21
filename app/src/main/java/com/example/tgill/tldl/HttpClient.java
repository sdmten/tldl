package com.example.tgill.tldl;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public static int sendData (String base64Audio) throws IOException{
        HttpURLConnection connection = null;
        InputStream inputStream = null;
        try {
            Log.v("HTTP", "about to open connection");
            connection = (HttpURLConnection) (new URL("http://10.0.2.2:3000/sendAudio")).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.connect();

            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write("audioString=");
            writer.write(base64Audio);

            writer.flush();
            writer.close();
            os.close();
            return connection.getResponseCode();
        }
        catch (IOException e) {
            Log.v("Connecting", "Could not connect");
            throw e;
        }

    }
}
