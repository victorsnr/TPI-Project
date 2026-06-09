package ui;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import models.Obra;
import models.ArteGenerativa;

import interfaces.IArtGallery;

public class TelaPublicarArteGenerativa extends JPanel {
	private static final long serialVersionUID = 3806684102637360850L;

	public TelaPublicarArteGenerativa(CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {	
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Publicar Arte Generaativa");
		
		JLabel labelTituloObra = new JLabel("Título da Obra:");
		JTextField textFieldTituloObra = new JTextField(20);
		
		JLabel labelNomeAutor = new JLabel("Nome do Autor");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JLabel labelAlgoritmo = new JLabel("Algoritmo");
		JTextField textFieldAlgoritmo = new JTextField(20);
		
		JLabel labelSeed = new JLabel("Seed:");
		JTextField textFieldSeed = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String titulo = textFieldTituloObra.getText();		
			String autor = textFieldNomeAutor.getText();
			String algoritmo = textFieldAlgoritmo.getText();
			long seed;
			try {
				seed = Long.parseLong(textFieldSeed.getText());
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(this, "Seed deve conter apenas números", "Erro", JOptionPane.ERROR_MESSAGE);
				textFieldSeed.setText("");
				return;
			}
			
			Obra obra;
			
			try {
				obra = new ArteGenerativa(titulo, autor, algoritmo, seed);
			} catch (IllegalArgumentException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				
				return;
			}
			
			String message = minhaGaleria.publicarObra(obra);

			if (message.contains("sucesso")) {
				JOptionPane.showMessageDialog(this, message, "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
				textFieldTituloObra.setText("");
				textFieldNomeAutor.setText("");
				textFieldAlgoritmo.setText("");
				textFieldSeed.setText("");
				cards.show(painelPrincipal, "MENU_PUBLICAR");
			} else {
				JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		botaoVoltar.addActionListener(e -> {
			cards.show(painelPrincipal, "INICIO");
		});
		
		JPanel painelBotoes = new JPanel();
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoVoltar);
		
		JPanel painelTituloObra = new JPanel();
		
		painelTituloObra.add(labelTituloObra);
		painelTituloObra.add(textFieldTituloObra);
		
		JPanel painelNomeAutor = new JPanel();
		
		painelNomeAutor.add(labelNomeAutor);
		painelNomeAutor.add(textFieldNomeAutor);
		
		JPanel painelAlgoritmo = new JPanel();
		
		painelAlgoritmo.add(labelAlgoritmo);
		painelAlgoritmo.add(textFieldAlgoritmo);
		
		JPanel painelSeed = new JPanel();
		
		painelSeed.add(labelSeed);
		painelSeed.add(textFieldSeed);
		
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		gbc.gridx = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 0;
		add(labelTitulo, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridy = 1;
		add(painelTituloObra, gbc);

		gbc.gridy = 2;
		add(painelNomeAutor, gbc);

		gbc.gridy = 3;
		add(painelAlgoritmo, gbc);
		
		gbc.gridy = 4;
		add(painelSeed, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 5;
		add(painelBotoes, gbc);
	}
}
