package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import BLL.BookAdmin;
import Model.Book;

public class ChangeBookInfoPane extends JPanel implements ActionListener{
	static String path="img\\cover\\huancun";
	static String fileType=".jpg";
	static int a=0;
	static BookAdmin bll=new BookAdmin();
	static File bkPhoto;
	//[start] 控件定义
	Font font = new Font("宋体", Font.PLAIN, 20);
	Font font1 = new Font("宋体", Font.PLAIN, 18);
	
	private JLabel labels[]= {
			new JLabel("图书ID"),new JLabel("索书号"),new JLabel("图书名称"),
			new JLabel("图书作者"),new JLabel("出版社"),new JLabel("出版日期"),
			new JLabel("ISBN"),new JLabel("分类号"),
			new JLabel("语种"),new JLabel("图书页数"),new JLabel("图书价格"),
			new JLabel("入馆日期"),new JLabel("图书状态"),new JLabel(""),new JLabel(""),
	};
	private static JTextArea briefTextArea=new JTextArea();
	private static JLabel coverLabel=new JLabel("封面图");
	
	private JButton saveBtn=new JButton("保存");
	private JButton returnBtn=new JButton("返回");
	private JButton LoadImageBtn=new JButton("添加图片"); 
	private JButton DestroyBtn=new JButton("销毁");
	private JButton SellOutBtn=new JButton("变卖");
	private JButton LostBtn=new JButton("遗失");

	static JTextField startNumText= new JTextField();
	static JTextField selectBookNumText=new JTextField();
	static JTextField bookNameText=new JTextField();
	static JTextField authorText=new JTextField();
	static JTextField publishText=new JTextField();
	static JTextField publishDateText=new JTextField();
	
	static JTextField ISBNText=new JTextField();
	static JComboBox typeNameBox=new JComboBox();
	
	static JTextField typeNumText=new JTextField();
	static JComboBox languageBox=new JComboBox();
	
	static JTextField bookPageNumText=new JTextField();
	static JTextField bookPriceText=new JTextField();
	
	static JTextField inLibDateText=new JTextField();
	static JTextField bookCountText=new JTextField();
	static JTextField bookStautsText=new JTextField();
	//[end]
	
	//[start] 将图书信息显示
	public static void setResult(int id) throws Exception {
		Book bk;
		bk=bll.getBookByID(id);
		startNumText.setText(String.valueOf(bk.getBkID()));
		selectBookNumText.setText(bk.getBkCode());
		bookNameText.setText(bk.getBkName());
		authorText.setText(bk.getBkAythor());
		publishText.setText(bk.getBkPress());
		publishDateText.setText(bk.getBkDatePress());
		ISBNText.setText(bk.getBkISBN());
		typeNumText.setText(bk.getBkCatalog());
		languageBox.setSelectedIndex(bk.getBkLanguage());
		bookPageNumText.setText(String.valueOf(bk.getBkPages()));
		bookPriceText.setText(String.valueOf(bk.getBkPrice()));
		inLibDateText.setText(String.valueOf(bk.getBkDateIn()));
		bkPhoto=ByteToFile(bk.getBkCover());
		briefTextArea.setText(bk.getBkBrief());
		bookStautsText.setText(bk.getBkStatus());
		if(bkPhoto!=null) {
			ImageIcon icon=new ImageIcon(bkPhoto.getPath());  
			coverLabel.setIcon(icon); 
	    }  
		else {
			coverLabel.setIcon(null); 
		}
	}
	//[end]

	//[start]设置提示标签
	public void setLabels() {
		for(int i=0;i<15;i++) {
			labels[i].setFont(font1);
			if(i<13) {
			labels[i].setBounds(20, 30+i*40, 150, 30);
			}
			else {
				labels[i].setBounds(300+(i-12)*300, 30, 150, 30);	
			}
			add(labels[i]);
		}
	}
	//[end]
	
	
	//[start] 设置文本框控件控件）
	public void setTextField() {
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
		languageBox.setModel(new DefaultComboBoxModel(new String[] {"0-中文","1-英文","2-日文","3-俄文","4-德文","5-法文"}));
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
		
		bookStautsText.setFont(font1);
		bookStautsText.setBounds(140,510,200,30);
		add(bookStautsText);	
	}
	//[end]
	
	//[start]用控件显示的图书信息初始化图书对象
	private Book setBookinfo() throws Exception {
		Book bk=new Book();
		bk.setBkID(Integer.parseInt(startNumText.getText()));
		String bkCode=selectBookNumText.getText();
		String bkName=bookNameText.getText();
		String bkAuthor=authorText.getText();
		String publish=publishText.getText();
		String pubDate=publishDateText.getText();
		String bkBrief=briefTextArea.getText();
		String ISBN=ISBNText.getText();
		String typeNum=typeNumText.getText();
		String bkStatus=bookStautsText.getText();
		
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
		bk.setBkStatus(bkStatus);
		return bk;
	}
	//[end]
	
	
	//[start]构造函数(设置按钮控件)
	public ChangeBookInfoPane() {
		setBorder(new TitledBorder(null, "图书信息维护", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setLayout(null);
		setBounds(4, 4, 1276, 730);
		
		
		setLabels();
		setTextField();
		briefTextArea.setLineWrap(true);
		
		briefTextArea.setBounds(470, 70, 340, 450);
		add(briefTextArea);
		
		coverLabel.setBounds(882, 70, 355, 450);
		add(coverLabel);
		
		saveBtn.setFont(font1);
		saveBtn.setBounds(470, 550, 100, 30);
		add(saveBtn);
		saveBtn.addActionListener(this);
		
		returnBtn.setFont(font1);
		returnBtn.setBounds(592, 550, 100, 30);
		add(returnBtn);
		returnBtn.addActionListener(this);
		
		LoadImageBtn.setFont(font1);
		LoadImageBtn.setBounds(900, 550, 140, 30);
		add(LoadImageBtn);
		LoadImageBtn.addActionListener(this);
		
		DestroyBtn.setFont(font1);
		DestroyBtn.setBounds(370, 650, 140, 30);
		add(DestroyBtn);
		DestroyBtn.addActionListener(this);
		
		SellOutBtn.setFont(font1);
		SellOutBtn.setBounds(570, 650, 140, 30);
		add(SellOutBtn);
		SellOutBtn.addActionListener(this);
		
		LostBtn.setFont(font1);
		LostBtn.setBounds(770, 650, 140, 30);
		add(LostBtn);
		LostBtn.addActionListener(this);
	}
	//[end]

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==LoadImageBtn) {
			JFileChooser chooser = new JFileChooser("imgy\\cover");
	        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	        chooser.showDialog(new JLabel(), "选择");
	        bkPhoto = chooser.getSelectedFile();
	        if(bkPhoto!=null) {
	        ImageIcon icon=new ImageIcon(bkPhoto.getPath()); 
	        coverLabel.setIcon(icon); 
	        }
		}
		
		else if(e.getSource()==saveBtn) {
			try {
				Book bk =setBookinfo();
				bll.updateBook(bk);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		else if(e.getSource()==LostBtn) {
			if(bookStautsText.getText().trim().equals("在馆")||bookStautsText.getText().trim().equals("借出")) {
				bookStautsText.setText("遗失");
			}
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可修改为“遗失”", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
			
		}
		
		else if(e.getSource()==SellOutBtn) {
			if(bookStautsText.getText().trim().equals("在馆")) {
			bookStautsText.setText("变卖");
			}
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可变卖", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
		}
		
		else if(e.getSource()==DestroyBtn) {
			if(bookStautsText.getText().trim().equals("在馆")) {
				bookStautsText.setText("销毁");
			}
			else {
				JOptionPane.showMessageDialog(null, "当前状态不可销毁", "提示", JOptionPane.YES_NO_CANCEL_OPTION);
			}
		}
	
	}

	
	public  byte[] fileToByte(File img) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] bytes;
		try {
			BufferedImage bi;
			bi = ImageIO.read(img);
			ImageIO.write(bi, "jpg", baos);
			bytes = baos.toByteArray();
			//System.err.println(bytes.length);
			return bytes;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			baos.close();
		}
		return null;
	}
	
	
	public static File ByteToFile(byte[] bytes)throws Exception{ 
		if(bytes!=null) {
		delAllFile("img");
		a++;
		File file=new File(path+a+fileType);
		InputStream in = new ByteArrayInputStream(bytes); 
        FileOutputStream fos = null;
        try {  
            fos = new FileOutputStream(file);
            int len = 0;
            byte[] buf = new byte[9102];
            while ((len = in.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            in.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {   
            e.printStackTrace();   
        }
       	return file; }
		return null;
    }  

	public static  boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				flag = true;
			}
		}
		return flag;
	}	
	
}
