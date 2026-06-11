package interfaces;
import models.Obra;
import models.Avaliacao;
import models.Exposicao;
import java.util.Vector;
import exceptions.ExposicaoNaoEncontradaException;
import exceptions.ObraJaCadastradaException;
import exceptions.ExposicaoExistenteException;

public interface IArtGallery {
	String publicarObra(Obra obra);
	String removerObra(String titulo, String autor);
	String avaliarObra(String titulo, String autor, Avaliacao avaliacao);
	Vector<Obra> listarObras();
	Vector<Obra> buscarPorAutor(String autor);
	Vector<Obra> topObras();
	Vector<Obra> obrasExpostas (String nomeExposicao) throws ExposicaoNaoEncontradaException;
	void adicionarExposicao(Exposicao exposicao) throws ExposicaoExistenteException;
	void adicionarObraNaExposicao(String nomeExpo, Obra obra) throws ExposicaoNaoEncontradaException, ObraJaCadastradaException;
}
