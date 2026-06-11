package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Vector;

import javax.swing.*;

import exceptions.ExposicaoExistenteException;
import interfaces.IArtGallery;
import models.Exposicao;
import models.Obra;

import exceptions.ExposicaoNaoEncontradaException;
import exceptions.ObraJaCadastradaException;

public class TelaAdicionarObraExposicao extends JPanel{
	
	private IArtGallery minhaGaleria;
	private DefaultListModel<Obra> listModelObras;
	
	public TelaAdicionarObraExposicao (CardLayout cards, JPanel painelPrincipal, IArtGallery minhaGaleria) {
		this.minhaGaleria = minhaGaleria;
		
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Criar Exposição");
		
		JLabel labelNomeExpo = new JLabel("Nome da Exposição:");
		JTextField textFieldNomeExpo = new JTextField(20);
		
		this.listModelObras = new DefaultListModel<>();
		
		JList<Obra> listObras = new JList<>(listModelObras);
		
		listObras.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
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
		JButton botaoConfirmar = new JButton("Confirmar");
		
		botaoConfirmar.addActionListener(e -> {
			String nomeExpo = textFieldNomeExpo.getText();
			if (!nomeExpo.isBlank()) {
				Vector<Obra> obrasSelecionadas = new Vector<>(listObras.getSelectedValuesList());
				if (!obrasSelecionadas.isEmpty()) {	
					try {
						for (Obra obra : obrasSelecionadas) {
							minhaGaleria.adicionarObraNaExposicao(nomeExpo, obra);
						}
						JOptionPane.showMessageDialog(this, "Obra(s) adicionadas com sucesso!", "ArtGallery diz:", JOptionPane.INFORMATION_MESSAGE);
						textFieldNomeExpo.setText("");
						cards.show(painelPrincipal, "INICIO");
					} catch (ExposicaoNaoEncontradaException erro) {
						JOptionPane.showMessageDialog(this, erro.getMessage(), "ArtGallery diz:", JOptionPane.ERROR_MESSAGE);
					} catch (ObraJaCadastradaException erro) {
						JOptionPane.showMessageDialog(this, erro.getMessage(), "ArtGallery diz:", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "Erro! Nenhuma obra selecionada!", "ArtGallery diz:", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Erro! Campos em branco", "ArtGallery diz:", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		botaoVoltar.addActionListener(e -> {
			textAreaDetalhesObra.setText("");
			cards.show(painelPrincipal, "INICIO");
		});
		
		textAreaDetalhesObra.setEditable(false);
		textAreaDetalhesObra.setLineWrap(true);
		textAreaDetalhesObra.setWrapStyleWord(true);
		
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		JScrollPane scrollPaneListObras = new JScrollPane(listObras);
		
		JPanel painelNomeExpo = new JPanel();
		
		painelNomeExpo.add(labelNomeExpo);
		painelNomeExpo.add(textFieldNomeExpo);
		
		JPanel painelInfoObra = new JPanel();
		
		painelInfoObra.add(scrollPaneListObras);
		painelInfoObra.add(textAreaDetalhesObra);
		
		JPanel painelBotoes = new JPanel();
		
		painelBotoes.add(botaoVoltar);
		painelBotoes.add(botaoConfirmar);
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		
		
		gbc.gridy = 0;
		add(labelTitulo, gbc);

		gbc.gridy = 1;
		add(painelNomeExpo, gbc);
		
		gbc.gridy = 2;
		add(painelInfoObra, gbc);
		
		gbc.gridy = 3;
		add(painelBotoes, gbc);
	}
	
	public void atualizarLista() {
		listModelObras.clear();
		
		for (Obra obra : minhaGaleria.listarObras()) {
	        listModelObras.addElement(obra);
	    }
	}
}
