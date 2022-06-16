package view;

import javax.swing.JPanel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import controller.VeiculoController;
import model.seletor.SeletorVeiculo;
import model.vo.MotoristaVO;
import model.vo.VeiculoVO;

import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PanelConsultaVeiculo extends JPanel {
	private JTable table;
	private JTextField textRenavam;
	private JTextField textPlaca;
	private JTextField textMarca;
	private VeiculoController veiculoController = new VeiculoController();
	private List<VeiculoVO> veiculos = new ArrayList<VeiculoVO>();
	SeletorVeiculo selecionado = new SeletorVeiculo();
	private JButton btnConsultar;
	
	/**
	 * Create the panel.
	 */
	public PanelConsultaVeiculo() {
		setBackground(new Color(112, 128, 144));
		setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.GROWING_BUTTON_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(52dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(62dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(31dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(51dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(PanelConsultaVeiculo.class.getResource("/icon/floresta.jpg")));
		add(lblNewLabel, "1, 1, 20, 5");
		
		JLabel lblNewLabel_1 = new JLabel("CONSULTA DE VEICULO");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.BOLD, 30));
		add(lblNewLabel_1, "1, 6, 20, 1, center, center");
		
		JLabel lblNewLabel_2 = new JLabel("RENAVAM");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_2, "4, 8, left, default");
		
		JLabel lblNewLabel_3 = new JLabel("PLACA");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_3, "10, 8, left, default");
		
		JLabel lblNewLabel_4 = new JLabel("MARCA");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		add(lblNewLabel_4, "16, 8, left, default");
		
		textRenavam = new JTextField();
		add(textRenavam, "4, 10, 3, 2, fill, default");
		textRenavam.setColumns(10);
		
		textPlaca = new JTextField();
		add(textPlaca, "10, 10, 3, 2, fill, default");
		textPlaca.setColumns(10);
		
		textMarca = new JTextField();
		add(textMarca, "16, 10, 3, 2, fill, default");
		textMarca.setColumns(10);
		
		table = new JTable();
		add(table, "3, 14, 16, 1, fill, fill");
		veiculos=veiculoController.consulta(selecionado);
		atualizarTabela();

		JButton btnNewButton = new JButton("EXCLUIR");
		add(btnNewButton, "4, 17, 3, 3");
		
		btnConsultar = new JButton("CONSULTAR");
		btnConsultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selecionado.setMarca(textMarca.getText());
				selecionado.setPlaca(textPlaca.getText());
				selecionado.setRenavam(textRenavam.getText());
				atualizarTabela();
			}
		});
		add(btnConsultar, "10, 17, 3, 3");
		
		JButton btnNewButton_1 = new JButton("IMPRIMIR");
		add(btnNewButton_1, "16, 17, 3, 3");

	}

	protected void atualizarTabela() { 
		veiculos=veiculoController.consulta(selecionado);
		
		table.setModel(new DefaultTableModel(new String[][] { 
			{ "Renavam","ano","marca","modelo","status","placa" }, },
			new String[] {"Renavam","ano","marca","modelo","status","placa"}));
		
		DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		
		for(VeiculoVO veiculo: veiculos) {
			String[] novaLinha = new String[] {
				veiculo.getRenavam(),
				veiculo.getAno()+"",
				veiculo.getMarca(),
				veiculo.getModelo(),
				veiculo.isStatus()+"",
				veiculo.getPlaca()
				
			};
			
			modelo.addRow(novaLinha);
		}
		
	}

	public JButton getBtnConsultar() {
		return btnConsultar;
	}

	public void setBtnConsultar(JButton btnConsultar) {
		this.btnConsultar = btnConsultar;
	}


}
