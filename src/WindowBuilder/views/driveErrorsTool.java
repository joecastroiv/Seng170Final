package WindowBuilder.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import WindowBuilder.common.LoginChecker;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Pattern;

import javax.swing.ListSelectionModel;

public class driveErrorsTool extends JFrame {

	private JPanel contentPane;
	private JLabel lblErrorCode;
	private JTextField txtSearchError;
	private JList listPuttyCommands;
	private JScrollPane scrollPaneCommands;
	private JScrollPane scrollPaneParts;
	private JList listPartNumbers;
	private JButton btnSearchButton;
	private JLabel lblPartNumbers;
	private JLabel lblPuttyScripts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					driveErrorsTool frame = new driveErrorsTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public driveErrorsTool() {
		
		initComponents();
		createEvents();
		
	}
	
	public void initComponents() {
		setTitle("H-Drive Error Log Description");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setBounds(100, 100, 475, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblErrorCode = new JLabel("Enter Error:");
		
		txtSearchError = new JTextField();
		txtSearchError.setColumns(10);
		
		scrollPaneCommands = new JScrollPane();
		
		scrollPaneParts = new JScrollPane();
		
		btnSearchButton = new JButton("Search");
		
		lblPuttyScripts = new JLabel("Putty Scripts");
		
		lblPartNumbers = new JLabel("Part Numbers");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneCommands, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
							.addGap(42)
							.addComponent(scrollPaneParts, GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblErrorCode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtSearchError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSearchButton)))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(64)
					.addComponent(lblPuttyScripts, GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
					.addGap(169)
					.addComponent(lblPartNumbers, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
					.addGap(65))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblErrorCode)
						.addComponent(txtSearchError, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearchButton))
					.addGap(5)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPuttyScripts, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lblPartNumbers, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPaneParts, GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPaneCommands, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
							.addGap(1)))
					.addGap(13))
		);
		
		listPartNumbers = new JList();
		listPartNumbers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		listPartNumbers.setModel(new AbstractListModel() {
			String[] values = new String[] {"Drive Motor", "Lift Motor", "Turntable Motor", "ODS", "MEA", "Backplane", "Battery", "UI", "Caster Wheels", "Caster Nyloc Nut", "IRDA", "Drive Wheel", "Disconnect Switch", "Disconnect Housing", "Charge Cartridge", "Turntable Pad", "Front Fairing", "Rear Fairing", "Delta Charger"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneParts.setViewportView(listPartNumbers);
		
		listPuttyCommands = new JList();
		
		listPuttyCommands.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listPuttyCommands.setModel(new AbstractListModel() {
			String[] values = new String[] {"Root Into Drive", "Reboot Station", "Update Firmware 3.1.4_focus3", "Send Drive To Charger", "Troubleshoot Drive Cause Codes", "Reboot Station IDS"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneCommands.setViewportView(listPuttyCommands);
		contentPane.setLayout(gl_contentPane);
		
	}
	
	public void createEvents() {
		
		//Search Error Code # Description
		btnSearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//regex to detect for only numbers being entered into txtarea
				if(Pattern.matches("[0-9]+", txtSearchError.getText()))
				{
					System.out.println("Yes, string contains numbers only");
				}
				else {
					JOptionPane.showMessageDialog(null, "Should only contain numbers, Invalid Error!!!");
				}
				
				JOptionPane.showMessageDialog(null, LoginChecker.isMatch(txtSearchError.getText()), "Error Description", JOptionPane.INFORMATION_MESSAGE);			
								
			}
		});
		
		
		
		
		//Scripts
		
		//Reboot Drive
		listPuttyCommands.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(listPuttyCommands.isSelectedIndex(0)) {
					JTextArea rootIntoDrive = new JTextArea(5,5);
					rootIntoDrive.setText("ssh root@kdu-xxxxxxx");
					
					rootIntoDrive.setWrapStyleWord(true);
					rootIntoDrive.setLineWrap(true);
					rootIntoDrive.setCaretPosition(0);
					rootIntoDrive.setEditable(true);

	                JOptionPane.showMessageDialog(null, new JScrollPane(rootIntoDrive), "Root Into", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		
		//Reboot Station
		listPuttyCommands.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				
				if(listPuttyCommands.isSelectedIndex(1)) {
					JTextArea rebootStation = new JTextArea(5,40);
					rebootStation.setText("sudo -u kivasvs /apollo/env/FCXInfra/bin/mstnexec -s Station-XXXX.GRR1-paKivaA03.ar.corp.amazon.com -c \"reboot & logout\" ");
					
					rebootStation.setWrapStyleWord(true);
					rebootStation.setLineWrap(true);
					rebootStation.setCaretPosition(0);
					rebootStation.setEditable(true);

	                JOptionPane.showMessageDialog(null, new JScrollPane(rebootStation), "Reboot Station", JOptionPane.INFORMATION_MESSAGE);
					
				}
				
			}
		});
		
		//Drive Firmware Update
				listPuttyCommands.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						
						if(listPuttyCommands.isSelectedIndex(2)) {
							JTextArea driveFirmware = new JTextArea(5,40);
							driveFirmware.setText("cd /apollo/env/FCXInfra/var/fcxfw/H3.1.4_focus3"
									+ "sudo -u kivasvs ./update_drive.sh kdu-XXXXXX");
							
							driveFirmware.setWrapStyleWord(true);
							driveFirmware.setLineWrap(true);
							driveFirmware.setCaretPosition(0);
							driveFirmware.setEditable(true);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveFirmware), "Firmware Update", JOptionPane.INFORMATION_MESSAGE);
							
						}
						
					}
				});
						
		
		//Drive Motor Selected
/*		listPartNumbers.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(listPartNumbers.isSelectedIndex(0)) {
					JOptionPane.showMessageDialog(null, "N43327");
				}				
			}
		});
*/
				
//---------------------------------------------------------------------------
//List Commonly Used Parts within AR
//---------------------------------------------------------------------------
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(0)) {
							JTextArea driveMotor = new JTextArea(3,2);
							driveMotor.setText("N43327");
							
							driveMotor.setWrapStyleWord(true);
							driveMotor.setLineWrap(true);
							driveMotor.setCaretPosition(0);
							driveMotor.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveMotor), "Drive Motor", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(1)) {
							JTextArea liftMotor = new JTextArea(3,2);
							liftMotor.setText("N43326");
							
							liftMotor.setWrapStyleWord(true);
							liftMotor.setLineWrap(true);
							liftMotor.setCaretPosition(0);
							liftMotor.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(liftMotor), "Lift Motor", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(2)) {
							JTextArea ttMotor = new JTextArea(3,2);
							ttMotor.setText("N43328");
							
							ttMotor.setWrapStyleWord(true);
							ttMotor.setLineWrap(true);
							ttMotor.setCaretPosition(0);
							ttMotor.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(ttMotor), "Turntable Motor", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(3)) {
							JTextArea driveODS = new JTextArea(3,2);
							driveODS.setText("N43368");
							
							driveODS.setWrapStyleWord(true);
							driveODS.setLineWrap(true);
							driveODS.setCaretPosition(0);
							driveODS.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveODS), "Drive ODS", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(4)) {
							JTextArea driveMEA = new JTextArea(3,2);
							driveMEA.setText("N43367");
							
							driveMEA.setWrapStyleWord(true);
							driveMEA.setLineWrap(true);
							driveMEA.setCaretPosition(0);
							driveMEA.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveMEA), "MEA", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(5)) {
							JTextArea driveBackplane = new JTextArea(3,2);
							driveBackplane.setText("N43329");
							
							driveBackplane.setWrapStyleWord(true);
							driveBackplane.setLineWrap(true);
							driveBackplane.setCaretPosition(0);
							driveBackplane.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveBackplane), "Backplane", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(6)) {
							JTextArea driveBattery = new JTextArea(3,2);
							driveBattery.setText("100627");
							
							driveBattery.setWrapStyleWord(true);
							driveBattery.setLineWrap(true);
							driveBattery.setCaretPosition(0);
							driveBattery.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveBattery), "Battery", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(7)) {
							JTextArea driveUI = new JTextArea(3,2);
							driveUI.setText("N43373");
							
							driveUI.setWrapStyleWord(true);
							driveUI.setLineWrap(true);
							driveUI.setCaretPosition(0);
							driveUI.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveUI), "User Interface 'UI'", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(8)) {
							JTextArea driveCaster = new JTextArea(3,2);
							driveCaster.setText("N43345");
							
							driveCaster.setWrapStyleWord(true);
							driveCaster.setLineWrap(true);
							driveCaster.setCaretPosition(0);
							driveCaster.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveCaster), "Caster Wheel", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(9)) {
							JTextArea driveCasterNyloc = new JTextArea(3,2);
							driveCasterNyloc.setText("N43366");
							
							driveCasterNyloc.setWrapStyleWord(true);
							driveCasterNyloc.setLineWrap(true);
							driveCasterNyloc.setCaretPosition(0);
							driveCasterNyloc.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveCasterNyloc), "Caster Nyloc Nut", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(10)) {
							JTextArea driveIRDA = new JTextArea(3,2);
							driveIRDA.setText("N43365");
							
							driveIRDA.setWrapStyleWord(true);
							driveIRDA.setLineWrap(true);
							driveIRDA.setCaretPosition(0);
							driveIRDA.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveIRDA), "IRDA", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(11)) {
							JTextArea driveWheel = new JTextArea(3,2);
							driveWheel.setText("N43374");
							
							driveWheel.setWrapStyleWord(true);
							driveWheel.setLineWrap(true);
							driveWheel.setCaretPosition(0);
							driveWheel.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(driveWheel), "Drive Motor Wheel", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(12)) {
							JTextArea disconnectSwitch = new JTextArea(3,2);
							disconnectSwitch.setText("100634");
							
							disconnectSwitch.setWrapStyleWord(true);
							disconnectSwitch.setLineWrap(true);
							disconnectSwitch.setCaretPosition(0);
							disconnectSwitch.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(disconnectSwitch), "Disconnect Switch", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(13)) {
							JTextArea disconnectHousing = new JTextArea(3,2);
							disconnectHousing.setText("81818z");
							
							disconnectHousing.setWrapStyleWord(true);
							disconnectHousing.setLineWrap(true);
							disconnectHousing.setCaretPosition(0);
							disconnectHousing.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(disconnectHousing), "Disconnect Housing", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(14)) {
							JTextArea chargeCartridge = new JTextArea(3,2);
							chargeCartridge.setText("N43352");
							
							chargeCartridge.setWrapStyleWord(true);
							chargeCartridge.setLineWrap(true);
							chargeCartridge.setCaretPosition(0);
							chargeCartridge.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(chargeCartridge), "Charge Cartridge", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(15)) {
							JTextArea ttPad = new JTextArea(3,2);
							ttPad.setText("N43372");
							
							ttPad.setWrapStyleWord(true);
							ttPad.setLineWrap(true);
							ttPad.setCaretPosition(0);
							ttPad.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(ttPad), "Turntable Top Pad", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(16)) {
							JTextArea frontFairing = new JTextArea(3,2);
							frontFairing.setText("100617");
							
							frontFairing.setWrapStyleWord(true);
							frontFairing.setLineWrap(true);
							frontFairing.setCaretPosition(0);
							frontFairing.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(frontFairing), "Drive Front Fairing", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(17)) {
							JTextArea rearFairing = new JTextArea(3,2);
							rearFairing.setText("100617");
							
							rearFairing.setWrapStyleWord(true);
							rearFairing.setLineWrap(true);
							rearFairing.setCaretPosition(0);
							rearFairing.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(rearFairing), "Drive Rear Fairing", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
				listPartNumbers.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseReleased(MouseEvent e) {
						if(listPartNumbers.isSelectedIndex(18)) {
							JTextArea deltaCharger = new JTextArea(3,2);
							deltaCharger.setText("N51988");
							
							deltaCharger.setWrapStyleWord(true);
							deltaCharger.setLineWrap(true);
							deltaCharger.setCaretPosition(0);
							deltaCharger.setEditable(false);

			                JOptionPane.showMessageDialog(null, new JScrollPane(deltaCharger), "Delta Charger Head", JOptionPane.INFORMATION_MESSAGE);
						}				
					}
				});
				
	}
}
