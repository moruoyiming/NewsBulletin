/*
 * Copyright (c) 2016 咖枯 <kaku201313@163.com | 3772304@qq.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.mrym.newsbulletion.db;



import com.mrym.newsbulletion.NewsApplication;
import com.mrym.newsbulletion.R;
import com.mrym.newsbulletion.domain.modle.VideoChannelTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 视频管理
 * Created by Jian on 2016/9/14.
 * Email: 798774875@qq.com
 * Github: https://github.com/moruoyiming
 */
public class VideosChannelTableManager {

    /**
     * 加载视频类型
     * @return
     */
    public static List<VideoChannelTable> loadVideosChannelsMine() {
        List<String> channelName = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.video_channel_name));
        List<String> channelId = Arrays.asList(NewsApplication.getContext().getResources().getStringArray(R.array.video_channel_id));
        ArrayList<VideoChannelTable>newsChannelTables=new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            VideoChannelTable entity = new VideoChannelTable(channelId.get(i), channelName.get(i));
            newsChannelTables.add(entity);
        }
        return newsChannelTables;
    }

}
