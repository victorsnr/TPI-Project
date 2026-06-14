package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import interfaces.IArtGallery;
import models.PinturaDigital;
import models.Obra;

public class TelaPublicarPinturaDigital extends JPanel {
	
	private static final long serialVersionUID = 1927797722468237509L;

	public TelaPublicarPinturaDigital (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Publicar Pintura Digital");
		
		JLabel labelTituloObra = new JLabel("Título da Obra:");
		JTextField textFieldTituloObra = new JTextField(20);
		
		JLabel labelNomeAutor = new JLabel("Nome do Autor");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JLabel labelResolucao = new JLabel("Resolução: ");
		JTextField textFieldResolucao = new JTextField(20);
		
		JLabel labelSoftware = new JLabel("Engine:");
		JTextField textFieldSoftware = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String titulo = textFieldTituloObra.getText();		
			String autor = textFieldNomeAutor.getText();
			String resolucao = textFieldResolucao.getText();	
			String software = textFieldSoftware.getText();
			
			Obra obra;
			try {
				obra = new PinturaDigital(titulo, autor, resolucao, software);
			} catch (IllegalArgumentException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String message = minhaGaleria.publicarObra(obra);

			if (message.contains("sucesso")) {
				JOptionPane.showMessageDialog(this, message, "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
				textFieldTituloObra.setText("");
				textFieldNomeAutor.setText("");
				textFieldResolucao.setText("");
				textFieldSoftware.setText("");
				cards.show(painelPrincipal, "MENU_PUBLICAR");
			} else {
				JOptionPane.showMessageDialog(this, message, "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		botaoVoltar.addActionListener(e -> {
			cards.show(painelPrincipal, "MENU_PUBLICAR");
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
		
		JPanel painelResolucao = new JPanel();
		
		painelResolucao.add(labelResolucao);
		painelResolucao.add(textFieldResolucao);
		
		JPanel painelSoftware = new JPanel();
		
		painelSoftware.add(labelSoftware);
		painelSoftware.add(textFieldSoftware);	
		
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
		add(painelResolucao, gbc);
		
		gbc.gridy = 4;
		add(painelSoftware, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 5;
		add(painelBotoes, gbc);
	}
}
