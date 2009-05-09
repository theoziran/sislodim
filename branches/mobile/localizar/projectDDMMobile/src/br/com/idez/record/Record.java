package br.com.idez.record;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class Record {

	private static int ID_TEMPO;
	private static String KEY_TEMPO = "TEMPO";

	private static String KEY_RECORD_STORE;
	private static RecordStore rs;
	
	public static void init(String recordStore){
		
	}
	

	private static void openRecord(String recordStore) throws RecordStoreFullException, RecordStoreNotFoundException, RecordStoreException {
		rs = RecordStore.openRecordStore(recordStore, true);
	}
	
	private static void closeRecord() throws RecordStoreNotOpenException, RecordStoreException {
		rs.closeRecordStore();
	}
}
