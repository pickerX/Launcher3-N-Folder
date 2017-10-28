package com.gwchina.launcher3.sort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 预装应用的分类
 */
public class FolderTypeHelper {

    public final static String AUTO = "auto";

    private static Map<String, FolderType> map = new HashMap<>();
    /**
     * 游戏类
     */
    private static final Set<String> gameSet = new HashSet<String>() {
        {
            add("com.baidu.lbs.waimai");

        }
    };
    /**
     * 生活工具
     */
    private static Set<String> lifeAmusementSet = new HashSet<String>() {
        {
            add("com.baidu.lbs.waimai");
            add("com.chinatelecom.bestpayclient");
            add("com.ct.client");
            add("com.eg.android.AlipayGphone");
            add("com.huati");
            add("com.huluxia.gemetools");
            add("com.google.android.apps.fitness");
            add("com.MobileTicket");
            add("com.hotbody.fitzero");
            add("com.jingdong.app.mall");
            add("com.jrdcom.weather");
            add("com.sankuai.meituan");
            add("com.smile.gifmaker");
            add("com.meitu.meiyancamera");
            add("com.mt.mtxx.mtxx");
            add("com.tct.weather");
            add("com.qzone");
            add("com.sina.weibo");
            add("com.ting.mp3.android");
            add("com.ubercab");
            add("com.taobao.taobao");
            add("com.tct.screenrecorder");
            add("com.tianqi2345");
            add("com.yipiao");
            add("com.achievo.vipshop");
            add("cmb.pb");
        }
    };
    /**
     * 音频类
     */
    private static Set<String> audioVideoSet = new HashSet<String>() {
        {
            add("cn.kuwo.player");
            add("com.alcatel.music5.china");
            add("com.android.videoeditor");
            add("com.baidu.video");
            add("com.changba");
            add("com.google.android.maps.mytracks");
            add("com.google.android.music");
            add("com.google.android.videos");
            add("com.google.android.youtube");
            add("com.hunantv.imgo.activity");
            add("com.kugou.android");
            add("com.letv.android.client");
            add("com.pplive.androidphone");
            add("com.qihoo.video");
            add("com.qiyi.video");
            add("com.qvod.player");
            add("com.sohu.sohuvideo");
            add("com.storm.smart");
            add("com.tencent.karaoke");
            add("com.tencent.qqlive");
            add("com.tencent.qqmusic");
            add("com.youku.phone");
            add("org.keke.tv.vod");
            add("tv.pps.mobile");
            add("com.tudou.android");
        }
    };
    /**
     * 学习办公
     */
    private static Set<String> learnOfficeSet = new HashSet<String>() {
        {
            add("com.google.android.apps.docs");
            add("com.google.android.apps.docs.editors.docs");
            add("com.google.android.apps.docs.editors.sheets");
            add("com.google.android.apps.docs.editors.slides");

            add("air.thix.sciencesense.beaker"); // 烧杯
            add("com.visionobjects.calculator"); // Calculate
            add("com.fenbi.android.gaozhong"); // 猿题库
            add("com.youdao.hanyu"); // 有道汉语
            add("com.youdao.dict"); // 有道翻译
        }
    };
    /**
     * 主题
     */
    private static Set<String> themeSet = new HashSet<String>() {
        {
            add("com.google.android.launcher");
            add("com.moxiu.launcher");
            add("com.nd.android.pandahome2");
            add("com.miui.mihome2");
            add("com.dh.launcher3");

        }
    };
    /**
     * 系统工具类
     */
    private static Set<String> systemToolsSet = new HashSet<String>() {
        {
            add("com.android.jrdfota");
            add("com.android.settings");
            add("com.android.stk");
            add("com.autoconnectwifi.app");
            add("com.autonavi.minimap");
            add("com.baidu.BaiduMap");
            add("com.baidu.browser.apps");
            add("com.baidu.netdisk");
            add("com.baidu.searchbox");
            add("com.cleanmaster.mguard_cn");
            add("com.dewmobile.kuaiya");
            add("com.dianxinos.optimizer.channel");
            add("com.duowan.groundhog.mctools");
            add("com.google.android.gms");
            add("com.google.android.googlequicksearchbox");
            add("com.google.zxing.client.android");
            add("com.iflytek.inputmethod");
            add("com.jrdcom.alcatelhelp");
            add("com.jrdcom.compass");
            add("com.jrdcom.setupwizard");
            add("com.jrdcom.wifidisplay");
            add("com.jrdcom.wifitransfer");
            add("com.ludashi.benchmark");
            add("com.mediatek.StkSelection");
            add("com.qihoo.appstore");
            add("com.qihoo.browser");
            add("com.qihoo360.mobilesafe");
            add("com.shoujiduoduo.ringtone");
            add("com.sohu.inputmethod.sogou");
            add("com.moji.mjweather");
            add("com.tcl.account.china");
            add("com.tcl.live");
            add("com.tcl.pcsuite");
            add("com.tencent.mtt");
            add("com.sdax.sz");
            add("com.snda.wifilocating");
            add("com.tencent.qqpim");
            add("com.tencent.qqpimsecure");
            add("com.wandoujia.phoenix2");
            add("com.youloft.calendar");
            add("com.tencent.android.qqdownloader");
            add("com.example.android.apis");
            add("com.android.customlocale2");
            add("com.android.development_settings");
            add("com.android.development");
            add("com.android.providers.downloads.ui");
            add("com.android.voicedialer");
            add("com.android.gesture.builder");
            add("com.genymotion.superuser");
            add("com.android.quicksearchbox");
            add("com.UCMobile");
        }
    };
    /**
     * 聊天通讯类
     */
    private static Set<String> chatCommunicationSet = new HashSet<String>() {
        {
            add("com.android.chrome");
            add("com.baidu.input");
            add("com.facebook.katana");
            add("com.google.android.apps.messaging");
            add("com.google.android.apps.plus");
            add("com.immomo.momo");
            add("com.sec.chaton");
            add("com.tencent.mm");
            add("com.tencent.mobileqq");
            add("com.tencent.qqlite");
            add("deezer.android.app");
        }
    };
    /**
     * 新闻阅读类
     */
    private static Set<String> newsReadingSet = new HashSet<String>() {
        {
            add("com.book2345.reader");
            add("com.chaozh.iReader");
            add("com.chaozh.iReaderFree");
            add("com.google.android.apps.magazines");
            add("com.shuqi.controller");
            add("com.sohu.newsclient");
            add("com.ss.android.article.news");
            add("com.ss.android.article.news");
            add("com.tencent.news");
        }
    };

    public static FolderType getFolderTypeByPackage(String packageName) {
        // 初始化数据
        if (map.isEmpty()) {
            init();
        }
        return map.get(packageName);
    }

    private static void init() {
        // 游戏
        enMap(gameSet, FolderType.Game);
        // 学习办公
        enMap(learnOfficeSet, FolderType.LearnOffice);
        // 新闻阅读
        enMap(newsReadingSet, FolderType.NewsReading);
        // 生活娱乐
        enMap(lifeAmusementSet, FolderType.LifeAmusement);
        // 影音播放
        enMap(audioVideoSet, FolderType.AudioVideo);
        // 聊天通讯
        enMap(chatCommunicationSet, FolderType.ChatCommunication);
        // 主题壁纸
        enMap(themeSet, FolderType.Theme);
        // 系统工具
        enMap(systemToolsSet, FolderType.SystemTools);
    }


    private static void enMap(Set<String> set, FolderType type) {
        if (set == null || set.isEmpty()) return;
        for (String packageName : set) {
            map.put(packageName, type);
        }
    }

}
