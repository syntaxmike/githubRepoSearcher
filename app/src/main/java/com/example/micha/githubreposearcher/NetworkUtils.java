package com.example.micha.githubreposearcher;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


public class NetworkUtils {

    public static final String TAG = "NetworkUtils";

    public static final String GITHUB_BASE_URL =
            "https://api.github.com/search/repositories";

    public static final String PARA_QUERY = "q";

    public static final String PARAM_SORT = "sort";


    public static URL makeURL(String searchQuery, String sortBy){
        Uri uri = Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARA_QUERY, searchQuery)
                .appendQueryParameter(PARAM_SORT, sortBy).build();

        URL url = null;

        try {
            String urlString = uri.toString();
            Log.d(TAG, "Url: " + urlString);
            url = new URL(uri.toString());
        }catch(MalformedURLException e){
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();
            Scanner input = new Scanner(in);

            input.useDelimiter("\\A");
            return(input.hasNext())? input.next():null;

        } finally {
            urlConnection.disconnect();
        }
    }

}
