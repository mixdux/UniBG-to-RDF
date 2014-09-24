/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.fon.is.bgunirdf;

import com.google.gdata.data.spreadsheet.*;
import rs.fon.is.bgunirdf.util.Connection;
import rs.fon.is.bgunirdf.logic.ParseJob;

/**
 *
 * @author Milan
 */
public class Parser {

    public static void main(String[] args) {

        SpreadsheetEntry spreadsheet = Connection.getTheSpreadsheet();
        if (spreadsheet == null) {
            return;
        }

        System.out.println(spreadsheet.getTitle().getPlainText()+"\n");
        
        ParseJob pJob = new ParseJob();
        pJob.parse(spreadsheet);
        
    }
    
}
