package org.blog.paperedu.server.management.model;

import net.kyori.adventure.text.Component;
import org.blog.paperedu.PaperEdu;

public class AnnouncementRunnable implements Runnable{
    @Override
    public void run() {
        PaperEdu.getServerInstance().getServer().broadcast(Component.text("공지사항 테스트"));
    }
}
