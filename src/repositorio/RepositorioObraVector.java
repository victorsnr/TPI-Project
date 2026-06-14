package repositorio;
import interfaces.IRepositorioObra;
import java.util.Vector;
import models.Obra;
import exceptions.*;

public class RepositorioObraVector implements IRepositorioObra {
	
	private Vector<Obra> repositorio;
	
	public RepositorioObraVector() {
		this.repositorio = new Vector<>();
	}
	
	@Override
	public void cadastrar(Obra obra) throws ObraJaCadastradaException {
		String titulo = obra.getTitulo();
		String autor = obra.getAutor();
		Obra obraExistente = buscar(titulo, autor);
		
		if (obraExistente != null) {
			throw new ObraJaCadastradaException("Obra já cadastrada no repertório!");
		}
		
		repositorio.add(obra);
	}
	
	@Override
	public Obra buscar(String titulo, String autor){
		titulo = titulo.trim().toUpperCase();
		autor = autor.trim().toUpperCase();
		
		for (Obra obra : repositorio) {
			if (obra.getTitulo().equals(titulo) && obra.getAutor().equals(autor)) {
				return obra;
			}
		}
		
		return null;
	}
	
	@Override
	public void atualizar(Obra obra) throws ObraNaoEncontradaException, TiposIncompativeisException {
		String titulo = obra.getTitulo();
		String autor = obra.getAutor();
		Obra obraEncontrada = buscar(titulo, autor);
		
		if (obraEncontrada == null) {
			throw new ObraNaoEncontradaException();
		}
		
		if (!obraEncontrada.getType().equals(obra.getType())) {
			throw new TiposIncompativeisException();
		}
		
		int indice = repositorio.indexOf(obraEncontrada);
		repositorio.set(indice, obra);;
	}
	
	@Override
	public void remover(String titulo, String autor) throws ObraNaoEncontradaException {
		titulo = titulo.trim().toUpperCase();
		autor = autor.trim().toUpperCase();
		Obra obraEncontrada = buscar(titulo, autor);
		
		if (obraEncontrada == null) {
			throw new ObraNaoEncontradaException();
		}
		
		obraEncontrada.setAtiva(false);
	}
	
	@Override
	public Vector<Obra> listar(){
		return repositorio;
	}
}
