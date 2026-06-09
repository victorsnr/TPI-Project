package ui;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;

import exceptions.NotaInvalidaException;

import interfaces.IArtGallery;

import models.Avaliacao;

public class TelaAvaliarObra extends JPanel {
	
	public TelaAvaliarObra (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Remover Obra");
		
		JLabel labelTituloObra = new JLabel("Título da Obra:");
		JTextField textFieldTituloObra = new JTextField(20);
		
		JLabel labelNomeAutor = new JLabel("Autor:");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JLabel labelUsername = new JLabel("Username:");
		JTextField textFieldUsername = new JTextField(20);
		
		JLabel labelNota = new JLabel("Nota:");
		SpinnerNumberModel modeloNota = new SpinnerNumberModel(0, 0, 10, 1);
		JSpinner spinnerNota = new JSpinner(modeloNota);
		
		JLabel labelComentario = new JLabel("Comentario");
		JTextArea textAreaComentario = new JTextArea(5, 20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String titulo = textFieldTituloObra.getText();
			String autor = textFieldNomeAutor.getText()	;
			String username = textFieldUsername.getText();	
			int nota = (Integer) spinnerNota.getValue();
			String comentario = textAreaComentario.getText();	
			
			Avaliacao avaliacao;
			try {
				avaliacao = new Avaliacao(username, nota, comentario);
			} catch (IllegalArgumentException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (NotaInvalidaException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String message = minhaGaleria.avaliarObra(titulo, autor, avaliacao);
			if (message.contains("sucesso")) {
				JOptionPane.showMessageDialog(this, message, "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
				textFieldTituloObra.setText("");
				textFieldNomeAutor.setText("");
				cards.show(painelPrincipal, "INICIO");
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
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 3;
		add(labelUsername, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(textFieldUsername, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 4;
		add(labelNota, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(spinnerNota, gbc);
		
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 5;
		add(labelComentario, gbc);
		
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		gbc.weightx = 1;
		add(textAreaComentario, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 6;
		gbc.gridwidth = 2;
		add(painelBotoes, gbc);
	}
}
