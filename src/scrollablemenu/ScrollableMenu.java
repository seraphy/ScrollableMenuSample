/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scrollablemenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class ScrollableMenu extends JFrame {

    JMenuBar menubar;
    JMenu menuFile;
    JMenuItem upButton;
    JMenuItem downButton;
    Timer timer;
    boolean up;

    public static void main(String[] args) {
        ScrollableMenu frame = new ScrollableMenu();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public ScrollableMenu() {
        
        ImageIcon iconDown = new ImageIcon(getClass().getResource("arrow-down.png"));
        ImageIcon iconUp = new ImageIcon(getClass().getResource("arrow-up.png"));
        
        timer = new Timer(300, new Timer_Action());
        menubar = new JMenuBar();
        menuFile = new JMenu("ファイル(F)");
        upButton = new JMenuItem(iconUp);
        Scroll_Clicked sc = new Scroll_Clicked();
        upButton.addMouseListener(sc);

        downButton = new JMenuItem(iconDown);
        downButton.addMouseListener(sc);

        setJMenuBar(menubar);
        menubar.add(menuFile);
        menuFile.add(upButton);
        for (int i = 0; i < 50; i++) {
            JMenuItem item = new JMenuItem("メニュー" + Integer.toString(i));
            if (i > 30) {
                item.setVisible(false);
            }
            menuFile.add(item);
        }
        menuFile.add(downButton);
    }

    private void scrollUp() {
        for (int i = 1; i < menuFile.getItemCount() - 30; i++) {
            if (menuFile.getItem(i).isVisible()) {
                menuFile.getItem(i).setVisible(false);
                menuFile.getItem(i + 30).setVisible(true);
                return;
            }
        }
        timer.stop();
    }

    private void scrollDown() {
        for (int i = menuFile.getItemCount() - 2; i > 30; i--) {
            if (menuFile.getItem(i).isVisible()) {
                menuFile.getItem(i).setVisible(false);
                menuFile.getItem(i - 30).setVisible(true);
                return;
            }
        }
        timer.stop();
    }

    private class Scroll_Clicked extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            Object source = e.getSource();
            if (source == upButton) {
                up = true;
            } else {
                up = false;
            }

            timer.start();
        }

        @Override
        public void mouseExited(MouseEvent e) {
            timer.stop();
        }
    }

    private class Timer_Action implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (up) {
                scrollDown();
            } else {
                scrollUp();
            }
        }
    }
}
