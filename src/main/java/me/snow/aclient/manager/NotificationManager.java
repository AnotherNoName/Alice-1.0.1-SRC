/*
 * Decompiled with CFR 0.150.
 */
package me.snow.aclient.manager;

import java.util.ArrayList;
import me.snow.aclient.AliceMain;
import me.snow.aclient.module.modules.client.HUD;
import me.snow.aclient.notifications.Notification;

public class NotificationManager {
    private final /* synthetic */ ArrayList<Notification> notifications;

    public NotificationManager() {
        this.notifications = new ArrayList();
    }

    public ArrayList<Notification> getNotifications() {
        return this.notifications;
    }

    public void handleNotifications(int n) {
        for (int i = 0; i < this.getNotifications().size(); ++i) {
            this.getNotifications().get(i).onDraw(n);
            n -= AliceMain.moduleManager.getModuleByClass(HUD.class).renderer.getFontHeight() + 5;
        }
    }

    public void addNotification(String string, long l) {
        this.getNotifications().add(new Notification(string, l));
    }
}

