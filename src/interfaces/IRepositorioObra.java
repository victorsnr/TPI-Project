package interfaces;
import models.Obra;
import java.util.Vector;
import exceptions.*;

public interface IRepositorioObra {
	public void cadastrar(Obra obra) throws ObraJaCadastradaException;
	Obra buscar(String titulo, String autor);
	void atualizar(Obra obra) throws ObraNaoEncontradaException, TiposIncompativeisException;
	void remover(String titulo, String autor) throws ObraNaoEncontradaException;
	Vector<Obra> listar();
}
