public class CloudMusicSpider {

    public static void main(String[] args){

        String url = "http://music.163.com/#/discover/playlist/?order=hot&cat=%E5%85%A8%E9%83%A8&limit=35&offset=0";
        WebFetcher webFetcher = new WebFetcher();
        String result = webFetcher.fetch(url);

    }
}
