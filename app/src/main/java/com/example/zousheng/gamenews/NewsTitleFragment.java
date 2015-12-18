package com.example.zousheng.gamenews;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zou Sheng on 2015/12/16.
 */
public class NewsTitleFragment extends Fragment implements AdapterView.OnItemClickListener{

    private ListView newsTitleListView;

    private List<News> newsList;

    private NewsAdapter newsAdapter;

    private boolean isTwoPane;
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        News news=newsList.get(position);
        if (isTwoPane){
            NewsContentFragment newsContentFragment= (NewsContentFragment) getFragmentManager()
                    .findFragmentById(R.id.news_content_fragment);
            newsContentFragment.refresh(news.getTitle(),news.getContent());
        }
        else{
            NewsContentActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
        }
    }

    @Override
    public void onAttach(Activity activity) {
         super.onAttach(activity);
        newsList=getNews();
        newsAdapter=new NewsAdapter(activity,R.layout.news_item,newsList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.news_title_frag,container,false);
        newsTitleListView= (ListView) view.findViewById(R.id.news_title_list_view);
        newsTitleListView.setAdapter(newsAdapter);
        newsTitleListView.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }
        else
        {
            isTwoPane=false;
        }
    }
    private List<News> getNews(){
        List<News> newsList=new ArrayList<News>();
        News news1=new News();
        news1.setTitle("《最终幻想13：雷霆归来》游民评测8.8分 时代的分水岭");
        news1.setContent("        虽然同为《最终幻想13》三部曲的一员，但《雷霆归来》完全像是由不同的团队开发的作品，其与" +
                "前两部作品的差异就像是《最终幻想9》和《最终幻想10》之间的区别。从这个角度来看，如果说《最终幻想13》" +
                "那触礁了的改革在《最终幻想13-2》修改得还不够新锐大胆，《雷霆归来》则更像彻彻底底的推倒重建。这不禁" +
                "让人浮想联翩：因为要是第一部《最终幻想13》就能有《雷霆归来》的形态，那么可能在三部曲终章的时候，我们" +
                "已经能够提前见到类似《最终幻想15》那样划时代的设计了。");
        newsList.add(news1);
        News news2=new News();
        news2.setTitle("《刺客信条：枭雄》游民星空评测8.0分 这雾霾味儿正！");
        news2.setContent("2015年度最佳的争夺者都有着类似的特征，那就是“开放世界”。其中《巫师3》和《合金装备5》" +
                "向业界证明了自己的开放世界方法论：质量大于容量，上百个收集品和谜题，低质量的随机事件已经落伍。" +
                "相比之下，尽管在机制上还算有所改进，年货级的《刺客信条：枭雄》在这方面还有一段不短的距离需要赶超。");
        newsList.add(news2);
        return newsList;
    }
}
