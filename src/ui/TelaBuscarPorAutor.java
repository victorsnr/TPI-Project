package ui;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TelaBuscarPorAutor extends JPanel {

	private static final long serialVersionUID = 6408546676571617947L;

	public TelaBuscarPorAutor (CardLayout cards, JPanel painelPrincipal, JPanel telaListarObrasAutor) {	
		setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		JLabel labelTitulo = new JLabel("Publicar Arte Generaativa");
		
		JLabel labelNomeAutor = new JLabel("Nome do Autor");
		JTextField textFieldNomeAutor = new JTextField(20);
		
		JButton botaoConfirmar = new JButton("Confirmar");
		JButton botaoVoltar = new JButton("Voltar");
		
		botaoConfirmar.addActionListener(e -> {
			String autor = textFieldNomeAutor.getText();
			if (!autor.isBlank()) {
				((TelaListarObrasAutor) telaListarObrasAutor).atualizarListaAutor(autor);
				cards.show(painelPrincipal, "LISTAR_AUTOR");
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
