package ui;

import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

public class TelaBuscarExposicao extends JPanel{
	
	public TelaBuscarExposicao(CardLayout cards, JPanel painelPrincipal, JPanel telaExibirObrasExpo) {
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Buscar Exposição");
		
		JLabel labelNomeAutor = new JLabel("Nome da Exposição");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String exposicao = textFieldNomeAutor.getText();
			if (!exposicao.isBlank()) {
				((TelaExibirObrasExposicao) telaExibirObrasExpo).atualizarListaExposicao(exposicao);
				cards.show(painelPrincipal, "LISTAR_EXPO");
			} else {
				JOptionPane.showMessageDialog(this, "Erro! Campo vazio!", "Erro", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		botaoVoltar.addActionListener(e -> {
			cards.show(painelPrincipal, "INICIO");
		});
		
		JPanel painelBotoes = new JPanel();
		JPanel painelNomeAutor = new JPanel();
		
		painelBotoes.add(botaoConfirmar);
		painelBotoes.add(botaoVoltar);
		
		painelNomeAutor.add(labelNomeAutor);
		
		painelNomeAutor.add(textFieldNomeAutor);
		labelTitulo.setFont(labelTitulo.getFont().deriveFont(Font.BOLD, 20f));
		
		gbc.fill = GridBagConstraints.NONE;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.weightx = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		
		gbc.gridy = 0;
		add(labelTitulo, gbc);
		
		gbc.gridy = 1;
		add(painelNomeAutor, gbc);
		
		gbc.gridy = 2;
		add(painelBotoes, gbc);
	}
}
