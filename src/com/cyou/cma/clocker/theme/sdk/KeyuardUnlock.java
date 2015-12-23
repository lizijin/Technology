
package com.cyou.cma.clocker.theme.sdk;

public interface KeyuardUnlock {
    /**
     * 解锁
     */
    public void unlockScreen();

    /**
     * 解锁到短信
     */
    public void unlockMessage(int count);

    /**
     * 解锁到电话
     */
    public void unlockCall(int count);
}
