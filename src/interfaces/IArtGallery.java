package interfaces;
import models.Obra;
import models.Avaliacao;
import java.util.Vector;
import exceptions.ExposicaoNaoEncontradaException;

public interface IArtGallery {
	String publicarObra(Obra obra);
	String removerObra(String titulo, String autor);
	String avaliarObra(String titulo, String autor, Avaliacao avaliacao);
	Vector<Obra> listarObras();
	Vector<Obra> buscarPorAutor(String autor);
	Vector<Obra> topObras();
	Vector<Obra> obrasExpostas (String nomeExposicao) throws ExposicaoNaoEncontradaException;
}
