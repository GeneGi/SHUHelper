import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class orderMenu extends JFrame{

	JFrame frame=new JFrame();
	JPanel foodtable=new JPanel();
	JPanel f1=new JPanel();
	ImageIcon icon=new ImageIcon("/mifan.png");
	JLabel Imagelabel=new JLabel(icon);
	
	
	
	JToggleButton mifan=new JToggleButton("米饭 1元");
	JToggleButton miantiao=new JToggleButton("面条 3元");
	JToggleButton jiaozi=new JToggleButton("饺子 5元");
	
	JToggleButton dapai=new JToggleButton("大排 5元");
	JToggleButton yuxiangrousi=new JToggleButton("鱼香肉丝 8元");
	JToggleButton hongshaoniurou=new JToggleButton("红烧牛肉 7元");
	JToggleButton fanqiejidan=new JToggleButton("番茄鸡蛋 6元");
	
	JButton finish=new JButton("结账");
	JButton row=new JButton("随机");
	JLabel label=new JLabel();
	int sum=0;
	orderMenu(){
		
		frame.setTitle("点菜系统");
		frame.setVisible(true);
		frame.setSize(400,400);
		frame.setResizable(false);
		
		GridBagLayout layout = new GridBagLayout();
		
		frame.getContentPane().add(foodtable); 
		foodtable.setLayout(layout);
		foodtable.setBackground(Color.PINK);
//		f1.setBackground(Color.PINK);
		Imagelabel.setBounds(0, 0, f1.getWidth(), f1.getHeight());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		 foodtable.add(mifan);
	     foodtable.add(yuxiangrousi);
	     foodtable.add(miantiao);
	     foodtable.add(hongshaoniurou);
	     foodtable.add(jiaozi);
	     foodtable.add(dapai);
	     foodtable.add(f1);
	     foodtable.add(fanqiejidan);
        
		
		
		foodtable.add(row);
		foodtable.add(finish);
		foodtable.add(label);
		f1.add(Imagelabel);
        GridBagConstraints s= new GridBagConstraints();//定义一个GridBagConstraints，
        //是用来控制添加进的组件的显示位置
        s.fill = GridBagConstraints.BOTH;
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(mifan, s);//设置组件
        
        
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(yuxiangrousi, s);//设置组件
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(miantiao, s);//设置组件
        
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(hongshaoniurou, s);//设置组件
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(jiaozi, s);//设置组件
        
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(dapai, s);//设置组件
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(f1, s);//设置组件
        
        s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(fanqiejidan, s);//设置组件
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        layout.setConstraints(label, s);//设置组件
        
     
        
        
        
		mifan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("米饭 1元");
				sum=sum+1;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		miantiao.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("面条 3元");
				sum=sum+3;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		jiaozi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("饺子 5元");
				sum=sum+5;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		yuxiangrousi.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("鱼香肉丝 8元");
				sum=sum+8;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		hongshaoniurou.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("红烧牛肉 7元");
				sum=sum+7;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		dapai.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("大排 5元");
				sum=sum+5;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		fanqiejidan.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("番茄鸡蛋 6元");
				sum=sum+6;
				icon=new ImageIcon("");
				Imagelabel=new JLabel(icon);
			}
		});
		row.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String[] zushi={"米饭","面条","饺子"};
				String[] cai={"鱼香肉丝","红烧牛肉","大排","番茄鸡蛋"};
				int r1 = (int) ( Math.random () * 3 );
				int r2 = (int) ( Math.random () * 4 );
				label.setText("主食："+zushi[r1]+" 菜："+cai[r2]);
				
			}
		});
		finish.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				label.setText("总价："+sum+" 元");
				sum=0;
			}
		});
		
	}
	public void show(){
		frame.show();
	}
	
	
	public static void main(String args[]){
		orderMenu a=new orderMenu();
		a.show();
		
	}
	
	
	
}
