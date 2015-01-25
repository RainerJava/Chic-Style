package chicstyle.abstraction;

import java.util.Observable;
import java.util.Vector;


public abstract class ChicEtStyle extends Observable {

protected Piece pieceCourante;
	
	public static final Integer CHANGEMENT_PIECE_COURANT = new Integer(1);
	public static final Integer CHANGEMENT_COMPETITION_COURANTE = new Integer(2);
	public static final Integer CHANGEMENT_ATHLETE_COURANT = new Integer(3);
	
	public static final Integer CHANGEMENT_PIECES = new Integer(4);
	public static final Integer CHANGEMENT_COMPETITIONS = new Integer(5);
	public static final Integer CHANGEMENT_ATHLETES = new Integer(6);
	
	public abstract void savePiece(Piece p, boolean creation);
	public abstract void saveRef(Referencage r, boolean creation);
	public abstract void saveExpedier(Expedier e, boolean creation);
	public abstract Vector<String> findAllClients();
	public abstract Vector<String> findAllModelesClient(String client);
	public abstract Vector<String> findAllTissusModele(String modele);
	public abstract Vector<String> findAllCouleursTissu(String tissu);
	public abstract Vector<Housser> findAllHousser();
	public abstract int[] findTabExpedierPieceTaille(String id_piece, int taille);
	public abstract Vector<Object> findProduitsFinisClient(String client);
	public abstract Vector<Object> findProduitsFinisModele(String client, String modele);
	public abstract Vector<Object> findProduitsFinisTissu(String client, String modele, String tissu);
	public abstract Vector<Object> findProduitsFinisCouleur(String client, String modele, String tissu, String couleur);
	public abstract Piece findPiece(String id_piece);
	public abstract Referencage findRef(String ref);
	public abstract Piece findPiece(String client, String modele, String tissu, String couleur);
	public abstract void saveNomenclature(Nomenclature n, boolean creation);
	public abstract Nomenclature findNomenclature(String id_piece, String ref);
	public abstract Lancement findLancement(String id_piece, int taille);
	public abstract void saveLancement(Lancement l, boolean creation);
	public abstract Vector<Object> findNomenclaturePiece(String id_piece);
	public abstract int[] findHoussageLancement(String id_piece, int taille);
	public abstract void saveHoussage(Housser h, boolean creation);
	public abstract Housser findHousser(String id_piece,int taille);
	public abstract Expedier findExpedier(String id_piece, int taille);
	public abstract Vector<String> findAllRefClient(String client);
	public abstract Vector<String> findRefPiece(String id_piece);
	public abstract String findTypeRef(String ref);
	public abstract void saveRecep_Fournitures(Recep_Fournitures e, boolean creation);
	public abstract int findRecep_Fournitures(String ref);
	public abstract String findRefRecep_Fourniture(String ref);
	public abstract Vector<Object> findRealisationBoite(String id_piece);
	public abstract Recep_Tissu findRecep_Tissu(String ref, double laize);
	public abstract double findLaizePatronRef(String id_piece, String ref);
	public abstract Vector<String> findRefPiecePasTissu(String id_piece);
	public abstract boolean findOkBoite(String id_piece, String ref);
	public abstract void saveOkBoite(Boite b, boolean creation);
	public abstract void saveRecep_Tissu(Recep_Tissu recep, boolean creation);
	public abstract double findLongueurRouleauRecep_Tissu(String ref, double laize);
	public abstract double findLRouleauStock(String ref);
	public abstract Vector<String> findRefPieceEstTissu(String id_piece, boolean estTissu);
	public abstract void savePatron(Patron patron, boolean creation);
	public abstract int findTotalClaudieDecoupe(String id_piece, String ref, int taille);
	public abstract void saveLongueurCoupee(LongueurCoupee longueurCoupee, boolean creation);
	public abstract int findLongueurCoupee(String id_piece, String ref);
	public abstract boolean findEstTissuRef(String ref);
	public abstract Object[] findRealisationBoiteRef(String id_piece, String ref);
	public abstract Vector<String[]> findAllAlertes();
	public abstract void saveAlerte(Alertes al);
	public abstract void deleteAlerte(String source, String message);
	public abstract void saveDateLancement(Lancement_Prod lancement, boolean creation);
	public abstract String findDateLancement(String id_piece);
	public abstract String findMdp(String login);
	public abstract boolean[] findPermissions(String login);
	public abstract void deletePiece(String id_piece);
	public abstract void deleteRreferencage(String ref);
	public abstract Vector<String> findIdPieceClient(String client);
	public abstract Vector<String> findIdPieceClientModele(String client, String modele);
	
	public ChicEtStyle() {
		super();
		this.pieceCourante = null;
	}
	
	public Piece getPieceCourante(){
		return this.pieceCourante;
	}
	
	public void setPieceCourante(Piece selectedPiece) {
		if (this.pieceCourante != null
				&& !this.pieceCourante.equals(selectedPiece)
				|| (selectedPiece != null && !selectedPiece
						.equals(this.pieceCourante))) {
			this.pieceCourante = selectedPiece;
			this.setChanged();
			this.notifyObservers(CHANGEMENT_PIECE_COURANT);
		}
	}
}
