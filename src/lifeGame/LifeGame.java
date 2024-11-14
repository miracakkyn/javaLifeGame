package lifeGame;

import java.io.IOException;

public class LifeGame {
	final int habitatSatir = 19;
	final int habitatSutun = 19;
	int hucreHabitat[][];
	int hucreHabitatTmp[][];
	int[] pulsarSatir;
	int[] pSatir;
	int pulsarSutun[];

	public LifeGame() {
		// pulsar desni oluşması için gerekli ön tanımlamalar
		pSatir = new int[] { 3, 8, 10, 15 };
		pulsarSatir = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
		pulsarSutun = new int[] { 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0 };
		// gerçek yaşam alanı ve değişikliklerin yapılacağı yedek yaşam alanı tanımı
		hucreHabitat = new int[habitatSatir][habitatSutun];
		hucreHabitatTmp = new int[habitatSatir][habitatSutun];
		// tüm yedek ve gerçek yaşam alanı sıfırlanıyor
		int c = 0;
		for (int i = 0; i < habitatSatir; i++) {
			for (int y = 0; y < habitatSutun; y++) {
				hucreHabitatTmp[i][y] = c;
				hucreHabitat[i][y] = c;
			}
		}
		// pulsar deseni gerçek yaşam alanına atanıyor
		for (int satir = 0; satir < pSatir.length; satir++) {
			for (int sutun = 0; sutun < habitatSutun; sutun++) {
				hucreHabitat[pSatir[satir]][sutun] = pulsarSatir[sutun];
			}
		}
		for (int sutun = 0; sutun < pSatir.length; sutun++) {
			for (int satir = 0; satir < habitatSutun; satir++) {
				hucreHabitat[satir][pSatir[sutun]] = pulsarSatir[satir];
			}
		}
	}

	public void drawHabitat() {
		// gerçek yaşam alanı (hucreHabitat) ekrana çizdiriliyor
		for (int i = 1; i < 18; i++) {
			for (int j = 1; j < 18; j++) {
				if (hucreHabitat[i][j] == 1) {
					System.out.print("#");
				} else {
					System.out.print("-");
				}
			}
			System.out.println();
		}

	}

	public int komsuCanliSayisi(int satir, int sutun) {
		int canliKomsuSayisi = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i == 1 && j == 1) {
					continue; // Kendi hücresini saymamak için
				}
				int yeniSatir = satir + i - 1;
				int yeniSutun = sutun + j - 1;

				// Sınır kontrolü ekleyin
				if (yeniSatir >= 0 && yeniSatir < habitatSatir && yeniSutun >= 0 && yeniSutun < habitatSutun) {
					if (hucreHabitat[yeniSatir][yeniSutun] == 1) {
						canliKomsuSayisi++;
					}
				}
			}
		}
		return canliKomsuSayisi;
	}

	public void newHabitatRule() {
		int cks;

		// Life Game'in 4 kuralına göre gerçek habitata bakılarak
		// bir sonraki iterasyon için geçici habitat (hucreHabitatTmp)
		// güncelleniyor
		// *******KODLANACAK*********
		for (int i = 1; i < 18; i++) {
			for (int j = 1; j < 18; j++) {
				cks = komsuCanliSayisi(i, j);
				if (cks < 2 && hucreHabitat[i][j] == 1) {
					hucreHabitatTmp[i][j] = 0;

				} else if ((cks == 2 || cks == 3) && hucreHabitat[i][j] == 1) {
					hucreHabitatTmp[i][j] = 1;

				} else if (cks > 3 && hucreHabitat[i][j] == 1) {
					hucreHabitatTmp[i][j] = 0;

				} else if (hucreHabitat[i][j] == 0 && cks == 3) {
					hucreHabitatTmp[i][j] = 1;
				} else {
					continue;
				}
			}
		}
		copyHabitat();
	}

	public void copyHabitat() {
		for (int i = 1; i < 18; i++) {
			for (int j = 1; j < 18; j++) {
				hucreHabitat[i][j] = hucreHabitatTmp[i][j];
			}
		}
	}

	public static void main(String[] args) throws Exception {
		LifeGame lg = new LifeGame();
		for (int i = 0; i < 20; i++) {
			lg.drawHabitat();
			lg.newHabitatRule();
			System.out.println();
			Thread.sleep(1500);

		}

	}
}
