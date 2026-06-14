package ui;

import javax.swing.*;
import interfaces.IArtGallery;
import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import database.DatabaseConstructor;

public class JanelaPrincipal extends JFrame {

	private static final long serialVersionUID = 1136798509608670621L;

	private CardLayout cards;
	private JPanel painelPrincipal;
	private IArtGallery minhaGaleria;
	
	private JPanel telaInicial;
	private JPanel telaPublicarMenu;
	private JPanel telaPublicarArteGenerativa;
	private JPanel telaPublicarModelagem3D;
	private JPanel telaPublicarPinturaDigital;
	private JPanel telaDesativarObra;
	private JPanel telaAvaliarObra;
	private JPanel telaExibirObrasAtivas;
	private JPanel telaBuscarPorAutor;
	private JPanel telaListarObrasAutor;
	private JPanel telaRankingObras;
	private JPanel telaBuscarExpo;
	private JPanel telaExibirObrasExpo;
	private JPanel telaCriarExpo;
	private JPanel telaAdicionarObraExpo;
	
	public JanelaPrincipal(String titulo, IArtGallery minhaGaleria, DatabaseConstructor database) {
		super(titulo);
		this.cards = new CardLayout();
		this.painelPrincipal = new JPanel(cards);
		this.minhaGaleria = minhaGaleria;
		
		telaPublicarMenu = new TelaPublicarMenu(cards, painelPrincipal);
		telaPublicarArteGenerativa = new TelaPublicarArteGenerativa(cards, painelPrincipal, this.minhaGaleria);
		telaPublicarModelagem3D = new TelaPublicarModelagem3D(cards, painelPrincipal, this.minhaGaleria);
		telaPublicarPinturaDigital = new TelaPublicarPinturaDigital(cards, painelPrincipal, this.minhaGaleria);
		telaDesativarObra = new TelaDesativarObra(cards, painelPrincipal, this.minhaGaleria);
		telaAvaliarObra = new TelaAvaliarObra(cards, painelPrincipal, this.minhaGaleria);
		telaExibirObrasAtivas = new TelaExibirObrasAtivas(cards, painelPrincipal, this.minhaGaleria);
		telaListarObrasAutor = new TelaListarObrasAutor(cards, painelPrincipal, this.minhaGaleria);
		telaBuscarPorAutor = new TelaBuscarPorAutor(cards, painelPrincipal, telaListarObrasAutor);
		telaRankingObras = new TelaRankingObras(cards, painelPrincipal, this.minhaGaleria);
		telaExibirObrasExpo = new TelaExibirObrasExposicao(cards, painelPrincipal, this.minhaGaleria);
		telaBuscarExpo = new TelaBuscarExposicao(cards, painelPrincipal, telaExibirObrasExpo);
		telaCriarExpo = new TelaCriarExposicao(cards, painelPrincipal, this.minhaGaleria);
		telaAdicionarObraExpo = new TelaAdicionarObraExposicao(cards, painelPrincipal, this.minhaGaleria);
		
		telaInicial = new TelaInicial(cards, painelPrincipal, telaExibirObrasAtivas, telaRankingObras, telaCriarExpo, telaAdicionarObraExpo);
        
		painelPrincipal.add(telaInicial, "INICIO");
        painelPrincipal.add(telaPublicarMenu, "MENU_PUBLICAR");
        painelPrincipal.add(telaPublicarArteGenerativa, "PUBLICAR_AG");
        painelPrincipal.add(telaPublicarModelagem3D, "PUBLICAR_MD");
        painelPrincipal.add(telaPublicarPinturaDigital, "PUBLICAR_PD");
        painelPrincipal.add(telaDesativarObra, "DESATIVAR_OBRA");
        painelPrincipal.add(telaAvaliarObra, "AVALIAR_OBRA");
        painelPrincipal.add(telaExibirObrasAtivas, "EXIBIR_OBRAS");
        painelPrincipal.add(telaBuscarPorAutor, "BUSCAR_AUTOR");
        painelPrincipal.add(telaListarObrasAutor, "LISTAR_AUTOR");
        painelPrincipal.add(telaRankingObras, "RANKING");
        painelPrincipal.add(telaBuscarExpo, "BUSCAR_EXPO");
        painelPrincipal.add(telaExibirObrasExpo, "LISTAR_EXPO");
        painelPrincipal.add(telaCriarExpo, "CRIAR_EXPO");
        painelPrincipal.add(telaAdicionarObraExpo, "ADD_OBRA_EXPO");
        
        addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e) {
        		database.writeData();
        		database.endConnection();
        		dispose();
        		System.exit(0);
        	}
        });
        
        add(painelPrincipal);
        pack();
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        
    }
}