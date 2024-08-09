package huskygame.src.com.huskygame.ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener,ActionListener {

    //创建一个二维数组。目的：用来管理数据，在加载图片时会根据二维数组中的数据进行加载
    int[][] data = new int[4][4];

    //设置全局变量x、y记录空白方块的位置
    int x = 0;
    int y = 0;

    //定义一个正确的二维数组win
    int[][] win = new int[][]{
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //定义一个变量用来记录游戏的步数。
    int step = 0;

    //定义一个String类型变量，记录当前显示图片的路径。
    String path = "image\\animal\\animal3\\";

    //创建选项下的条目对象
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    //创建JMenuItem对象
    JMenuItem girl = new JMenuItem("美女");
    JMenuItem animal = new JMenuItem("动物");
    JMenuItem sport = new JMenuItem("运动");


    public GameJFrame() {
        //初始化窗口
        initJFrame();

        //初始化菜单
        initJMenuBar();

        //初始化数据，实现打乱图片
        initData();

        //初始化图片
        initImage();

        //让窗口显示出来
        this.setVisible(true);
    }

    //初始化数据
    private void initData() {
        int[] tempArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random random = new Random();
        //打乱数组中的元素
        for (int i = 0; i < tempArr.length; i++) {
            int index = random.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }
        //将打乱后的一位数组变为二维数组
        for (int i = 0; i < tempArr.length; i++) {
            if (tempArr[i] == 0) {
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];

        }
    }

    //初始化图片
    private void initImage() {
        //清空之前已经加载的图片
        this.getContentPane().removeAll();

        //判断是否胜利
        if (victory()) {
            JLabel jLabel = new JLabel(new ImageIcon("image\\win.png"));
            jLabel.setBounds(203,283,197,73);
            this.getContentPane().add(jLabel);
        }

        //添加记录步数的组件
        JLabel stepCount = new JLabel("步数" + step);
        stepCount.setBounds(50,30,100,20);
        this.getContentPane().add(stepCount);

        //遍历添加图片
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                //获得二维数组的值
                int num = data[j][i];
                //创建一个JLabel的对象，并将图片对象放入到这个管理容器中
                JLabel jLabel = new JLabel(new ImageIcon(path + num + ".jpg"));
                //指定图片位置
                jLabel.setBounds(105 * i + 83, 105 * j + 134, 105, 105);
                //给图片设置边框
                jLabel.setBorder(new BevelBorder(0));
                //把管理容器添加到窗口中
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image\\background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);

        //刷新一下界面
        this.getContentPane().repaint();
    }

    //初始化菜单
    private void initJMenuBar() {
        //创建菜单对象
        JMenuBar jMenuBar = new JMenuBar();
        //创建菜单上面的两个选项的对象
        JMenu functionJmenu = new JMenu("功能");
        JMenu aboutJmenu = new JMenu("关于我们");
        //创建菜单上的更换图片的对象
        JMenu changeImage = new JMenu("更换图片");

        //把美女，动物，运动加到更换图片中
        changeImage.add(girl);
        changeImage.add(animal);
        changeImage.add(sport);


        //将选项下的条目添加到选项中
        functionJmenu.add(changeImage);
        functionJmenu.add(replayItem);
        functionJmenu.add(reLoginItem);
        functionJmenu.add(closeItem);

        aboutJmenu.add(accountItem);

        //给条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);

        //给美女，动物，运动添加事件
        girl.addActionListener(this);
        animal.addActionListener(this);
        sport.addActionListener(this);

        //将菜单里的两个选项添加到菜单中
        jMenuBar.add(functionJmenu);
        jMenuBar.add(aboutJmenu);
        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    //    初始化窗口
    private void initJFrame() {
        //设置界面的宽和高
        this.setSize(603, 680);
        //设置界面的标题
        this.setTitle("哈士奇的拼图游戏v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭格式
        this.setDefaultCloseOperation(3);
        //取消默认的剧中设置
        this.setLayout(null);
        //给整个窗口添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //设置查看全图的功能
        int code = e.getKeyCode();
        if (code == 65) {
            //清楚原来的图片
            this.getContentPane().removeAll();
            //加载完整的图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83, 134, 420, 420);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel background = new JLabel(new ImageIcon("image\\background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //增加一个胜利判断，若胜利就停止游戏
        if (victory()) {
            return;
        }

        //左键code:37,上键code:38,右键code:39,下键code:40。
        int code = e.getKeyCode();
        //空白图片左移
        if (code == 37) {
            if (y == 0) {
                return;
            } else {
                System.out.println("左移");
                data[x][y] = data[x][y - 1];
                data[x][y - 1] = 0;
                y--;

                //当执行移动步数时，计步数加1
                step++;
            }
        }
        //空白图片上移
        if (code == 38) {
            if (x == 0) {
                return;
            } else {
                System.out.println("上移");
                data[x][y] = data[x - 1][y];
                data[x - 1][y] = 0;
                x--;

                //当执行移动步数时，计步数加1
                step++;
            }
        }
        //空白图片右移
        if (code == 39) {
            if (y == 3) {
                return;
            } else {
                System.out.println("右移");
                data[x][y] = data[x][y + 1];
                data[x][y + 1] = 0;
                y++;

                //当执行移动步数时，计步数加1
                step++;
            }
        }
        //空白图片下移
        if (code == 40) {
            if (x == 3) {
                return;
            } else {
                System.out.println("下移");
                data[x][y] = data[x + 1][y];
                data[x + 1][y] = 0;
                x++;

                //当执行移动步数时，计步数加1
                step++;
            }
        }

        //当松开A时，调用方法返回原来的样子
        if (code == 65) {
            initImage();
        }

        //实现作弊码的功能，按w直接完成拼图游戏。
        if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            initImage();
        }

        //调用方法按照最新的数字来加载图片
        initImage();
    }

    //判断当前游戏是否胜利.遍历数组判断data数组与win数组是否相同。
    public boolean victory() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获得当前点击的条目对象
        Object obj = e.getSource();

        //创建一个随机数生成对象
        Random random = new Random();

        //判断
        if (obj == replayItem) {
            System.out.println("重新游戏");

            //再次打乱二维数组中的数据
            initData();

            //计步数清零
            step = 0;

            //重新初始化图片
            initImage();

        } else if (obj == reLoginItem) {
            System.out.println("重新登录");

            //关闭当前页面
            this.setVisible(false);

            //打开登录页面
            new LoginJFrame();

        } else if (obj == closeItem) {
            System.out.println("关闭游戏");

            //直接关闭虚拟机
            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("公众号");

            //创建一个弹窗对象
            JDialog jDialog = new JDialog();
            //创建一个管理图片的容器对象JLabel
            JLabel jLabel = new JLabel(new ImageIcon("image\\account.jpg"));
            //设置容器的位置和宽高
            jLabel.setBounds(0,0,258,258);
            //将容器添加到弹窗中
            jDialog.getContentPane().add(jLabel);
            //给弹窗设置大小
            jDialog.setSize(344,344);
            //让弹窗置顶
            jDialog.setAlwaysOnTop(true);
            //让弹窗居中
            jDialog.setLocationRelativeTo(null);
            //弹窗不关闭无法操作下面的界面
            jDialog.setModal(true);
            //让弹窗显示出来
            jDialog.setVisible(true);

        } else if (obj == girl) {
            System.out.println("点击了美女条目");

            //通过生成随机数来修改path的值
            path = "image\\girl\\";
            int girlNum = random.nextInt(13) + 1;
            path = path + "girl" + girlNum + "\\";
            System.out.println(path);
            //再次打乱二维数组中的数据
            initData();

            //计步数清零
            step = 0;

            //重新初始化图片
            initImage();
        } else if (obj == animal) {
            System.out.println("点击了动物条目");

            //通过生成随机数来修改path的值
            path = "image\\animal\\";
            int animalNum = random.nextInt(8) + 1;
            path = path + "animal" + animalNum + "\\";
            System.out.println(path);
            //再次打乱二维数组中的数据
            initData();

            //计步数清零
            step = 0;

            //重新初始化图片
            initImage();
        } else if (obj == sport) {
            System.out.println("点击了运动条目");

            //通过生成随机数来修改path的值
            path = "image\\sport\\";
            int sportNum = random.nextInt(10) + 1;
            path = path + "sport" + sportNum + "\\";
            System.out.println(path);
            //再次打乱二维数组中的数据
            initData();

            //计步数清零
            step = 0;

            //重新初始化图片
            initImage();
        }
    }
}
