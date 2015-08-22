/*
 * Copyright (c) NiconSystem inc 2013
 * License: GPLv3
 *
 * Authors:
 * Frederick Adolfo Salazar Sanchez <fredefass01@gmail.com>
 *
 *   This program is free software; you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as
 *   published by the Free Software Foundation; either version 3,
 *   or (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details
 *
 *   You should have received a copy of the GNU General Public
 *   License along with this program; if not, write to the
 *   Free Software Foundation, Inc.,
 *   51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package nicon.notify.gui.desktopNotify;

import java.applet.AudioClip;
import nicon.notify.core.util.ControlNotify;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import nicon.notify.core.util.NLabel;
import nicon.notify.core.NiconEvent;
import nicon.notify.core.Notification;
import nicon.notify.core.util.NotifyConfig;
import nicon.notify.core.util.NotifyUtil;
import nicon.notify.gui.themes.NiconDarkTheme;
import nicon.notify.gui.themes.NiconLightTheme;
import nicon.notify.gui.themes.NiconTheme;

/**
 * Esta clase define una notificacion del tipo DesktopNotify, esta notificacion
 * es el centro de esta tecnologia,en esta clase solo definimos los aspectos
 * graficos.
 * 
 * @author frederick
 */
public class DesktopNotify extends JDialog implements NotifyDesktopInterface{
    
          
    private final NiconEvent ev;
    private int nid;
    private int iconOption;
    private char selSkin;
    private String urlIcon; 
    
    private JLabel jlTitle;
    private NLabel jlMessage;
    private JLabel jlIcon;   
    
    private final NotifyConfig config;
    private final NotifyUtil util;
    private NiconTheme theme;
    
    private JButton jbClose;    
    private JPanel panel;    
    
    private Timer timer;
    private String urlSound;
    
    /**
     * Este metodo constructor permite crear una nueva notificacion de escritorio
     * de forma que se configura por defecto con la informacion y caracteristicas
     * del objeto NiconEvent que llega en su constructor.
     * 
     * @param ev 
     */
    
    public DesktopNotify(NiconEvent ev) {        
        this.ev=ev;
        this.config=NotifyConfig.getInstance();
        this.util=NotifyUtil.getInstance();
        this.theme=NiconDarkTheme.getInstance();        
        setSize(380,98);
        setUndecorated(true);  
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        closeNotify();
    }    
  
    /**
     * Este metodo constructor permite crear una nueva notificacion de escritorio
     * de forma que se configura por defecto con la informacion y caracteristicas
     * del objeto NiconEvent que llega en su constructor, ademas recibe un char
     * con la seleccion del NiconTheme que el usuario desea usar
     * 
     * @param ev 
     * @param optionTheme 
     */
    
     public DesktopNotify(NiconEvent ev, char optionTheme) {        
        this.ev=ev;
        this.selSkin=optionTheme;
        this.config=NotifyConfig.getInstance();
        this.util=NotifyUtil.getInstance();  
        selectTheme();        
        setSize(380,98);
        setUndecorated(true); 
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        closeNotify();
    }     
    
    /**
     * Este metodo permite crear una nueva notificacion de escritorio recibiendo
     * como parametros el Evento que se desea mostrar y un Icono de los iconos 
     * proveidos por la libreria, para hacer uso de los iconos debera acceder a 
     * ellos mediante las variables constantes de Notification
     * @param ev
     * @param iconOption 
     */
    public DesktopNotify(NiconEvent ev, int iconOption){
        this.ev=ev;
        this.iconOption=iconOption;
        config=NotifyConfig.getInstance();
        util=NotifyUtil.getInstance();        
        theme=NiconDarkTheme.getInstance();        
        setSize(380,98);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        setIconOption();
        closeNotify();
    }    
    
    /**
     * Este metodo constructor permite crear una nueva notificacion de escritorio
     * de forma que se configura por defecto con la informacion y caracteristicas
     * del objeto NiconEvent que llega en su constructor, ademas recibe un int
     * que representa el icono a usar en la notificacion y un char con el NiconTheme
     * seleccionado para la notificacion
     * 
     * @param ev 
     * @param iconOption 
     * @param optionTheme 
     */
    
    public DesktopNotify(NiconEvent ev, int iconOption,char optionTheme){
        this.ev=ev;
        this.iconOption=iconOption;
        this.selSkin=optionTheme;
        this.config=NotifyConfig.getInstance();
        this.util=NotifyUtil.getInstance();
        this.selectTheme();        
        setSize(380,98);
        setUndecorated(true); 
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        setIconOption();
    }    
    
    /**
     * Este metodo constructor permite crear una nueva notificacion de escritorio
     * de forma que se configura por defecto con la informacion y caracteristicas
     * del objeto NiconEvent que llega en su constructor, ademas de un string 
     * con la url de un icono que el usuario desea mostrar
     * 
     * @param ev 
     * @param url 
     */
    
    public DesktopNotify(NiconEvent ev,String url){
        this.ev=ev;
        this.iconOption=-1;        
        this.urlIcon=url;
        this.theme=NiconDarkTheme.getInstance();
        this.config=NotifyConfig.getInstance();
        this.util=NotifyUtil.getInstance();
        this.selectTheme();        
        setSize(380,98);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        setIconOption();
    }
    
    /**
     * Este metodo constructor permite crear una nueva notificacion de escritorio
     * de forma que se configura por defecto con la informacion y caracteristicas
     * del objeto NiconEvent que llega en su constructor, ademas recibe un 
     * String con el Url de un icono a cargar y el char del NiconTheme a usar en 
     * la notificacion
     * 
     * @param ev 
     * @param url 
     * @param skin 
     */
    
    public DesktopNotify(NiconEvent ev,String url,char skin){
        this.ev=ev;
        this.iconOption=-1;        
        this.urlIcon=url;
        this.selSkin=skin;
        this.config=NotifyConfig.getInstance();
        this.util=NotifyUtil.getInstance();
        this.selectTheme();        
        setSize(380,98);
        setUndecorated(true); 
        setAlwaysOnTop(true);
        setResizable(false);
        init();        
        setDesktopInterface();
        setIconOption();
    }
    
    /**
     * Metodo que inicia el proceso de carga y configuracion de la notificacion
     */
    private void init() {        
        panel=new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(Integer.parseInt(theme.getBakcgroundPanel(), 16)));
        
        jbClose=new JButton();
        jbClose.setBounds(355, 5, 15, 15);
        jbClose.setBorderPainted(false);
        jbClose.setContentAreaFilled(false);
        jbClose.setIcon(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"CloseNotify.png")));
        jbClose.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();                
                ControlNotify.displayed--;
            }
        });
                
        jlIcon=new JLabel();
        jlIcon.setBounds(0,0,65,65);
        
        jlTitle=new JLabel(ev.getTitleEvent());
        jlTitle.setFont(config.getTitleFontDesk());
        jlTitle.setBounds(75,10,305, 18);
        
        jlMessage=new NLabel(util.setTextMessageEvent(ev.getTextEvent()));
        jlMessage.setFont(config.getMessageFontDesk());
        jlMessage.setBounds(78,30,295,50);
        jlMessage.setForeground(new Color(Integer.parseInt(theme.getMessageForeground(),16)));
                
        panel.add(jlIcon);
        panel.add(jlTitle);
        panel.add(jlMessage);
        panel.add(jbClose);
        add(panel);            
        closeNotify();
    }
    
    /**
     * Este metodo permite agregar  un nuevo boton a una notificacion
     * @param buton 
     */
    public void addButton(JButton buton){
        panel.add(buton);
    }
    
    /**
     * Ajusta el icono de la notificacion
     * @param icon 
     */
    private void setIconNotify(ImageIcon icon){
        jlIcon.setIcon(icon);
    }
    
    
    /**
     * Este metodo permite Ajustar toda la interfaz de la notificacion segun la
     * configuracion del objeto niconEvent, en este caso si estamos mostrando
     * una notificacion de OK la DesktopNotify podrá configurarse para que toda
     * su interfaz sea simbolo de OK, o en caso de warning de igual forma, ademas
     * permite definir una Interfaz Neutral por defecto con una configuracion 
     * simple y sin ninguna expresion.
     */
    private void setDesktopInterface(){
        if(ev.getTipeMessage()==Notification.DEFAULT_MESSAGE){
            setIconNotify(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconDefault.png")));
            jlTitle.setForeground(new Color(Integer.parseInt(theme.getTitleForeground(), 16)));
        }
        if(ev.getTipeMessage()==Notification.OK_MESSAGE){
            setIconNotify(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconOK.png")));
            jlTitle.setForeground(new Color(Integer.parseInt(theme.getTitleOKForeground(), 16)));
        }
        if(ev.getTipeMessage()==Notification.WARNING_MESSAGE){
            setIconNotify(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconWarning.png")));
            jlTitle.setForeground(new Color(Integer.parseInt(theme.getTitleWarningForeground(), 16)));
        }
        if(ev.getTipeMessage()==Notification.ERROR_MESSAGE){
            setIconNotify(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconError.png")));
            jlTitle.setForeground(new Color(Integer.parseInt(theme.getTitleErrorForeground(), 16)));
        }
        if(ev.getTipeMessage()==Notification.CONFIRM_MESSAGE){
            setIconNotify(new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconConfirm.png")));
            jlTitle.setForeground(new Color(Integer.parseInt(theme.getTitleForeground(), 16)));
        }
    }
    
    /**
     * Ajusta el Icono de la notificacion con la opcion recibida del API.
     */
    private void setIconOption(){
        
        if(iconOption==-1) {
            try {
                File file=new File(urlIcon);
                    if(file.exists()){
                        ImageIcon prevIcon=new ImageIcon(file.getPath());
                        setIconNotify(new ImageIcon(prevIcon.getImage().getScaledInstance(67, 67, Image.SCALE_DEFAULT)));
                    }
            } catch (Exception ex) {
                Logger.getLogger(DesktopNotify.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(iconOption==1) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconFacebook.png"))));
        
        if(iconOption==2) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconTwitter.png"))));
        
        if(iconOption==3) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconTwitterOff.png"))));
        
        if(iconOption==4) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconUpdateGreen.png"))));
        
        if(iconOption==5) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconSecure.png"))));
        
        if(iconOption==6) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconGoogle.png"))));
        
        if(iconOption==7) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconHard.png"))));
        
        if(iconOption==8)  setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconPlus.png"))));
        
        if(iconOption==9)  setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconWeather.png"))));
        
        if(iconOption==10) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconWifi.png"))));
        
        if(iconOption==11) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconDownload.png"))));
        
        if(iconOption==12) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconRss.png"))));
        
        if(iconOption==13) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconUpdateBlue.png"))));
        
        if(iconOption==14) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconEverNote.png"))));
        
        if(iconOption==15) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMessageOrange.png"))));
        
        if(iconOption==16) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMessageBlue.png"))));
        
        if(iconOption==17) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMusic.png"))));
        
        if(iconOption==18) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconShield.png"))));
        
        if(iconOption==19) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconPlugin.png"))));
        
        if(iconOption==20) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMailRed.png"))));
        
        if(iconOption==21) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMailBlue.png"))));
        
        if(iconOption==22) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"ImageIcon.png"))));
        
        if(iconOption==23) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconNotes.png"))));
        
        if(iconOption==24) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconCalendar.png"))));
        
        if(iconOption==25) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconInfo.png"))));
        
        if(iconOption==26) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconFull.png"))));
        
        if(iconOption==27) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconMed.png"))));
        
        if(iconOption==28) setIconNotify((new ImageIcon(getClass().getResource(config.getNitruxIconsPath()+"NiconDown.png"))));
    }
    
    /**
     * Metodo que permite ajustar un NiconTheme segun el seleccionado por el 
     * usuario
     */
    private void selectTheme(){
        if(selSkin==Notification.NICON_DARK_THEME)
            theme=NiconDarkTheme.getInstance();
        
        if(selSkin==Notification.NICON_LIGHT_THEME)
            theme=NiconLightTheme.getInstance();        
    }
    
    /**
     * Retorna un objeto del tipo NiconTheme con el tema seleccionado por el 
     * usuario
     * @return NiconTheme
     */
    public NiconTheme getTheme(){
        return this.theme;
    }
    
    /**
     * Este metodo retorna el color de fuente usado en los titulos de las notificaciones
     * segun el tipo que sea.
     * @return Color titleforeground
     */
    public Color getForegroundTitle(){
        return this.jlTitle.getForeground();
    }
    
    /**
     * Este metodo permitira reproducir un sonido al momento de mostrar la 
     * notificacion
     * @param type
     */    
    public void playSound(int type){
        
        if(type==0){
            urlSound="/nicon/notify/core/sound/notify.wav";
        }
        if(type==1){
            urlSound="/nicon/notify/core/sound/warning.wav";
        }
        if(type==2){
            urlSound="/nicon/notify/core/sound/error.wav";
        }
        AudioClip adio = java.applet.Applet.newAudioClip(getClass().getResource(urlSound));
        adio.play();
    }

    /*
        Permite obtener el nid de la desktopNotify asignado por el serverOSD
    */
    public int getNid() {
        return nid;
    }

    /*
        Ajustamos el nid al desktopNotify, es asignado por serverOSD
    */
    public void setNid(int nid) {
        this.nid = nid;
    }
    
    
       
    /**
     *
     */
    @Override
    public final void closeNotify() {
       final DesktopNotify notify = this;
       timer = new Timer(6500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ControlNotify.removeNotify(notify);
                timer.stop();
                timer=null;
            }
        });
        timer.start();
    }

            
}
