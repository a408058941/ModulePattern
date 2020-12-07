package com.fd.baselibrary.baseMethod;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.fd.baselibrary.baseBean.ActivitiesBean;
import com.fd.baselibrary.utils.ImageUtil;
import com.youth.banner.loader.ImageLoader;

public class MyLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        final ActivitiesBean bean = (ActivitiesBean) path;
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageUtil.load(imageView, bean.getBigAdvg());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(bean.getLineType()))
                    return;
                if (bean.getLineType().equals("0")) {
//                    平台内链类型 0--品牌连接、1--商户连接、2--产品连接、3--推文连接、4--我的钱包 5--分太星球',6--集合页
                    try {
                        Intent intentMert = new Intent();
                        switch (Integer.valueOf(bean.getInnrType())) {
                            case 0:
                                break;
                            case 1:
//                                intentMert.setClass(context, PolyBannerAct.class);
//                                intentMert.putExtra("actyCode", bean.getActyCode());
//                                intentMert.putExtra("innrType", bean.getInnrType());
//                                intentMert.putExtra("title", bean.getActyName());
//                                context.startActivity(intentMert);
                                break;
                            case 2:
//                                intentMert.setClass(context, PolyBannerAct.class);
//                                intentMert.putExtra("actyCode", bean.getActyCode());
//                                intentMert.putExtra("innrType", bean.getInnrType());
//                                intentMert.putExtra("title", bean.getActyName());
//                                context.startActivity(intentMert);
                                break;
                            case 3:
//                                intentMert.setClass(context, PolyBannerAct.class);
//                                intentMert.putExtra("actyCode", bean.getActyCode());
//                                intentMert.putExtra("innrType", bean.getInnrType());
//                                intentMert.putExtra("title", bean.getActyName());
//                                context.startActivity(intentMert);
                                break;
                            case 4:
//                                if (TextUtils.isEmpty(MySelfInfo.getInstance().getUserCode())) {
//                                    LemonHelloInfoUtils.showLemonLogin(context);
//                                } else {
//                                    SystemUtils.gotoActivity(context, WalletActivity.class);
//                                }
                                break;
                            case 5:
//                                if (TextUtils.isEmpty(MySelfInfo.getInstance().getUserCode())) {
//                                    LemonHelloInfoUtils.showLemonLogin(context);
//                                } else {
//                                    SystemUtils.gotoActivity(context, IntegralPlanetActivity.class);
//                                }
                                break;
                            case 6:
//                                if (TextUtils.isEmpty(MySelfInfo.getInstance().getUserCode())) {
//                                    LemonHelloInfoUtils.showLemonLogin(context);
//                                } else {
//                                    SystemUtils.gotoActivity(context, IntegralPlanetActivity.class);
//                                }
//                                if (!TextUtils.isDigitsOnly(bean.getLinkAddr())){
//                                    Intent intent = new Intent(context, WebViewActivity.class);
//                                    intent.putExtra("url", bean.getLinkAddr());
//                                    intent.putExtra("title", bean.getActyName());
//                                    context.  startActivity(intent);
//                                }else {
//                                    intentMert.setClass(context, PolymerizationAct.class);
//                                    intentMert.putExtra("typeCode", bean.getPolyTypeCode());
//                                    intentMert.putExtra("title", bean.getActyName());
//                                    context.startActivity(intentMert);
//                                }

                                break;
                        }
                    } catch (Exception e) {

                    }

                } else if (bean.getLineType().equals("1")) {
//                    if (bean.getLineUrls() == null)
//                        return;
//                    Intent intent = new Intent(context, WebViewActivity.class);
//                    intent.putExtra("url", bean.getLineUrls());
//                    intent.putExtra("title", bean.getActyName());
//                    context.startActivity(intent);
                }
            }
        });
    }
}
