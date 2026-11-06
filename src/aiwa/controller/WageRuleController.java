package aiwa.controller;

import java.util.ArrayList;
import java.util.Scanner;
import aiwa.entity.Shop;
import aiwa.entity.User;
import aiwa.entity.WageRule;
import aiwa.model.ShopModel;
import aiwa.model.WageRuleModel;

public class WageRuleController {

    Scanner scan = new Scanner(System.in);
    Scanner scanStr = new Scanner(System.in);

    WageRuleModel wageRuleModel = new WageRuleModel();
    ShopModel shopModel = new ShopModel();

    public void showMenu(User user) {

        while (true) {

            System.out.println();
            System.out.println("==============================");
            System.out.println("        給与ルール MENU");
            System.out.println("==============================");
            System.out.println("1. 給与ルール登録");
            System.out.println("2. 給与ルール一覧");
            System.out.println("3. 給与ルール削除");
            System.out.println("0. 戻る");
            System.out.print("番号を入力してください：");

            int menu = scan.nextInt();

            if (menu == 1) {

                ArrayList<Shop> shops =
                        shopModel.getAllByUserId(user.getId());

                if (shops.isEmpty()) {

                    System.out.println("先に店舗を登録してください。");

                } else {

                    System.out.println();
                    System.out.println("===== 店舗を選択 =====");

                    for (Shop shop : shops) {

                        System.out.println(
                                "ID: " + shop.getId()
                                + " / 店舗: " + shop.getName()
                                + " / 時給: ¥" + shop.getHourlyRate()
                        );
                    }

                    System.out.print("店舗ID：");
                    int shopId = scan.nextInt();

                    if (!shopModel.existsShop(
                            shopId, user.getId())) {

                        System.out.println(
                                "この店舗IDは登録されていません。");

                    } else {

                        System.out.print(
                                "開始時間 (HH:mm)：");

                        String startTime =
                                scanStr.nextLine();

                        System.out.print(
                                "終了時間 (HH:mm)：");

                        String endTime =
                                scanStr.nextLine();

                        System.out.print(
                                "割増率（%）：");

                        double ratePercent =
                                scan.nextDouble();

                        WageRule rule =
                                new WageRule();

                        rule.setShopId(shopId);
                        rule.setStartTime(startTime);
                        rule.setEndTime(endTime);
                        rule.setRatePercent(ratePercent);

                        boolean result =
                                wageRuleModel.register(rule);

                        if (result) {

                            System.out.println(
                                    "給与ルール登録 OK！");

                        } else {

                            System.out.println(
                                    "給与ルール登録 FAIL");
                        }
                    }
                }

            } else if (menu == 2) {

                ArrayList<Shop> shops =
                        shopModel.getAllByUserId(user.getId());

                for (Shop shop : shops) {

                    System.out.println();
                    System.out.println(
                            "===== " + shop.getName()
                            + " =====");

                    ArrayList<WageRule> rules =
                            wageRuleModel.getAllByShopId(
                                    shop.getId());

                    if (rules.isEmpty()) {

                        System.out.println(
                                "給与ルールがありません。");

                    } else {

                        for (WageRule rule : rules) {

                            System.out.println(
                                    "ID: " + rule.getId()
                                    + " / "
                                    + rule.getStartTime()
                                    + " ～ "
                                    + rule.getEndTime()
                                    + " / +"
                                    + rule.getRatePercent()
                                    + "%"
                            );
                        }
                    }
                }

            } else if (menu == 3) {

            	ArrayList<Shop> shops =
                        shopModel.getAllByUserId(user.getId());

                System.out.println();
                System.out.println("===== 給与ルール削除 =====");

                boolean hasRule = false;

                for (Shop shop : shops) {

                    ArrayList<WageRule> rules =
                            wageRuleModel.getAllByShopId(
                                    shop.getId());

                    for (WageRule rule : rules) {

                        hasRule = true;

                        System.out.println(
                                "ID: " + rule.getId()
                                + " / 店舗: " + shop.getName()
                                + " / "
                                + rule.getStartTime()
                                + " ～ "
                                + rule.getEndTime()
                                + " / +"
                                + rule.getRatePercent()
                                + "%"
                        );
                    }
                }

                if (!hasRule) {

                    System.out.println(
                            "削除できる給与ルールがありません。");

                } else {

                    System.out.print(
                            "削除する給与ルールID：");

                    int id = scan.nextInt();

                    boolean deleted = false;

                    for (Shop shop : shops) {

                        boolean result =
                                wageRuleModel.delete(
                                        id,
                                        shop.getId());

                        if (result) {
                            deleted = true;
                            break;
                        }
                    }

                    if (deleted) {

                        System.out.println(
                                "給与ルールを削除しました。");

                        HtmlGenerator generator =
                                new HtmlGenerator();

                        generator.generateAllHtml(user);

                    } else {

                        System.out.println(
                                "この給与ルールIDは登録されていません。");
                    }}

            } else if (menu == 0) {

                break;

            } else {

                System.out.println(
                        "正しい番号を入力してください。");
            }
        }
    }
}