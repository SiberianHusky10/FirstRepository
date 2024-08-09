package huskygame.src.com.huskygame.ui;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {

    JButton reset = new JButton();
    JButton register = new JButton();

    JTextField username = new JTextField();
    //JTextField password = new JTextField();
    JPasswordField password = new JPasswordField();
    JPasswordField passwordAgain = new JPasswordField();

    //显示密码功能
    JLabel showPassword = new JLabel(new ImageIcon("image\\login\\显示密码.png"));

    //正确的验证码


    public RegisterJFrame() {

        //初始化窗口
        initJFrame();

        //给界面添加内容组件
        initView();

        this.setVisible(true);
    }

    //给界面添加内容组件
    public void initView() {
        //1. 添加用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image\\register\\注册用户名.png"));
        usernameText.setBounds(105, 140, 79, 17);
        this.getContentPane().add(usernameText);

        //2.添加用户名输入框

        username.setBounds(195, 134, 200, 30);
        this.getContentPane().add(username);

        //3.添加密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image\\register\\注册密码.png"));
        passwordText.setBounds(120, 200, 64, 16);
        this.getContentPane().add(passwordText);

        //4.密码输入框
        password.setBounds(195, 195, 200, 30);
        password.setEchoChar('*');
        this.getContentPane().add(password);

        //5.再次输入密码文字
        JLabel passwordTextAgain = new JLabel(new ImageIcon("image\\register\\再次输入密码.png"));
        passwordTextAgain.setBounds(90, 260, 97, 17);
        this.getContentPane().add(passwordTextAgain);

        //6.再次输入密码输入框
        passwordAgain.setBounds(195, 255, 200, 30);
        passwordAgain.setEchoChar('*');
        this.getContentPane().add(passwordAgain);


        //显示密码
        showPassword.setBounds(395, 195, 18, 29);
        this.getContentPane().add(showPassword);

        //显示密码添加事件
        showPassword.addMouseListener(this);

        //5.添加重置按钮
        reset.setBounds(123, 310, 128, 47);
        reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        //去除按钮的边框
        reset.setBorderPainted(false);
        //去除按钮的背景
        reset.setContentAreaFilled(false);
        //给重置按钮绑定鼠标事件
        reset.addMouseListener(this);
        this.getContentPane().add(reset);

        //6.添加注册按钮
        register.setBounds(256, 310, 128, 47);
        register.setIcon(new ImageIcon("image\\login\\注册按钮.png"));
        //去除按钮的边框
        register.setBorderPainted(false);
        //去除按钮的背景
        register.setContentAreaFilled(false);
        //给注册按钮绑定鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);


        //7.添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\login\\background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);

    }

    //初始化窗口界面
    private void initJFrame() {
//        设置界面的宽和高
        this.setSize(488, 430);
//        设置界面的标题
        this.setTitle("哈士奇的拼图游戏 v1.0注册");
//        设置界面置顶
        this.setAlwaysOnTop(true);
//        设置界面居中
        this.setLocationRelativeTo(null);
//        设置关闭格式
        this.setDefaultCloseOperation(3);
//        让窗口显示出来
    }

    //遍历集合，并查找输入的数据与集合中的数据是否有重复
    public boolean listContain(String username, ArrayList<User> list) {
        for (int i = 0; i < list.size(); i++) {
            User user = list.get(i);
            if (user.getName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    //点击
    @Override
    public void mouseClicked(MouseEvent e) {
        //点击重置后将三个文本框中的内容清空
        if (e.getSource() == reset) {
            username.setText("");
            password.setText("");
            passwordAgain.setText("");
        }
        //点击注册，实行判断
        if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
            if (!username.getText().equals("")) {
                boolean flag = listContain(username.getText(), LoginJFrame.allUsers);
                if (!flag) {
                    if (password.getText().equals(passwordAgain.getText())) {
                        User user = new User(username.getText(), password.getText());
                        LoginJFrame.allUsers.add(user);
                        showJDialog("注册成功");
                        this.setVisible(false);
                        new LoginJFrame();
                    } else {
                        showJDialog("两次输入密码不一致");
                    }
                } else {
                    showJDialog("用户名已经被注册");
                }

            } else {
                showJDialog("用户名不能为空");
            }
        }
    }

    //显示弹窗
    public void showJDialog(String content) {
        //创建一个弹框对象
        JDialog jDialog = new JDialog();
        //给弹框设置大小
        jDialog.setSize(200, 150);
        //让弹框置顶
        jDialog.setAlwaysOnTop(true);
        //让弹框居中
        jDialog.setLocationRelativeTo(null);
        //弹框不关闭永远无法操作下面的界面
        jDialog.setModal(true);

        //创建Jlabel对象管理文字并添加到弹框当中
        JLabel warning = new JLabel(content);
        warning.setBounds(0, 0, 200, 150);
        jDialog.getContentPane().add(warning);

        //让弹框展示出来
        jDialog.setVisible(true);
    }

    //按下
    @Override
    public void mousePressed(MouseEvent e) {
        //按下重置按钮后，改变重置图片
        if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image\\register\\重置按下.png"));
        }
        //按下注册按钮，改变注册图片
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\register\\注册按下.png"));
        }
        //按下小眼睛图片，改变小眼睛图片，并且将密码变成可视状态
        if (e.getSource() == showPassword) {
            showPassword.setIcon(new ImageIcon("image\\login\\显示密码按下.png"));
            password.setEchoChar((char) 0);
        }
    }

    //松开
    @Override
    public void mouseReleased(MouseEvent e) {
        //松开重置按钮后，改变重置图片
        if (e.getSource() == reset) {
            reset.setIcon(new ImageIcon("image\\register\\重置按钮.png"));
        }
        //松开注册按钮，改变注册图片
        if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image\\register\\注册按钮.png"));
        }
        //松开小眼睛图片，改变小眼睛图片,并且将密码变回不可视的状态
        if (e.getSource() == showPassword) {
            showPassword.setIcon(new ImageIcon("image\\login\\显示密码.png"));
            password.setEchoChar('*');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
