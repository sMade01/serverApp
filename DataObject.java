/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverApp;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sMade
 */
public class DataObject implements Serializable{
  
    private boolean isSendingData;    
    private ArrayList<Byte>data;

    public boolean isSendingData() {
        return isSendingData;
    }

    public ArrayList<Byte> getData() {
        return data;
    }

    public DataObject(boolean isSendingData, ArrayList<Byte> data) {
        this.isSendingData = isSendingData;
        this.data = data;
    }
    
}
