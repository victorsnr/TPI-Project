package ui;

import javax.swing.*;

import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;

import models.Obra;

import interfaces.IArtGallery;

public class TelaExibirObrasAtivas extends JPanel {

	private static final long serialVersionUID = -4140216729883756612L;
	
	private IArtGallery minhaGaleria;
	private DefaultListModel<Obra> listModelObras;
	
	public TelaExibirObrasAtivas (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		
		this.minhaGaleria = minhaGaleria;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Obras Cadastradas");
		
		this.listModelObras = new DefaultListModel<>();
		
		JList<Obra> listObras = new JList<>(listModelObras);
		
		JTextArea textAreaDetalhesObra = new JTextArea(10, 20);
		
		listObras.addListSelectionListener(e -> {
		    if (!e.getValueIsAdjusting()) {
		        Obra obra = listObras.getSelectedValue();
		        if (obra != null) {
		        	textAreaDetalhesObra.setText(obra.exibirDetalhes());
		        }
		   }
		});
		
		JButton botaoVoltar = new JButton("Voltar");

		botaoVoltar.addActionListener(e -> {
			textAreaDetalhesObra.setText("");
			cards.show(painelPrincipal, "INICIO");
		});
		
		textAreaDetalhesObra.setEditable(false);
		textAreaDetalhesObra.setLineWrap(true);
		textAreaDetalhesObra.setWrapStyleWord(true);
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		JScrollPane scrollPaneListObras = new JScrollPane(listObras);
		
		JPanel painelInfoObra = new JPanel();
		
		painelInfoObra.add(scrollPaneListObras);
		painelInfoObra.add(textAreaDetalhesObra);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		
		gbc.gridy = 0;
		add(labelTitulo, gbc);
		
		gbc.gridy = 1;
		add(painelInfoObra, gbc);
		
		gbc.gridy = 2;
		add(botaoVoltar, gbc);
	}
	
	public void atualizarLista() {
		listModelObras.clear();
		
		for (Obra obra : minhaGaleria.listarObras()) {
	        listModelObras.addElement(obra);
	    }
	}
}
