package ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import scrollablemenu.JScrollableMenu;

/**
 * スクローラブルメニューを使うフレームの例
 * @author seraphy
 */
public class ScrollableMenuFrame extends JFrame {

    /**
     * メニューバー
     */
    private JMenuBar menubar;

    /**
     * お気に入りメニュー
     */
    private JScrollableMenu menuFavorites;
    
    /**
     * コンストラクタ
     */
    public ScrollableMenuFrame() {
        setTitle("ScrollableMenu Sample");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        menubar = new JMenuBar();

        // ファイルメニュー
        JMenu menuFile = new JMenu("ファイル(F)");
        JMenuItem mnuExit = new JMenuItem(new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent e) {
                ScrollableMenuFrame.this.dispose();
            }
        });
        menuFile.add(mnuExit);
        menubar.add(menuFile);
        

        // お気に入りに設定するメニュー項目の事前準備(100個)
        int numOfItems = 1000;
        ArrayList<JMenuItem> menus = new ArrayList<JMenuItem>();
        for (int i = 0; i < numOfItems; i++) {
            final String name = "メニュー No." + Integer.toString(i);
            JMenuItem item = new JMenuItem(new AbstractAction(name) {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(ScrollableMenuFrame.this, name);
                }
            });
            menus.add(item);
        }

        // お気に入りメニュー(スクローラブル)の設定
        menuFavorites = new JScrollableMenu("お気に入り(F)");
        menuFavorites.initScroller();
        menuFavorites.setScrollableItems(menus);
        menubar.add(menuFavorites);

        // お気に入りメニューのメニューイベントのハンドラ
        menuFavorites.addMenuListener(new MenuListener() {
            @Override
            public void menuCanceled(MenuEvent e) {
                // なにもしない
            }

            @Override
            public void menuDeselected(MenuEvent e) {
                // なにもしない
            }

            @Override
            public void menuSelected(MenuEvent e) {
                // お気に入りメニューが選択された場合、
                // お気に入りアイテム一覧を表示するよりも前に
                // 表示可能なアイテム数を現在のウィンドウの高さから算定する.
                int height = ScrollableMenuFrame.this.getHeight();
                menuFavorites.adjustMaxVisible(height);
                System.out.println("maxVisible=" + menuFavorites.getMaxVisible());
            }
        });

        // メニューバーをフレームに設定する.
        setJMenuBar(menubar);
        
        // 診断用パネル生成
        initDiagPanel();
    }
    
    /**
     * システムプロパティ一覧を表示する、診断パネル生成
     */
    private void initDiagPanel() {
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        String lf = System.getProperty("line.separator");

        Properties sysProps = System.getProperties();
        ArrayList<String> propNames = new ArrayList<String>();
        propNames.addAll(sysProps.stringPropertyNames());
        Collections.sort(propNames);
        
        StringBuilder buf = new StringBuilder();
        for (String propName : propNames) {
            String value = sysProps.getProperty(propName);
            buf.append(propName).append("=").append(value).append(lf);
        }
        
        JTextPane textPane = new JTextPane();
        textPane.setText(buf.toString());

        JScrollPane scr = new JScrollPane(textPane);
        contentPane.add(scr, BorderLayout.CENTER);
    }
}
