package calendarium.ui;

import calendarium.ui.tray.TrayListener;
import calendarium.ui.tray.TrayUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    private static CalendarView frame;


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        EventQueue.invokeLater(() -> {
            try {
               trayListener.open();
                if (SystemTray.isSupported()) {
                    TrayUtils td = new TrayUtils(trayListener);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static TrayListener trayListener=new TrayListener() {
        @Override
        public void open() {
            if(frame == null) {
                frame = new CalendarView();
                frame.setVisible(true);
                frame.setResizable(false);
                frame.addWindowListener(windowListener);
            } else {
                int sta = frame.getExtendedState() & ~JFrame.ICONIFIED & JFrame.NORMAL;
                frame.setExtendedState(sta);
                frame.setAlwaysOnTop(true);
                frame.toFront();
                frame.requestFocus();
                frame.setAlwaysOnTop(false);
                frame.requestFocus();
                frame.repaint();
            }
        }

        @Override
        public void exit() {
            frame.setVisible(false);
            frame.dispose();
            frame = null;
        }
    };

    private static WindowListener windowListener = new WindowListener() {
        @Override
        public void windowOpened(WindowEvent e) {

        }

        @Override
        public void windowClosing(WindowEvent e) {

        }

        @Override
        public void windowClosed(WindowEvent e) {
            frame = null;
        }

        @Override
        public void windowIconified(WindowEvent e) {

        }

        @Override
        public void windowDeiconified(WindowEvent e) {

        }

        @Override
        public void windowActivated(WindowEvent e) {

        }

        @Override
        public void windowDeactivated(WindowEvent e) {

        }
    };

}
