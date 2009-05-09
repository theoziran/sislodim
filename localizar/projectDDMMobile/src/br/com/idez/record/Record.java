package br.com.idez.record;

import javax.microedition.lcdui.Alert;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class Record {

	private static int ID_TEMPO;
	private static String KEY_TEMPO = "TEMPO";
	public static int tempo_counter;

	private static String KEY_RECORD_STORE;
	private static RecordStore rs;
	
	private Alert alert;
	

	public static void init(String recordStore) {

		KEY_RECORD_STORE = recordStore;
		int count = 0;
		try {
			openRecord(KEY_RECORD_STORE);
			RecordEnumeration re = rs.enumerateRecords(null, null, false);

			while (re.hasNextElement()) {
				count = re.nextRecordId();
				String str = new String(rs.getRecord(count));
				System.out.println(str);	
				if (str.startsWith(KEY_TEMPO)) {
					ID_TEMPO = count;
				}
			}
			
		} catch (RecordStoreFullException e) {
			
		} catch (RecordStoreNotFoundException e) {
			
		} catch (RecordStoreException e) {
			
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
				
			}
		}

	}

	private static void openRecord(String recordStore)
			throws RecordStoreFullException, RecordStoreNotFoundException,
			RecordStoreException {
		rs = RecordStore.openRecordStore(recordStore, true);
	}

	private static void closeRecord() throws RecordStoreNotOpenException,
			RecordStoreException {
		rs.closeRecordStore();
	}

	public static void setTempo(int tempo) {
			try {
				openRecord(KEY_RECORD_STORE);
				String recTempo = KEY_TEMPO + tempo_counter++ + ":" + tempo;
				byte[] recBytes = recTempo.getBytes();
				if(ID_TEMPO != 0){
					rs.setRecord(ID_TEMPO, recBytes, 0, recBytes.length);
				} else {
					ID_TEMPO = rs.addRecord(recBytes, 0, recBytes.length);
				}
			} catch (RecordStoreFullException e) {
				
			} catch (RecordStoreNotFoundException e) {
				
			} catch (RecordStoreException e) {
				
			} finally{
				try {
					closeRecord();
				} catch (Exception e) {
					// TODO exibir Alert
					
				} 
			}
	}

	public static int getTempo() {
		int tempo = 0;
		try {
			openRecord(KEY_RECORD_STORE);
			String recTempo;

			if (ID_TEMPO != 0) {
				recTempo = new String(rs.getRecord(ID_TEMPO));
				tempo = Integer.parseInt(recTempo.substring(recTempo
						.indexOf(":") + 1, recTempo.length()));
			} else {
				System.out.println(ID_TEMPO);
			}
		} catch (RecordStoreFullException e) {
			
		} catch (RecordStoreNotFoundException e) {
			
		} catch (RecordStoreException e) {
			
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
				
			}
		}

		return tempo;
	}
}
