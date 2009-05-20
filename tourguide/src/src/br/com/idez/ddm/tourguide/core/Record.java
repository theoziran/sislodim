package br.com.idez.ddm.tourguide.core;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

import br.com.idez.ddm.tourguide.PontoEstrategico;

public class Record {

	private static String key_record_store;
	private static RecordStore rs;

	public static int ID_CFG_SOUND;
	public static int ID_CFG_SYNC;
	public static int ID_CFG_MAX_TIME;
	public static int ID_CFG_MULTIMEDIA;

	public static final String KEY_CFG_SOUND = "SOUND";
	public static final String KEY_CFG_SYNC = "SYNC";
	public static final String KEY_CFG_MAX_TIME = "MAXTIME";
	public static final String KEY_CFG_MULTIMEDIA = "MULTIMEDIA";
	public static final String KEY_PTO_LATITUDE = "LATI";
	public static final String KEY_PTO_LONGITUDE = "LONG";
	public static final String KEY_PTO_NOME = "NOME";

	public static void openRecord(String recordStore)
			throws RecordStoreFullException, RecordStoreNotFoundException,
			RecordStoreException {
		rs = RecordStore.openRecordStore(recordStore, true);
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
					if (str.startsWith(KEY_CFG_MAX_TIME)) {
						ID_CFG_MAX_TIME = count;
					} else if (str.startsWith(KEY_CFG_MULTIMEDIA)) {
						ID_CFG_MULTIMEDIA = count;
					} else if (str.startsWith(KEY_CFG_SOUND)) {
						ID_CFG_SOUND = count;
					} else if (str.startsWith(KEY_CFG_SYNC)) {
						ID_CFG_SYNC = count;
					}
				}
			}
		} catch (Exception e) {
			// TODO exibir Alert
			System.out.println("exceção maldita");
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
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
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}

		/**
		 * testa se já existe o registro se não existir cria com valor padrão
		 * "ON"
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
			} else {// se não, adiciona
				ID_CFG_SOUND = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}
	}

	public static String getConfigSync() {
		String configSync = null;
		try {
			openRecord(key_record_store);
			String recConfigSync = null;
			if (ID_CFG_SYNC != 0) {
				recConfigSync = new String(rs.getRecord(ID_CFG_SYNC));
				configSync = recConfigSync.substring(
						recConfigSync.indexOf(":") + 1, recConfigSync.length());
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}

		/**
		 * testa se já existe o registro se não existir cria com valor padrão
		 * "ON"
		 */

		if (configSync == null) {
			setConfigSync("ON");
			configSync = getConfigSync();
		}

		return configSync;
	}

	public static void setConfigSync(String configSync) {
		try {
			openRecord(key_record_store);
			String recConfigSync = KEY_CFG_SYNC + ":" + configSync;
			byte[] recBytes = recConfigSync.getBytes();
			// se existir, atualiza
			if (ID_CFG_SYNC != 0) {
				rs.setRecord(ID_CFG_SYNC, recBytes, 0, recBytes.length);
			} else {// se não, adiciona
				ID_CFG_SYNC = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}
	}

	public static String getConfigMultimedia() {
		String configMultimedia = null;
		try {
			openRecord(key_record_store);
			String recConfigMultimedia = null;
			if (ID_CFG_MULTIMEDIA != 0) {
				recConfigMultimedia = new String(rs
						.getRecord(ID_CFG_MULTIMEDIA));
				configMultimedia = recConfigMultimedia.substring(
						recConfigMultimedia.indexOf(":") + 1,
						recConfigMultimedia.length());
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}

		/**
		 * testa se já existe o registro se não existir cria com valor padrão
		 * "ON"
		 */

		if (configMultimedia == null) {
			setConfigMultimedia("ON");
			configMultimedia = getConfigMultimedia();
		}

		return configMultimedia;
	}

	public static void setConfigMultimedia(String configMultimedia) {
		try {
			openRecord(key_record_store);
			String recConfigMultimedia = KEY_CFG_MULTIMEDIA + ":"
					+ configMultimedia;
			byte[] recBytes = recConfigMultimedia.getBytes();
			// se existir, atualiza
			if (ID_CFG_MULTIMEDIA != 0) {
				rs.setRecord(ID_CFG_MULTIMEDIA, recBytes, 0, recBytes.length);
			} else {// se não, adiciona
				ID_CFG_MULTIMEDIA = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}
	}

	public static String getConfigMaxTime() {
		String configMaxTime = null;
		try {
			openRecord(key_record_store);
			String recConfigMaxTime = null;
			if (ID_CFG_MAX_TIME != 0) {
				recConfigMaxTime = new String(rs.getRecord(ID_CFG_MAX_TIME));
				configMaxTime = recConfigMaxTime.substring(recConfigMaxTime
						.indexOf(":") + 1, recConfigMaxTime.length());
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}

		/**
		 * testa se já existe o registro se não existir cria com valor padrão
		 * "15" segundos
		 */

		if (configMaxTime == null) {
			setConfigMaxTime("15");
			configMaxTime = getConfigMaxTime();
		}

		return configMaxTime;
	}

	public static void setConfigMaxTime(String configMaxTime) {
		try {
			openRecord(key_record_store);
			String recConfigMaxTime = KEY_CFG_MAX_TIME + ":" + configMaxTime;
			byte[] recBytes = recConfigMaxTime.getBytes();

			if (ID_CFG_MAX_TIME != 0) {
				rs.setRecord(ID_CFG_MAX_TIME, recBytes, 0, recBytes.length);
			} else {
				ID_CFG_MAX_TIME = rs.addRecord(recBytes, 0, recBytes.length);
			}
		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}
	}

	public static void addPontoEstrategico(PontoEstrategico ponto) {
		try {
			openRecord(key_record_store);

			String recPontoID = Integer.toString(ponto.getId());

			String recPontoNome = KEY_PTO_NOME + ":" + recPontoID + "="
					+ ponto.getNome();
			String recPontoLatitude = KEY_PTO_LATITUDE + ":" + recPontoID + "="
					+ Double.toString(ponto.getLatitude());
			String recPontoLongitude = KEY_PTO_LONGITUDE + ":" + recPontoID
					+ "=" + Double.toString(ponto.getLongitude());

			byte[] recBytes = recPontoNome.getBytes();
			rs.addRecord(recBytes, 0, recBytes.length);

			recBytes = recPontoLatitude.getBytes();
			rs.addRecord(recBytes, 0, recBytes.length);

			recBytes = recPontoLongitude.getBytes();
			rs.addRecord(recBytes, 0, recBytes.length);

		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}
	}

	public static PontoEstrategico getPontoEstrategico(int id) {
		PontoEstrategico pontoEstrategico = null;
		int count = 0;
		try {
			openRecord(key_record_store);

			String recPontoID = Integer.toString(id);

			String recPontoNome = null;
			String recPontoLatitude = null;
			String recPontoLongitude = null;

			// executa a pesquisa pelo id informado
			if (rs != null) {
				RecordEnumeration re = rs.enumerateRecords(null, null, false);
				while (re.hasNextElement()
						|| (recPontoNome != null && recPontoLongitude != null && recPontoLatitude != null)) {
					count = re.nextRecordId();
					String str = new String(rs.getRecord(count));
					if (str.startsWith(KEY_PTO_NOME + ":" + recPontoID + "=")) {
						recPontoNome = new String(rs.getRecord(count));
					} else if (str.startsWith(KEY_PTO_LATITUDE + ":"
							+ recPontoID + "=")) {
						recPontoLatitude = new String(rs.getRecord(count));
					} else if (str.startsWith(KEY_PTO_LONGITUDE + ":"
							+ recPontoID + "=")) {
						recPontoLongitude = new String(rs.getRecord(count));
					}
				}

				if (recPontoNome != null) {
					pontoEstrategico = new PontoEstrategico();
					
					pontoEstrategico.setId(id);
					pontoEstrategico.setNome(recPontoNome.substring(
							recPontoNome.indexOf("=") + 1, recPontoNome
									.length()));
					pontoEstrategico.setLatitude(Double
							.parseDouble((recPontoLatitude.substring(
									recPontoLatitude.indexOf("=") + 1,
									recPontoLatitude.length()))));
					pontoEstrategico.setLongitude(Double
							.parseDouble((recPontoLongitude.substring(
									recPontoLongitude.indexOf("=") + 1,
									recPontoLongitude.length()))));
				}
			}

		} catch (Exception e) {
			// TODO exibir Alert
		} finally {
			try {
				closeRecord();
			} catch (Exception e) {
				// TODO exibir Alert
			}
		}

		return pontoEstrategico;
	}
}
