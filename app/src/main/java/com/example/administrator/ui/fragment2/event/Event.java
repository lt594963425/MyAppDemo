package com.example.administrator.ui.fragment2.event;

import com.example.administrator.ui.fragment2.bean.Item;

import java.util.List;

/**
 * $name
 *
 * @author ${LiuTao}
 * @date 2017/11/21/021
 */

public class Event {
        /** 列表加载事件 */
      public static   class ItemListEvent
        {
            private List<Item> items;

            public ItemListEvent(List<Item> items)
            {
                this.items = items;
            }

            public List<Item> getItems()
            {
                return items;
            }
        }

    }