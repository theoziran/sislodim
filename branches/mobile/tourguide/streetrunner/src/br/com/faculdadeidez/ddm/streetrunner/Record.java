package br.com.faculdadeidez.ddm.streetrunner;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

public class Record {

	private static String key_record_store;
	private static RecordStore rs;

	public static int ID_CFG_SOUND;
	public static int ID_LEVEL;

	public static final String KEY_CFG_SOUND = "SOUND";
	public static final String KEY_LEVEL = "LEVEL";

	public static void openRecord(String recordStore)
			throws RecordStoreFullException, RecordStoreNotFoundException,
			RecordStoreException {
		rs = RecordStore.openRecordStore(recordStore, true);
	}

	public static void destroy(String recordStore) {
		try {
			RecordStore.deleteRecordStore(recordStore);
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	private static void closeRecord() throws RecordStoreNotOpenException,
			RecordStoreException {
		rs.closeRecordStore();
	}

	public static void init(String recordStore) {
		key_record_store = recordStore;
		int count = 0;
		try {
			openRecord(key_record_store);
			// TODO implementar RecordComparator
			// TODO implementar RecordFilter
			if (rs != null) {
				RecordEnumeration re = rs.enumerateRecords(null, null, false);
				while (re.hasNextElement()) {
					count = re.nextRecordId();
					String str = new String(rs.getRecord(count));
					if (str.startsWith(KEY_LEVEL)) {
						ID_LEVEL = count;
					} else if (str.startsWith(KEY_CFG_SOUND)) {
						ID_CFG_SOUND = count;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("exceÇÃO maldita");
			e.printStackTrace();
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getConfigSound() {
		String configSound = null;
		try {
			openRecord(key_record_store);
			String recConfigSound = null;
			if (ID_CFG_SOUND != 0) {
				recConfigSound = new String(rs.getRecord(ID_CFG_SOUND));
				configSound = recConfigSound.substring(recConfigSound
						.indexOf(":") + 1, recConfigSound.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * testa se jï¿½ existe o registro se nï¿½o existir cria com valor
		 * padrï¿½o "ON"
		 */

		if (configSound == null) {
			setConfigSound("ON");
			configSound = getConfigSound();
		}

		return configSound;
	}

	public static void setConfigSound(String configSound) {
		try {
			openRecord(key_record_store);
			String recConfigSound = KEY_CFG_SOUND + ":" + configSound;
			byte[] recBytes = recConfigSound.getBytes();
			// se existir, atualiza
			if (ID_CFG_SOUND != 0) {
				rs.setRecord(ID_CFG_SOUND, recBytes, 0, recBytes.length);
			} else {// se nï¿½o, adiciona
				ID_CFG_SOUND = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getLevel() {
		String level = null;
		try {
			openRecord(key_record_store);
			String recConfigSync = null;
			if (ID_LEVEL != 0) {
				recConfigSync = new String(rs.getRecord(ID_LEVEL));
				level = recConfigSync.substring(recConfigSync.indexOf(":") + 1,
						recConfigSync.length());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * testa se já existe o registro se não existir cria com valor padrão
		 * "0"
		 */

		if (level == null) {
			setLevel("0");
			level = getLevel();
		}

		return level;
	}

	public static void setLevel(String level) {
		try {
			openRecord(key_record_store);
			String recConfigSync = KEY_LEVEL + ":" + level;
			byte[] recBytes = recConfigSync.getBytes();
			// se existir, atualiza
			if (ID_LEVEL != 0) {
				rs.setRecord(ID_LEVEL, recBytes, 0, recBytes.length);
			} else {// se nï¿½o, adiciona
				ID_LEVEL = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("level saved succesfully: " + level);
	}

}
