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

import java.util.ArrayList;
import nicon.notify.core.util.NotifyUtil;

/**
 * Esta clase es la encargada de Controlar y gestionar el buen uso de las
 * DesktopNotify que ofrece la libreria, el acceso a esta clase permitirá
 * mostrar la notificacion deseada de forma ordenada en el escritorio del
 * dispositivo en el que se esta ejecutando la aplicacion cliente.
 *
 * @author frederick
 */
public class ControllerDesktopNotify {
    
    private final static int HEIGHT_SEPARATOR=8;
    private static ArrayList listNotify=new ArrayList();
    private static boolean active;
    private static DesktopNotify deskNotify;

    private static NotifyUtil util = NotifyUtil.getInstance();
    
    public static int displayed = 0;
    private static int ypos;
    private static int prevHeight=0;
    private static int prevYPos=0;
    private static int[] pos = util.getDesktopNotifyPosition();
    
   /**
    * Lanza la notificacion recibida al escritorio
    * @param notify 
    */
    public static void launchNotify(DesktopNotify notify) {
            if (notify != null) {
                System.out.println("ControllerDesktopNotify:  Preparando notificacion ...");
                ypos = setYAxisPosition();
                notify.setLocation(pos[0], ypos);
                prevHeight=notify.getHeight();
                prevYPos=notify.getY();
                displayed++;
                listNotify.add(notify);
                notify.setVisible(true); 
        }
    }
    
    public static void removeNotify(DesktopNotify notify){
        for(int i=0;i< listNotify.size();i++){
            if(notify.equals(listNotify.get(i))){
                deskNotify=(DesktopNotify) listNotify.get(i);
                deskNotify.dispose();
                listNotify.remove(i);
                displayed--;
                break;
            }
        }
    }
    
    public static boolean isActiveNotify(DesktopNotify notify){
        boolean contains = listNotify.contains(notify);
            if(contains==true){
                active=true;
            }else{
                active=false;
            }        
        return active;
    }
    

    /**
     * Ajusta la posision de la notificacion en el Eje Y para ser lanzada en el
     * escritorio.
     */
    private static int setYAxisPosition() {
        if (displayed == 0) {
            ypos = pos[1];
            System.out.println("ControllerDesktopNotify: YAxisPosition #" + ypos);
        } else {
            ypos=prevYPos+prevHeight+HEIGHT_SEPARATOR;
            System.out.println("ControllerDesktopNotify: YAxisPosition #" + ypos);
        }
        return ypos;
    }  
    
}
