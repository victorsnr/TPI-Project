package ui;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class TelaInicial extends JPanel{

	private static final long serialVersionUID = -1970210562945307331L;

	public TelaInicial(CardLayout cards, JPanel painelPrincipal, JPanel telaExibirObrasAtivas, JPanel telaRankingObras) {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Bem-vindo ao ArtGallery");
		
		JButton botaoPublicar = new JButton("Publicar Obra");
		JButton botaoRemover = new JButton("Remover Obra");
		JButton botaoAvaliar = new JButton("Avaliar Obra");
		JButton botaoListar = new JButton("Repertório");
		JButton botaoBuscarPorAutor = new JButton("Buscar por autor");
		JButton botaoRanking = new JButton("Ranking de Obras");
		JButton botaoBuscarExposicao = new JButton("Buscar Exposição");
		JButton botaoCriarExposicao = new JButton("Criar Exposição");
		JButton botaoAdicionarObraExpo = new JButton("Adicionar Obra em uma Expo");
		
		botaoPublicar.addActionListener(e -> {
			cards.show(painelPrincipal, "MENU_PUBLICAR");
		});
		
		botaoRemover.addActionListener(e -> {
			cards.show(painelPrincipal, "DESATIVAR_OBRA");
		});
		
		botaoAvaliar.addActionListener(e -> {
			cards.show(painelPrincipal, "AVALIAR_OBRA");
		});
		
		botaoListar.addActionListener(e -> {
			((TelaExibirObrasAtivas) telaExibirObrasAtivas).atualizarLista();
			cards.show(painelPrincipal, "EXIBIR_OBRAS");
		});
		
		botaoBuscarPorAutor.addActionListener(e -> {
			cards.show(painelPrincipal, "BUSCAR_AUTOR");
		});
		
		botaoRanking.addActionListener(e -> {
			((TelaRankingObras) telaRankingObras).atualizarListaRanking();
			cards.show(painelPrincipal, "RANKING");
		});
		
		botaoBuscarExposicao.addActionListener(e -> {
		});
		
		botaoCriarExposicao.addActionListener(e -> {
		});
		
		botaoAdicionarObraExpo.addActionListener(e -> {
		});
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 5, 5, 5);	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labelTitulo, gbc);
		
		gbc.gridy = 1;
		add(botaoPublicar, gbc);
		
		gbc.gridy = 2;
		add(botaoRemover, gbc);
		
		gbc.gridy = 3;
		add(botaoAvaliar, gbc);
		
		gbc.gridy = 4;
		add(botaoListar, gbc);
		
		gbc.gridy = 5;
		add(botaoBuscarPorAutor, gbc);
		
		gbc.gridy = 6;
		add(botaoRanking, gbc);
		
		gbc.gridy = 7;
		add(botaoBuscarExposicao, gbc);
		
		gbc.gridy = 8;
		add(botaoCriarExposicao, gbc);
		
		gbc.gridy = 9;
		add(botaoAdicionarObraExpo, gbc);
	}
}
