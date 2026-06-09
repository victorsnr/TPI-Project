package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import interfaces.IArtGallery;
import models.Obra;

public class TelaRankingObras extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1212494615824207450L;
	private IArtGallery minhaGaleria;
	private DefaultListModel<Obra> listModelObras;

	public TelaRankingObras (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		
		this.minhaGaleria = minhaGaleria;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Ranquing de Notas");
		
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

		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		add(labelTitulo, gbc);
		
		gbc.gridwidth = 1;
		gbc.anchor = GridBagConstraints.EAST;
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(scrollPaneListObras, gbc);
	
		gbc.anchor = GridBagConstraints.WEST;
		gbc.gridx = 1;
		add(textAreaDetalhesObra, gbc);
		
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridwidth = 2;
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(botaoVoltar, gbc);
	}
	
	public void atualizarListaRanking() {
		listModelObras.clear();
		
		for (Obra obra : minhaGaleria.topObras()) {
	        listModelObras.addElement(obra);
	    }
	}
}
