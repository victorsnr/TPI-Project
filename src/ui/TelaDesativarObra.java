package ui;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import interfaces.IArtGallery;

public class TelaDesativarObra extends JPanel {

	private static final long serialVersionUID = 5105995617033578716L;

	public TelaDesativarObra (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Remover Obra");
		
		JLabel labelTituloObra = new JLabel("Título da Obra:");
		JTextField textFieldTituloObra = new JTextField(20);
		
		JLabel labelNomeAutor = new JLabel("Autor:");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String titulo = textFieldTituloObra.getText();
			String autor = textFieldNomeAutor.getText();
			
			if (!titulo.isBlank() && !autor.isBlank()) {
				String message = minhaGaleria.removerObra(titulo, autor);
				if (message.contains("sucesso")) {
					JOptionPane.showMessageDialog(this, message, "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
					textFieldTituloObra.setText("");
					textFieldNomeAutor.setText("");
					cards.show(painelPrincipal, "INICIO");
				} else {
					JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Erro! Campos faltando!", "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
		});
		
		botaoVoltar.addActionListener(e -> {
			cards.show(painelPrincipal, "INICIO");
		});
		
		JPanel painelBotoes = new JPanel();
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoVoltar);
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(labelTitulo, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(labelTituloObra, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(textFieldTituloObra, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(labelNomeAutor, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(textFieldNomeAutor, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		add(painelBotoes, gbc);
	}
}
