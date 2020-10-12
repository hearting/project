package GUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import BLL.BookAdmin;
import Model.Book;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.awt.Color;


public class newBookPane extends JPanel implements ActionListener {
	String path="img\\cover\\huancun";
	String fileType=".jpg";
	static int a=0;
	
	
	static File bkPhoto;
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
	BookAdmin bll=new BookAdmin();
	
	
	Font font = new Font("宋体", Font.PLAIN, 20);
	Font font1 = new Font("宋体", Font.PLAIN, 18);
	
	private JLabel labels[]= {
			new JLabel("图书序号"),new JLabel("索书号"),new JLabel("图书名称"),
			new JLabel("图书作者"),new JLabel("出版社"),new JLabel("出版日期"),
			new JLabel("ISBN"),new JLabel("分类号"),
			new JLabel("语种"),new JLabel("图书页数"),new JLabel("图书价格"),
			new JLabel("入馆日期"),new JLabel("内容简介"),
	};
	private JTextArea briefTextArea=new JTextArea();
	private JLabel coverLabel=new JLabel("封面图");
	
	private JButton insertBtu=new JButton("添加");
	private JButton cancelBtu=new JButton("取消");
	private JButton returnBtu=new JButton("返回");
	private JButton LoadImageBtu=new JButton("添加图片");

	
	JTextField startNumText=new JTextField();
	JTextField selectBookNumText=new JTextField();
	JTextField bookNameText=new JTextField();
	JTextField authorText=new JTextField();
	JTextField publishText=new JTextField();
	JTextField publishDateText=new JTextField();
	JTextField ISBNText=new JTextField();
	JTextField typeNumText=new JTextField();
	JComboBox languageBox=new JComboBox();
	JTextField bookPageNumText=new JTextField();
	JTextField bookPriceText=new JTextField();
	JTextField inLibDateText=new JTextField();
	

	//[start] 设置提示标签
	private void setLabels() {
		for(int i=0;i<labels.length;i++) {
			labels[i].setFont(font1);
			if(i<12) {
			labels[i].setBounds(20, 30+i*40, 150, 30);
			}
			else {
				labels[i].setBounds(300+(i-11)*300, 30, 150, 30);	
			}
			add(labels[i]);
		}
	}
	//[end]
	
	//[start]设置文本框控件
	private void setTextField() {
		startNumText.setEditable(false);
		startNumText.setFont(font1);
		startNumText.setBounds(140,30,200,30);
		add(startNumText);
		startNumText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
				String txt=startNumText.getText();
				StringBuffer text=new StringBuffer("");
				for(int i=0;i<txt.length();i++) {
					if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
						text.append(txt.charAt(i));
					}
				}
				startNumText.setText(text.toString());
			}
		});
		
		selectBookNumText.setFont(font1);
		selectBookNumText.setBounds(140,70,200,30);
		add(selectBookNumText);
		
		bookNameText.setFont(font1);
		bookNameText.setBounds(140,110,200,30);
		add(bookNameText);
		
		authorText.setFont(font1);
		authorText.setBounds(140,150,200,30);
		add(authorText);
		
		publishText.setFont(font1);
		publishText.setBounds(140,190,200,30);
		add(publishText);
		
		publishDateText.setFont(font1);
		publishDateText.setBounds(140,230,200,30);
		add(publishDateText);
		
		ISBNText.setFont(font1);
		ISBNText.setBounds(140,270,200,30);
		add(ISBNText);
		ISBNText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int keyChar=e.getKeyChar();
				if (keyChar<KeyEvent.VK_0 || keyChar>KeyEvent.VK_9) {
					e.consume();
				} 
			}
		
			@Override
			public void keyReleased(KeyEvent e) {
				String txt=ISBNText.getText();
				StringBuffer text=new StringBuffer("");
				for(int i=0;i<txt.length();i++) {
					if(txt.charAt(i)>='0'&&txt.charAt(i)<='9') {
						text.append(txt.charAt(i));
					}
				}
				ISBNText.setText(text.toString());
			}
		});
		
		typeNumText.setFont(font1);
		typeNumText.setBounds(140,310,200,30);
		add(typeNumText);
		
		languageBox.setFont(font1);
		languageBox.setModel(new DefaultComboBoxModel(new String[] {"中文","英文","日文","俄文","德文","法文"}));
		languageBox.setBounds(140,350,200,30);
		add(languageBox);
		
		bookPageNumText.setFont(font1);
		bookPageNumText.setBounds(140,390,200,30);
		add(bookPageNumText);
		
		bookPriceText.setFont(font1);
		bookPriceText.setBounds(140,430,200,30);
		add(bookPriceText);
		
		inLibDateText.setFont(font1);
		inLibDateText.setBounds(140,470,200,30);
		add(inLibDateText);	
	}
	//[end]

	//[start]构造函数
	public newBookPane() {
		setBackground(Color.LIGHT_GRAY);
		setBorder(new TitledBorder(null, "新书入库", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setBounds(4, 4, 1276, 730);
		setLabels();
		setTextField();
		briefTextArea.setLineWrap(true);
		briefTextArea.setFont(new Font("Monospaced", Font.PLAIN, 15));
		briefTextArea.setBounds(470, 70, 340, 450);
		add(briefTextArea);
		coverLabel.setBounds(882, 70, 355, 450);
		add(coverLabel);
		insertBtu.setFont(font1);
		insertBtu.setBounds(470, 550, 100, 30);
		add(insertBtu);
		insertBtu.addActionListener(this);
		
		cancelBtu.setFont(font1);
		cancelBtu.setBounds(600, 550, 100, 30);
		add(cancelBtu);
		cancelBtu.addActionListener(this);
		
		returnBtu.setFont(font1);
		returnBtu.setBounds(730, 550, 100, 30);
		add(returnBtu);
		returnBtu.addActionListener(this);
		
		LoadImageBtu.setFont(font1);
		LoadImageBtu.setBounds(900, 550, 140, 30);
		add(LoadImageBtu);
		LoadImageBtu.addActionListener(this);
	}
	//[end]
	
	//[start]使用控件中的内容初始化图书对象	
	private Book setBookinfo() throws Exception {
		Book bk=new Book();
		String bkCode=selectBookNumText.getText();
		String bkName=bookNameText.getText();
		String bkAuthor=authorText.getText();
		String publish=publishText.getText();
		String pubDate=publishDateText.getText();
		String bkBrief=briefTextArea.getText();
		String ISBN=ISBNText.getText();
		String typeNum=typeNumText.getText();
		
		int language=languageBox.getSelectedIndex();
		int pages=Integer.parseInt(bookPageNumText.getText());
		float price=Float.parseFloat(bookPriceText.getText());
		if(bkPhoto!=null) {
			bk.setBkCover(fileToByte(bkPhoto));
		}
		bk.setBkCode(bkCode);
		bk.setBkName(bkName);
		bk.setBkAythor(bkAuthor);
		bk.setBkPress(publish);
		bk.setBkDatePress(pubDate);
		bk.setBkBrief(bkBrief);
		bk.setBkISBN(ISBN);
		bk.setBkCatalog(typeNum);
		bk.setBkLanguage(language);
		bk.setBkPrice(price);
		bk.setBkPages(pages);
		return bk;
	}
	//[end]
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==LoadImageBtu) {
			JFileChooser chooser = new JFileChooser("imgy\\cover");
	        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        chooser.showDialog(new JLabel(), "选择");
	        bkPhoto = chooser.getSelectedFile();
	        ImageIcon icon=new ImageIcon(bkPhoto.getPath()); 
	        coverLabel.setIcon(icon);  
		}
		
		else if(e.getSource()==insertBtu) {
			if(selectBookNumText.getText().equals("")
					||bookNameText.getText().equals("")
					||authorText.getText().equals("")
					||publishText.getText().equals("")
					||publishDateText.getText().equals("")
					||ISBNText.getText().equals("")
					||bookPageNumText.getText().equals("")
					||typeNumText.getText().equals("")
					||bookPriceText.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "信息填写不全", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
				return ;
			}
			Book bk;
			try {
				bk = setBookinfo();
				bll.insertBook(bk);
				JOptionPane.showMessageDialog(null, "添加成功", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
		}
	}


	private  byte[] fileToByte(File img) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes;
		try {
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "jpg", baos);
			bytes = baos.toByteArray();
			System.err.println(bytes.length);
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return null;
	}
	
}
