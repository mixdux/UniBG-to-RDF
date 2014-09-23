/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 *
 * @author Milan
 */
public class Connection {

    public static SpreadsheetService service;

    public static SpreadsheetEntry getTheSpreadsheet(){
        SpreadsheetEntry spreadsheet = new SpreadsheetEntry();
        try {
            service = new SpreadsheetService("MySpreadsheetIntegration-v1");
            
            service.setUserCredentials("milan.civilche@gmail.com", "milanidusan");
            
            // TODO: Authorize the service object for a specific user (see other sections)
            // Define the URL to request.  This should never change.
            URL SPREADSHEET_FEED_URL = new URL(
                    "https://spreadsheets.google.com/feeds/spreadsheets/private/full");
            
            // Make a request to the API and get all spreadsheets.
            SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL,
                    SpreadsheetFeed.class);
            List<SpreadsheetEntry> spreadsheets = feed.getEntries();
            
            if (spreadsheets.size() == 0) {
                // TODO: There were no spreadsheets, act accordingly.
            }
            
            // TODO: Choose a spreadsheet more intelligently based on your
            // app's needs.
            for (SpreadsheetEntry spsht : spreadsheets) {
                if (spsht.getTitle().getPlainText().equals("Podaci za RDF bazu v2.0")) {
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
}
