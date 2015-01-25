package chicstyle.abstraction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;



public class ChicEtStyleJDBC extends ChicEtStyle {

	protected Statement statement;
	protected Connection connexion;

	//Constructeur.
	public ChicEtStyleJDBC() {
		super();
		BDChicEtStyle.connexionBD();
		this.connexion=BDChicEtStyle.getConnexion();
		try {
			statement = connexion.createStatement();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<String[]> findAllAlertes(){
		Vector<String[]> al = new Vector<String[]>();
		try {
			String myQuery = "SELECT source, message FROM ALERTES;";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				String[] tab = {r.getString("source"), r.getString("message")};
				al.add(tab);
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return al;
	}

	public void saveAlerte(Alertes al){
		try {
			String myQuery = "INSERT INTO ALERTES (source, message) VALUES ('"+al.getSource()+"','"+al.getMessage()+"')";
			statement.executeUpdate(myQuery);
			statement.execute("CHECKPOINT");
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void deleteAlerte(String source, String message) {
		try {
			String myQuery = "DELETE FROM ALERTES WHERE ALERTES.source = '"+source+"' AND ALERTES.message = '"+message+"'";
			statement.executeUpdate(myQuery);
			statement.execute("CHECKPOINT");
			this.setChanged();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}

	}

	public Vector<String> findAllClients() {
		Vector<String> clients = new Vector<String>();
		try {
			String myQuery = "SELECT DISTINCT client FROM PIECE;";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				clients.add(r.getString("client"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return clients;
	}

	public Vector<String> findAllModelesClient(String client) {
		Vector<String> modeles = new Vector<String>();
		try {
			String myQuery = "SELECT DISTINCT modele FROM PIECE WHERE PIECE.client = '"+client+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				modeles.add(r.getString("modele"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return modeles;
	}

	public Vector<String> findAllRefClient(String client){
		Vector<String> refs = new Vector<String>();
		try{
			String myQuery = "SELECT ref FROM PIECE, NOMENCLATURE WHERE NOMENCLATURE.id_piece = PIECE.id_piece AND PIECE.client = '"+client+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				refs.add(r.getString("ref"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return refs;
	}

	public Vector<String> findAllTissusModele(String modele) {
		Vector<String> tissus = new Vector<String>();
		try {
			String myQuery = "SELECT DISTINCT tissu FROM PIECE WHERE PIECE.modele = '"+modele+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				tissus.add(r.getString("tissu"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return tissus;
	}

	public Vector<String> findAllCouleursTissu(String tissu) {
		Vector<String> couleurs = new Vector<String>();
		try {
			String myQuery = "SELECT DISTINCT couleur FROM PIECE WHERE PIECE.tissu = '"+tissu+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				couleurs.add(r.getString("couleur"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return couleurs;
	}


	public Piece findPiece(String client, String modele, String tissu, String couleur) {
		Piece p = null;
		try {
			String myQuery = "SELECT id_piece , client , modele, tissu, couleur, type FROM PIECE WHERE PIECE.client = '"+client+"' AND PIECE.modele = '"+modele+"' AND PIECE.tissu = '"+tissu+"'AND PIECE.couleur = '"+couleur+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				p = new Piece(r.getString("client"), r.getString("modele"),r.getString("tissu"), r.getString("couleur"), r.getString("type"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public Piece findPiece(String id_piece) {
		Piece p = null;
		try {
			String myQuery = "SELECT id_piece , client , modele, tissu, couleur, type FROM PIECE WHERE PIECE.id_piece = '"+id_piece+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				p = new Piece(r.getString("client"), r.getString("modele"),r.getString("tissu"), r.getString("couleur"), r.getString("type"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return p;
	}

	public int findQteExpedier(int idpiece, int taille) {
		int quantiteExpediee = 0;
		try {
			String myQuery = "SELECT qte FROM EXPEDIER WHERE id_piece ="+idpiece+" AND taille="+taille+"";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				quantiteExpediee = Integer.parseInt(r.getString("qte"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return quantiteExpediee;
	}

	public Referencage findRef(String ref) {
		Referencage re = null;
		try {
			String myQuery = "SELECT ref , type , estTissu FROM REFERENCAGE WHERE REFERENCAGE.ref = '"+ref+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				re = new Referencage(r.getString("ref"), r.getString("type"),r.getBoolean("estTissu"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return re;
	}

	public Vector<String> findRefPiece(String id_piece) {
		Vector<String> refARet = new Vector<String>();
		try {
			String myQuery = "SELECT DISTINCT ref FROM NOMENCLATURE WHERE NOMENCLATURE.id_piece = '"+id_piece+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				refARet.add(r.getString("ref"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return refARet;
	}
	
	public Vector<String> findRefPieceEstTissu(String id_piece, boolean estTissu) {
		Vector<String> refARet = new Vector<String>();
		try {
			String myQuery = "SELECT NOMENCLATURE.ref FROM REFERENCAGE, NOMENCLATURE WHERE NOMENCLATURE.ref = REFERENCAGE.ref AND REFERENCAGE.estTissu = "+estTissu+" AND NOMENCLATURE.id_piece = '"+id_piece+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				refARet.add(r.getString("ref"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return refARet;
	}
	
	public Vector<String> findRefPiecePasTissu(String client) {
		Vector<String> refARet = new Vector<String>();
		try {
			String myQuery = "SELECT NOMENCLATURE.ref FROM PIECE, NOMENCLATURE, REFERENCAGE WHERE NOMENCLATURE.id_piece = PIECE.id_piece AND REFERENCAGE.ref = NOMENCLATURE.ref AND PIECE.client = '"+client+"' AND REFERENCAGE.ESTTISSU = false";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				refARet.add(r.getString("ref"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return refARet;
	}

	public Nomenclature findNomenclature(String id_piece, String ref) {
		Nomenclature n = null;
		try {
			String myQuery = "SELECT id_piece , ref, qte_unit, fact FROM NOMENCLATURE WHERE NOMENCLATURE.id_piece = '"+id_piece+"' AND NOMENCLATURE.ref = '"+ref+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				n = new Nomenclature(r.getString("id_piece"), r.getString("ref"),r.getInt("qte_unit"), r.getBoolean("fact"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return n;
	}

	public Vector<Object> findNomenclaturePiece(String id_piece){
		Vector<Object> o = new Vector<Object>();
		try {
			String myQuery = "SELECT ref, type, estTissu, qte_unit, fact FROM REFERENCAGE, NOMENCLATURE WHERE NOMENCLATURE.id_piece = '"+id_piece+"' AND REFERENCAGE.ref = NOMENCLATURE.ref";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				Object[] tab = {r.getString("ref"), r.getString("type"), r.getInt("qte_unit"), r.getBoolean("fact"), r.getBoolean("estTissu")};
				o.add(tab);
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return o;
	}

	public Vector<Object> findRealisationBoite(String id_piece){
		Vector<Object> o = new Vector<Object>();
		try{
			String myQuery = "SELECT NOMENCLATURE.ref, REFERENCAGE.type, NOMENCLATURE.qte_unit, SUM(LANCEMENT.QTE) AS TOTAL, RECEP_FOURNITURES.qte_recue FROM NOMENCLATURE, REFERENCAGE, LANCEMENT, RECEP_FOURNITURES WHERE RECEP_FOURNITURES.ref = REFERENCAGE.ref AND NOMENCLATURE.id_piece = '"+id_piece+"' AND REFERENCAGE.REF = NOMENCLATURE.REF AND LANCEMENT.ID_PIECE = NOMENCLATURE.ID_PIECE AND REFERENCAGE.ESTTISSU = false GROUP BY NOMENCLATURE.REF, REFERENCAGE.TYPE, NOMENCLATURE.QTE_UNIT, RECEP_FOURNITURES.qte_recue";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				Object[] tab = {r.getString("ref"), r.getString("type"), r.getInt("qte_unit")*r.getInt("TOTAL"), r.getInt("qte_recue")};
				o.add(tab);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return o;
	}
	
	public Object[] findRealisationBoiteRef(String id_piece, String ref){
		Object[] o = new Object[3];
		try{
			String myQuery = "SELECT REFERENCAGE.type, NOMENCLATURE.qte_unit, SUM(LANCEMENT.QTE) AS TOTAL, RECEP_FOURNITURES.qte_recue FROM NOMENCLATURE, REFERENCAGE, LANCEMENT, RECEP_FOURNITURES WHERE RECEP_FOURNITURES.ref = REFERENCAGE.ref AND NOMENCLATURE.id_piece = '"+id_piece+"' AND REFERENCAGE.REF = NOMENCLATURE.REF AND LANCEMENT.ID_PIECE = NOMENCLATURE.ID_PIECE AND REFERENCAGE.ESTTISSU = false AND NOMENCLATURE.ref = '"+ref+"' GROUP BY REFERENCAGE.TYPE, NOMENCLATURE.QTE_UNIT, RECEP_FOURNITURES.qte_recue";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				o[0]=r.getString("type");
				o[1]=r.getInt("qte_unit")*r.getInt("TOTAL");
				o[2]=r.getInt("qte_recue");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return o;
	}
		
	public int[] findHoussageLancement(String id_piece, int taille){
		int[] t = new int[2];
		try{
			String myQuery = "SELECT HOUSSER.qte AS qte_housse , LANCEMENT.qte AS qte_total, EXPEDIER.qte AS qte_exp FROM HOUSSER, LANCEMENT, EXPEDIER WHERE HOUSSER.id_piece = LANCEMENT.id_piece AND HOUSSER.id_piece = EXPEDIER.id_piece AND HOUSSER.taille = '"+taille+"' AND HOUSSER.id_piece = '"+id_piece+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				int reste = (r.getInt("qte_total")-r.getInt("qte_housse")-r.getInt("qte_exp"));
				t[0] = r.getInt("qte_total");
				t[1] = reste;
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}

	public int[] findTabExpedierPieceTaille(String id_piece, int taille){
		int[] t = new int[3];
		try {
			String myQuery = "SELECT HOUSSER.qte AS pret, LANCEMENT.qte AS total FROM HOUSSER, LANCEMENT WHERE HOUSSER.id_piece = LANCEMENT.id_piece AND HOUSSER.taille = LANCEMENT.taille AND LANCEMENT.id_piece = '"+id_piece+"' AND LANCEMENT.taille = '"+taille+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				int diff = (r.getInt("total")-r.getInt("pret"));
				t[0] = r.getInt("pret");
				t[1] = diff;
				t[2] = r.getInt("total");

			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return t;
	}



	public Vector<Housser> findAllHousser(){
		Vector<Housser> Housses = new Vector<Housser>();		
		try {
			String myQuery = "SELECT id_piece, taille, qte FROM HOUSSER WHERE HOUSSER.qte > 0";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				Housses.add(new Housser(r.getString("id_piece"),r.getInt("taille"), r.getInt("qte")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return Housses;
	}

	public void savePiece(Piece p, boolean creation) {
		try {
			if((creation)&&(this.findPiece(p.getId_piece())==null)) {
				String myQuery = "INSERT INTO PIECE(client, modele, tissu, couleur, type) VALUES ('"+p.getClient()+"','"+p.getModele()+"','"+p.getTissu()+"','"+p.getCouleur()+"','"+p.getType()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE PIECE SET PIECE.client = '"+p.getClient()+"', PIECE.modele = '"+p.getModele()+"', PIECE.tissu = '"+p.getTissu()+"', PIECE.couleur = '"+p.getCouleur()+"', PIECE.type = '"+p.getType()+"' WHERE PIECE.id_piece = '"+this.getPieceCourante().getId_piece()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
			this.notifyObservers(CHANGEMENT_PIECES);
			this.setPieceCourante(p);
			this.notifyObservers(CHANGEMENT_PIECE_COURANT);
			this.setChanged();
			this.notifyObservers(CHANGEMENT_ATHLETES);
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void saveRef(Referencage r, boolean creation) {
		try {
			if((creation)&&(this.findRef(r.getRef())==null)) {
				String myQuery = "INSERT INTO REFERENCAGE (ref, type, estTissu) VALUES ('"+r.getRef()+"','"+r.getType()+"','"+r.isEstTissu()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE REFERENCAGE SET REFERENCAGE.type = '"+r.getType()+"', REFERENCAGE.estTissu = '"+r.isEstTissu()+"' WHERE REFERENCAGE.ref = '"+r.getRef()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void saveNomenclature(Nomenclature n, boolean creation) {
		try {
			if((creation)&&(this.findNomenclature(n.getId_piece(), n.getRef())==null)) {
				String myQuery = "INSERT INTO NOMENCLATURE (id_piece, ref, qte_unit, fact) VALUES ('"+n.getId_piece()+"','"+n.getRef()+"','"+n.getQte_unit()+"','"+n.isFact()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE NOMENCLATURE SET NOMENCLATURE.qte_unit = '"+n.getQte_unit()+"', NOMENCLATURE.fact = '"+n.isFact()+"' WHERE NOMENCLATURE.id_piece = '"+n.getId_piece()+"' AND NOMENCLATURE.ref = '"+n.getRef()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Housser findHousser(String id_piece,int taille) {
		Housser h = null;
		try {
			String myQuery = "SELECT id_piece, taille, qte FROM HOUSSER WHERE HOUSSER.id_piece = '"+id_piece+"' AND HOUSSER.taille = '"+taille+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				h = new Housser(r.getString("id_piece"), r.getInt("taille"), r.getInt("qte"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return h;
	}

	public void saveHoussage(Housser h, boolean creation) {
		try {
			if((creation)&&(this.findHousser(h.getId_piece(), h.getTaille())==null)) {
				String myQuery = "INSERT INTO HOUSSER (id_piece, taille, qte) VALUES ('"+h.getId_piece()+"','"+h.getTaille()+"','"+h.getQte()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE HOUSSER SET HOUSSER.qte = '"+h.getQte()+"' WHERE HOUSSER.id_piece = '"+h.getId_piece()+"' AND HOUSSER.taille = '"+h.getTaille()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean findOkBoite(String id_piece, String ref){
		boolean ok = false;
		try{
			String myQuery = "SELECT ok FROM BOITE WHERE BOITE.ref = '"+ref+"' AND BOITE.id_piece = '"+id_piece+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()){
				ok = r.getBoolean("ok");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return ok;
	}
	
	public void saveOkBoite(Boite b, boolean creation){
		try{
			if((creation)&&(this.findOkBoite(b.getId_piece(), b.getRef())==false)){
				String myQuery = "INSERT INTO BOITE (id_piece,ref,ok) VALUES('"+b.getId_piece()+"','"+b.getRef()+"','"+b.isOk()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE BOITE SET BOITE.ok = '"+b.isOk()+"' WHERE BOITE.id_piece = '"+b.getId_piece()+"' AND BOITE.ref = '"+b.getRef()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Lancement findLancement(String id_piece, int taille) {
		Lancement l = null;
		try {
			String myQuery = "SELECT id_piece, taille, qte FROM LANCEMENT WHERE LANCEMENT.id_piece = '"+id_piece+"' AND LANCEMENT.taille = '"+taille+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				l = new Lancement(r.getString("id_piece"), r.getInt("taille"), r.getInt("qte"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	public void saveLancement(Lancement l, boolean creation) {
		try {
			if((creation)&&(this.findLancement(l.getId_piece(), l.getTaille())==null)) {
				String myQuery = "INSERT INTO LANCEMENT (id_piece, taille, qte) VALUES ('"+l.getId_piece()+"','"+l.getTaille()+"','"+l.getQte()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE LANCEMENT SET LANCEMENT.id_piece = '"+l.getId_piece()+"', LANCEMENT.taille = '"+l.getTaille()+"', LANCEMENT.qte = '"+l.getQte()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	public Expedier findExpedier(String id_piece, int taille) {
		Expedier ex = null;
		try {
			String myQuery = "SELECT id_piece, taille, qte FROM EXPEDIER WHERE EXPEDIER.id_piece = '"+id_piece+"' AND EXPEDIER.taille = '"+taille+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				ex = new Expedier(r.getString("id_piece"), r.getInt("taille"), r.getInt("qte"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ex;
	}

	public void saveExpedier(Expedier e, boolean creation) {
		try {
			if((creation)&&(this.findExpedier(e.getId_piece(), e.getTaille())==null)) {
				String myQuery = "INSERT INTO EXPEDIER (id_piece, taille, qte) VALUES ('"+e.getId_piece()+"','"+e.getTaille()+"','"+e.getQte()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE EXPEDIER SET EXPEDIER.qte = '"+e.getQte()+"' WHERE EXPEDIER.id_piece = '"+e.getId_piece()+"' AND EXPEDIER.taille = '"+e.getTaille()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String findTypeRef(String ref) {
		String type = "";
		try {
			String myQuery = "SELECT type FROM REFERENCAGE WHERE REFERENCAGE.ref = '"+ref+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				type = r.getString("type");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return type;
	}
	
	public boolean findEstTissuRef(String ref) {
		boolean estTissu=false;
		try {
			String myQuery = "SELECT estTissu FROM REFERENCAGE WHERE REFERENCAGE.ref = '"+ref+"' ";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				estTissu = r.getBoolean("estTissu");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return estTissu;
	}

	public int findRecep_Fournitures(String ref){
		int quantite = 0;
		try {
			String myQuery = "SELECT qte_recue FROM RECEP_FOURNITURES WHERE RECEP_FOURNITURES.ref = '"+ref+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				quantite = r.getInt("qte_recue");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return quantite;
	}

	public String findRefRecep_Fourniture(String ref){
		String resultat = null;
		try {
			String myQuery = "SELECT ref FROM RECEP_FOURNITURES WHERE RECEP_FOURNITURES.ref = '"+ref+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				resultat = r.getString("ref");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return resultat;
	}

	public void saveRecep_Fournitures(Recep_Fournitures e, boolean creation){
		try{
			if((creation)&&(this.findRefRecep_Fourniture(e.getRef())==null)){
				String myQuery = "INSERT INTO RECEP_FOURNITURES (ref, qte_recue) VALUES ('"+e.getRef()+"','"+e.getQte_Recue()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else{
				String myQuery = "UPDATE RECEP_FOURNITURES SET RECEP_FOURNITURES.qte_recue = '"+e.getQte_Recue()+"' WHERE RECEP_FOURNITURES.ref = '"+e.getRef()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public double findLaizePatronRef(String id_piece, String ref){
		double laize = 0;
		try {
			String myQuery = "SELECT DISTINCT laize FROM PATRON WHERE PATRON.id_piece = '"+id_piece+"' AND PATRON.ref = '"+ref+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				laize = r.getDouble("laize");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return laize;
	}
	
	public Recep_Tissu findRecep_Tissu(String ref, double laize){
		Recep_Tissu recep = null;
		try {
			String myQuery = "SELECT ref, laize, longueur_rouleau FROM RECEP_Tissu WHERE RECEP_TISSU.ref = '"+ref+"' AND RECEP_TISSU.laize = "+laize+"";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				recep = new Recep_Tissu(r.getString("ref"), r.getDouble("laize"), r.getDouble("longueur_rouleau"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return recep;
	}
	
	public double findLongueurRouleauRecep_Tissu(String ref, double laize){
		double longueur_rouleau = 0;
		try {
			String myQuery = "SELECT longueur_rouleau FROM RECEP_TISSU WHERE RECEP_TISSU.ref = '"+ref+"' AND RECEP_TISSU.laize = '"+laize+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				longueur_rouleau = r.getDouble("longueur_rouleau");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return longueur_rouleau;
	}
	
	public double findLRouleauStock(String ref){
		double longueur_rouleau = 0;
		try {
			String myQuery = "SELECT longueur_rouleau FROM RECEP_TISSU WHERE RECEP_TISSU.ref = '"+ref+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				longueur_rouleau = r.getDouble("longueur_rouleau");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return longueur_rouleau;
	}
	
	
	public void saveRecep_Tissu(Recep_Tissu recep, boolean creation){
		try{
			if((creation)&&(this.findRecep_Tissu(recep.getRef(), recep.getLaize())==null)){
				String myQuery = "INSERT INTO RECEP_TISSU (ref, laize, longueur_rouleau) VALUES ('"+recep.getRef()+"','"+recep.getLaize()+"','"+recep.getLongueur_rouleau()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else{
				String myQuery = "UPDATE RECEP_TISSU SET RECEP_TISSU.longueur_rouleau = "+recep.getLongueur_rouleau()+" WHERE RECEP_TISSU.ref = '"+recep.getRef()+"' AND RECEP_TISSU.laize =  "+recep.getLaize()+" ";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public Vector<Object> findProduitsFinisClient(String client){
		Vector<Object> ProduitsFinisClients = new Vector<Object>();
		try{
			String myQuery = "SELECT DISTINCT PIECE.MODELE, PIECE.TISSU, PIECE.COULEUR, LANCEMENT.TAILLE, HOUSSER.QTE AS qte_houssee, EXPEDIER.QTE AS qte_expediee, LANCEMENT.QTE AS qte_totale"
					+ " FROM LANCEMENT, EXPEDIER, HOUSSER, PIECE"
					+ " WHERE EXPEDIER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND EXPEDIER.TAILLE = LANCEMENT.TAILLE"
					+ " AND HOUSSER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND HOUSSER.TAILLE = LANCEMENT.TAILLE"
					+ " AND LANCEMENT.ID_PIECE = PIECE.ID_PIECE"
					+ " AND PIECE.CLIENT = '"+client+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				String[] tab = {r.getString("MODELE"), r.getString("TISSU"), r.getString("COULEUR"), r.getString("TAILLE") ,r.getString("qte_houssee"), r.getString("qte_expediee"), r.getString("qte_totale")};
				ProduitsFinisClients.add(tab);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ProduitsFinisClients;
	}

	public Vector<Object> findProduitsFinisModele(String client, String modele){
		Vector<Object> ProduitsFinisModeles = new Vector<Object>();
		try{
			String myQuery = "SELECT DISTINCT PIECE.MODELE, PIECE.TISSU, PIECE.COULEUR, LANCEMENT.TAILLE, HOUSSER.QTE AS qte_houssee, EXPEDIER.QTE AS qte_expediee, LANCEMENT.QTE AS qte_totale"
					+ " FROM LANCEMENT, EXPEDIER, HOUSSER, PIECE"
					+ " WHERE EXPEDIER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND EXPEDIER.TAILLE = LANCEMENT.TAILLE"
					+ " AND HOUSSER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND HOUSSER.TAILLE = LANCEMENT.TAILLE"
					+ " AND LANCEMENT.ID_PIECE = PIECE.ID_PIECE"
					+ " AND PIECE.CLIENT = '"+client+"'"
					+ " AND PIECE.MODELE = '"+modele+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				String[] tab = {r.getString("MODELE"), r.getString("TISSU"), r.getString("COULEUR"), r.getString("TAILLE") ,r.getString("qte_houssee"), r.getString("qte_expediee"), r.getString("qte_totale")};
				ProduitsFinisModeles.add(tab);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ProduitsFinisModeles;
	}

	public Vector<Object> findProduitsFinisTissu(String client, String modele, String tissu){
		Vector<Object> ProduitsFinisTissus = new Vector<Object>();
		try{
			String myQuery = "SELECT DISTINCT PIECE.MODELE, PIECE.TISSU, PIECE.COULEUR, LANCEMENT.TAILLE, HOUSSER.QTE AS qte_houssee, EXPEDIER.QTE AS qte_expediee, LANCEMENT.QTE AS qte_totale"
					+ " FROM LANCEMENT, EXPEDIER, HOUSSER, PIECE"
					+ " WHERE EXPEDIER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND EXPEDIER.TAILLE = LANCEMENT.TAILLE"
					+ " AND HOUSSER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND HOUSSER.TAILLE = LANCEMENT.TAILLE"
					+ " AND LANCEMENT.ID_PIECE = PIECE.ID_PIECE"
					+ " AND PIECE.CLIENT = '"+client+"'"
					+ " AND PIECE.MODELE = '"+modele+"'"
					+ " AND PIECE.TISSU = '"+tissu+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				String[] tab = {r.getString("MODELE"), r.getString("TISSU"), r.getString("COULEUR"), r.getString("TAILLE") ,r.getString("qte_houssee"), r.getString("qte_expediee"), r.getString("qte_totale")};
				ProduitsFinisTissus.add(tab);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ProduitsFinisTissus;
	}

	public Vector<Object> findProduitsFinisCouleur(String client, String modele, String tissu, String couleur){
		Vector<Object> ProduitsFinisCouleurs = new Vector<Object>();
		try{
			String myQuery = "SELECT DISTINCT PIECE.MODELE, PIECE.TISSU, PIECE.COULEUR, LANCEMENT.TAILLE, HOUSSER.QTE AS qte_houssee, EXPEDIER.QTE AS qte_expediee, LANCEMENT.QTE AS qte_totale"
					+ " FROM LANCEMENT, EXPEDIER, HOUSSER, PIECE"
					+ " WHERE EXPEDIER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND EXPEDIER.TAILLE = LANCEMENT.TAILLE"
					+ " AND HOUSSER.ID_PIECE = LANCEMENT.ID_PIECE"
					+ " AND HOUSSER.TAILLE = LANCEMENT.TAILLE"
					+ " AND LANCEMENT.ID_PIECE = PIECE.ID_PIECE"
					+ " AND PIECE.CLIENT = '"+client+"'"
					+ " AND PIECE.MODELE = '"+modele+"'"
					+ " AND PIECE.TISSU = '"+tissu+"'"
					+ " AND PIECE.COULEUR = '"+couleur+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				String[] tab = {r.getString("MODELE"), r.getString("TISSU"), r.getString("COULEUR"), r.getString("TAILLE") ,r.getString("qte_houssee"), r.getString("qte_expediee"), r.getString("qte_totale")};
				ProduitsFinisCouleurs.add(tab);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ProduitsFinisCouleurs;
	}
	
	public Patron findPatron(String id_piece, String ref, int taille){
		Patron aRet = null;
		try{
			String myQuery = "SELECT id_piece, ref, taille, laize, longueur FROM PATRON WHERE PATRON.id_piece= '"+id_piece+"' AND PATRON.ref= '"+ref+"' AND PATRON.taille= '"+taille+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while (r.next()) {
				aRet = new Patron(r.getString("id_piece"), r.getString("ref"),Integer.parseInt(r.getString("taille")),
						Double.parseDouble(r.getString("laize")), Double.parseDouble(r.getString("longueur")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return aRet;
	}

	public void savePatron(Patron patron, boolean creation) {
		try {
			if((creation)&&(this.findPatron(patron.getId_piece(),patron.getRef(),patron.getTaille())==null)) {
				String myQuery = "INSERT INTO PATRON (id_piece, ref, taille, laize, longueur) VALUES ('" +patron.getId_piece()+ "','" +patron.getRef()+ "'," +patron.getTaille()+ "," +patron.getLaize()+ "," +patron.getLongueur()+ ")";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else {
				String myQuery = "UPDATE PATRON SET PATRON.laize = '"+patron.getLaize()+"', PATRON.longueur = '"+patron.getLongueur()+"' WHERE PATRON.ref = '"+patron.getId_piece()+"' AND PATRON.ref = '"+patron.getRef()+"' AND PATRON.taille = '"+patron.getTaille()+"'";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public int findTotalClaudieDecoupe(String id_piece, String ref, int taille){
		int total = 0;
		try{
			String myQuery = "SELECT (PATRON.LONGUEUR * LANCEMENT.QTE) AS TOTAL_NECESSAIRE FROM PATRON, LANCEMENT WHERE PATRON.ID_PIECE = '"+id_piece+"' AND PATRON.REF = '"+ref+"' AND PATRON.TAILLE = '"+taille+"' AND LANCEMENT.ID_PIECE=PATRON.ID_PIECE AND LANCEMENT.TAILLE=PATRON.TAILLE";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()){
				total = r.getInt("TOTAL_NECESSAIRE");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	public int findLongueurCoupee(String id_piece, String ref){
		int l = 0;
		try{
			String myQuery = "SELECT longueur_coupee FROM RESTE_TISSU, NOMENCLATURE WHERE RESTE_TISSU.id_piece = '"+id_piece+"' AND RESTE_TISSU.ref = '"+ref+"' AND RESTE_TISSU.id_piece = NOMENCLATURE.id_piece AND RESTE_TISSU.ref = NOMENCLATURE.ref";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()){
				l = r.getInt("longueur_coupee");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return l;
	}
	
	public String findDateLancement(String id_piece){
		String date = null;
		try{
			String myQuery = "SELECT date FROM LANCEMENT_PROD WHERE LANCEMENT_PROD.id_piece = '"+id_piece+"'";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()){
				date = r.getString("date");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public void saveDateLancement(Lancement_Prod lancement, boolean creation){
		try{
			if((creation)&&(this.findDateLancement(lancement.getId_piece())==null)){
				String myQuery = "INSERT INTO LANCEMENT_PROD (id_piece, date) VALUES ('"+lancement.getId_piece()+"','"+lancement.getDate()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else{
				String myQuery = "UPDATE LANCEMENT_PROD SET LANCEMENT_PROD.date = '"+lancement.getDate()+"' WHERE LANCEMENT_PROD.id_piece =  '"+lancement.getId_piece()+"' ";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void saveLongueurCoupee(LongueurCoupee longueurCoupee, boolean creation){
		try{
			if((creation)&&(this.findLongueurCoupee(longueurCoupee.getId_piece(), longueurCoupee.getRef())==0)){
				String myQuery = "INSERT INTO RESTE_TISSU (id_piece,ref,longueur_coupee) VALUES ('"+longueurCoupee.getId_piece()+"','"+longueurCoupee.getRef()+"','"+longueurCoupee.getLongueurCoupee()+"')";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			else{
				String myQuery = "UPDATE RESTE_TISSU SET RESTE_TISSU.longueur_coupee = "+longueurCoupee.getLongueurCoupee()+" WHERE RESTE_TISSU.ref = '"+longueurCoupee.getRef()+"' AND RESTE_TISSU.id_piece =  '"+longueurCoupee.getId_piece()+"' ";
				statement.executeUpdate(myQuery);
				statement.execute("CHECKPOINT");
			}
			this.setChanged();
		}
		catch(SQLException ex) {
			ex.printStackTrace();
		}
	} 
	
	public String findMdp(String login){
		String mdp = "";
		try {
			String myQuery = "SELECT mdp FROM LOGIN WHERE LOGIN.login = '"+login+"';";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				mdp = r.getString("mdp");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return mdp;
	}
	
	public boolean[] findPermissions(String login){
		boolean[] permissions = new boolean[5];
		try {
			String myQuery = "SELECT direction, catherine, vanessa, claudie, marlene FROM LOGIN WHERE LOGIN.login = '"+login+"';";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				permissions[0] = r.getBoolean("direction");
				permissions[1] = r.getBoolean("catherine");
				permissions[2] = r.getBoolean("vanessa");
				permissions[3] = r.getBoolean("claudie");
				permissions[4] = r.getBoolean("marlene");
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return permissions;
	}
	
	public void deletePiece(String id_piece) {
		try {
			String myQuery = "DELETE FROM PIECE WHERE PIECE.id_piece = '"+id_piece+"'";
			statement.executeUpdate(myQuery);
			statement.execute("CHECKPOINT");
			this.setChanged();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteRreferencage(String ref) {
		try {
			String myQuery = "DELETE FROM REFERENCAGE WHERE REFERENCAGE.ref = '"+ref+"'";
			statement.executeUpdate(myQuery);
			statement.execute("CHECKPOINT");
			this.setChanged();
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vector<String> findIdPieceClient(String client){
		Vector<String> ids = new Vector<String>();
		try {
			String myQuery = "SELECT id_piece FROM PIECE WHERE PIECE.client = '"+client+"';";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				ids.add(r.getString("id_piece"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
	
	public Vector<String> findIdPieceClientModele(String client, String modele){
		Vector<String> ids = new Vector<String>();
		try {
			String myQuery = "SELECT id_piece FROM PIECE WHERE PIECE.client = '"+client+"' AND PIECE.modele = '"+modele+"';";
			ResultSet r = statement.executeQuery(myQuery);
			while(r.next()) {
				ids.add(r.getString("id_piece"));
			}
		} 
		catch(SQLException e) {
			e.printStackTrace();
		}
		return ids;
	}
}
