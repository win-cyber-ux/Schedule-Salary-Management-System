package aiwa.controller;

import java.util.ArrayList;

import aiwa.entity.Schedule;
import aiwa.entity.Shop;
import aiwa.entity.User;
import aiwa.model.ScheduleModel;
import aiwa.model.ShopModel;
import aiwa.view.HomeHtmlView;
import aiwa.view.ScheduleEditHtmlView;
import aiwa.view.ScheduleListHtmlView;
import aiwa.view.ScheduleRegisterHtmlView;
import aiwa.view.ShopListHtmlView;
import aiwa.view.ShopRegisterHtmlView;

public class HtmlGenerator {

    public void generateAllHtml(User user) {

        int userid = user.getId();

        // Home
        HomeHtmlView home = new HomeHtmlView();
        home.showHome(userid);

        // Shop
        ShopModel shopModel = new ShopModel();

        ArrayList<Shop> shops =
                shopModel.getAllByUserId(userid);

        ShopListHtmlView shopList =
                new ShopListHtmlView();

        shopList.showList(shops);

        ShopRegisterHtmlView shopRegister =
                new ShopRegisterHtmlView();

        shopRegister.showRegisterForm();

        // Schedule
        ScheduleModel scheduleModel =
                new ScheduleModel();

        ArrayList<Schedule> schedules =
                scheduleModel.getAllByUserId(userid);

        ScheduleListHtmlView scheduleList =
                new ScheduleListHtmlView();

        scheduleList.showList(schedules);

        if (!schedules.isEmpty()) {

            ScheduleEditHtmlView scheduleEdit =
                    new ScheduleEditHtmlView();

            scheduleEdit.showEditForm(schedules.get(0));
        }

        ScheduleRegisterHtmlView scheduleRegister =
                new ScheduleRegisterHtmlView();

        scheduleRegister.showRegisterForm();

        // Salary
        SalaryController salaryController =
                new SalaryController();

        salaryController.showMenu(user);
    }
}