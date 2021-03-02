import java.awt.event.*;
import java.awt.Font.*;
import javax.swing.*;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.filechooser.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.text.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate; 
import java.time.LocalTime; 
import javax.swing.JDialog.*;
   
class NotepadMenu extends JFrame implements ActionListener 
{
	class Myhighlightpainter extends DefaultHighlighter.DefaultHighlightPainter
	{
	public Myhighlightpainter(Color color){super(color);}
	} 
Highlighter.HighlightPainter myHilite =new Myhighlightpainter(Color.yellow); 
	JFrame f;
	JEditorPane t1;
	JScrollPane scroll;
	JMenuBar mb;
	JMenu file,edit,format;
	JMenuItem fnew,fopen,fsave,fexit,eundo,ecut,ecopy,epaste,edel,efind,ereplace,eselectall,etimedate;
	JMenuItem frfont,vaboutnotepad;
	JCheckBoxMenuItem frwordwrap;
	UndoManager manage=new UndoManager();
	NotepadMenu()
	{
		super("JScrollPane Demonstration"); 
		f=new JFrame("Notepad");
		 mb=new JMenuBar();
		fnew=new JMenuItem("New");
		fnew.addActionListener(this);
		fopen=new JMenuItem("Open");
		fopen.addActionListener(this);
		fsave=new JMenuItem("Save");
		fsave.addActionListener(this);
		fexit=new JMenuItem("Exit");
		eundo=new JMenuItem("Undo");
		eundo.addActionListener(this);
		ecut=new JMenuItem("Cut");
		ecut.addActionListener(this);
		ecopy=new JMenuItem("Copy");
		ecopy.addActionListener(this);
		epaste=new JMenuItem("Paste");
		epaste.addActionListener(this);
		edel=new JMenuItem("Delete");
		edel.addActionListener(this);
		efind=new JMenuItem("Find");
		efind.addActionListener(this);
		ereplace=new JMenuItem("Replace");
		ereplace.addActionListener(this);
		eselectall=new JMenuItem("Select All");
		eselectall.addActionListener(this);
		etimedate=new JMenuItem("Time/Date");
		etimedate.addActionListener(this);
       frwordwrap = new JCheckBoxMenuItem("Word Wrap"); 
        frwordwrap.addActionListener(this);	   
		frfont=new JMenuItem("Font");
		frfont.addActionListener(this);
	    file=new JMenu("File");
		edit=new JMenu("Edit");
		format=new JMenu("Format");
		
		file.add(fnew);
		file.add(fopen);
		file.add(fsave);
		file.add(fexit);
		edit.add(eundo);
		edit.add(ecut);
		edit.add(ecopy);
		edit.add(epaste);
		edit.add(edel);
		edit.add(efind);
		edit.add(ereplace);
		edit.add(eselectall);
		edit.add(etimedate);
		format.add(frwordwrap); 
		format.add(frfont);
		mb.add(file);
		mb.add(edit);
		mb.add(format);
		f.add(mb);
		fexit.addActionListener(this);
		 t1=new JEditorPane();
		t1.setContentType("text/plain");
		t1.setText("");
		scroll=new JScrollPane(t1);
		 scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		  scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		f.getContentPane().add(scroll);
         
		
		t1.getDocument().addUndoableEditListener(new UndoableEditListener() {
			public void undoableEditHappened(UndoableEditEvent e) {
				manage.addEdit(e.getEdit());
			}
		});
		t1.setEditable(true);
		
		manage=new UndoManager(); 
	      f.setJMenuBar(mb);
		  Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Users\\1234\\Downloads\\note.jpg");    
           f.setIconImage(icon);	   
		f.setSize(400,400);
		f.setVisible(true);
	f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              
	}
	 public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==fnew)
		{
			t1.setText("");
		}
		if (e.getSource()==fsave) { 
            JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory()); 
   int r = j.showSaveDialog(null); 
 if (r == JFileChooser.APPROVE_OPTION) 
  { 
File file=j.getSelectedFile();
try {
	
        	final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
            writer.write(this.t1.getText());
            writer.close();
        }
        catch (IOException ioe) {
            this.t1.setText("Pardon. Can't write file. Please contact with: pkrawczak@gmail.com");
        }
		}}
		if(e.getSource()==fopen)
		{
			JFileChooser choose=new JFileChooser();
			int i=choose.showOpenDialog(this);
			if(i==JFileChooser.APPROVE_OPTION)
			{
				File f1=choose.getSelectedFile();
				String filepath=f1.getPath();
				try{
					BufferedReader br=new BufferedReader(new FileReader(filepath));
					String s1="",s2="";
					while((s1=br.readLine())!=null)
					{
						s2+=s1+"\n";
					}
					t1.setText(s2);
					br.close();
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
				}
			}
		}
		if(e.getActionCommand().equals("Exit"))
	{
	System.exit(0);
    }
	 if(e.getSource()==etimedate)
		  {
		    LocalDate myObj = LocalDate.now(); 
              String s=String.valueOf(myObj);
			   LocalTime myObj1 = LocalTime.now(); 
              String s1=String.valueOf(myObj1);
			  t1.setText("Date:"+s+"time:"+s1);
			  
		  }
		  
		   
		  if(e.getActionCommand().equals("Word Wrap"))
		  {
			  if(frwordwrap.isSelected())
			  {
				   scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				   scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			  }
			  else
				  {    
			         scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				     scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				   
			  }
		  }
		  if(e.getActionCommand().equals("Font"))
		  {
			  JDialog font=new JDialog(f,"Font",true);
			  String[] choosefont = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
			  String choosesize[]={"10","11","12","13","14","15","16","18","20","22","24","26","28","36","48","72"};
			  JLabel font1=new JLabel("Font");
			  font1.setBounds(30,30,100,20);
			  JLabel font3=new JLabel("Size");
			  font3.setBounds(260,30,100,20);
			  JComboBox c1=new JComboBox(choosefont);
			  c1.setBounds(30,50,150,20);
			  JComboBox c3=new JComboBox(choosesize);
			  c3.setBounds(260,50,100,20);
			  JLabel sample=new JLabel("Sample");
			  sample.setBounds(50,150,100,20);
			  JLabel show=new JLabel("Output");
			  show.setBounds(50,170,400,100);
			  JButton apply=new JButton("Apply");
			  apply.setBounds(40,350,100,20);
			  apply.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent b)
				  {
					  if(b.getSource()==apply)
					  {
	
						  String s3=String.valueOf(c3.getSelectedItem());
						  int n=Integer.parseInt(s3);
						  show.setFont(new Font((String)c1.getSelectedItem(),Font.PLAIN,n));
					  }
			  }});
			  JButton ok=new JButton("Ok");
			  ok.setBounds(160,350,100,20);
			  ok.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent c)
				  {
					  if(c.getSource()==ok)
					  {
						  String s3=String.valueOf(c3.getSelectedItem());
						  int n=Integer.parseInt(s3);
						  t1.setFont(new Font((String)c1.getSelectedItem(),Font.PLAIN,n));
					  }
			  }});
			  
			  JButton cancel=new JButton("Cancel");
			  cancel.setBounds(270,350,100,20);
			  cancel.addActionListener(new ActionListener(){
				  public void actionPerformed(ActionEvent d)
				  {
					  if(d.getSource()==cancel)
					  {
						  font.setVisible(false);
						  font.dispose();
					  }
			  }});
			  font.add(font1);
			  font.add(font3);
			  font.add(c1);
			  font.add(c3);
			  font.add(sample);
			  font.add(show);
			  font.add(apply);
			  font.add(ok);
			  font.add(cancel);
			  font.setLayout(null);
               font.setSize(500,500);
               font.setVisible(true);			   
		 }
		 
	if(e.getActionCommand().equals("Find"))
		{
		 JDialog find=new JDialog(f,"check",true);
		 JLabel l=new JLabel("To Find");
		 JTextField check=new JTextField();
		 JButton b2=new JButton("Find");
		 b2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent b)
			 {
		 if(b.getActionCommand().equals("Find"))
		 {
			 Highlight(t1,check.getText());
			 }}
		 });
		 find.addWindowListener(new WindowAdapter(){
		 public void windowClosing(WindowEvent e)
          {
           removeHighlights(t1);
            }
          });
		
		 l.setBounds(40,30,80,20);
		 check.setBounds(100,30,100,20);
		 b2.setBounds(85,80,80,20);
		 find.add(l);
		 find.add(check);
		 find.add(b2);
		 find.setLayout(null);
		 find.setSize(250,150);
		 find.setVisible(true);
		}
		if(e.getActionCommand().equals("Replace"))
		{
		 JDialog replace=new JDialog(f,"replace",true);
		 JLabel l=new JLabel("To Find:");
		 JLabel l2=new JLabel("Replace with:");
		 JTextField check=new JTextField();
		 JTextField change=new JTextField();
		 JButton b2=new JButton("Find");
		 JButton b3=new JButton("Replace");
		 b2.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent b)
			 {
		 if(b.getActionCommand().equals("Find"))
		 {
			 Highlight(t1,check.getText());
			 }}
		 });
		 replace.addWindowListener(new WindowAdapter(){
		 public void windowClosing(WindowEvent e)
          {
           removeHighlights(t1);
            }
          });
		  b3.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent c)
			  {
				  if(c.getActionCommand().equals("Replace"))
				  {
					  String x=check.getText();
					  String y=change.getText();
					  String z=t1.getText();
					  String n=z.replaceAll(x,y);
					  t1.setText(n);
				  }
			  }
		  });
		 
		 l.setBounds(20,30,80,20);
		 l2.setBounds(20,50,80,20);
		 check.setBounds(100,30,100,20);
		 change.setBounds(100,50,100,20);
		 b2.setBounds(20,80,80,20);
		 b3.setBounds(110,80,80,20);
		 replace.add(l);
		 replace.add(l2);
		 replace.add(change);
		 replace.add(check);
		 replace.add(b2);
		 replace.add(b3);
		 replace.setLayout(null);
		 replace.setSize(250,200);
		 replace.setVisible(true);
		}
	
		 if(e.getSource()==ecut)    
          t1.cut();    
          if(e.getSource()==epaste)    
          t1.paste();    
          if(e.getSource()==ecopy)    
          t1.copy();    
          if(e.getSource()==eselectall)    
          t1.selectAll();
          if(e.getSource()==eundo)
		 {
			 try{
				 manage.undo();
			 }
			 catch(Exception ex){}
	}
	int l1,l2;
	l1=t1.getSelectionStart();
    l2=t1.getSelectedText().length();
	if(e.getSource()==edel)
	{
	try{
     t1.getDocument().remove(l1, l2);
	 }
	catch(BadLocationException bad)
	{
		System.out.println("couldn't delete intial text");
	}}
	
	
	}
	
	public void removeHighlights(JTextComponent textcomp)
	{
		Highlighter hilite=textcomp.getHighlighter();
		Highlighter.Highlight[] hilites=hilite.getHighlights();
		for(int i=0;i<hilites.length;i++)
		{
			if(hilites[i].getPainter() instanceof Myhighlightpainter)
			{
				hilite.removeHighlight(hilites[i]);
			}
		}
		
	}
	public void Highlight(JTextComponent textcomp,String pattern)
	{
		removeHighlights(textcomp);
		try{
			Highlighter hilite =textcomp.getHighlighter();
			Document doc =textcomp.getDocument();
			String text=doc.getText(0,doc.getLength());
			int pos=0;
			while((pos=text.toUpperCase().indexOf(pattern.toUpperCase(),pos))>=0)
			{
				hilite.addHighlight(pos,pos+pattern.length(),myHilite);
				pos+=pattern.length();
			}
		}
		catch(BadLocationException bd){};
			
		}
        
	
	}	
class Notepad
{
	public static void main(String args[])
	{    
	javax.swing.SwingUtilities.invokeLater(new Runnable() {
		public void run(){
	new NotepadMenu(); }});
				
		
	}
}