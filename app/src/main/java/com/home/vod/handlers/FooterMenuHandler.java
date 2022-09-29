package com.home.vod.handlers;

import android.app.Activity;

import com.home.apisdk.apiModel.MenusOutputModel;
import com.home.vod.model.NavDrawerItem;

import java.util.ArrayList;

/**
 * Created by BISHAL on 20-10-2017.
 */

public class FooterMenuHandler {
    private Activity context;
    public FooterMenuHandler(Activity context){
        this.context=context;
    }
    public void addFooterMenu(MenusOutputModel menusOutputModel,ArrayList<NavDrawerItem> menuList ){

        for (MenusOutputModel.FooterMenu menuListOutput : menusOutputModel.getFooterMenuModel()) {
            menuList.add(new NavDrawerItem(menuListOutput.getDisplay_name(), menuListOutput.getPermalink(), menuListOutput.isEnable(), menuListOutput.getLink_type(), menuListOutput.getUrl()));
        }
    }
}
