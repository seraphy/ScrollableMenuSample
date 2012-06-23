import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import ui.ScrollableMenuFrame;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author seraphy
 */
public class Main {
 
    public static void main(String[] args) throws Exception {
        // Mac OS Xの場合、以下のシステムプロパティが設定されている場合は
        // スクリーンメニューになる.
        //System.setProperty("apple.laf.useScreenMenuBar", "true");

        // システム標準のL&Fを設定.
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        // JFrameを生成して表示
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ScrollableMenuFrame frame = new ScrollableMenuFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(300, 200);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }
    
}
