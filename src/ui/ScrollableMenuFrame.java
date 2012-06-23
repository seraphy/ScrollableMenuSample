package ui;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
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
    }
}
