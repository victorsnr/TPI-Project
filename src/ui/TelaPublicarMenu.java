package ui;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

public class TelaPublicarMenu extends JPanel {

	private static final long serialVersionUID = -6708757222616664439L;

	public TelaPublicarMenu (CardLayout cards, JPanel painelPrincipal) {
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Selecione o tipo de arte");
		
		JButton botaoArteGenerativa = new JButton("Arte Generativa");
		JButton botaoModelagem3D = new JButton("Modelagem 3D");
		JButton botaoPinturaDigital = new JButton("Pintura Digital");
		JButton botaoVoltar = new JButton("Voltar");
		
		
		botaoArteGenerativa.addActionListener(e -> {
			cards.show(painelPrincipal, "PUBLICAR_AG");
		});
		
		botaoModelagem3D.addActionListener(e -> {
			cards.show(painelPrincipal, "PUBLICAR_MD");
		});
		
		botaoPinturaDigital.addActionListener(e -> {
			cards.show(painelPrincipal, "PUBLICAR_PD");
		});
		
		botaoVoltar.addActionListener(e -> {
			cards.show(painelPrincipal, "INICIO");
		});
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.insets = new Insets(5, 5, 5, 5);	
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(labelTitulo, gbc);
		
		gbc.gridy = 1;
		add(botaoArteGenerativa, gbc);
		
		gbc.gridy = 2;
		add(botaoModelagem3D, gbc);
		
		gbc.gridy = 3;
		add(botaoPinturaDigital, gbc);
		
		gbc.gridy = 4;
		add(botaoVoltar, gbc);
	}
	
}
