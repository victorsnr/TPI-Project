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
import models.Modelagem3D;
import models.Obra;

import exceptions.QuantidadeInvalidaException;

public class TelaPublicarModelagem3D extends JPanel{

	private static final long serialVersionUID = -8046725571077164232L;

public TelaPublicarModelagem3D (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {	
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Publicar Modelagem 3D");
		
		JLabel labelTituloObra = new JLabel("Título da Obra:");
		JTextField textFieldTituloObra = new JTextField(20);
		
		JLabel labelNomeAutor = new JLabel("Nome do Autor");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JLabel labelNumPoligonos = new JLabel("Número de Polígonos");
		JTextField textFieldNumPoligonos = new JTextField(20);
		
		JLabel labelEngine = new JLabel("Engine:");
		JTextField textFieldEngine = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String titulo = textFieldTituloObra.getText();		
			String autor = textFieldNomeAutor.getText();
			String engine = textFieldEngine.getText();
			
			int poligonos;
			try {
				poligonos = Integer.parseInt(textFieldNumPoligonos.getText());
			} catch (NumberFormatException erro) {
				JOptionPane.showMessageDialog(this, "Poligonos deve conter apenas números", "Erro", JOptionPane.ERROR_MESSAGE);
				textFieldNumPoligonos.setText("");
				return;
			}
			
			Obra obra;
			try {
				obra = new Modelagem3D(titulo, autor, poligonos, engine);
			} catch (IllegalArgumentException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (QuantidadeInvalidaException erro) {
				JOptionPane.showMessageDialog(this, erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				textFieldNumPoligonos.setText("");
				return;
			}
			
			String message = minhaGaleria.publicarObra(obra);

			if (message.contains("sucesso")) {
				JOptionPane.showMessageDialog(this, message, "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
				textFieldTituloObra.setText("");
				textFieldNomeAutor.setText("");
				textFieldNumPoligonos.setText("");
				textFieldEngine.setText("");
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
		
		JPanel painelNumPoligonos = new JPanel();
		
		painelNumPoligonos.add(labelNumPoligonos);
		painelNumPoligonos.add(textFieldNumPoligonos);
		
		JPanel painelEngine = new JPanel();
		
		painelEngine.add(labelEngine);
		painelEngine.add(textFieldEngine);
		
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
		add(painelNumPoligonos, gbc);
		
		gbc.gridy = 4;
		add(painelEngine, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridy = 5;
		add(painelBotoes, gbc);
	}
}
