import ui.JanelaPrincipal;
import interfaces.IArtGallery;
import interfaces.IRepositorioObra;
import repositorio.RepositorioObraVector;
import gallery.ArtGallery;
public class Main {
	public static void main(String args[]) {
		IRepositorioObra meuRepositorio = new RepositorioObraVector();
		IArtGallery minhaGaleria = new ArtGallery(meuRepositorio);
		
		new JanelaPrincipal("ArtGallery", minhaGaleria);
	}
}
