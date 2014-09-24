/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf.util;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Milan
 */
public class Connection {

    public static SpreadsheetService service;

    public static SpreadsheetEntry getTheSpreadsheet() {

        SpreadsheetEntry spreadsheet = new SpreadsheetEntry();
        try {
            service = new SpreadsheetService("BGUNIRDF");
            Credential cred = getCredentials();
            service.setOAuth2Credentials(cred);
            //URL url = FeedURLFactory.getDefault().getWorksheetFeedUrl(Constants.SPREADSHEET_KEY, "private", "full");
            //URL url = new URL("http://spreadsheets.google.com/feeds/spreadsheets/" + Constants.SPREADSHEET_KEY);
            URL url = new URL(Constants.SPREADSHEET_FEED);
            SpreadsheetFeed feed = service.getFeed(url,
                    SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();
            for (SpreadsheetEntry spsht : spreadsheets) {
                if (spsht.getTitle().getPlainText().equals(Constants.SPREADSHEET_NAME)) {
                    spreadsheet = spsht;
                    break;
                }
            }
        } catch (AuthenticationException ex) {
            System.out.println("Could not get the spreadsheet");
            System.out.println(ex.getMessage());
            return null;
        } catch (MalformedURLException ex) {
            System.out.println("Could not get the spreadsheet");
            System.out.println(ex.getMessage());
            return null;
        } catch (IOException ex) {
            System.out.println("Could not get the spreadsheet");
            System.out.println(ex.getMessage());
            return null;
        } catch (ServiceException ex) {
            System.out.println("Could not get the spreadsheet");
            System.out.println(ex.getMessage());
            return null;
        }
        return spreadsheet;
    }

    private static Credential getCredentials() throws IOException {
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        List<String> scopes = Arrays.asList(Constants.SCOPE);
        // Step 1: Authorize -->
        String authorizationUrl
                = new GoogleAuthorizationCodeRequestUrl(Constants.CLIENT_ID, Constants.REDIRECT_URI, scopes).build();
        // Point or redirect your user to the authorizationUrl.
        System.out.println("Go to the following link in your browser:");
        System.out.println(authorizationUrl);
        // Read the authorization code from the standard input stream.
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("What is the authorization code?");
        String code = in.readLine();
        // End of Step 1 <--
        // Step 2: Exchange -->
        GoogleTokenResponse response
                = new GoogleAuthorizationCodeTokenRequest(transport, jsonFactory, Constants.CLIENT_ID, Constants.CLIENT_SECRET,
                        code, Constants.REDIRECT_URI).execute();
        // End of Step 2 <--
        // Build a new GoogleCredential instance and return it.
        return new GoogleCredential.Builder().setClientSecrets(Constants.CLIENT_ID, Constants.CLIENT_SECRET)
                .setJsonFactory(jsonFactory).setTransport(transport).build()
                .setAccessToken(response.getAccessToken()).setRefreshToken(response.getRefreshToken());
    }

}
