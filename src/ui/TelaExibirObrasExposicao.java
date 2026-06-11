package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import interfaces.IArtGallery;
import models.Obra;

import java.util.Vector;

import exceptions.ExposicaoNaoEncontradaException;

public class TelaExibirObrasExposicao extends JPanel{
	
	private static final long serialVersionUID = 3194870437005052897L;
	private IArtGallery minhaGaleria;
	private DefaultListModel<Obra> listModelObras;
	private CardLayout cards;
	private JPanel painelPrincipal;
	
	public TelaExibirObrasExposicao (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		this.minhaGaleria = minhaGaleria;
		this.cards = cards;
		this.painelPrincipal = painelPrincipal;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Obras Expostas");
		
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
			this.cards.show(this.painelPrincipal, "INICIO");
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
	
	public void atualizarListaExposicao(String nomeExposicao) {
		listModelObras.clear();
		
		Vector<Obra> obrasExpostas; 
		try {
			obrasExpostas = minhaGaleria.obrasExpostas(nomeExposicao);
			for (Obra obra : obrasExpostas) {
				listModelObras.addElement(obra);
			}
		} catch (ExposicaoNaoEncontradaException erro) {
			JOptionPane.showMessageDialog(this, "Exposição não encontrada!", "ArtGallery diz:", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
