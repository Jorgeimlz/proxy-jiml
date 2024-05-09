package refactoring_guru.proxy.example;

import refactoring_guru.proxy.example.proxy.YoutubeCacheProxy;
import refactoring_guru.proxy.example.some_cool_media_library.ThirdPartyYoutubeClass;

public class Demo {

    public static void main(String[] args) {
        refactoring_guru.proxy.example.downloader.YoutubeDownloader naiveDownloader = new refactoring_guru.proxy.example.downloader.YoutubeDownloader(new ThirdPartyYoutubeClass());
        refactoring_guru.proxy.example.downloader.YoutubeDownloader smartDownloader = new refactoring_guru.proxy.example.downloader.YoutubeDownloader(new YoutubeCacheProxy());

        long naive = test(naiveDownloader);
        long smart = test(smartDownloader);
        System.out.print("Time saved by caching proxy: " + (naive - smart) + "ms");

    }

    private static long test(refactoring_guru.proxy.example.downloader.YoutubeDownloader downloader) {
        long startTime = System.currentTimeMillis();

        // User behavior in our app:
        downloader.renderPopularVideos();
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderPopularVideos();
        downloader.renderVideoPage("dancesvideoo");
        // Users might visit the same page quite often.
        downloader.renderVideoPage("catzzzzzzzzz");
        downloader.renderVideoPage("someothervid");

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.print("Time elapsed: " + estimatedTime + "ms\n");
        return estimatedTime;
    }
}
