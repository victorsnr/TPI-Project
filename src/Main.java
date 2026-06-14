import ui.JanelaPrincipal;
import interfaces.IArtGallery;
import interfaces.IRepositorioObra;
import repositorio.RepositorioObraVector;
import gallery.ArtGallery;
import database.DatabaseConstructor;
import java.sql.SQLException;

public class Main {
	public static void main(String args[]) {
		IRepositorioObra meuRepositorio = new RepositorioObraVector();
		IArtGallery minhaGaleria = new ArtGallery(meuRepositorio);
		
		try {
			DatabaseConstructor database = new DatabaseConstructor(minhaGaleria);
			database.makeDatabase();
			database.readData();
			new JanelaPrincipal("ArtGallery", minhaGaleria, database);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
